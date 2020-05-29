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
        HttpSession session = request.getSession();
        int groupId = (int) session.getAttribute("groupID");

        // add list of files to session     
        FileManagementBean file = new FileManagementBean(); 
        session.setAttribute("folder", (file.getAllFiles(groupId))); 

        // redirect to jsp manage files page
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
        FileManagementBean file = new FileManagementBean(); 

        // get file values
        HttpSession session = request.getSession();
        int groupId = (int) session.getAttribute("groupID");

        // find out if user wants to add or remove a file
        if((request.getParameter("remove")) != null) // remove file
        {
            // get name of file to be removed 
            String name = request.getParameter("removeFileName");
            int version = Integer.parseInt(request.getParameter("removeFileVersion")); 

            // remove file
            file.removeFile(groupId, name, version);
        }
        else if((request.getParameter("status")) != null) // user wished to submit file or unsubmit it
        {
            // get values
            boolean status; 
            String name = request.getParameter("statusFileName");
            int version = Integer.parseInt(request.getParameter("statusFileVersion")); 

            if((request.getParameter("status").equals("true"))) // user wamts to unsubmit
            {
                status = false; 
            }
            else // user wants to submit
            {
                status = true; 
            }

            // update file status
            file.updateStatus(groupId, name, version, status); 
        }
        else // add file
        {
            if((request.getParameter("newVersion")) != null) // user wants to add new version of a file
            {
                // get values of new version
                String name = request.getParameter("newVersionFileName"); 
                String url = request.getParameter("versionUrl");
                String description = request.getParameter("newVersionDesc");

                // get current value of file
                int latestVersion = ((file.getCurrentVersion(groupId, name)) + 1);

                // add new version
                file.addFile(groupId, name, url, description, latestVersion, false);
            }
            else // user wants to add new file
            {
                // get values from form 
                String name = request.getParameter("fileName");

                if(file.doesNameExist(groupId, name)) // file name already exists, add new version
                {
                    // get values of new file
                    String url = request.getParameter("fileUrl");
                    String description = request.getParameter("fileDesc");

                    // get current value of file
                    int latestVersion = ((file.getCurrentVersion(groupId, name)) + 1);

                    // add new version
                    file.addFile(groupId, name, url, description, latestVersion, false);
                }
                else // no file has this name, therefor we can add to database
                {
                    // get values of new file
                    String url = request.getParameter("fileUrl");
                    String description = request.getParameter("fileDesc");

                    // add new file to database
                    file.addFile(groupId, name, url, description, 1, false);
                }
            }
        }

        // redirect to list of files jsp page. 
        response.sendRedirect("/Assignment3/ManageFiles");
    }
}