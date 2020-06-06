import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import beans.*;

@WebServlet(name = "/PeerReviewController", urlPatterns = { "/PeerReview" })
public class PeerReviewController extends HttpServlet {
    
    public PeerReviewController () {
        super();
    }
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        Connection connection = null;
        try { connection = ConfigBean.getConnection(); } catch (Exception e) { e.printStackTrace(); }
        

        ResultSet groupInfoRS = null;
        ResultSet groupMemberRS = null;
        
        List <GroupBean> groupInfo = new ArrayList<>();
        List <GroupMemberBean> groupMemberList = new ArrayList<>();
        
        HttpSession session = request.getSession();
        int groupID = (int) session.getAttribute("groupID");
        
        String error = request.getParameter("error");
        if (error != null && !error.equals(""))
        {
            session.setAttribute("error",error);
        }
        
        String getGroupInfoQuery = "SELECT group_id, group_name, group_description FROM group_info WHERE group_id =  " + groupID + "";
        
        groupInfoRS = DatabaseQuery.getResultSet(getGroupInfoQuery, connection);

        try {       
            while (groupInfoRS.next()) {

                session = request.getSession();

                GroupBean groupBean = new GroupBean();
                groupBean.setGroupId(groupInfoRS.getInt("group_id"));
                groupBean.setGroupName(groupInfoRS.getString("group_name"));
                groupBean.setGroupDesc(groupInfoRS.getString("group_description"));
                groupInfo.add(groupBean);
                session.setAttribute("groupBean", groupBean);

            }
            session.setAttribute("groupInfo", groupInfo);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        String getGroupMembers = "SELECT user.user_name, user.user_id FROM user JOIN user_group_info on user_group_info.user_id = user.user_id WHERE user_group_info.group_id = " + groupID + ";";

        groupMemberRS = DatabaseQuery.getResultSet(getGroupMembers, connection);
        
        try {

            while (groupMemberRS.next()) {

                session = request.getSession();

                GroupMemberBean groupMemberBean = new GroupMemberBean();
                groupMemberBean.setMemberID(groupMemberRS.getInt("user.user_id"));
                groupMemberBean.setMemberName(groupMemberRS.getString("user.user_name"));
                groupMemberList.add(groupMemberBean);
                session.setAttribute("groupMemberBean", groupMemberBean);

            }
            session.setAttribute("groupMemberList", groupMemberList);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        try {
                groupInfoRS.close();
                groupMemberRS.close();
                connection.close(); 
            } catch (Exception e) {
                e.printStackTrace();
            }
        

        try {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/peer_review/peer_review.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    
      public void doPost(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
        Connection connection = null;
        try { connection = ConfigBean.getConnection(); } catch (Exception e) { e.printStackTrace(); }
        
        String members = request.getParameter("members");
        String groupId = request.getParameter("groupId");
        int m = Integer.parseInt(members);
        List<String> percentages = new ArrayList<String>();
        List<String> ids = new ArrayList<String>();
        int error = 0;
        for (int i = 0; i < m; i++)
        {
            percentages.add(request.getParameter("reviewFor" + i));
            ids.add(request.getParameter("memberIdFor" + i));
            error += Integer.parseInt(request.getParameter("reviewFor" + i));
            
        }
        if (error > 100)
        {
            response.sendRedirect("/Assignment3/PeerReview?error=1");
        }
        Iterator<String> idIterator = ids.iterator();
        Iterator<String> percentIterator = percentages.iterator();
        if(percentages.size() == m && !(error > 100))
        {
            while (idIterator.hasNext())
            {
                String id = idIterator.next();
                String percent = percentIterator.next();
                System.out.println("ID: "+ id + "\nPercent: " + percent);
                String clearReview = DatabaseQuery.removeReview(id, groupId);
                String addReview = DatabaseQuery.addReview(id, groupId, percent);
                try {
                    connection = ConfigBean.getConnection();
                    Statement statement = connection.createStatement();
                    statement.execute(clearReview);
                    statement.execute(addReview);
                    statement.close();
                    connection.close(); 
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
            }
            response.sendRedirect("/Assignment3/PeerReview");
        }
        
      }
    
}
