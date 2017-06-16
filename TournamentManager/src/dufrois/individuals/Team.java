package dufrois.individuals;

import java.util.ArrayList;

/**
 * A group of players that compete in tournaments
 * 
 * @author Christian Dufrois
 * @version
 */
public class Team {
    
    private String name;
    private ArrayList<Player> players;
    private int seed;
    
    public Team(String name) {
        this(name, 0, null);
    }
    
    /**
     * Constructor for a team of people
     * 
     * @param name
     *            Name of the team
     * @param seed
     *            Seed / rank of the team
     */
    public Team(String name, int seed) {
        this(name, seed, null);
    }
    
    /**
     * Constructor for a team of players
     * 
     * @param teamName
     *            Name of the team
     * @param teamSeed
     *            Seed / rank of the team
     * @param originalPlayers
     *            An array of players on the team
     */
    public Team(String name, Player[] originalPlayers) {
        this(name, 0, originalPlayers);
    }
    
    /**
     * Constructor for a team of players
     * 
     * @param teamName
     *            Name of the team
     * @param teamSeed
     *            Seed / rank of the team
     * @param originalPlayers
     *            An array of players on the team
     */
    public Team(String teamName, int teamSeed, Player[] originalPlayers) {
        name = teamName;
        seed = teamSeed;
        players = new ArrayList<Player>();
        if (originalPlayers != null) {
            for (int i = 0; i < originalPlayers.length; i++) {
                players.add(originalPlayers[i]);
            }
        }
    }
    
    /**
     * Name of the team
     * 
     * @return Name of the team
     */
    public String getName() {
        return name;
    }
    
    /**
     * Seed / rank of the team
     * 
     * @return Seed of the team
     */
    public int getSeed() {
        return seed;
    }
    
    /**
     * The number of players on the team
     * 
     * @return Number of players on the team
     */
    public int getNumPlayers() {
        return players.size();
    }
    
    /**
     * Get the player at the given index
     * 
     * @param index
     *            Index of the player to get
     * @return The player at the given index
     */
    public Player getPlayer(int index) {
        return players.get(index);
    }
    
    /**
     * Give the team a new name
     * 
     * @param newName
     *            The new name of the team
     */
    public void setName(String newName) {
        name = newName;
    }
    
    /**
     * Give the team a new seed
     * 
     * @param newSeed
     *            The new seed for the team
     */
    public void setSeed(int newSeed) {
        seed = newSeed;
    }
    
    /**
     * Add the player to the team
     * 
     * @param newPlayer
     *            Player to be added
     */
    public void addPlayer(Player newPlayer) {
        players.add(newPlayer);
    }
    
    /**
     * The name of the team
     * 
     * @return String representation of the team
     */
    public String toString() {
        if (name.equals("Bye Match")) {
            return name;
        }
        return "Team " + name;
    }
}
