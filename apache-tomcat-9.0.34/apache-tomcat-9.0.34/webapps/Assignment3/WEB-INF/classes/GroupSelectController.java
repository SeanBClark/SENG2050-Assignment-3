import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import beans.*;

// Controller to post and get all group details for a user

@WebServlet(name = "/GroupSelectController", urlPatterns = { "/GroupSelect" })
public class GroupSelectController extends HttpServlet {

    public GroupSelectController() {
        super();
    }

    //  Gets a list of all groups the current user is a part of
    public void doGet(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
            try {
                HttpSession session = request.getSession();
                GroupListBean groupListBean = new GroupListBean();
                session.setAttribute("groupList", (groupListBean.getGroupList((int) session.getAttribute("userID"))));
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/group_select/group_select.jsp");
                dispatcher.forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    // Post request to create a new group and attach it to a project 
    public void doPost(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {

            try {
                HttpSession session = request.getSession();
                GroupBean groupBean = new GroupBean();

                if (groupBean.ifProjectExist(request.getParameter("courseCode"), request.getParameter("projectName")) == true) {
                    
                    groupBean.insertGroup(request.getParameter("groupName"), request.getParameter("groupDesc"));
                    groupBean.attachProject(request.getParameter("courseCode"), request.getParameter("projectName"), request.getParameter("groupName"));
                    groupBean.insertGroupUser((int) session.getAttribute("userID"), request.getParameter("groupName"));
    
                    response.sendRedirect("/Assignment3/GroupHome?groupid=" + groupBean.lookUpGroupID(request.getParameter("groupName")));    
                }
                else {
                    response.sendRedirect("/Assignment3/GroupSelect?exists=false");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
                     
    }
}