import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import beans.*;

@WebServlet(name = "/LectGroupInterfaceController", urlPatterns = { "/GroupFeedback" })
public class LectGroupInterfaceController extends HttpServlet {

    public LectGroupInterfaceController() {
        super();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try { 
            HttpSession session = request.getSession();
            int courseID = (int) session.getAttribute("courseID");
            // int courseID = Integer.parseInt(request.getParameter("courseID"));

            // Gets group members
            GroupMemberBean groupMemberBean = new GroupMemberBean();
            session.setAttribute("groupMemberList", (groupMemberBean.getMemberList(Integer.parseInt(request.getParameter("groupID")))));

            // Gets Assignments to mark
            FileMarkListBean fileMarkListBean = new FileMarkListBean();
            session.setAttribute("fileMarkListBean", (fileMarkListBean.getCourses(Integer.parseInt(request.getParameter("groupID")), courseID)));

            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/course_mngt/group_mngt.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}