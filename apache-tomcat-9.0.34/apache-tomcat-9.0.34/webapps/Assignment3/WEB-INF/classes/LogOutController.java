import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import beans.*;

// Logs user out and deletes their session

@WebServlet(urlPatterns = { "/LogOutController" })
public class LogOutController extends HttpServlet {

    public LogOutController() {
        super();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.invalidate(); 
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/login/login.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}