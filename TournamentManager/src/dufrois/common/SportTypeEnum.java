package dufrois.common;

/**
 * 
 * 
 * @author Christian Dufrois
 * @version 25.09.2017
 */
public enum SportTypeEnum
{
    /**
     * A standard bare bones game
     */
    BASIC("Simple"),
    
    /**
     * A ping pong game
     */
    PINGPONG("Ping Pong");
    
    private String value;
    
    private SportTypeEnum(String strVal)
    {
        value = strVal;
    }
    
    public String toString()
    {
        return value;
    }
    
    public static String[] getStringValues()
    {
        SportTypeEnum[] obs = SportTypeEnum.values();
        String[] str = new String[obs.length];
        
        for (int i = 0; i < obs.length; i++)
        {
            str[i] = obs[i].toString();
        }
        
        return str;
    }
}
