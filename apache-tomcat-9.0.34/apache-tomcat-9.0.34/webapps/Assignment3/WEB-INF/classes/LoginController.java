import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import beans.*;

@WebServlet(urlPatterns = { "/LoginController" })
public class LoginController extends HttpServlet {

    public LoginController() {
        super();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) {

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/login/login.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
            Connection connection = null;
            String inputEmail = request.getParameter("userEmail");
            String inputPassword = request.getParameter("userPassword");

            // Gets database connection
            try {
                connection = ConfigBean.getConnection();
                System.out.println("Connected");
            }
            catch (Exception e){
                System.out.println("Could not connect to DBMS");
            }

            String queryString = "SELECT EXISTS(select * from user where user_email = '" + inputEmail + "' and user_password = sha1('" + inputPassword + "'));";
            // Checks if user exists
            // True = exists, False = !exists
            boolean exists = DatabaseQuery.ifExists(queryString, connection);

            try {
                if (exists != false) {

                    String userString = "SELECT user_id, user_email, user_name, user_status FROM user WHERE user_email = '" + inputEmail + "';";
                    ResultSet resultSet = DatabaseQuery.getResultSet(userString, connection);
                    HttpSession session = request.getSession();

                    while (resultSet.next()) {
                        if (resultSet.getInt("user_status") == 0) {
                            // TO DO: Redirect to account deactivated page (need to make)
                            System.out.println("Account deactivated");
                        }
                        else {
                            UserBean userBean = new UserBean();
                            userBean.setUserId(resultSet.getInt("user_id"));
                            userBean.setUserEmail(resultSet.getString("user_email"));
                            userBean.setUserName(resultSet.getString("user_name"));
                            userBean.setUserStatus(resultSet.getInt("user_status"));
                            session.setAttribute("userBean", userBean);
                        }
                    }
                }
                else {
                    // TO DO: set user to register page
                    // response.setRedirect("../Assignment3/views/login/login.jsp");
                }
                connection.close();                
            } catch (Exception e) {
                e.printStackTrace();
            }
            // TO DO: setRedirect to user interface page
    }
}