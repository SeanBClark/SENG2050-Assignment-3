import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import beans.*;

// Controller to fill out group management home page

@WebServlet(name = "/GroupController", urlPatterns = { "/GroupHome" })
public class GroupController extends HttpServlet {

    public GroupController() {
        super();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        
        try { 
        String groupID = request.getParameter("groupid");
        HttpSession session = request.getSession();
        session.setAttribute("groupID", Integer.parseInt(groupID));

        GroupBean groupBean = new GroupBean();
        session.setAttribute("groupInfo", (groupBean.getGroupInfo(Integer.parseInt(groupID))));

        GroupMemberBean groupMemberBean = new GroupMemberBean();
        session.setAttribute("groupMemberList", (groupMemberBean.getMemberList(Integer.parseInt(groupID))));

        UpcomingMilestoneBean upcomingMilestoneBean = new UpcomingMilestoneBean();
        session.setAttribute("upcomingMSList", upcomingMilestoneBean.getUpcomingMS(Integer.parseInt(groupID)));

        UpcomingAppBean upcomingAppBean = new UpcomingAppBean();
        session.setAttribute("upcomingAppList", upcomingAppBean.getUpcomingApp(Integer.parseInt(groupID)));        
        session.setAttribute("percentageComplete", upcomingAppBean.getProgress(Integer.parseInt(groupID)));

        FileManagementBean fileManagementBean = new FileManagementBean();
        session.setAttribute("recentFilesList", fileManagementBean.getRecentFiles(Integer.parseInt(groupID)));

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/group_home/group_home.jsp");
        dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}