import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import beans.*;

// Controller to get group details for lecturer feedback

@WebServlet(name = "/LectGroupInterfaceController", urlPatterns = { "/GroupFeedback" })
public class LectGroupInterfaceController extends HttpServlet {

    public LectGroupInterfaceController() {
        super();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try { 
            HttpSession session = request.getSession();
            int courseID = Integer.parseInt(request.getParameter("courseID"));
            int groupID = Integer.parseInt(request.getParameter("groupID"));
            

            System.out.println("Course ID = " + courseID);
            System.out.println("Group ID = " + groupID);

            //Gets group info
            GroupBean groupBean = new GroupBean();
            session.setAttribute("groupInfo", (groupBean.getGroupInfo(groupID)));
            // Gets group members
            GroupMemberBean groupMemberBean = new GroupMemberBean();
            session.setAttribute("groupMemberList", (groupMemberBean.getMemberList(groupID)));
            
            UpcomingAppBean upcomingAppBean = new UpcomingAppBean();
            session.setAttribute("percentageComplete", upcomingAppBean.getProgress(groupID));

            // Gets Assignments to mark
            FileMarkListBean fileMarkListBean = new FileMarkListBean();
            session.setAttribute("submittedProjects", (fileMarkListBean.getProjects(groupID, courseID)));

            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/course_mngt/group_mngt.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}