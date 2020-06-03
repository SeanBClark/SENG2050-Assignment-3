import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import beans.*;

// Controller to invite a new user to a group

@WebServlet(name = "/InviteController", urlPatterns = { "/InviteMembers" })
public class InviteController extends HttpServlet {

    public InviteController() {
        super();
    }

    // gets JSP
    public void doGet(HttpServletRequest request, HttpServletResponse response) {        
        try {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/group_invite/group_invite.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Post request to insert users into a group
    // Group can had many members in one go
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

                    String email = request.getParameter("userEmail");
                    try {
                        
                        GroupBean groupBean = new GroupBean();
                        groupBean.insertGroupMember(email, groupID);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else {

                    String email = request.getParameter("userEmail" + i);
                    try {
                        
                        GroupBean groupBean = new GroupBean();
                        groupBean.insertGroupMember(email, groupID);

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