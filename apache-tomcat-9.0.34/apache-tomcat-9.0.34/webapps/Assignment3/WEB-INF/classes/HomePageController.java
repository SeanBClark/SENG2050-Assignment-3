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

    public void doPost(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {

    }
}