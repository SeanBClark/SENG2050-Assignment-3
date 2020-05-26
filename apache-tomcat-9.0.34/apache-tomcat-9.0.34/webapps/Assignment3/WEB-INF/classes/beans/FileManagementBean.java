package beans;

public class FileManagementBean implements java.io.Serializable
{
    // Private variables
    private static final long serialVersionUID = 1L;
    private String name; 
    private String url;
    private String description; 

    // Constructors
    public FileManagementBean()
    {}

    public FileManagementBean(String name, String url, String description)
    {
        this.name = name;
        this.url = url;
        this.description = description;
    }

    // Getters
    public String getName() 
    {
        return this.name;
    }

    public String getUrl() 
    {
        return this.url;
    }

    public String getDescription() 
    {
        return this.description;
    }

    // Setters
    public void setName(String name)
    {
        this.name = name;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }
}