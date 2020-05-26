import java.io.*;
import java.sql.*;
import java.util.*; 

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import beans.*;

@WebServlet(name = "/FilesController", urlPatterns = { "/ManageFiles" })
public class FilesController extends HttpServlet 
{
    public FilesController() 
    {
        super();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) 
    {
        List<FileManagementBean> folder = new LinkedList<>(); 

        HttpSession session = request.getSession();
        int groupId = (int) session.getAttribute("groupID");

        // prepare query
        String fileQuery = ("SELECT * FROM file_mngt WHERE group_id = " + groupId + ";" ); 

        try(Connection connection = ConfigBean.getConnection(); 
            Statement statement = connection.createStatement();   
            ResultSet result = statement.executeQuery(fileQuery);)       
        { 
            while(result.next())                          
            {
                // create new file 
                FileManagementBean file = new FileManagementBean(); 

                // get file info
                file.setName(result.getString("file_name"));
                file.setUrl(result.getString("file_url"));
                file.setDescription(result.getString("file_desc")); 

                folder.add(file); // add file to folder
            }

            session.setAttribute("folder", folder); // add folder to session 
        }
        catch(SQLException e)
        {
            System.err.println(e.getMessage());
            System.err.println(e.getStackTrace());
        }



        try 
        {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/manage_files/manage_files.jsp");
            dispatcher.forward(request, response);
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) 
    throws ServletException, IOException 
    {
        // get file values
        HttpSession session = request.getSession();
        int groupId = (int) session.getAttribute("groupID");

        String name = request.getParameter("fileName");
        String url = request.getParameter("fileUrl");
        String description = request.getParameter("fileDesc");

        // prepare query 
        String fileQuery = "INSERT INTO file_mngt (group_id, file_name, file_url, file_desc) VALUES (?, ?, ?, ?);"; 

        // connect to database and assign values to file
        try(Connection connection = ConfigBean.getConnection(); 
            PreparedStatement pS = connection.prepareStatement(fileQuery);)
        {
            pS.setInt(1, groupId);
            pS.setString(2, name);
            pS.setString(3, url);
            pS.setString(4, description);

            pS.executeUpdate(); 
        }
        catch(SQLException e)
        {
            System.err.println(e.getMessage());
            System.err.println(e.getStackTrace()); 
        }


        // redirect to list of files jsp page. 
        response.sendRedirect("/Assignment3/ManageFiles");
    }
}