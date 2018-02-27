package dufrois.gui.tourntypes;

import javax.swing.JPanel;

import dufrois.teams.Team;

public interface ReceiveTeamInterface
{
    /**
     * Allow the panel that is creating/modifying a team to give the team back to the original panel. True if the team
     * was accepted from the correct panel, false otherwise.
     * 
     * @param input The panel that is delivering the team
     * @param team The team that is being delivered
     * @return True if team was accepted from the correct panel, false if not
     */
    public boolean deliverTeam(JPanel input, Team team);
}
