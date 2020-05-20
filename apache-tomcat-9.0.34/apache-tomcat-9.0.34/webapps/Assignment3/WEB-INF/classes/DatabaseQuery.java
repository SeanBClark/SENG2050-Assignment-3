import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseQuery {

    public static ResultSet getResultSet(String query, Connection connection) {

        ResultSet resultSet = null;

        try {
            
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            System.out.println("Query Success");

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
            System.out.println("Query Success. Result = " + result);

            while(resultSet.next()) {
                if(resultSet.getInt(1) == 1) {
                    check = 1;
                    result = true;
                }
            }
            System.out.println("Result = " + result);
            
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

}