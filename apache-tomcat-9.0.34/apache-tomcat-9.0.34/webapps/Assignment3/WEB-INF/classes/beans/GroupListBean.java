package beans;

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




}