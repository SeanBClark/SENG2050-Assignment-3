package beans;

import java.io.*; 
import javax.sql.*;
import java.sql.*;
import java.util.*; 
import javax.naming.*;

import beans.*;

public class FileManagementBean implements java.io.Serializable
{
    // Private variables
    private static final long serialVersionUID = 1L;
    private String name; 
    private String url;
    private String description; 
    private int version; 
    private boolean status; // submitted to lecturer




    // Constructors
    public FileManagementBean()
    {}

    public FileManagementBean(String name, String url, String description, int version, boolean status)
    {
        this.name = name;
        this.url = url;
        this.description = description;
        this.version = version;
        this.status = status; 
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
}