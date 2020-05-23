package beans;

public class UpcomingAppBean implements java.io.Serializable
{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String appName;
    private String appDate;

    public UpcomingAppBean(){}

    public UpcomingAppBean(String appName, String appDate)
    {

        this.appName = appName;
        this.appDate = appDate;

    }

    public void setAppName(String param) 
    {

        this.appName = param;

    }

    public String getAppName()
    {

        return appName;

    }

    public void setAppDate(String param) 
    {

        this.appDate = param;

    }

    public String getAppDate()
    {

        return appDate;

    }
}