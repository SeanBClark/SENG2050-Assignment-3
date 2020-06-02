package beans;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

// Java class to manage major database interactions as a way to avoid repeating code
// Gets result sets and checks if something exists
// As well as provides some default queries that work, and are easy to call

public class DatabaseQuery {

    public static ResultSet getResultSet(String query, Connection connection) {

        ResultSet resultSet = null;

        try {
            
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultSet;

    }

    public static boolean ifExists(String query, Connection connection) {

        boolean result = false;
        int check = 0;
        ResultSet resultSet = null;
        
        try {

            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while(resultSet.next()) {
                if(resultSet.getInt(1) == 1) {
                    check = 1;
                    result = true;
                }
            }
            
        } catch (Exception e) { e.printStackTrace(); }

        return result;

    }

    public static String ifExistsQuery(String email, String password) {
        String result = "SELECT EXISTS(select * from user where user_email = '" + email + "' and user_password = sha1('" + password + "'));";
        return result;
    }

    public static String insertUser(String name, String email, String password) {
        String result = "INSERT INTO user(user_email, user_password, user_name) VALUES ('" + email + "', sha1('" + password + "'), '" + name + "');";
        return result;
    }

    public static String insertGroup(String groupName, String groupDesc) {
        String result = "INSERT INTO group_info(group_name, group_description) VALUES ('" + groupName + "', '" + groupDesc + "');";
        return result;
    }

    public static String insertGroupUser(int userID, String groupName) {
        String result = "INSERT INTO user_group_info(user_id, group_id) VALUES ('" + userID + "', (SELECT group_id FROM group_info WHERE group_name = '" + groupName + "'))";
        return result;
    }

    public static String ifStdExists(String userEmail) {
        String result = "SELECT EXISTS( SELECT user_id FROM user WHERE user_email = '" + userEmail + "');";
        return result;
    }

    public static String ifCourseExists(String courseCode) {
        String result = "SELECT EXISTS( SELECT id FROM course WHERE course_code = '" + courseCode + "');";
        return result;
    }

    public static String enrollStudentQuery(String userEmail, String courseID) {
        String result = "INSERT INTO course_enrollments(course_id, std_id) VALUES ( ( SELECT id FROM course WHERE course_code = '" + courseID + "' ), ( SELECT user_id FROM user WHERE user_email = '" + userEmail + "'  ) );";
        return result;
    }

    public static String createCourseQuery(String courseName, String courseDesc, String CourseCode){
        String result = "INSERT INTO course(name, description,course_code) VALUES ('" + courseName + "', '" + courseDesc + "', '" + CourseCode + "');";
        return result;
    }

    



    public static String getGroupMembers(int groupID) {
        String result = "SELECT user.user_name, user.user_id FROM user JOIN user_group_info on user_group_info.user_id = user.user_id WHERE user_group_info.group_id = " + groupID + ";";
        return result;
    }

    public static String getUser(String email) {
        return "SELECT user_id, user_email, user_name, user_status, user_type FROM user WHERE user_email = '" + email + "';";
    }

    public static String insertProject(String name, String description, String dueDate, int courseID){
        return "INSERT INTO project(name, description, course_id, due_date) VALUES ('" + name + "', '" + description + "', " + courseID + ", '" + dueDate + "');";
    }



    public static String getFileDetails(int fileID) {
        return "SELECT group_id, file_name, file_url, file_desc, file_status FROM file_mngt WHERE file_id = " + fileID + ";";
    }

}