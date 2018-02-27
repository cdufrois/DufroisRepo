package dufrois.teams;

import dufrois.common.Player;

public class PingPongTeam extends Team
{
    public PingPongTeam(String name)
    {
        super(name);
    }
    
    public PingPongTeam(String name, Player p1, Player p2)
    {
        super(name);
        addPlayer(p1);
        addPlayer(p2);
    }
    
    public PingPongTeam(String name, int seed, Player p1, Player p2)
    {
        super(name, seed);
        addPlayer(p1);
        addPlayer(p2);
    }
    
    public Player getPlayerOne()
    {
        return super.getPlayer(0);
    }
    
    public Player getPlayerTwo()
    {
        return super.getPlayer(1);
    }
    
    public void setPlayerOne(Player p)
    {
        super.setPlayer(0, p);
    }
    
    public void setPlayerTwo(Player p)
    {
        super.setPlayer(1, p);
    }
}
