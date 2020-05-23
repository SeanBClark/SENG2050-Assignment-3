import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import beans.*;

@WebServlet(name = "/GroupController", urlPatterns = { "/GroupHome" })
public class GroupController extends HttpServlet {

    public GroupController() {
        super();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        Connection connection = null;
        try { connection = ConfigBean.getConnection(); } catch (Exception e) { e.printStackTrace(); }

        ResultSet groupInfoRS = null;
        ResultSet groupMemberRS = null;
        ResultSet completedPercentageRS = null;
        ResultSet upcomingMilestonesRS = null;
        ResultSet upcomingAppRS = null;
        String groupID = request.getParameter("groupid");
        List <GroupBean> groupInfo = new ArrayList<>();
        List <GroupMemberBean> groupMemberList = new ArrayList<>();
        List <UpcomingMilestoneBean> upcomingMSList = new ArrayList<>();
        List <UpcomingAppBean> upcomingAppList = new ArrayList<>();
        String getGroupInfoQuery = "SELECT group_id, group_name, group_description FROM group_info WHERE group_id =  " + groupID + "";

        HttpSession session = request.getSession();
        groupInfoRS = DatabaseQuery.getResultSet(getGroupInfoQuery, connection);

        try {       
            while (groupInfoRS.next()) {

                session = request.getSession();

                GroupBean groupBean = new GroupBean();
                groupBean.setGroupId(groupInfoRS.getInt("group_id"));
                groupBean.setGroupName(groupInfoRS.getString("group_name"));
                groupBean.setGroupDesc(groupInfoRS.getString("group_description"));
                groupInfo.add(groupBean);
                session.setAttribute("groupBean", groupBean);

            }
            session.setAttribute("groupInfo", groupInfo);
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        String getGroupMembers = "SELECT user.user_name, user.user_id FROM user JOIN user_group_info on user_group_info.user_id = user.user_id WHERE user_group_info.group_id = " + groupID + ";";

        groupMemberRS = DatabaseQuery.getResultSet(getGroupMembers, connection);
        
        try {

            while (groupMemberRS.next()) {

                session = request.getSession();

                GroupMemberBean groupMemberBean = new GroupMemberBean();
                groupMemberBean.setMemberID(groupMemberRS.getInt("user.user_id"));
                groupMemberBean.setMemberName(groupMemberRS.getString("user.user_name"));
                groupMemberList.add(groupMemberBean);
                session.setAttribute("groupMemberBean", groupMemberBean);

            }
            session.setAttribute("groupMemberList", groupMemberList);
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Calculates the percentage of currently completed milestones 
        String percentCompleteQuery = "SELECT (count(milestone_status) * 100 / (SELECT count(milestone_status) FROM group_milestones where " + groupID + " = 1)) as percentageComplete from group_milestones where " + groupID + " = 1 and milestone_status = 0;";

        completedPercentageRS = DatabaseQuery.getResultSet(percentCompleteQuery, connection);

        try {

            session = request.getSession();

            while (completedPercentageRS.next()) {
                int percentageComplete = completedPercentageRS.getInt("percentageComplete");
                session.setAttribute("percentageComplete", percentageComplete);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Gets 4 upcoming milestones
        String upcomingMilestoneQuery = "SELECT milestone_datetime, milestone_name, milestone_status FROM group_milestones WHERE " + groupID + " = 1 ORDER BY milestone_datetime ASC LIMIT 4;";

        upcomingMilestonesRS = DatabaseQuery.getResultSet(upcomingMilestoneQuery, connection);

        try {

            while (upcomingMilestonesRS.next()) {

                session = request.getSession();

                UpcomingMilestoneBean upcomingMilestoneBean = new UpcomingMilestoneBean();
                upcomingMilestoneBean.setMilestoneName(upcomingMilestonesRS.getString("milestone_name"));
                upcomingMilestoneBean.setMilestoneDate(upcomingMilestonesRS.getString("milestone_datetime"));
                upcomingMilestoneBean.setMilestoneStatus(upcomingMilestonesRS.getInt("milestone_status"));
                upcomingMSList.add(upcomingMilestoneBean);
                session.setAttribute("upcomingMilestoneBean", upcomingMilestoneBean);

            }

            session.setAttribute("upcomingMSList", upcomingMSList);
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Upcoming Appointments
        String upcomingAppQuery = "SELECT app_name, appointment_datetime FROM group_appointment WHERE " + groupID + " = 1 and app_status = 0 ORDER BY appointment_datetime ASC LIMIT 4;";

        upcomingAppRS = DatabaseQuery.getResultSet(upcomingAppQuery, connection);

        try {

            while (upcomingAppRS.next()) {

                session = request.getSession();

                UpcomingAppBean upcomingAppBean = new UpcomingAppBean();
                upcomingAppBean.setAppName(upcomingAppRS.getString("app_name"));
                upcomingAppBean.setAppDate(upcomingAppRS.getString("appointment_datetime"));
                upcomingAppList.add(upcomingAppBean);
                session.setAttribute("upcomingAppBean", upcomingAppBean);

            }

            session.setAttribute("upcomingAppList", upcomingAppList);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        try {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/group_home/group_home.jsp");
            connection.close(); 
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
    }
}