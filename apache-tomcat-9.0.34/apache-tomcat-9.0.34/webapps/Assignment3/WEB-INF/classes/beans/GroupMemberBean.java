package beans;

public class GroupMemberBean implements java.io.Serializable
{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private int memberID;
    private String memberName;

    public GroupMemberBean(){}

    public GroupMemberBean(String memberName, int memberID)
    {

        this.memberID = memberID;
        this.memberName = memberName;

    }

    public void setMemberID(int param) 
    {

        this.memberID = param;

    }

    public int getMemberID()
    {

        return memberID;

    }

    public void setMemberName(String param) 
    {

        this.memberName = param;

    }

    public String getMemberName()
    {

        return memberName;

    }

}