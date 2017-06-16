package dufrois.tournaments;

import dufrois.individuals.Team;

/**
 * Interface for all kinds of tournaments
 * 
 * @author Christian Dufrois
 *
 * @param <T
 *            extends Team> Kinds of objects that can take part in the
 *            tournament
 */
public interface TournamentInterface<T extends Team> {
    
    /**
     * Get the Team at the given location
     * 
     * @param index
     *            The location of the Team
     * @return The team at the location
     */
    public T getTeam(int index);
    
    /**
     * Put all the Teams into array form
     * 
     * @return Array of all Teams
     */
    public T[] getTeams();
    
    /**
     * Add a team to the tournament
     * 
     * @param team
     *            The team to be added
     * @throws TournamentStartedException
     *             If the tournament has already started
     */
    public void addTeam(T team) throws TournamentStartedException;
    
    /**
     * 
     * 
     * @throws TournamentStartedException
     */
    public void startTournament() throws TournamentStartedException;
}
