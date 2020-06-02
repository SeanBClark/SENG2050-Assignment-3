package beans;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class CourseBean {
    
    public void insertAssignment(int courseID, String dueDate, String name, String description) {

        try {

            Connection connection = ConfigBean.getConnection(); 
            Statement statement = connection.createStatement();
            statement.execute(insertProject(name, description, dueDate, courseID));
            connection.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static String insertProject(String name, String description, String dueDate, int courseID){
        return "INSERT INTO project(name, description, course_id, due_date) VALUES ('" + name + "', '" + description + "', " + courseID + ", '" + dueDate + "');";
    }

}