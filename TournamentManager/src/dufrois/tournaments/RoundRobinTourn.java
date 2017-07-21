package dufrois.tournaments;

import java.util.ArrayList;
import java.util.Random;

import dufrois.individuals.Team;
import dufrois.list.TeamList;
import dufrois.matches.Match;

/**
 * A tournament in a round robin format
 * 
 * @author Christian Dufrois
 * @version 2017.04.17
 *
 * @param <T> Generic type that is a team
 */
public class RoundRobinTourn<T extends Team> implements TournamentInterface<T>
{
    
    private TeamList<T> teams;
    private String name;
    private int runThroughs;
    private boolean started;
    private ArrayList<Match<T>[]> games;
    
    /**
     * Constructor for the round robin tournament format
     * 
     * @param name Name identifier for the tournament
     * @param rnThrghs Number
     */
    public RoundRobinTourn(Class<? extends Team> class1, String name, int rnThrghs)
    {
        started = false;
        teams = new TeamList<T>(class1);
        runThroughs = rnThrghs;
        this.name = name;
    }
    
    /**
     * Number of teams in the tournament
     * 
     * @return Number of teams
     */
    public int getNumTeams()
    {
        return teams.getSize();
    }
    
    /**
     * The team at the given index
     * 
     * @param index The index of the team to return
     * @return The team at the given index
     * @throws IndexOutOfBoundsException if index is too large or below zero
     */
    @Override
    public T getTeam(int index) throws IndexOutOfBoundsException
    {
        return teams.getTeam(index);
    }
    
    /**
     * An array of all the teams
     * 
     * @return An array of all the teams
     */
    @Override
    public T[] getTeams()
    {
        return teams.toArray();
    }
    
    /**
     * The name identifier of the tournament
     * 
     * @return The name of the tournament
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * Add a team to the tournament
     * 
     * @param team The team to be added
     * @throws TournamentStartedException If the tournament has already started
     */
    @Override
    public void addTeam(T team) throws TournamentStartedException
    {
        if (started)
        {
            throw new TournamentStartedException("Adding a team when tournament started");
        }
        teams.addTeam(team);
    }
    
    /**
     * Remove the team from the tournament
     * 
     * @param team Team to be removed
     * @return The team being removed
     * @throws TournamentStartedException If the tournament has already started
     */
    public T removeTeam(T team) throws TournamentStartedException
    {
        if (started)
        {
            throw new TournamentStartedException();
        }
        return teams.removeTeam(team);
    }
    
    public boolean hasTournamentStarted()
    {
        return started;
    }
    
    /**
     * Create the matches for the game
     * 
     * @throws TournamentStartedException
     */
    @SuppressWarnings("unchecked")
    @Override
    public void startTournament() throws TournamentStartedException
    {
        // Only start game once
        if (started)
        {
            throw new TournamentStartedException();
        }
        started = true;
        
        // Important variables
        int numTeams = teams.getSize();
        
        // Assure even number of teams
        if (numTeams % 2 == 1)
        {
            teams.addTeam((T) new Team("Bye Match"));
            numTeams++;
        }
        randomizeTeams();
        
        int wpr = numTeams - 1; // Weeks per round
        int mpw = numTeams / 2; // Matches per week
        games = new ArrayList<Match<T>[]>(runThroughs * wpr);
        
        // Create all the matches
        // Each round
        for (int r = 0; r < runThroughs; r++)
        {
            // Each week
            for (int w = 0; w < wpr; w++)
            {
                // Each match
                Match<T>[] week = new Match[mpw]; // Array of matches
                for (int m = 0; m < mpw; m++)
                {
                    // Correct number based on which round, week, and match
                    int matchNum = (r * wpr * mpw) + (w * mpw) + (m + 1);
                    T tOne = (T) teams.getTeam(m);
                    T tTwo = (T) teams.getTeam(numTeams - m - 1);
                    week[m] = new Match<T>(tOne, tTwo, matchNum);
                }
                
                games.add(week);
                rotateTeams();
            }
        }
    }
    
    /**
     * Randomize the order of the teams
     */
    private void randomizeTeams()
    {
        int numTeams = teams.getSize();
        Random rand = new Random();
        
        // Go through list of teams
        for (int i = numTeams - 1; i >= 0; i--)
        {
            // Random number between 0 and last index
            int num = rand.nextInt(i + 1);
            
            if (i != num)
            {
                T temp = teams.getTeam(num);
                teams.setTeam(num, teams.getTeam(i));
                teams.setTeam(i, temp);
            }
        }
    }
    
    /**
     * Rotate all the teams but the first team to the right.
     */
    private void rotateTeams()
    {
        if (teams.getSize() == 2)
        {
            return;
        }
        
        T temp = teams.getTeam(teams.getSize() - 1);
        for (int i = teams.getSize() - 1; i > 1; i--)
        {
            teams.setTeam(i, teams.getTeam(i - 1));
        }
        teams.setTeam(1, temp);
    }
    
    /**
     * Get the match at the given week and position
     * 
     * @param week The week of the tournament
     * @param pos The position in the week for the match
     * @return The match at the given values
     */
    public Match<T> getMatch(int week, int pos)
    {
        return games.get(week)[pos];
    }
    
    /**
     * Returns a string representation of all the matches If the tournament hasn't started, it returns the name of the
     * tournament
     * 
     * @return String representation of the tournament
     */
    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder("Tournament: ");
        builder.append(name);
        if (!started)
        {
            for (T team : teams.toArray())
            {
                builder.append("\n");
                builder.append(team.toString());
            }
        }
        else
        {
            int numTeams = teams.getSize();
            for (int r = 0; r < runThroughs; r++) // Each round
            {
                builder.append("\nRun Through ");
                builder.append(r + 1);
                for (int w = 0; w < numTeams - 1; w++) // Each week
                {
                    builder.append("\n  Week ");
                    builder.append(r * (numTeams - 1) + w + 1);
                    
                    Match<T>[] week = games.get(r * (numTeams - 1) + w);
                    for (Match<T> match : week) // Each match
                    {
                        builder.append("\n    ");
                        builder.append(match.toString());
                    }
                }
            }
        }
        return builder.toString();
    }
}
