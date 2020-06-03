import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import beans.*;

@WebServlet(name = "/AppointmentController", urlPatterns = { "/ManageAppointments" })
public class AppointmentController extends HttpServlet 
{

    public AppointmentController() 
    {
        super();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) 
    {
        HttpSession session = request.getSession();
        int groupId = (int) session.getAttribute("groupID");

        // add list of appointments to session     
        UpcomingAppBean appointment = new UpcomingAppBean(); 
        session.setAttribute("schedule", (appointment.getAllAppointments(groupId))); 

        // redirect to jsp manage appointments page
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/appointment/appointment.jsp");
        try 
        {
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
        HttpSession session = request.getSession();

        boolean redirectSent = false; 

        int groupId = (int) session.getAttribute("groupID"); // used to call other databse functions in bean 

        // add list of appointments to session     
        UpcomingAppBean appointment = new UpcomingAppBean(); 

        // find out if user wants to add or remove a file
        if((request.getParameter("remove")) != null) // remove file
        {
            // get name of file to be removed 
            String name = request.getParameter("removeAppName");

            // call beaan function to remove appointment
            appointment.removeAppointment(groupId, name);
        }
        else if((request.getParameter("status")) != null) // group has completed meeting
        {
            // get values
            boolean status; 
            String name = request.getParameter("statusAppName");

            if((request.getParameter("status").equals("true"))) // user wants to uncomplete meeting 
            {
                status = false; 
            }
            else // user has completed meeing  
            {
                status = true; 
            }

            // update appointment status
            appointment.updateStatus(groupId, name, status); 
        }
        else // user wants to add appointment to database 
        {
            String name = request.getParameter("appName"); // get name to check against database for other appointments with the same name

            if(appointment.doesNameExist(groupId, name)) // appointment name already exists, notify user
            {
                response.sendRedirect("/Assignment3/ManageAppointments?exists=true");
                redirectSent = true; 
            }
            else // new appointment and ni appointment exists with such name
            {
                // get values from form 
                String description = request.getParameter("appDesc");

                String date = request.getParameter("appDate");
                String time = request.getParameter("appTime");
                String dateTime = date + " " + time + ":00"; // convert date a time into from that database will understand


                // add new appointment
                appointment.addAppointment(groupId, name, dateTime, description, false);
            }
        }

        if(redirectSent == false)
        {
            // redirect to list of appointments jsp page. 
            response.sendRedirect("/Assignment3/ManageAppointments");
        }
    }
}