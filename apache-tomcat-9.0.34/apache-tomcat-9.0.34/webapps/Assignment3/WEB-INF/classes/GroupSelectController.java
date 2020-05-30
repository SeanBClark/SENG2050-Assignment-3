import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import beans.*;

@WebServlet(name = "/GroupSelectController", urlPatterns = { "/GroupSelect" })
public class GroupSelectController extends HttpServlet {

    public GroupSelectController() {
        super();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        Connection connection = null;
        try { connection = ConfigBean.getConnection(); } catch (Exception e) { e.printStackTrace(); }

        ResultSet groupResultSet = null;
        List <GroupListBean> groupList = new ArrayList<>();

        HttpSession session = request.getSession();
        int userID = (int) session.getAttribute("userID");

        String groupInfo = getGroupInfo(userID);
        groupResultSet = DatabaseQuery.getResultSet(groupInfo, connection);

        try {
            
            while(groupResultSet.next()) {

                session = request.getSession();

                GroupListBean groupListBean = new GroupListBean();
                groupListBean.setGroupId(groupResultSet.getInt("group_id"));
                groupListBean.setGroupName(groupResultSet.getString("group_name"));
                groupListBean.setGroupDesc(groupResultSet.getString("group_description"));
                session.setAttribute("groupID", groupResultSet.getInt("group_id"));
                groupList.add(groupListBean);
                session.setAttribute("groupListBean", groupListBean);

            }
            session.setAttribute("groupList", groupList);

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/group_select/group_select.jsp");
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

            String groupName = request.getParameter("groupName");
            String groupDesc = request.getParameter("groupDesc");

            String insertGroupQuery = DatabaseQuery.insertGroup(groupName, groupDesc);

            try {
                Statement statement = connection.createStatement();
                statement.execute(insertGroupQuery);
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Assign group to a project
            String projectName = request.getParameter("projectName");
            String courseCode = request.getParameter("courseCode");
            // TO DO: Need to put in check if 0 do nothing
            int groupID = 0;
            System.out.println(projectName + ":" + courseCode);

            // TO DO: Query is to complex
            String getCourseID = "SELECT id FROM course WHERE course_code = '" + courseCode + "'";
            String getProjectID = "SELECT id FROM project WHERE name = '" + projectName + "'";
            String getGroupID = "SELECT group_id FROM group_info WHERE group_name = '" + groupName + "'";
            String insertProjectGroup = "INSERT INTO project_assign(project_id, group_id) VALUES ("
                                                        +  "( " + getProjectID + " AND course_id = (" + getCourseID  + ") ),"
                                                        +  "( " + getGroupID + " ));";
                                                        
            try {
                Statement statement = connection.createStatement();
                statement.execute(insertProjectGroup);

                ResultSet getGroupIdRS = DatabaseQuery.getResultSet(getGroupID, connection);
                while (getGroupIdRS.next()){
                    groupID = getGroupIdRS.getInt(1);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Add user to session then sends user to the new groups home page
            HttpSession session = request.getSession();
            int userID = (int) session.getAttribute("userID");

            String addGroupUser = DatabaseQuery.insertGroupUser(userID, groupName);

            try {
                Statement statement = connection.createStatement();
                statement.execute(addGroupUser);
                response.sendRedirect("/Assignment3/GroupHome?groupid=" + groupID);
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            try {
                connection.close(); 
            } catch (Exception e) {
                e.printStackTrace();
            }
                     
    }

    public static String getGroupInfo(int userID) {
        String result = "SELECT group_info.group_id, group_info.group_name, group_info.group_description, user.user_name, user.user_id" 
                                + " FROM user_group_info"  
                                + " JOIN user ON user.user_id = user_group_info.user_id"  
                                + " JOIN group_info ON group_info.group_id = user_group_info.group_id" 
                                + " WHERE user_group_info.user_id = " + userID + ";";
        return result;
    }
}