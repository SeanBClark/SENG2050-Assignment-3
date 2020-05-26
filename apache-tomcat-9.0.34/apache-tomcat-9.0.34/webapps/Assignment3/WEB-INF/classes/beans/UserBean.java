package beans;

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




}