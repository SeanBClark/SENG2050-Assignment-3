package beans;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CourseListBean implements java.io.Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String courseName;
    private String courseCode;
    private int courseID;

    public CourseListBean(){}

    public CourseListBean(String courseName, String courseCode, int courseID)
    {

        this.courseName = courseName;
        this.courseCode = courseCode;
        this.courseID = courseID;

    }

    public void setCourseName(String param) 
    {

        this.courseName = param;

    }

    public String getCourseName()
    {

        return courseName;

    }

    public void setCourseCode(String param) 
    {

        this.courseCode = param;

    }

    public String getCourseCode()
    {

        return courseCode;

    }

    public void setCourseId(int param) 
    {

        this.courseID = param;

    }

    public int getCourseId()
    {

        return courseID;

    }

    public List<CourseListBean> getCourses(int lectID) {

        List<CourseListBean> list = new ArrayList<>();

        try {

            Connection connection = ConfigBean.getConnection();
            ResultSet resultSet = DatabaseQuery.getResultSet(getLectCourses(lectID), connection);

            while (resultSet.next()) {

                CourseListBean bean = new CourseListBean();
                bean.setCourseId(resultSet.getInt("course_id"));
                bean.setCourseName(resultSet.getString("name"));
                bean.setCourseCode(resultSet.getString("course_code"));
                list.add(bean);

            }

            connection.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;

    }

    public static String getLectCourses(int lectID) {
        return "SELECT course_cord.course_id, course.name, course.course_code FROM course_cord" 
                                + " JOIN course ON course.id = course_cord.course_id"
                                + " WHERE course_cord.lect_id = " + lectID + ";";
    }
}