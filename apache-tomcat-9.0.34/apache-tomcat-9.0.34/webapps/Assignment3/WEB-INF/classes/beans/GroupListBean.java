package beans;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import beans.*;

// Gets a list of groups and their members

public class GroupListBean implements java.io.Serializable
{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String groupName;
    private String groupDesc;
    private int groupId;

    public GroupListBean(){}

    public GroupListBean(String groupName, String groupDesc, int groupId)
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

    // List of group members
    public List<GroupListBean> getGroupList(int userID) {

        List<GroupListBean> groupList = new ArrayList<>();
        try {
            Connection connection = ConfigBean.getConnection();
            ResultSet resultSet = DatabaseQuery.getResultSet(getGroupInfo(userID), connection);

            while(resultSet.next()) {

                GroupListBean groupListBean = new GroupListBean();
                groupListBean.setGroupId(resultSet.getInt("group_id"));
                groupListBean.setGroupName(resultSet.getString("group_name"));
                groupListBean.setGroupDesc(resultSet.getString("group_description"));
                groupList.add(groupListBean);

            }
            resultSet.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return groupList;

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