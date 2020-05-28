package beans;

public class LectGroupListBean implements java.io.Serializable
{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String groupName;
    private int groupId;
    private String projectName;
    private int projectId;

    public LectGroupListBean(){}

    public LectGroupListBean(String groupName, String  projectName, int groupId, int projectId)
    {

        this.groupName = groupName;
        this. projectName =  projectName;
        this.groupId = groupId;
        this.projectId = projectId;

    }

    public void setGroupName(String param) 
    {

        this.groupName = param;

    }

    public String getGroupName()
    {

        return groupName;

    }

    public void setProjectName(String param) 
    {

        this. projectName = param;

    }

    public String getProjectName()
    {

        return  projectName;

    }

    public void setGroupId(int param) 
    {

        this.groupId = param;

    }

    public int getGroupId()
    {

        return groupId;

    }

    public void setProjectId(int param) 
    {

        this.projectId = param;

    }

    public int getProjectId()
    {

        return projectId;

    }


}