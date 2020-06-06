package beans;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

// Bean to manage group info

public class GroupBean implements java.io.Serializable
{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String groupName;
    private String groupDesc;
    private int groupId;

    public GroupBean(){}

    public GroupBean(String groupName, String groupDesc, int groupId)
    {

        this.groupName = groupName;
        this.groupDesc = groupDesc;
        this.groupId = groupId;

    }

    public void setGroupName(String param) 
    {

        this.groupName = param;

    }

    public String getGroupName()
    {

        return groupName;

    }

    public void setGroupDesc(String param) 
    {

        this.groupDesc = param;

    }

    public String getGroupDesc()
    {

        return groupDesc;

    }

    public void setGroupId(int param) 
    {

        this.groupId = param;

    }

    public int getGroupId()
    {

        return groupId;

    }

    // Gets all details about a group
    public List<GroupBean> getGroupInfo(int groupID) {

        List<GroupBean> groupInfo = new ArrayList<>();

        try {
            
            Connection connection = ConfigBean.getConnection();
            ResultSet resultSet = DatabaseQuery.getResultSet(groupInfoQuery(groupID), connection);

            while (resultSet.next()) {

                GroupBean bean = new GroupBean();
                bean.setGroupId(resultSet.getInt("group_id"));
                bean.setGroupName(resultSet.getString("group_name"));
                bean.setGroupDesc(resultSet.getString("group_description"));
                groupInfo.add(bean);

            }

            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return groupInfo;

    }

    public static String groupInfoQuery(int groupID) {
        return "SELECT group_id, group_name, group_description FROM group_info WHERE group_id =  " + groupID + "";
    }

    // Inserts a new group
    public void insertGroup(String groupName, String groupDesc){
        try {
            Connection connection = ConfigBean.getConnection();
            Statement statement = connection.createStatement();
            statement.execute(insertGroupQuery(groupName, groupDesc));            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String insertGroupQuery(String groupName, String groupDesc) {
        String result = "INSERT INTO group_info(group_name, group_description) VALUES ('" + groupName + "', '" + groupDesc + "');";
        return result;
    }

    // Attaches a project to a group
    public void attachProject(String courseCode, String projectName, String groupName){
        try {
            Connection connection = ConfigBean.getConnection();
            Statement statement = connection.createStatement();
            statement.execute(insertProjectGroup(courseCode, projectName, groupName));    
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getGroupID(String groupName) {
        return "SELECT group_id FROM group_info WHERE group_name = '" + groupName + "'";
    }

    public String getProjectID(String projectName) {
        return "SELECT id FROM project WHERE name = '" + projectName + "'";
    }

    public String getCourseID(String courseCode) {
        return "SELECT id FROM course WHERE course_code = '" + courseCode + "'";
    }

    public String insertProjectGroup(String courseCode, String projectName, String groupName){
        return "INSERT INTO project_assign(project_id, group_id) VALUES ("
                +  "( " + getProjectID(projectName) + " AND course_id = (" + getCourseID(courseCode)  + ") ),"
                +  "( " + getGroupID(groupName) + " ));";
    }

    // Gets group id
    public int lookUpGroupID(String groupName) {
        int groupID = 0;

        try {
            Connection connection = ConfigBean.getConnection();
            ResultSet resultSet = DatabaseQuery.getResultSet(getGroupID(groupName), connection);
            while (resultSet.next()){
                groupID = resultSet.getInt(1);
            }
            resultSet.close();
            connection.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return groupID;
    }

    // Inserts new user to a group
    public void insertGroupUser(int userID, String groupName){
        try {
            Connection connection = ConfigBean.getConnection();
            Statement statement = connection.createStatement();
            statement.execute(insertGroupUserQuery(userID, groupName));    
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String insertGroupUserQuery(int userID, String groupName) {
        return "INSERT INTO user_group_info(user_id, group_id) VALUES ('" + userID + "', (SELECT group_id FROM group_info WHERE group_name = '" + groupName + "'))";
    }

    // Checks if project exists
    public boolean ifProjectExist(String courseCode, String projectName) {

        boolean exists = false;
        int result = 0;

        try {

            Connection connection = ConfigBean.getConnection();
            ResultSet resultSet = DatabaseQuery.getResultSet(ifProjectExistsQuery(courseCode, projectName), connection);
            while (resultSet.next()){
                result = resultSet.getInt(1);
            }

            if (result == 1) {
                exists = true;
            }
            resultSet.close();
            connection.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return exists;

    }

    public String ifProjectExistsQuery(String courseCode, String projectName) {
        return "SELECT EXISTS(SELECT id FROM project WHERE name = '" + projectName +"' AND course_id = (SELECT id FROM course WHERE course_code = '" + courseCode +"'))";
    }

    // Insert group members to a group
    public void insertGroupMember(String email, int groupID) {

        try {

            Connection connection = ConfigBean.getConnection();
            Statement statement = connection.createStatement();
            statement.execute(insertGroupMemberQuery(email, groupID));
            statement.close();
            connection.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String insertGroupMemberQuery(String email, int groupID) {
        return "INSERT INTO user_group_info(user_id, group_id) VALUES ((SELECT user_id FROM user WHERE user_email = '" + email + "'), " + groupID + ");";
    }

}