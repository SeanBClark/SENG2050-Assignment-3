import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import beans.*;

// Controller to list out groups under a course

@WebServlet(name = "/LectGroupMngtController", urlPatterns = { "/GroupManagement" })
public class LectGroupMngtController extends HttpServlet {

    public LectGroupMngtController() {
        super();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) 
    throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            LectGroupListBean lectGroupListBean = new LectGroupListBean();
            session.setAttribute("groupList", (lectGroupListBean.getCourses(Integer.parseInt(request.getParameter("courseID")))));

            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/course_mngt/course_mngt.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}