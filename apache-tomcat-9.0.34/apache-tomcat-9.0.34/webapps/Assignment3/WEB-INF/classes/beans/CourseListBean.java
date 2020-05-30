package beans;

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
}