import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import beans.*;

@WebServlet(name = "/GroupController", urlPatterns = { "/GroupHome" })
public class GroupController extends HttpServlet {

    public GroupController() {
        super();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/group_home/group_home.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
    }
}