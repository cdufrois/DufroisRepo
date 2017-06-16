package dufrois.individuals;

public class ByeMatchException extends Exception
{
    
    private static final long serialVersionUID = 1L;
    private String message;
    
    public ByeMatchException()
    {
        this("This match is a bye match for team 1");
    }
    
    public ByeMatchException(String msg)
    {
        message = msg;
    }
}
