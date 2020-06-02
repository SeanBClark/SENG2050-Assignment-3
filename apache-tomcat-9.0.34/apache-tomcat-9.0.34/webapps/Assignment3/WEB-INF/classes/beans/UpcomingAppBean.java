package beans;

import java.io.*; 
import javax.sql.*;
import java.sql.*;
import java.util.*; 
import javax.naming.*;
import java.text.*; 
import java.util.Date;

import beans.*;

public class UpcomingAppBean implements java.io.Serializable
{
    // Private variables
    private static final long serialVersionUID = 1L;
    private String appName;
    private String appDate;
    private String description;
    private boolean status; // complete or incomplete



    // Constructors
    public UpcomingAppBean()
    {

    }

    public UpcomingAppBean(String appName, String appDate, String description, boolean status)
    {
        this.appName = appName;
        this.appDate = appDate;
        this.description = description; 
        this.status = status; 
    }




    // Setters
    public void setAppName(String appName) 
    {
        this.appName = appName;
    }

    public void setAppDate(String appDate) 
    {
        this.appDate = appDate;
    }

    public void setDescription(String description) 
    {
        this.description = description;
    }

    public void setStatus(boolean status)
    {
        this.status = status; 
    }




    // Getters
    public String getAppName()
    {
        return appName;
    }

    public String getAppDate()
    {
        return appDate;
    }

    public String getDescription()
    {
        return description;
    }

    public boolean getStatus()
    {
        return status; 
    }





    // database functions

    // returns list of appointments in database
    public List<UpcomingAppBean> getAllAppointments(int groupId)
    {
        List<UpcomingAppBean> schedule = new LinkedList<>(); 

        // prepare query
        String appQuery = ("SELECT * FROM group_appointment WHERE group_id = " + groupId + ";"); 

        try(Connection connection = ConfigBean.getConnection(); 
            Statement statement = connection.createStatement();   
            ResultSet result = statement.executeQuery(appQuery);)       
        { 
            while(result.next())                          
            {
                // create new appointment 
                UpcomingAppBean appointment = new UpcomingAppBean(); 

                // get appointment info
                appointment.setAppName(result.getString("app_name"));
                appointment.setDescription(result.getString("app_description"));
                appointment.setStatus(result.getBoolean("app_status")); 
                appointment.setAppDate(changeTimeFormat(groupId, (result.getString("app_name"))));

                schedule.add(appointment); // add appointment to schedule
            }

            result.close();
            connection.close();
        }
        catch(SQLException e)
        {
            System.err.println(e.getMessage());
            System.err.println(e.getStackTrace());
        }

        return schedule; 
    }

    // returns time and date in new format 
    public String changeTimeFormat(int groupId, String app_name)
    {
        String newTimeFormat = ""; 

        // prepare query
        String appQuery = ("SELECT DATE_FORMAT(appointment_datetime,  '%r %M %D %Y') AS app_time FROM group_appointment WHERE group_id = " +  groupId + " AND app_name = '" + app_name + "';");   

        try(Connection connection = ConfigBean.getConnection(); 
            Statement statement = connection.createStatement();   
            ResultSet result = statement.executeQuery(appQuery);)       
        { 
            while(result.next())                          
            {
                newTimeFormat = (result.getString("app_time"));
            }

            result.close();
            connection.close();
        }
        catch(SQLException e)
        {
            System.err.println(e.getMessage());
            System.err.println(e.getStackTrace());
        }

        return newTimeFormat; 
    }


    // add new appointment to database
    public void addAppointment(int groupId, String name, String datetimeString, String description, boolean status)
    {
        Timestamp datetime = null; 
        try 
        {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date parsedDate = dateFormat.parse(datetimeString);
            datetime = new java.sql.Timestamp(parsedDate.getTime());
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }


        // prepare statment 
        String appStatment= "INSERT INTO group_appointment (group_id, app_name, app_description, app_status, appointment_datetime) VALUES (?, ?, ?, ?, ?);"; 

        // connect to database and assign values to appointment
        try(Connection connection = ConfigBean.getConnection(); 
            PreparedStatement pS = connection.prepareStatement(appStatment);)
        {
            pS.setInt(1, groupId);
            pS.setString(2, name);
            pS.setString(3, description);
            pS.setBoolean(4, status);
            pS.setTimestamp(5, datetime);

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


    // remove appointment from database
    public void removeAppointment(int groupId, String name)
    {
        // prepare statment 
        String appStatment = ("DELETE FROM group_appointment WHERE group_id = " + groupId + " AND  app_name = '" + name + "';"); 
        
        // connect to database and delete appointment 
        try  
        { 
            Connection connection = ConfigBean.getConnection(); 
            Statement statement = connection.createStatement();   
            statement.executeUpdate(appStatment);

            connection.close();
        }
        catch(SQLException e)
        {
            System.err.println(e.getMessage());
            System.err.println(e.getStackTrace());
        }
    }


    // updates the status of a appointment 
    public void updateStatus(int groupId, String name, boolean status)
    {
        // prepare statment 
        String appStatment = ("UPDATE group_appointment SET app_status = " + status + " WHERE group_id = " + groupId + " AND  app_name = '" + name + "';");

        // connect to database and update appointment 
        try  
        { 
            Connection connection = ConfigBean.getConnection(); 
            Statement statement = connection.createStatement();   
            statement.executeUpdate(appStatment);

            connection.close();
        }
        catch(SQLException e)
        {
            System.err.println(e.getMessage());
            System.err.println(e.getStackTrace());
        }
    }


    // returns true if name exists and false if it does not in the database
    public boolean doesNameExist(int groupId, String testName)
    {
        // prepare query
        String fileQuery = ("SELECT * FROM group_appointment WHERE group_id = " + groupId + ";"); 

        try(Connection connection = ConfigBean.getConnection(); 
            Statement statement = connection.createStatement();   
            ResultSet result = statement.executeQuery(fileQuery);)       
        { 
            while(result.next())                          
            {
                if((result.getString("app_name")).equals(testName)) // tests to see if name exists 
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


}