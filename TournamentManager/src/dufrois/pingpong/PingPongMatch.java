package dufrois.pingpong;

import dufrois.common.*;

public class PingPongMatch extends Match<PingPongTeam>
{
	/**
	 * True for team 1 serving, false for team 2
	 */
	private boolean initialServer;
	private int winScore;
	private int serveAmount;
	private boolean winByTwo;
	
	public PingPongMatch(PingPongTeam t1, PingPongTeam t2, boolean edit, int mn)
	{
		super(t1, t2, edit, mn);
		initialServer = true;
		winScore = 21;
		serveAmount = 5;
		winByTwo = true;
	}
	
	public PingPongMatch(PingPongTeam t1, PingPongTeam t2, boolean edit, boolean initial, int win, int serve,
			boolean two)
	{
		super(t1, t2, edit);
		initialServer = initial;
		winScore = win;
		serveAmount = serve;
		winByTwo = two;
	}
	
	@Override
	public boolean addScoreTeamOne(int points) throws ByeMatchException
	{
		if (this.getScoreTeamOne() + points < 0)
		{
			return false;
		}
		super.addScoreTeamOne(points);
		return true;
	}
	
	@Override
	public boolean addScoreTeamTwo(int points) throws ByeMatchException
	{
		if (this.getScoreTeamTwo() + points < 0)
		{
			return false;
		}
		super.addScoreTeamTwo(points);
		return true;
	}
	
	/*
	 * Getters and Setters for all the initial conditions
	 */
	public boolean getInitialServer()
	{
		return initialServer;
	}
	
	public boolean setInitialServer(boolean ser)
	{
		if (!super.isEditableMatch())
			return false;
		initialServer = ser;
		return true;
	}
	
	public int getWinningScore()
	{
		return winScore;
	}
	
	public boolean setWinningScore(int score)
	{
		if (score < 1 || !super.isEditableMatch())
			return false;
		winScore = score;
		return true;
	}
	
	public int getServingAmount()
	{
		return serveAmount;
	}
	
	public boolean setServingAmount(int amount)
	{
		if (amount < 1 || !super.isEditableMatch())
			return false;
		serveAmount = amount;
		return true;
	}
	
	public boolean getWinByTwo()
	{
		return winByTwo;
	}
	
	public boolean setWinByTwo(boolean two)
	{
		if (!super.isEditableMatch())
			return false;
		winByTwo = two;
		return true;
	}
	
	/*
	 * Values that change thorughout the match
	 */
	public boolean getCurrentServer() throws ByeMatchException
	{
		int oneScore = super.getScoreTeamOne();
		int twoScore = super.getScoreTeamTwo();
		
		int server = (oneScore + twoScore) / serveAmount;
		
		if (server % 2 == 0)
			return initialServer;
		else
			return !initialServer;
	}
	
	public PingPongTeam getWinner() throws ByeMatchException
	{
		int oneScore = getScoreTeamOne();
		int twoScore = getScoreTeamTwo();
		int dif = oneScore - twoScore;
		
		int minDif = 2;
		if (!winByTwo)
			minDif = 1;
		
		if (dif >= minDif && oneScore >= winScore)
		{
			return getTeamOne();
		}
		else if (dif <= -minDif && twoScore >= winScore)
		{
			return getTeamTwo();
		}
		return null;
	}
}
