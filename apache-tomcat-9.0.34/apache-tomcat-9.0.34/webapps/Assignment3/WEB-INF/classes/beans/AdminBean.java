package beans;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

// Bean to manage all admin functionality

public class AdminBean implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    // Function to turn standard account into a lecturer account

    public void makeUserLect(int id, String email) {

        try {
            Connection connection = ConfigBean.getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(updateUserQuery(id, email));
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String updateUserQuery(int id, String email) {
        return "UPDATE user SET user_type = 'lect' WHERE user_id = " + id + " AND user_email = '" + email + "';";
    }

    // Gets the users ID

    public int getUserID(String email) {

        int id = 0;

        try {

            Connection connection = ConfigBean.getConnection();
            ResultSet resultSet = DatabaseQuery.getResultSet(getUserIDQuery(email), connection);
            while (resultSet.next()) {
                id = resultSet.getInt(1);
            }

            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }        

        return id;
    }

    public String getUserIDQuery(String email) {
        return "SELECT user_id FROM user where user_email = '" + email + "';";
    }

    // Enrols a student into a course
    // This would be much better automated, but would have taken to much time

    public void enrolStudent(String email, String code) {
        try {            
            Connection connection = ConfigBean.getConnection();
            Statement statement = connection.createStatement();
            statement.execute(enrollStudentQuery(email, code));
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String enrollStudentQuery(String email, String code) {
        return "INSERT INTO course_enrollments(course_id, std_id) VALUES ( ( SELECT id FROM course WHERE course_code = '" + code + "' ), ( SELECT user_id FROM user WHERE user_email = '" + email + "'  ) );";
    }

    // Checks if student exists

    public int ifStdExists(String email) {
        int exists = 0;
        try {
            Connection connection = ConfigBean.getConnection();

            ResultSet resultSet = DatabaseQuery.getResultSet(ifStdExistsQuery(email), connection);
            while (resultSet.next()) {
                exists = resultSet.getInt(1);
            }

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return exists;
    }

    public static String ifStdExistsQuery(String email) {
        return "SELECT EXISTS( SELECT user_id FROM user WHERE user_email = '" + email + "');";
    }

    //Checks if course exists

    public int ifCourseExists(String code) {
        int exists = 0;
        try {
            Connection connection = ConfigBean.getConnection();

            ResultSet resultSet = DatabaseQuery.getResultSet(ifCourseExistsQuery(code), connection);
            while (resultSet.next()) {
                exists = resultSet.getInt(1);
            }

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return exists;
    }

    public static String ifCourseExistsQuery(String code) {
        return "SELECT EXISTS( SELECT id FROM course WHERE course_code = '" + code + "');";
    }

    // Creates a new course

    public void createCourse(String name, String desc, String code){
        try {            
            Connection connection = ConfigBean.getConnection();
            Statement statement = connection.createStatement();
            statement.execute(createCourseQuery(name, desc, code));
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String createCourseQuery(String name, String desc, String code){
        return "INSERT INTO course(name, description,course_code) VALUES ('" + name + "', '" + desc + "', '" + code + "');";
    }

    // Checks if lecturer exists

    public int ifLectExists(String email) {
        int exists = 0;
        try {
            Connection connection = ConfigBean.getConnection();

            ResultSet resultSet = DatabaseQuery.getResultSet(ifStdExistsQuery(email), connection);
            while (resultSet.next()) {
                exists = resultSet.getInt(1);
            }

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return exists;
    }

    public static String ifLectExistsQuery(String email) {
        return "SELECT EXISTS( SELECT user_id FROM user WHERE user_email = '" + email + "' AND user_type = 'lect');";
    }

    // Assigns a lecturer to a course as its coordinator

    public void insertCourseCord(String email, String code) {

        try {            
            Connection connection = ConfigBean.getConnection();
            Statement statement = connection.createStatement();
            statement.execute(insertCord(email, code));
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String insertCord(String email, String code) {
        return "INSERT INTO course_cord(course_id, lect_id) VALUES ("
                    + " (SELECT id FROM course WHERE course_code = '" + code + "')," 
                    + " (SELECT user_id FROM user WHERE user_email = '" + email + "'));";
    }
    
}