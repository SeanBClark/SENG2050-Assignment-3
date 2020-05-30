import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import beans.*;

@WebServlet(name = "/MilestoneController", urlPatterns = { "/ManageMilestones" })
public class MilestoneController extends HttpServlet {

    public MilestoneController() {
        super();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        Connection connection = null;
        try { connection = ConfigBean.getConnection(); } catch (Exception e) { e.printStackTrace(); }
        
        ResultSet groupInfoRS = null;
        ResultSet groupMemberRS = null;
        ResultSet completedPercentageRS = null;
        ResultSet upcomingMilestonesRS = null;
        
        
        List <GroupBean> groupInfo = new ArrayList<>();
        List <GroupMemberBean> groupMemberList = new ArrayList<>();
        List <UpcomingMilestoneBean> upcomingMSList = new ArrayList<>();
        
        HttpSession session = request.getSession();
        int groupID = (int) session.getAttribute("groupID");
        String getGroupInfoQuery = "SELECT group_id, group_name, group_description FROM group_info WHERE group_id =  " + groupID + "";
        
        
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
        String percentCompleteQuery = "SELECT (count(milestone_status) * 100 / (SELECT count(milestone_status) FROM group_milestones where " + groupID + " = group_id)) as percentageComplete from group_milestones where " + groupID + " = group_id and milestone_status = 1;";

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
        String upcomingMilestoneQuery = "SELECT milestone_datetime, milestone_name, milestone_status FROM group_milestones WHERE " + groupID + " = group_id ORDER BY milestone_datetime ASC;";

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
        
        try {
                connection.close(); 
            } catch (Exception e) {
                e.printStackTrace();
            }
        

        try {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/group_milestones/group_milestones.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
        Connection connection = null;
        try { connection = ConfigBean.getConnection(); } catch (Exception e) { e.printStackTrace(); }
        
        String milestoneName = request.getParameter("milestoneName");
        String milestoneDateTime = request.getParameter("milestoneDate");
        milestoneDateTime += " " + request.getParameter("milestoneTime") + ":00";
        
        
        
        
        HttpSession session = request.getSession();
        int groupID = (int) session.getAttribute("groupID");
        
        
        
        if (milestoneName != null && !milestoneName.equals(""))
        {
            String addMilestoneQuery = DatabaseQuery.addNewMilestone(groupID,milestoneName,milestoneDateTime);
            try {
                Statement statement = connection.createStatement();
                statement.execute(addMilestoneQuery);
                connection.close(); 
                response.sendRedirect("/Assignment3/ManageMilestones");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        String markIncomplete = request.getParameter("markIncomplete");
        String markIncompleteQuery = DatabaseQuery.markMilestone(groupID,markIncomplete,0);
        if (markIncomplete != null && !markIncomplete.equals(""))
        {
            
            try {
                Statement statement = connection.createStatement();
                statement.execute(markIncompleteQuery);
                connection.close(); 
                response.sendRedirect("/Assignment3/ManageMilestones");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        String markComplete = request.getParameter("markComplete");
        String markCompleteQuery = DatabaseQuery.markMilestone(groupID,markComplete,1);
        if (markComplete != null && !markComplete.equals(""))
        {
            
            try {
                Statement statement = connection.createStatement();
                statement.execute(markCompleteQuery);
                connection.close(); 
                response.sendRedirect("/Assignment3/ManageMilestones");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        String markRemove = request.getParameter("markRemove");
        String removeQuery = DatabaseQuery.deleteMilestone(groupID,markRemove);
        if (markRemove != null && !markRemove.equals(""))
        {
            
            try {
                Statement statement = connection.createStatement();
                statement.execute(removeQuery);
                connection.close(); 
                response.sendRedirect("/Assignment3/ManageMilestones");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        try {
                connection.close(); 
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
}