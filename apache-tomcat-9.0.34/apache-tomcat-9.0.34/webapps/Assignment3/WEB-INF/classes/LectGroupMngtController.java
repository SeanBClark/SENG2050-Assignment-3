import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import beans.*;

@WebServlet(name = "/LectGroupMngtController", urlPatterns = { "/GroupManagement" })
public class LectGroupMngtController extends HttpServlet {

    public LectGroupMngtController() {
        super();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        Connection connection = null;
        try { 
            connection = ConfigBean.getConnection(); 
            HttpSession session = request.getSession();

            int courseID = Integer.parseInt(request.getParameter("courseID"));
            ResultSet groupListRS = DatabaseQuery.getResultSet(DatabaseQuery.getCourseGroupList(courseID), connection);
            List <LectGroupListBean> groupList = new ArrayList<>();

            while (groupListRS.next()) {
                ResultSet rs = groupListRS;
                LectGroupListBean bean = new LectGroupListBean();
                bean.setGroupId(rs.getInt("group_id"));
                bean.setGroupName(rs.getString("group_name"));
                bean.setProjectId(rs.getInt("id"));
                bean.setProjectName(rs.getString("name"));
                groupList.add(bean);
                session.setAttribute("LectGroupListBean", bean);

            }
            session.setAttribute("groupList", groupList);

            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/course_mngt/course_mngt.jsp");
            connection.close(); 
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
            Connection connection = null;
            try { 
                connection = ConfigBean.getConnection(); 
            } catch (Exception e) { e.printStackTrace(); }

            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
}