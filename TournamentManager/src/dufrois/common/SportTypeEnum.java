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
	 * A ping pong game
	 */
	PINGPONG("Ping Pong"),
	
	/**
	 * A standard bare bones game
	 */
	BASIC("Basic");
	
	private String value;
	
	private SportTypeEnum(String strVal)
	{
		value = strVal;
	}
	
	public String toString()
	{
		return value;
	}
	
	/*
	 * Class variables / methods
	 */
	
	private static String[] values = SportTypeEnum.makeStrArr();
	
	private static String[] makeStrArr()
	{
		SportTypeEnum[] obs = SportTypeEnum.values();
		String[] str = new String[obs.length];
		
		for (int i = 0; i < obs.length; i++)
		{
			str[i] = obs[i].toString();
		}
		
		return str;
	}
	
	public static String[] getStringValues()
	{
		String[] str = new String[values.length];
		
		for (int i = 0; i < str.length; i++)
		{
			str[i] = values[i];
		}
		
		return str;
	}
	
	public static SportTypeEnum getEnum(String str)
	{
		for (int i = 0; i < values.length; i++)
		{
			if (str == values[i])
			{
				return SportTypeEnum.values()[i];
			}
		}
		return null;
	}
}
