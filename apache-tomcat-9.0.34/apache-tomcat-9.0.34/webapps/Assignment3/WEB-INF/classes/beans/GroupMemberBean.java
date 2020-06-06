package beans;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

// Bean to manage group members
// Functions are almost the same as group bean, could be merged with it

public class GroupMemberBean implements java.io.Serializable
{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private int memberID;
    private String memberName;

    public GroupMemberBean(){}

    public GroupMemberBean(String memberName, int memberID)
    {

        this.memberID = memberID;
        this.memberName = memberName;

    }

    public void setMemberID(int param) 
    {

        this.memberID = param;

    }

    public int getMemberID()
    {

        return memberID;

    }

    public void setMemberName(String param) 
    {

        this.memberName = param;

    }

    public String getMemberName()
    {

        return memberName;

    }

    // List of group members
    public List<GroupMemberBean> getMemberList(int groupID) {

        List<GroupMemberBean> list = new ArrayList<>();
        
        try {
            Connection connection = ConfigBean.getConnection();
            ResultSet resultSet = DatabaseQuery.getResultSet(getGroupMembers(groupID), connection);

            while (resultSet.next()) {

                GroupMemberBean bean = new GroupMemberBean();
                bean.setMemberID(resultSet.getInt("user_id"));
                bean.setMemberName(resultSet.getString("user_name"));
                list.add(bean);
            }

            resultSet.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        

        return list;

    }

    public static String getGroupMembers(int groupID) {
        return "SELECT user.user_name, user.user_id"
                    + " FROM user JOIN user_group_info on user_group_info.user_id = user.user_id" 
                    + " WHERE user_group_info.group_id = " + groupID + ";";
    }

}