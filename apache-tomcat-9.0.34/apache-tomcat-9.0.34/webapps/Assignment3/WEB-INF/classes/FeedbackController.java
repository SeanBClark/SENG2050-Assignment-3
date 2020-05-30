import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import beans.*;

@WebServlet(name = "/FeedbackController", urlPatterns = { "/Feedback" })
public class FeedbackController extends HttpServlet {

    public FeedbackController() {
        super();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        try { 
            Connection connection = ConfigBean.getConnection(); 
            HttpSession session = request.getSession();
            List <FileManagementBean> fileDetails = new ArrayList<>();

            ResultSet fileInfoSet = DatabaseQuery.getResultSet(DatabaseQuery.getFileDetails(Integer.parseInt(request.getParameter("fileID"))), connection);
            while (fileInfoSet.next()){

                FileManagementBean fileManagementBean = new FileManagementBean();
                fileManagementBean.setName(fileInfoSet.getString("file_name"));
                fileManagementBean.setUrl(fileInfoSet.getString("file_url"));
                fileManagementBean.setDescription(fileInfoSet.getString("file_desc"));
                fileManagementBean.setStatusInt(fileInfoSet.getInt("file_status"));
                fileDetails.add(fileManagementBean);
                session.setAttribute("fileManagementBean", fileManagementBean);
                session.setAttribute("groupID", fileInfoSet.getInt("group_id"));

            }
            session.setAttribute("fileDetails", fileDetails);

            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/course_mngt/feedback_mark.jsp");
            connection.close(); 
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

                int courseID = (int) session.getAttribute("courseID");
                int groupID = (int) session.getAttribute("groupID");
                int fileID = (int) session.getAttribute("fileID");
                int projectID = 0;
                String feedback = request.getParameter("feedback");
                double grade = Double.parseDouble(request.getParameter("gradeSlider"));

                String getProjectID = "SELECT project.id FROM project JOIN project_assign ON project.id = project_assign.project_id WHERE project_assign.group_id = " + groupID + "  AND project.course_id = " + courseID + "";
                ResultSet projectRS = DatabaseQuery.getResultSet(getProjectID, connection);
                while (projectRS.next()) {projectID = projectRS.getInt(1);}

                String insertFeedback = "INSERT INTO project_assign(project_id, group_id, feedback, grade, mark, marked) VALUES ( " + projectID + ", " + groupID + ", '" + feedback + "', " + grade + ", '" + getMark(grade) + "', 1 );";

                Statement statement = connection.createStatement();
                statement.execute(insertFeedback);

                System.out.println("courseID ID = " + courseID);
                System.out.println("FIle ID = "  + fileID);
                System.out.println("Feedback = "  + feedback);
                System.out.println("Grade = "  + grade);

                connection.close();
                response.sendRedirect("/Assignment3/GroupManagement?courseID=" + courseID);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    public static String getMark(double grade) {

        String result = "";

        if (grade > 83.00) {
            result = "HD";
        }
        else if (grade > 73 && grade < 82.99) {
            result = "D";
        }
        else if (grade > 63 && grade < 72.99) {
            result = "C";
        }
        else if (grade > 50 && grade < 62.99) {
            result = "P";
        }
        else if (grade < 50 && grade > 0.00) {
            result = "N";
        }
        else {
            result = "NA";
        }
        
        return result;
    }
}