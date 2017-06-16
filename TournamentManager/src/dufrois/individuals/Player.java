package dufrois.individuals;

/**
 * A player that plays on a team in a tournament lol
 * 
 * @author Christian Dufrois
 * @version 2017.04.20
 */
public class Player
{
    
    private String name;
    private int number;
    
    /**
     * Constructor for the player
     * 
     * @param playerName The player's name
     * @param num The player's personal number
     */
    public Player(String playerName, int num)
    {
        name = playerName;
        number = num;
    }
    
    /**
     * The name of the player
     * 
     * @return Name of the player
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * The personal number of the player
     * 
     * @return Number of the player
     */
    public int getNumber()
    {
        return number;
    }
    
    /**
     * Give the player a new name
     * 
     * @param newName A new name for the player
     */
    public void setName(String newName)
    {
        name = newName;
    }
    
    /**
     * Give the player a new number
     * 
     * @param newNum A new number for the player
     */
    public void setNumber(int newNum)
    {
        number = newNum;
    }
}
