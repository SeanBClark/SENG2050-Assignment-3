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
        try {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/admin/admin.jsp");
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
            
            // Controller performs different actions depending on post request params
            // Creates lecturer based on already created account
            if (type.equals("createLect")) {

                try {
                    String email = request.getParameter("searchUser");
                    AdminBean adminBean = new AdminBean();
                    int id = adminBean.getUserID(email);

                    if (id == 0) {
                        System.out.println("Failed to get user ID");
                    }
                    else {
                        adminBean.makeUserLect(id, email);
                    }
                    response.sendRedirect("/Assignment3/Admin?update=true");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            // Enrols a student into a course
            else if (type.equals("enrolStd")) {

                try {
                    String email = request.getParameter("userEnrol");
                    String code = request.getParameter("classCode");

                    AdminBean adminBean = new AdminBean();
                    int exists = adminBean.ifStdExists(email);

                    if (exists == 0) {
                        response.sendRedirect("/Assignment3/Admin?enrolSuccess=false");
                    }
                    else {

                        int courseExists = adminBean.ifCourseExists(code);

                        if (courseExists == 0) {
                            response.sendRedirect("/Assignment3/Admin?courseExists=false");
                        }
                        else {
                            adminBean.enrolStudent(email, code);
                            response.sendRedirect("/Assignment3/Admin?courseExists=true");
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            // Creates a new course
            else if (type.equals("createCourse")) {

                try {
                    String name = request.getParameter("courseName");
                    String desc = request.getParameter("courseDesc");
                    String code = request.getParameter("courseCode");

                    AdminBean adminBean = new AdminBean();
                    int courseExists = adminBean.ifCourseExists(code);

                    if (courseExists == 1) {
                        response.sendRedirect("/Assignment3/Admin?courseExists=true");
                    }
                    else {
                        adminBean.createCourse(name, desc, code);
                        response.sendRedirect("/Assignment3/Admin?courseExists=created");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            // Assigns a lecturer to a course
            else if (type.equals("assignCoordinator")) {

                try {
                    String email = request.getParameter("assignEmail");
                    String code = request.getParameter("assignCode");
    
                    AdminBean adminBean = new AdminBean();
    
                    int lectExists = adminBean.ifLectExists(email);
    
                    if (lectExists == 0) {
                        response.sendRedirect("/Assignment3/Admin?lectExist=false");
                    }
                    else {
    
                        int courseExists = adminBean.ifCourseExists(code);
    
                        if (courseExists == 0) {
                            response.sendRedirect("/Assignment3/Admin?courseExist=false");
                        }
                        else {
    
                            adminBean.insertCourseCord(email, code);
                            response.sendRedirect("/Assignment3/Admin?courseExists=true");
        
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else {
                System.out.println("Post Request Failed");
                // TO DO: Redirect to error page
            }
    }
}