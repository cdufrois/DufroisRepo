package dufrois.individuals;

public class NamingException extends Exception
{
    
    private static final long serialVersionUID = 1L;
    private String message;
    
    public NamingException()
    {
        this("Invalid Name");
    }
    
    public NamingException(String msg)
    {
        message = msg;
    }
}
