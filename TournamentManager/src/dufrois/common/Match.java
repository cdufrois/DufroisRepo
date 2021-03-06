package dufrois.common;

/**
 * A match between two teams
 * 
 * @author Christian Dufrois
 *
 * @param <T>
 *            The kind of team that plays the match
 */
public class Match<T extends Team>
{
	private T team1;
	private T team2;
	private int score1;
	private int score2;
	private int matchNum;
	private boolean editableMatch;
	
	/**
	 * The scores are undefined if isByeMatch is true
	 */
	private boolean isByeMatch;
	private String name;
	
	/**
	 * Creates a match given two teams. If its a bye match, set a team's name to
	 * "Bye Match".
	 * 
	 * @param t1
	 *            Team one
	 * @param t2
	 *            Team two
	 */
	public Match(T t1, T t2, boolean edit)
	{
		this(t1, t2, edit, -1);
	}
	
	/**
	 * Creates a match given two teams and a match number. If its a bye match, team
	 * 1 will be the team with the bye match and team 2 will be "Bye Match".
	 * 
	 * @param t1
	 *            Team one
	 * @param t2
	 *            Team two
	 * @param matchNumber
	 *            Match number
	 */
	public Match(T t1, T t2, boolean edit, int matchNumber)
	{
		
		if (t1.getName().equals(ByeMatchException.BYE_MATCH_NAME))
		{
			isByeMatch = true;
			team1 = t2;
			team2 = t1;
		}
		else if (t2.getName().equals(ByeMatchException.BYE_MATCH_NAME))
		{
			isByeMatch = true;
			team1 = t1;
			team2 = t2;
		}
		else
		{
			isByeMatch = false;
			team1 = t1;
			team2 = t2;
		}
		score1 = 0;
		score2 = 0;
		editableMatch = edit;
		matchNum = matchNumber;
		name = null;
	}
	
	/**
	 * Get team one
	 * 
	 * @return Team one
	 */
	public T getTeamOne()
	{
		return team1;
	}
	
	/**
	 * Get team two
	 * 
	 * @return Team two or null if bye match
	 */
	public T getTeamTwo() throws ByeMatchException
	{
		if (isByeMatch)
		{
			throw new ByeMatchException();
		}
		return team2;
	}
	
	/**
	 * Get the score of team one
	 * 
	 * @return Score of team one
	 * @throws ByeMatchException
	 */
	public int getScoreTeamOne() throws ByeMatchException
	{
		if (isByeMatch)
		{
			throw new ByeMatchException();
		}
		return score1;
	}
	
	/**
	 * Get the score of team two
	 * 
	 * @return Score of team two
	 * @throws ByeMatchException
	 */
	public int getScoreTeamTwo() throws ByeMatchException
	{
		if (isByeMatch)
		{
			throw new ByeMatchException();
		}
		return score2;
	}
	
	/**
	 * Add points to team one's score
	 * 
	 * @param points
	 *            Number of points to add
	 * @throws ByeMatchException
	 */
	public boolean addScoreTeamOne(int points) throws ByeMatchException
	{
		if (isByeMatch)
		{
			throw new ByeMatchException();
		}
		score1 = score1 + points;
		return true;
	}
	
	/**
	 * Add points to team two's score
	 * 
	 * @param points
	 *            Number of points to add
	 * @throws ByeMatchException
	 */
	public boolean addScoreTeamTwo(int points) throws ByeMatchException
	{
		if (isByeMatch)
		{
			throw new ByeMatchException();
		}
		score2 = score2 + points;
		return true;
	}
	
	/**
	 * Set team one's score to a new score
	 * 
	 * @param score
	 *            Team one's new score
	 */
	public boolean setScoreTeamOne(int score)
	{
		if (!editableMatch)
			return false;
		score1 = score;
		return true;
	}
	
	/**
	 * Set team two's score to a new score
	 * 
	 * @param score
	 *            Team two's new score
	 */
	public boolean setScoreTeamTwo(int score)
	{
		if (!editableMatch)
			return false;
		score2 = score;
		return true;
	}
	
	/**
	 * The name of the match
	 * 
	 * @return Name of the match, null if it is not set
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * If this match is a bye match
	 * 
	 * @return is a bye match
	 */
	public boolean isByeMatch()
	{
		return isByeMatch;
	}
	
	public boolean isEditableMatch()
	{
		return editableMatch;
	}
	
	/**
	 * A string with the match number if the match was created with one and the two
	 * teams
	 * 
	 * @return String representation of a match
	 */
	@Override
	public String toString()
	{
		// Header
		StringBuilder builder = new StringBuilder();
		if (matchNum != -1)
		{
			builder.append("Match ");
			builder.append(matchNum);
			builder.append(": ");
		}
		// Team one
		builder.append(team1.toString());
		// Connection
		if (!isByeMatch)
		{
			builder.append(" vs ");
		}
		else
		{
			builder.append(" has a ");
		}
		// Team two
		builder.append(team2.toString());
		
		return builder.toString();
	}
}
