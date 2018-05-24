package dufrois.tournaments;

import dufrois.common.*;

/**
 * Interface for all kinds of tournaments
 * 
 * @author Christian Dufrois
 *
 * @param <T
 *            extends Team> Kinds of objects that can take part in the
 *            tournament
 */
public interface TournamentInterface
{
	
	/**
	 * Get the Team at the given location
	 * 
	 * @param index
	 *            The location of the Team
	 * @return The team at the location
	 * @throws IndexOutOfBoundsException
	 *             if index is too large or below zero
	 */
	public Team getTeam(int index) throws IndexOutOfBoundsException;
	
	/**
	 * Put all the Teams into array form
	 * 
	 * @return Array of all Teams
	 */
	public Team[] getTeams();
	
	/**
	 * Add a team to the tournament
	 * 
	 * @param team
	 *            The team to be added
	 * @throws TournamentStartedException
	 *             If the tournament has already started
	 */
	public boolean addTeam(Team team) throws TournamentStartedException;
	
	/**
	 * Remove the team from the tournament
	 * 
	 * @param team
	 *            Team to be removed
	 * @return The team being removed
	 * @throws TournamentStartedException
	 *             If the tournament has already started
	 */
	public Team removeTeam(Team team) throws TournamentStartedException;
	
	/**
	 * Creates the matches and allows the the matches to be played
	 * 
	 * @throws TournamentStartedException
	 */
	public void startTournament() throws TournamentStartedException;
	
	/**
	 * Has the tournament started
	 * 
	 * @return Whether the tournament has started or not
	 */
	public boolean hasTournamentStarted();
}
