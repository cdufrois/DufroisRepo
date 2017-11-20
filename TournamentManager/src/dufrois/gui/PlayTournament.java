package dufrois.gui;

import java.awt.Color;

import javax.swing.JFrame;

import dufrois.common.Player;
import dufrois.teams.PingPongTeam;
import dufrois.teams.Team;
import dufrois.tournaments.RoundRobinTourn;
import dufrois.tournaments.TournamentStartedException;

/**
 * 
 * 
 * @author Christian Dufrois
 * @version 25.09.2017
 */
public class PlayTournament
{
    
    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Tournament Manager");
        frame.setLocation(WindowVars.FRAME_LEFT, WindowVars.FRAME_TOP);
        frame.setSize(WindowVars.WIDTH, WindowVars.HEIGHT);
        frame.setBackground(Color.WHITE);
        frame.setContentPane(new HomePanel(frame));
        frame.setFocusable(true);
        frame.setVisible(true);
    }
    
    private void helper()
    {
        // Make teams
        Team team1 = new Team("1");
        Team team2 = new Team("2");
        Team team3 = new Team("3");
        Team team4 = new Team("4");
        Team team5 = new Team("5");
        Team team6 = new Team("6");
        Team team7 = new Team("7");
        
        // Add teams to tournament
        RoundRobinTourn<Team> tourn = new RoundRobinTourn<Team>(team1.getClass(), "Test", 2);
        try
        {
            tourn.addTeam(team1);
            tourn.addTeam(team2);
            tourn.addTeam(team3);
            tourn.addTeam(team4);
            tourn.addTeam(team5);
            tourn.addTeam(team6);
            tourn.addTeam(team7);
        }
        catch (TournamentStartedException e)
        {
            // Tournament all ready started
        }
        
        // Start tournament
        try
        {
            tourn.startTournament();
        }
        catch (TournamentStartedException e)
        {
        }
        
        System.out.println(tourn.toString());
        
    }
    
}
