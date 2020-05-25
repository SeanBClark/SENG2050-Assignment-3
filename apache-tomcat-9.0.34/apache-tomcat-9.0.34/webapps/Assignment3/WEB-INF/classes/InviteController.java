import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import beans.*;

@WebServlet(name = "/InviteController", urlPatterns = { "/InviteMembers" })
public class InviteController extends HttpServlet {

    public InviteController() {
        super();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        Connection connection = null;
        try { connection = ConfigBean.getConnection(); } catch (Exception e) { e.printStackTrace(); }

        HttpSession session = request.getSession();
        int groupID = (int) session.getAttribute("groupID");

        System.out.println("Group ID = " + groupID);
        
        try {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/group_invite/group_invite.jsp");
            connection.close(); 
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
    }
}