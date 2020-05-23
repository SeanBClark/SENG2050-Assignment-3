import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import beans.*;

@WebServlet(name = "/MilestoneController", urlPatterns = { "/ManageMilestones" })
public class MilestoneController extends HttpServlet {

    public MilestoneController() {
        super();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) {

        try {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/group_milestones/group_milestones.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
    }
}