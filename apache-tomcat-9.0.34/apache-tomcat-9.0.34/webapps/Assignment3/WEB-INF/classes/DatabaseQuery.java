import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

// Old Database Query 
// Was used before SQL was correctly placed in beans
// Left here just to be safe
// Should remove all referenced to SQL in controllers if possible
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
    public static String addNewMilestone (int groupID, String milestoneName, String milestoneDateTime) {
        String result = "INSERT INTO group_milestones(group_id, milestone_name, milestone_datetime) VALUES ('"+ groupID + "', '"+ milestoneName +"', '"+ milestoneDateTime +"');";
        return result;
    }
    
    public static String markMilestone (int groupID, String milestoneName, int mark, String date) {
        String result = "UPDATE group_milestones SET milestone_status =" + mark +" WHERE group_id='"+ groupID +"' AND milestone_name ='"+ milestoneName +"' AND milestone_datetime = '"+ date +"';"; 
        return result;
    }
    
    public static String deleteMilestone (int groupID, String milestoneName, String date) {
        String result = "DELETE FROM group_milestones WHERE milestone_name ='"+milestoneName+"' AND group_id='"+groupID+"' AND milestone_datetime = '"+ date +"';";
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

    public static String getUser(String email) {
        return "SELECT user_id, user_email, user_name, user_status, user_type FROM user WHERE user_email = '" + email + "';";
    }

    public static String insertProject(String name, String description, String dueDate, int courseID){
        return "INSERT INTO project(name, description, course_id, due_date) VALUES ('" + name + "', '" + description + "', " + courseID + ", '" + dueDate + "');";
    }

    public static String getSubmittedAssign(int groupID, int courseID) {
        return "SELECT file_mngt.file_id ,file_mngt.file_name" 
                    + " FROM file_mngt"
                    + " JOIN project_assign ON project_assign.group_id = file_mngt.group_id"
                    + " JOIN project ON project.id = project_assign.project_id"
                    + " WHERE file_mngt.group_id = " + groupID + "" 
                    + " AND project.course_id = " + courseID + ""
                    + " AND file_mngt.file_status = 1;";
    }


    public static String removeReview(String userId, String groupId) {
        String result = "DELETE FROM review WHERE user_id='"+ userId +"' AND group_id='"+ groupId +"';";
        return result;
    }
    
    public static String addReview(String userId, String groupId, String score) {
        String result = "INSERT INTO review(user_id, group_id, percent) VALUES ('"+ userId +"', '"+ groupId +"', '"+ score +"');";
        return result;
    }

}