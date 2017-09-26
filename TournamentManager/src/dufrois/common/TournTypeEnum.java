package dufrois.common;

public enum TournTypeEnum
{
    /**
     * A single match
     */
    MATCH ("Single Match"),
    
    /**
     * Round robin tournament: every team plays every other team
     */
    ROUNDROBIN ("Round Robin");
    
    private String value;
    
    private TournTypeEnum(String strVal)
    {
        value = strVal;
    }
    
    public String toString()
    {
        return value;
    }
    
    public static String[] getStringValues()
    {
        TournTypeEnum[] obs = TournTypeEnum.values();
        String[] str = new String[obs.length];
        
        for (int i = 0; i < obs.length; i++)
        {
            str[i] = obs[i].toString();
        }
        
        return str;
    }
}
