package beans;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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

}