package beans;

public class UpcomingMilestoneBean implements java.io.Serializable
{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String milestoneName;
    private String milestoneDate;
    private int milestoneStatus;

    public UpcomingMilestoneBean(){}

    public UpcomingMilestoneBean(String milestoneName, String milestoneDate, int milestoneStatus)
    {

        this.milestoneName = milestoneName;
        this.milestoneDate = milestoneDate;
        this.milestoneStatus = milestoneStatus;

    }

    public void setMilestoneName(String param) 
    {

        this.milestoneName = param;

    }

    public String getMilestoneName()
    {

        return milestoneName;

    }

    public void setMilestoneDate(String param) 
    {

        this.milestoneDate = param;

    }

    public String getMilestoneDate()
    {

        return milestoneDate;

    }

    public void setMilestoneStatus(int param) 
    {

        this.milestoneStatus = param;

    }

    public int getMilestoneStatus()
    {

        return milestoneStatus;

    }
}