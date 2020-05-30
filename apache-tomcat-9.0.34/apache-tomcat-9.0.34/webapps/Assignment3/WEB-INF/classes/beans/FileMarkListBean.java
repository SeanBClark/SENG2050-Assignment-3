package beans;

public class FileMarkListBean implements java.io.Serializable
{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String fileName;
    private int fileID;

    public FileMarkListBean(){}

    public FileMarkListBean(String fileName, String groupDesc, int fileID)
    {

        this.fileName = fileName;
        this.fileID = fileID;

    }

    public void setFileName(String param) 
    {

        this.fileName = param;

    }

    public String getFileName()
    {

        return fileName;

    }

    public void setFileID(int param) 
    {

        this.fileID = param;

    }

    public int getFileID()
    {

        return fileID;

    }




}