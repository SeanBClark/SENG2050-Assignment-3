import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

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

}