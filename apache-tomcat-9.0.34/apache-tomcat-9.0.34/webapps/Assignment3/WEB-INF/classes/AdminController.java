import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import beans.*;

@WebServlet(name = "/AdminController", urlPatterns = { "/Admin" })
public class AdminController extends HttpServlet {

    public AdminController() {
        super();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        // Connection connection = null;
        // try { connection = ConfigBean.getConnection(); } catch (Exception e) { e.printStackTrace(); }

        try {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/admin/admin.jsp");
            // connection.close(); 
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
            Connection connection = null;
            try { connection = ConfigBean.getConnection(); } catch (Exception e) { e.printStackTrace(); }

            ResultSet userIdRS = null;
            int userId = 0;
            String type = request.getParameter("param");
            
            if (type.equals("createLect")) {

                // TO DO: Check if user stdExists at all
                String userEmail = request.getParameter("searchUser");
                String userIDQuery = "SELECT user_id FROM user where user_email = '" + userEmail + "';";
                try {
                    userIdRS = DatabaseQuery.getResultSet(userIDQuery, connection);
                    while (userIdRS.next()) {
                        userId = userIdRS.getInt("user_id");
                    }
                    
                    String updateUserQuery = "UPDATE user SET user_type = 'lect' WHERE user_id = " + userId + " AND user_email = '" + userEmail + "';";
                    Statement  preparedStatement = connection.createStatement();
                    preparedStatement.executeUpdate(updateUserQuery);

                    connection.close();
                    response.sendRedirect("/Assignment3/Admin");
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else if (type.equals("enrolStd")) {

                String userEmail = request.getParameter("userEnrol");
                String courseID = request.getParameter("classCode");                

                try {

                    //Checks if Student Exists in Database
                    ResultSet ifStdExists = null;
                    int stdExists = 0;

                    ifStdExists = DatabaseQuery.getResultSet(DatabaseQuery.ifStdExists(userEmail), connection);
                    while (ifStdExists.next()){
                        stdExists = ifStdExists.getInt(1);                    
                    }

                    if (stdExists == 0) {
                        connection.close();
                        response.sendRedirect("/Assignment3/Admin?enrolSuccess=false");
                    }
                    else {

                        //Checks if Course Exists
                        ResultSet ifCourseExists = null;
                        int courseExists = 0;

                        ifCourseExists = DatabaseQuery.getResultSet(DatabaseQuery.ifCourseExists(courseID), connection);
                        while (ifCourseExists.next()){
                            courseExists = ifCourseExists.getInt(1);                    
                        }

                        if (courseExists == 0) {
                            connection.close();
                            response.sendRedirect("/Assignment3/Admin?courseExists=false");
                        }
                        else {

                            String enrolStd = DatabaseQuery.enrollStudentQuery(userEmail, courseID);
                            Statement statement = connection.createStatement();
                            statement.execute(enrolStd);

                            connection.close();
                            response.sendRedirect("/Assignment3/Admin?courseExists=true");
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
    }
}