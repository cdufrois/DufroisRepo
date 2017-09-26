package dufrois.common;

public enum GameTypeEnum
{
    /**
     * A standard bare bones game
     */
    BASIC ("Simple"),
    
    /**
     * A ping pong game
     */
    PINGPONG ("Ping Pong");
    
private String value;
    
    private GameTypeEnum(String strVal)
    {
        value = strVal;
    }
    
    public String toString()
    {
        return value;
    }
    
    public static String[] getStringValues()
    {
        GameTypeEnum[] obs = GameTypeEnum.values();
        String[] str = new String[obs.length];
        
        for (int i = 0; i < obs.length; i++)
        {
            str[i] = obs[i].toString();
        }
        
        return str;
    }
}
