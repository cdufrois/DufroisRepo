package dufrois.common;

import java.util.Random;

public class ByeMatchException extends Exception
{
	public static final String BYE_MATCH_NAME = makeRandomByeMatchName();
	
	private static String makeRandomByeMatchName()
	{
		Random rand = new Random();
		
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < 30; i++)
		{
			builder.append(rand.nextInt(10));
		}
		
		return builder.toString();
	}
	
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
	
	public String getMessage()
	{
		return message;
	}
}
