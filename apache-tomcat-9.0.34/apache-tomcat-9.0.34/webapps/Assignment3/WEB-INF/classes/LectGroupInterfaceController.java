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

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        Connection connection = null;
        try { 
            connection = ConfigBean.getConnection(); 
            HttpSession session = request.getSession();

            // Gets group members
            int groupID = Integer.parseInt(request.getParameter("groupID"));
            List <GroupMemberBean> groupMemberList = new ArrayList<>();
            ResultSet groupMemberRS = null;

            groupMemberRS = DatabaseQuery.getResultSet(DatabaseQuery.getGroupMembers(groupID), connection);

            while (groupMemberRS.next()) {

                session = request.getSession();

                GroupMemberBean groupMemberBean = new GroupMemberBean();
                groupMemberBean.setMemberID(groupMemberRS.getInt("user.user_id"));
                groupMemberBean.setMemberName(groupMemberRS.getString("user.user_name"));
                groupMemberList.add(groupMemberBean);
                session.setAttribute("groupMemberBean", groupMemberBean);

            }
            session.setAttribute("groupMemberList", groupMemberList);

            // TO DO: Gets group files

            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/course_mngt/group_mngt.jsp");
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