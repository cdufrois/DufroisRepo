package dufrois.teams;

import dufrois.common.Player;

public class PingPongTeam extends Team
{
    
    public PingPongTeam(String name, Player p1, Player p2)
    {
        super(name);
        super.addPlayer(p1);
        super.addPlayer(p2);
    }
    
    public PingPongTeam(String name, int seed, Player p1, Player p2)
    {
        super(name, seed);
        super.addPlayer(p1);
        super.addPlayer(p2);
    }
    
    public Player getPlayerOne()
    {
        return super.getPlayer(0);
    }
    
    public Player getPlayerTwo()
    {
        return super.getPlayer(1);
    }
}
