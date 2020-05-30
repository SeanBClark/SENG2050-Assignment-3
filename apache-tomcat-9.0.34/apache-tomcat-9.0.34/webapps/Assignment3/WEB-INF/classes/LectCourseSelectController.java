import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import beans.*;

@WebServlet(name = "/LectCourseSelectController", urlPatterns = { "/CourseSelect" })
public class LectCourseSelectController extends HttpServlet {

    public LectCourseSelectController() {
        super();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        Connection connection = null;
        try { connection = ConfigBean.getConnection(); } catch (Exception e) { e.printStackTrace(); }

        // Get courses the lecturer is course coordinator for
        HttpSession session = request.getSession();
        int lectId = (int) session.getAttribute("userID");

        try {

            ResultSet courseListRS = DatabaseQuery.getResultSet(DatabaseQuery.getLectCourses(lectId), connection);
            List <CourseListBean> courseList = new ArrayList<>();

            while (courseListRS.next()){

                session = request.getSession();

                CourseListBean courseListBean = new CourseListBean();
                courseListBean.setCourseId(courseListRS.getInt("course_id"));
                courseListBean.setCourseName(courseListRS.getString("name"));
                courseListBean.setCourseCode(courseListRS.getString("course_code"));
                courseList.add(courseListBean);
                session.setAttribute("courseListBean", courseListBean);

            }
            session.setAttribute("courseList", courseList);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        try {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/course_mngt/course_select.jsp");
            connection.close(); 
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
            Connection connection = null;
            try { connection = ConfigBean.getConnection(); } catch (Exception e) { e.printStackTrace(); }

            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
}