package beans;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

// Simple bean to get a list of the upcoming appointments

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

    public List<UpcomingAppBean> getUpcomingApp(int groupID) {

        List<UpcomingAppBean> list = new ArrayList<>();

        try {

            Connection connection = ConfigBean.getConnection();
            ResultSet resultSet = DatabaseQuery.getResultSet(getQuery(groupID), connection);

            while (resultSet.next()) {

                UpcomingAppBean bean = new UpcomingAppBean();
                bean.setAppName(resultSet.getString("app_name"));
                bean.setAppDate(resultSet.getString("appointment_datetime"));
                list.add(bean);

            }

            connection.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;

    }

    public static String getQuery(int groupID) {
        return "SELECT app_name, appointment_datetime FROM group_appointment" 
                    + " WHERE group_id = " + groupID + " and app_status = 0"
                    + " ORDER BY appointment_datetime ASC LIMIT 4;";
    }

    public int getProgress(int groupID) {

        int percentageComplete = 0;

        try {
            
            Connection connection = ConfigBean.getConnection();
            ResultSet completedPercentageRS = DatabaseQuery.getResultSet(getPercentQuery(groupID), connection);
            while (completedPercentageRS.next()) {
                percentageComplete = completedPercentageRS.getInt("percentageComplete");
            }

            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return percentageComplete;

    }

    public static String getPercentQuery(int groupID) {
        return "SELECT (count(milestone_status) * 100 / (SELECT count(milestone_status)" 
                    + " FROM group_milestones where group_id = " + groupID + ")) as percentageComplete" 
                    + " FROM group_milestones where group_id = " + groupID + " and milestone_status = 1;";
    }
}