import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import beans.*;

// Controller to list out courses a lecturer is course coordinator for

@WebServlet(name = "/LectCourseSelectController", urlPatterns = { "/CourseSelect" })
public class LectCourseSelectController extends HttpServlet {

    public LectCourseSelectController() {
        super();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
        try {

                HttpSession session = request.getSession();
                int lectID = (int) session.getAttribute("userID");

                CourseListBean courseListBean = new CourseListBean();
                session.setAttribute("courseList", (courseListBean.getCourses(lectID)));

                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/course_mngt/course_select.jsp");
                dispatcher.forward(request, response);
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
}