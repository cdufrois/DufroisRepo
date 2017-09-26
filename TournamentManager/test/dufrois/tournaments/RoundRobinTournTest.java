package dufrois.tournaments;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import dufrois.teams.Team;
import dufrois.tournaments.RoundRobinTourn;

public class RoundRobinTournTest
{
    RoundRobinTourn<Team> tourn;
    
    @Before
    public void setUp()
    {
        Team team1 = new Team("1");
        Team team2 = new Team("2");
        Team team3 = new Team("3");
        
        tourn = new RoundRobinTourn<Team>(team1.getClass(), "Test Tournament", 2);
        try
        {
            tourn.addTeam(team1);
            tourn.addTeam(team2);
            tourn.addTeam(team3);
        }
        catch (Exception e)
        {
            
        }
    }
    
    @Test
    public void testGetTeams()
    {
        Team[] teams = tourn.getTeams();
        
    }
}
