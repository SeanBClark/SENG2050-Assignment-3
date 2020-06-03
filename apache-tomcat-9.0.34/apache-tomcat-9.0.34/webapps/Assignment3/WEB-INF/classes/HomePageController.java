import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import beans.*;

@WebServlet(name = "/HomePageController", urlPatterns = { "/HomePage" })

public class HomePageController extends HttpServlet {

    public HomePageController() {

        super();

    }

    // gets JSP
    public void doGet(HttpServletRequest request, HttpServletResponse response) {        
        try {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/homepage/homepage.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}