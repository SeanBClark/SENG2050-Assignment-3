package beans;

import java.io.*; 
import javax.sql.*;
import java.sql.*;
import java.util.*; 
import javax.naming.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import beans.*;

// Files management Bean


public class FileManagementBean implements java.io.Serializable
{
    // Private variables
    private static final long serialVersionUID = 1L;
    private String name; 
    private String url;
    private String description; 
    private int version; 
    private boolean status; // submitted to lecturer
    private int statusInt;
    private int fileID;
    private int groupID;




    // Constructors
    public FileManagementBean()
    {}

    public FileManagementBean(String name, String url, String description, int version, boolean status, int statusInt, int fileID, int groupID)
    {
        this.name = name;
        this.url = url;
        this.description = description;
        this.version = version;
        this.status = status; 
        this.statusInt = statusInt;
        this.fileID = fileID;
        this.groupID = groupID;
        
    }




    // Getters
    public String getName() 
    {
        return this.name;
    }

    public String getUrl() 
    {
        return this.url;
    }

    public String getDescription() 
    {
        return this.description;
    }

    public int getVersion()
    {
        return this.version;
    }

    public boolean getStatus()
    {
        return this.status;
    }

    public int getStatusInt() {

        return this.statusInt;

    }

    public int getFileID() {

        return this.fileID;

    }

    public int getGroupID() {

        return this.groupID;

    }




