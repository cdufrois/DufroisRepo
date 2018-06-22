package dufrois.tournaments;

/**
 * 
 * 
 * @author Christian Dufrois
 * @version 25.09.2017
 */
public enum TournamentTypeEnum
{
	/**
	 * A single match
	 */
	MATCH("Single Match"),
	
	/**
	 * Round robin tournament: every team plays every other team
	 */
	ROUNDROBIN("Round Robin");
	
	private String value;
	
	private TournamentTypeEnum(String strVal)
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
	
	private static String[] values = TournamentTypeEnum.makeStrArr();
	
	private static String[] makeStrArr()
	{
		TournamentTypeEnum[] obs = TournamentTypeEnum.values();
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
	
	public static TournamentTypeEnum getEnum(String str)
	{
		for (int i = 0; i < values.length; i++)
		{
			if (str == values[i])
			{
				return TournamentTypeEnum.values()[i];
			}
		}
		return null;
	}
}
