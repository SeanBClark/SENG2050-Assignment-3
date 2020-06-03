package beans;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

// Bean to list upcoming milestones

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

    public List<UpcomingMilestoneBean> getUpcomingMS(int groupID) {

        List<UpcomingMilestoneBean> list = new ArrayList<>();

        try {

            Connection connection = ConfigBean.getConnection();
            ResultSet resultSet = DatabaseQuery.getResultSet(getQuery(groupID), connection);

            while (resultSet.next()) {

                UpcomingMilestoneBean bean = new UpcomingMilestoneBean();
                bean.setMilestoneName(resultSet.getString("milestone_name"));
                bean.setMilestoneDate(resultSet.getString("milestone_datetime"));
                bean.setMilestoneStatus(resultSet.getInt("milestone_status"));
                list.add(bean);

            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;

    }

    public static String getQuery(int groupID) {
        return "SELECT milestone_datetime, milestone_name, milestone_status FROM group_milestones" 
                        + " WHERE group_id = " + groupID + "" 
                        + " ORDER BY milestone_datetime ASC LIMIT 4;";
    }
}