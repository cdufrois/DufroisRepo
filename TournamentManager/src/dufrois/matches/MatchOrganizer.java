package dufrois.matches;

import dufrois.common.SportTypeEnum;
import dufrois.teams.PingPongTeam;
import dufrois.teams.Team;

public class MatchOrganizer
{
    private Match<?> match;
    private SportTypeEnum sport;
    
    public MatchOrganizer(SportTypeEnum s)
    {
        sport = s;
        match = null;
    }
    
    public MatchOrganizer(SportTypeEnum s, Team t1, Team t2, boolean editable, int num)
    {
        sport = s;
        if (!checkTeams(t1, t2))
            sport = SportTypeEnum.BASIC;
        
        switch (s)
        {
        case BASIC:
            initBasicMatch(t1, t2, editable, num);
            break;
        case PINGPONG:
            initPingPongMatch((PingPongTeam) t1, (PingPongTeam) t2, editable, num);
            break;
        default:
            initBasicMatch(t1, t2, editable, num);
            break;
        }
    }
    
    private boolean checkTeams(Team t1, Team t2)
    {
        Class<? extends Team> clazz = MatchOrganizer.getTeamClass(sport);
        if (clazz != t1.getClass() || clazz != t2.getClass())
            return false;
        return true;
    }
    
    /*
     * Static classes
     */
    public static Class<? extends Team> getTeamClass(SportTypeEnum s)
    {
        return getByeMatchTeam(s).getClass();
    }
    
    public static Team getByeMatchTeam(SportTypeEnum s)
    {
        switch (s)
        {
        case BASIC:
            return new Team("Bye Match");
        case PINGPONG:
            return new PingPongTeam("Bye Match");
        default:
            return new Team("Bye Match");
        }
    }
    
    /*
     * Initialize correct match
     */
    public boolean initBasicMatch(Team t1, Team t2, boolean edit, int num)
    {
        if (sport != SportTypeEnum.BASIC)
        {
            return false;
        }
        match = new Match<Team>(t1, t2, edit, num);
        return true;
    }
    
    public boolean initPingPongMatch(PingPongTeam t1, PingPongTeam t2, boolean edit, int num)
    {
        if (sport != SportTypeEnum.PINGPONG)
        {
            return false;
        }
        
        match = new PingPongMatch(t1, t2, edit, num);
        return true;
    }
    
    public boolean initPingPongMatch(PingPongTeam t1, PingPongTeam t2, boolean edit, boolean initial, int win, int serve, boolean two)
    {
        if (sport != SportTypeEnum.PINGPONG)
        {
            return false;
        }
        
        match = new PingPongMatch(t1, t2, edit, initial, win, serve, two);
        return true;
    }
    
    /*
     * Get the match in basic form
     */
    public Match<? extends Team> getMatch()
    {
        return match;
    }
    
    public String toString()
    {
        return match.toString();
    }
}
