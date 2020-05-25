package beans;

public class FileManagementBean implements java.io.Serializable
{
    // Private variables
    private static final long serialVersionUID = 1L;
    private String fileName; 
    private String description; 
    private int version; 

    // Constructors
    public FileManagementBean()
    {

    }

    public FileManagementBean(String fileName, String description, int version)
    {
        this.fileName = fileName;
        this.description = description;
        this.version = version;
    }

    // Getters
    public String getFileName() 
    {
        return this.fileName;
    }

    public String getDescription() 
    {
        return this.fileName;
    }

    public int getVersion() 
    {
        return this.version;
    }

    // Setters
    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public void setVersion(int version)
    {
        this.version = version;
    }
}