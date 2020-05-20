import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import beans.*;

@WebServlet(name = "/RegisterController", urlPatterns = { "/CreateAccount" })
public class RegisterController extends HttpServlet {

    public RegisterController() {
        super();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) {

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/register/register.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {            
            Connection connection = null;
            try { connection = ConfigBean.getConnection(); } catch (Exception e) { e.printStackTrace(); }

            String inputName = request.getParameter("userName");
            String inputEmail = request.getParameter("userEmailConf");
            String inputPassword = request.getParameter("userPasswordConf");

            // Check if User already exists
            String ifExistsQuery = DatabaseQuery.ifExistsQuery(inputEmail, inputPassword);
            boolean exists = DatabaseQuery.ifExists(ifExistsQuery, connection);

            try {
                if (exists == false) {
                    String insertNewUser = DatabaseQuery.insertUser(inputName, inputEmail, inputPassword);
                    Statement statement = connection.createStatement();
                    statement.execute(insertNewUser);
                    response.sendRedirect("/Assignment3/LoginController");
                }
                else {
                    // TO DO: Need to add a way to tell the user the account already exists. 
                    response.sendRedirect("/Assignment3/CreateAccount");
                    System.out.println("Account Exists");
                }                
            } catch (Exception e) { e.printStackTrace(); }

            try { connection.close(); } catch (Exception e) { e.printStackTrace(); }            
    }
}