import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import beans.*;

// Controller for users to register a new account within the app

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
            
            try {

                String name = request.getParameter("userName");
                String email = request.getParameter("userEmailConf");
                String password = request.getParameter("userPasswordConf");

                UserBean userBean = new UserBean();

                boolean exists = userBean.ifExists(email, password);
                if (exists == false) {
                    userBean.insertNewUser(name, email, password);
                    response.sendRedirect("/Assignment3/LoginController?success=true");
                }
                else {
                    response.sendRedirect("/Assignment3/CreateAccount?exists=true");
                }
                
            } catch (Exception e) {
                e.printStackTrace();
            }      
    }
}