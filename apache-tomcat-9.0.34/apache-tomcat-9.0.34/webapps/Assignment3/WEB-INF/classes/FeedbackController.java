import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import beans.*;

// Controller to provide feedback for group submissions

@WebServlet(name = "/FeedbackController", urlPatterns = { "/Feedback" })
public class FeedbackController extends HttpServlet {

    public FeedbackController() {
        super();
    }

    // Gets list of completed assignments the forwards to jsp
    public void doGet(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
            try { 
                HttpSession session = request.getSession();
                FileManagementBean fileManagementBean = new FileManagementBean();
                session.setAttribute("fileDetails", (fileManagementBean.getFileDetails(Integer.parseInt(request.getParameter("fileID")))));
                
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/course_mngt/feedback_mark.jsp");
                dispatcher.forward(request, response);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    //  Post request to submit feedback on an Assignment
    public void doPost(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
            
            try { 
                int groupID = Integer.parseInt(request.getParameter("groupID"));
                int courseID = Integer.parseInt(request.getParameter("courseID"));

                FileManagementBean fileManagementBean = new FileManagementBean();

                fileManagementBean.insertFeedback(
                    fileManagementBean.getProjectID(groupID, courseID), 
                    Integer.parseInt(request.getParameter("groupID")), 
                    request.getParameter("feedback"), 
                    Double.parseDouble(request.getParameter("gradeSlider")));
                response.sendRedirect("/Assignment3/GroupManagement?courseID=" + courseID);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
}