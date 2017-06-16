package dufrois;

public class PlayTournament {

    public static void main(String[] args) {

        Team team1 = new Team("Team 1");
        Team team2 = new Team("Team 2");
        Team team3 = new Team("Team 3");
        Team team4 = new Team("Team 4");
        Team team5 = new Team("Team 5");
        Team team6 = new Team("Team 6");
        Team team7 = new Team("Team 7");

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
        
        
    }

}
