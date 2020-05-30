import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import beans.*;

@WebServlet(name = "/CreateAssignmentController", urlPatterns = { "/CreateAssignment" })
public class CreateAssignmentController extends HttpServlet {

    public CreateAssignmentController() {
        super();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
            try { 
                Connection connection = ConfigBean.getConnection(); 
                HttpSession session = request.getSession();
                int courseID = (int) session.getAttribute("groupID");

                String name = request.getParameter("name");
                String description = request.getParameter("description");
                String dueDate = request.getParameter("dueDate");
                dueDate = dueDate.replace(" AM", "");

                Statement statement = connection.createStatement();
                statement.execute(DatabaseQuery.insertProject(name, description, dueDate, courseID));

                connection.close();
                response.sendRedirect("/Assignment3/GroupManagement?courseID=" + courseID);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
}