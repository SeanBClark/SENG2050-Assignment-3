package beans;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class FileMarkListBean implements java.io.Serializable
{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String fileName;
    private int fileID;

    public FileMarkListBean(){}

    public FileMarkListBean(String fileName, String groupDesc, int fileID)
    {

        this.fileName = fileName;
        this.fileID = fileID;

    }

    public void setFileName(String param) 
    {

        this.fileName = param;

    }

    public String getFileName()
    {

        return fileName;

    }

    public void setFileID(int param) 
    {

        this.fileID = param;

    }

    public int getFileID()
    {

        return fileID;

    }

    public List<FileMarkListBean> getCourses(int groupID, int courseID) {

        List<FileMarkListBean> list = new ArrayList<>();

        try {

            Connection connection = ConfigBean.getConnection();
            ResultSet resultSet = DatabaseQuery.getResultSet(getSubmittedAssign(groupID, courseID), connection);

            while (resultSet.next()) {

                FileMarkListBean bean = new FileMarkListBean();
                bean.setFileID(resultSet.getInt("file_id"));
                bean.setFileName(resultSet.getString("file_name"));
                list.add(bean);

            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;

    }

    public static String getSubmittedAssign(int groupID, int courseID) {
        return "SELECT file_mngt.file_id ,file_mngt.file_name" 
                    + " FROM file_mngt"
                    + " JOIN project_assign ON project_assign.group_id = file_mngt.group_id"
                    + " JOIN project ON project.id = project_assign.project_id"
                    + " WHERE file_mngt.group_id = " + groupID + "" 
                    + " AND project.course_id = " + courseID + ""
                    + " AND file_mngt.file_status = 1;";
    }


}