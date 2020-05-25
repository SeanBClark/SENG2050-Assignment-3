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
            Connection connection = null;
            try { connection = ConfigBean.getConnection(); } catch (Exception e) { e.printStackTrace(); }

            String emailCountReq = request.getParameter("inputCount");
            int emailCount = Integer.parseInt(emailCountReq);
            
            HttpSession session = request.getSession();
            int groupID = (int) session.getAttribute("groupID");

            for (int i = 0; i < emailCount; i++) {

                if (i == 0) {

                    String emailAddress = request.getParameter("userEmail");
                    String memberInsert = "INSERT INTO user_group_info(user_id, group_id) VALUES ((SELECT user_id FROM user WHERE user_email = '" + emailAddress + "'), " + groupID + ");";
                    try {
                        Statement statement = connection.createStatement();
                        statement.execute(memberInsert);                        
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else {

                    String emailAddress = request.getParameter("userEmail" + i);
                    String memberInsert = "INSERT INTO user_group_info(user_id, group_id) VALUES ((SELECT user_id FROM user WHERE user_email = '" + emailAddress + "'), " + groupID + ");";
                    try {
                        Statement statement = connection.createStatement();
                        statement.execute(memberInsert);                        
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            try {
                connection.close();
                response.sendRedirect("/Assignment3/GroupHome?groupid=" + groupID);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
}