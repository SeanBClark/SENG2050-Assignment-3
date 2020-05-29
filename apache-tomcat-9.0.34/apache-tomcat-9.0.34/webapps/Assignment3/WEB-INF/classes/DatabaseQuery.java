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

    public static String getLectCourses(int lectId) {
        String result = "SELECT course_cord.course_id, course.name, course.course_code FROM course_cord" 
                                + " JOIN course ON course.id = course_cord.course_id"
                                + " WHERE course_cord.lect_id = " + lectId + ";";
        return result;
    }

    public static String getCourseGroupList(int courseID) {
        String result = "SELECT project_assign.project_id, project.name, group_info.group_id, group_info.group_name, project_assign.marked" 
            + " FROM project_assign"
            + " JOIN project ON project_assign.project_id = project.id"
            + " JOIN group_info ON group_info.group_id = project_assign.group_id" 
            + " WHERE group_info.group_status = 1"
            + " AND project.course_id = " + courseID + ";";
        return result;
    }

    public static String getGroupMembers(int groupID) {
        String result = "SELECT user.user_name, user.user_id FROM user JOIN user_group_info on user_group_info.user_id = user.user_id WHERE user_group_info.group_id = " + groupID + ";";
        return result;
    }

}