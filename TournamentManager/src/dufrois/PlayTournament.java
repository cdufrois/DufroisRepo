package dufrois;

import java.util.ArrayList;
import java.util.Random;

import dufrois.individuals.Team;
import dufrois.tournaments.RoundRobinTourn;
import dufrois.tournaments.TournamentStartedException;

public class PlayTournament {
    
    private static ArrayList<Team> teams;
    private static int numTeams;
    
    public static void main(String[] args) {
        
        Team team1 = new Team("1");
        Team team2 = new Team("2");
        Team team3 = new Team("3");
        Team team4 = new Team("4");
        Team team5 = new Team("5");
        Team team6 = new Team("6");
        Team team7 = new Team("7");
        Team byeTeam = new Team("8");
        teams = new ArrayList<Team>(8);
        teams.add(team1);
        teams.add(team2);
        teams.add(team3);
        teams.add(team4);
        teams.add(team5);
        teams.add(team6);
        teams.add(team7);
        teams.add(byeTeam);
        numTeams = 8;
        
        RoundRobinTourn<Team> tourn = new RoundRobinTourn<Team>("Test Tournament", 2);
        try {
            tourn.addTeam(team1);
            tourn.addTeam(team2);
            tourn.addTeam(team3);
            tourn.addTeam(team4);
            tourn.addTeam(team5);
            tourn.addTeam(team6);
            tourn.addTeam(team7);
        } catch (TournamentStartedException e) {
            // Tournament all ready started
        }
        
        try {
            tourn.startTournament();
        } catch (TournamentStartedException e) {
        }
        
        System.out.println(tourn.toString());
    }
    
    /**
     * Randomize the order of the teams
     */
    private static void randomizeTeams() {
        Random rand = new Random();
        for (int i = 0; i < numTeams; i++) {
            int num = rand.nextInt(numTeams - i);
            if (i != num) {
                Team temp = teams.get(num);
                teams.set(num, teams.get(i));
                teams.set(i, temp);
            }
        }
    }
    
    /**
     * 1 2 3 4     1 8 2 3     1 7 8 2
     * | | | | --> | | | | --> | | | |
     * 8 7 6 5     7 6 5 4     6 5 4 3
     * 
     * 1 2 3 4 5 6 7 8 --> 1 8 2 3 4 5 6 7
     * 
     * Rotate all the teams but the first team to the right.
     */
    private static void rotateTeams() {
        if (teams.size() == 2) {
            return;
        }
        
        // 1 2 3 4 5 6 7 8 --> 
        Team temp = teams.get(teams.size() - 1); // Temp Team: 8
        
        for (int i = teams.size() - 1; i > 1; i--) {
            teams.set(i, teams.get(i - 1));
        }
        teams.set(1, temp);
    }
    
}