    // Setters
    public void setName(String name)
    {
        this.name = name;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public void setVersion(int version)
    {
        this.version = version; 
    }

    public void setStatus(boolean status)
    {
        this.status = status; 
    }

    public void setStatusInt(int param) {

        this.statusInt = param;

    }

    public void setFileID(int param) {
        this.fileID = param;
    }

    public void setGroupID(int param) {
        this.groupID = param;
    }




    // database functions

    // returns true if name exists and false if it does not in the database
    public boolean doesNameExist(int groupId, String testName)
    {
        // prepare query
        String fileQuery = ("SELECT * FROM file_mngt WHERE group_id = " + groupId + ";"); 

        try(Connection connection = ConfigBean.getConnection(); 
            Statement statement = connection.createStatement();   
            ResultSet result = statement.executeQuery(fileQuery);)       
        { 
            while(result.next())                          
            {
                if((result.getString("file_name")).equals(testName)) // tests to see if name exists 
                {
                    return true; // name exists 
                }
            }

            result.close();
            connection.close();
        }
        catch(SQLException e)
        {
            System.err.println(e.getMessage());
            System.err.println(e.getStackTrace());
        }

        return false; // name does not exist 
    }

    // returns list of files in database
    public List<FileManagementBean> getAllFiles(int groupId)
    {
        List<FileManagementBean> folder = new LinkedList<>(); 

        // prepare query
        String fileQuery = ("SELECT * FROM file_mngt WHERE group_id = " + groupId + ";"); 

        try(Connection connection = ConfigBean.getConnection(); 
            Statement statement = connection.createStatement();   
            ResultSet result = statement.executeQuery(fileQuery);)       
        { 
            while(result.next())                          
            {
                // create new file 
                FileManagementBean file = new FileManagementBean(); 

                // get file info
                file.setFileID(result.getInt("file_id"));
                file.setName(result.getString("file_name"));
                file.setUrl(result.getString("file_url"));
                file.setDescription(result.getString("file_desc")); 
                file.setVersion(result.getInt("file_version"));
                file.setStatus(result.getBoolean("file_status"));

                folder.add(file); // add file to folder
            }

            result.close();
            connection.close();
        }
        catch(SQLException e)
        {
            System.err.println(e.getMessage());
            System.err.println(e.getStackTrace());
        }

        return folder; 
    }

    // add new file to database
    public void addFile(int groupId, String name, String url, String description, int version, boolean status)
    {
        // prepare statment 
        String fileStatment= "INSERT INTO file_mngt (group_id, file_name, file_url, file_desc, file_version, file_status) VALUES (?, ?, ?, ?, ?, ?);"; 

        // connect to database and assign values to file
        try(Connection connection = ConfigBean.getConnection(); 
            PreparedStatement pS = connection.prepareStatement(fileStatment);)
        {
            pS.setInt(1, groupId);
            pS.setString(2, name);
            pS.setString(3, url);
            pS.setString(4, description);
            pS.setInt(5, version);
            pS.setBoolean(6, status);

            pS.executeUpdate(); 

            pS.close(); 
            connection.close();
        }
        catch(SQLException e)
        {
            System.err.println(e.getMessage());
            System.err.println(e.getStackTrace()); 
        }
    }

    // remove file from database
    public void removeFile(int groupId, String name, int version)
    {
        // prepare statment 
        String fileStatment = ("DELETE FROM file_mngt WHERE group_id = " + groupId + " AND  file_name = '" + name + "' AND file_version = " + version + ";"); 
        
        // connect to database and delete file 
        try  
        { 
            Connection connection = ConfigBean.getConnection(); 
            Statement statement = connection.createStatement();   
            statement.executeUpdate(fileStatment);

            connection.close();
        }
        catch(SQLException e)
        {
            System.err.println(e.getMessage());
            System.err.println(e.getStackTrace());
        }
    }

    // returns number of the current version in the database
    public int getCurrentVersion(int groupId, String versionName)
    {
        int currentVersion = 0; 

        // prepare query
        String fileQuery = ("SELECT file_version FROM file_mngt WHERE file_name = '" + versionName + "' AND group_id = " + groupId + ";");

        try(Connection connection = ConfigBean.getConnection(); 
            Statement statement = connection.createStatement();   
            ResultSet result = statement.executeQuery(fileQuery);)       
        { 
            while(result.next())                          
            {
                // get file version
                currentVersion = (result.getInt("file_version"));
            }

            result.close();
            connection.close();
        }
        catch(SQLException e)
        {
            System.err.println(e.getMessage());
            System.err.println(e.getStackTrace());
        }

        return currentVersion; 
    }

    // updates the status of a file 
    public void updateStatus(int groupId, String name, int version, boolean status)
    {
        // prepare statment 
        String fileStatment = ("UPDATE file_mngt SET file_status = " + status + " WHERE group_id = " + groupId + " AND  file_name = '" + name + "' AND file_version = " + version + ";");

        // connect to database and update file 
        try  
        { 
            Connection connection = ConfigBean.getConnection(); 
            Statement statement = connection.createStatement();   
            statement.executeUpdate(fileStatment);

            connection.close();
        }
        catch(SQLException e)
        {
            System.err.println(e.getMessage());
            System.err.println(e.getStackTrace());
        }
    }

    public List<FileManagementBean> getRecentFiles(int groupID) {

        List<FileManagementBean> list = new ArrayList<>();

        try {

            Connection connection = ConfigBean.getConnection(); 
            ResultSet resultSet = DatabaseQuery.getResultSet(getRecentFileQuery(groupID), connection);

            while (resultSet.next()) {

                FileManagementBean bean = new FileManagementBean();
                bean.setName(resultSet.getString("file_name"));
                bean.setUrl(resultSet.getString("file_url"));
                bean.setStatusInt(resultSet.getInt("file_status"));
                list.add(bean);

            }
            connection.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;

    }

    public String getRecentFileQuery(int groupID) {
        return "SELECT file_name, file_url, file_status FROM file_mngt" 
                    + " WHERE group_id = " + groupID + "" 
                    + " ORDER BY date_updated ASC LIMIT 4;";
    }

    public List<FileManagementBean> getFileDetails(int fileID) {

        List<FileManagementBean> list = new ArrayList<>();

        try {
            
            Connection connection = ConfigBean.getConnection();
            ResultSet resultSet = DatabaseQuery.getResultSet(getFileQuery(fileID), connection);

            while (resultSet.next()) {
                FileManagementBean bean = new FileManagementBean();
                bean.setFileID(resultSet.getInt("file_id"));
                bean.setName(resultSet.getString("file_name"));
                bean.setUrl(resultSet.getString("file_url"));
                bean.setDescription(resultSet.getString("file_desc"));
                bean.setStatusInt(resultSet.getInt("file_status"));
                bean.setGroupID(resultSet.getInt("group_id"));
                list.add(bean);
            }

            connection.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;

    }

    public static String getFileQuery(int fileID) {
        return "SELECT file_id, group_id, file_name, file_url, file_desc, file_status FROM file_mngt WHERE file_id = " + fileID + ";";
    }

    public int getProjectID(int groupID, int courseID) {

        int id = 0;

        try {

            Connection connection = ConfigBean.getConnection();
            ResultSet resultSet = DatabaseQuery.getResultSet(getProjectIDQuery(groupID, courseID), connection);
            while (resultSet.next()) {id = resultSet.getInt(1);}
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return id;

    }

    public static String getProjectIDQuery(int groupID, int courseID){
        return "SELECT project.id FROM project JOIN project_assign ON project.id = project_assign.project_id" 
                        + " WHERE project_assign.group_id = " + groupID + "  AND project.course_id = " + courseID + "";
    }

    public void insertFeedback(int projectID, int groupID, String feedback, double grade) {

        try {
            
            Connection connection = ConfigBean.getConnection(); 
            Statement statement = connection.createStatement();
            statement.execute(insertFeedbackQuery(projectID, groupID, feedback, grade));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static String insertFeedbackQuery(int projectID, int groupID, String feedback, double grade){
        return "UPDATE project_assign" 
                    + " SET feedback = '" + feedback + "', grade = '"+ grade +"', mark = '"+ getMark(grade) +"', marked = 1" 
                    + " WHERE project_id = '"+ projectID + "' AND group_id = '" + groupID + "';";
    }

    public static String getMark(double grade) {

        String result = "";

        if (grade > 83.00)
            result = "HD";
        else if (grade > 73 && grade < 82.99)
            result = "D";
        else if (grade > 63 && grade < 72.99)
            result = "C";
        else if (grade > 50 && grade < 62.99)
            result = "P";
        else if (grade < 50 && grade > 0.00)
            result = "N";
        else
            result = "NA";
        
        return result;
    }
}