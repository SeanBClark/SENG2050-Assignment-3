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

// Bean to manage all user interfaces

public class UserBean implements java.io.Serializable
{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String userName;
    private int userId;
    private String userEmail;
    private int userStatus;
    private String userType;

    public UserBean(){}

    public UserBean(String userName, int userId, String userEmail, int userStatus, String userType)
    {

        this.userName = userName;
        this.userId = userId;
        this.userEmail = userEmail;
        this.userStatus = userStatus;
        this.userType = userType;

    }

    public void setUserName(String param) 
    {

        this.userName = param;

    }

    public String getUserName()
    {

        return userName;

    }

    public void setUserId(int param) 
    {

        this.userId = param;

    }

    public int getUserId()
    {

        return userId;

    }

    public void setUserEmail(String param) 
    {

        this.userEmail = param;

    }

    public String getUserEmail()
    {

        return userEmail;

    }

    public void setUserStatus(int param) 
    {

        this.userStatus = param;

    }

    public int getUserStatus()
    {

        return userStatus;
        
    }

    public void setUserType(String param) 
    {

        this.userType = param;

    }

    public String getUserType()
    {

        return userType;

    }

    // Inserts a new user that registers
    public void insertNewUser(String name, String email, String password) {

        try {

            Connection connection = ConfigBean.getConnection();
            Statement statement = connection.createStatement();
            statement.execute(insertUser(name, email, password));
            statement.close();
            connection.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }        
    }

    // Checks if users exists
    public boolean ifExists(String email, String password) {

        boolean result = false;
        int check = 0;
        ResultSet resultSet = null;
        
        try {

            Connection connection = ConfigBean.getConnection();
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(ifExistsQuery(email, password));

            while(resultSet.next()) {
                if(resultSet.getInt(1) == 1) {
                    check = 1;
                    result = true;
                }
            }

            resultSet.close();
            connection.close();
            
        } catch (Exception e) { e.printStackTrace(); }

        return result;

    }

    public String insertUser(String name, String email, String password) {
        return "INSERT INTO user(user_email, user_password, user_name) VALUES ('" + email + "', sha1('" + password + "'), '" + name + "');";
    }

    public String ifExistsQuery(String email, String password) {
        String result = "SELECT EXISTS(select * from user where user_email = '" + email + "' and user_password = sha1('" + password + "'));";
        return result;
    }


}