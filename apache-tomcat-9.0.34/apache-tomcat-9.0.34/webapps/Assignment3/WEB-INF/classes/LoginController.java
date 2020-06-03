import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import beans.*;

// Controller to handle user logins
// Sends users to an interface specific to their type e.g. Admin goes to Admin
// Currently incorrectly completes SQL within controller instead of bean. This was made early on and currently breaks the app if moved into beans

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
            try {                
                Connection connection = ConfigBean.getConnection();
                HttpSession session = request.getSession();
                String email = request.getParameter("userEmail");
                String password = request.getParameter("userPassword");
                int exists = 0;

                ResultSet existsRS = DatabaseQuery.getResultSet(DatabaseQuery.ifExistsQuery(email, password), connection);
                while(existsRS.next()) {
                    exists = existsRS.getInt(1);
                }

                if (exists == 0){
                    response.sendRedirect("../Assignment3/LoginController?exists=false");
                }
                else {

                    ResultSet userRS = DatabaseQuery.getResultSet(DatabaseQuery.getUser(email), connection);
                    while (userRS.next()){
                        if (userRS.getInt("user_status") == 0){
                            response.sendRedirect("../Assignment3/LoginController?active=false");
                        }
                        else {

                            UserBean userBean = new UserBean();
                            userBean.setUserId(userRS.getInt("user_id"));
                            userBean.setUserEmail(userRS.getString("user_email"));
                            userBean.setUserName(userRS.getString("user_name"));
                            userBean.setUserStatus(userRS.getInt("user_status"));
                            userBean.setUserType(userRS.getString("user_type"));
                            session.setAttribute("userID", userRS.getInt("user_id"));
                            session.setAttribute("userType", userRS.getString("user_type"));
                            session.setAttribute("userBean", userBean);

                        }
                    }
                    String userType = (String) session.getAttribute("userType");

                    if ( userType.equals("std")) {
                        response.sendRedirect("/Assignment3/GroupSelect");
                    }
                    else if ( userType.equals("lect")) {
                        response.sendRedirect("/Assignment3/CourseSelect");
                    }
                    else if ( userType.equals("admin")) {
                        response.sendRedirect("/Assignment3/Admin");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
}