package beans;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

// Bean to manage groups under each course/course coordinator

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
    private int status;

    public LectGroupListBean(){}

    public LectGroupListBean(String groupName, String  projectName, int groupId, int projectId, int status)
    {

        this.groupName = groupName;
        this. projectName =  projectName;
        this.groupId = groupId;
        this.projectId = projectId;
        this.status = status;

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
    public void setMarkedStatus(int param) 
    {

        this.status = param;

    }

    public int getMarkedStatus()
    {

        return status;

    }

    // List of groups in a course
    public List<LectGroupListBean> getCourses(int courseID) {

        List<LectGroupListBean> list = new ArrayList<>();

        try {

            Connection connection = ConfigBean.getConnection();
            ResultSet resultSet = DatabaseQuery.getResultSet(getCourseGroupList(courseID), connection);

            while (resultSet.next()) {

                LectGroupListBean bean = new LectGroupListBean();
                bean.setGroupId(resultSet.getInt("group_id"));
                bean.setGroupName(resultSet.getString("group_name"));
                bean.setProjectId(resultSet.getInt("project_id"));
                bean.setProjectName(resultSet.getString("name"));
                bean.setMarkedStatus(resultSet.getInt("marked"));
                list.add(bean);

            }
            resultSet.close();
            connection.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;

    }

    public static String getCourseGroupList(int courseID) {
        String result = "SELECT project_assign.project_id, project.name, group_info.group_id, group_info.group_name, project_assign.marked" 
            + " FROM project_assign"
            + " JOIN project ON project_assign.project_id = project.id"
            + " JOIN group_info ON group_info.group_id = project_assign.group_id" 
            + " WHERE group_info.group_status = 1"
            + " AND project.course_id = " + courseID + ";";
        return result;
    }


}