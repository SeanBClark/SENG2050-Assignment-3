import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import beans.*;

// Controller to manage the course coordinators  ability to create new assignments for their course

@WebServlet(name = "/CreateAssignmentController", urlPatterns = { "/CreateAssignment" })
public class CreateAssignmentController extends HttpServlet {

    public CreateAssignmentController() {
        super();
    }

    // Post request to create new assignments for the course that the lecturer is the coordinator for
    public void doPost(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {

            try { 

                HttpSession session = request.getSession();
                int courseID = Integer.parseInt(request.getParameter("courseID"));
                String name = request.getParameter("name");
                String description = request.getParameter("description");
                String dueDate = request.getParameter("dueDate");
                dueDate = dueDate.replace(" AM", "");

                CourseBean courseBean = new CourseBean();
                courseBean.insertAssignment(courseID, dueDate, name, description);

                response.sendRedirect("/Assignment3/GroupManagement?courseID=" + courseID);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
}