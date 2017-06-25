package dufrois.list;

import java.lang.reflect.Array;

import dufrois.individuals.Team;

public class TeamList<T extends Team>
{
    private static final int MAX_CAPACITY = 128;
    private static final int DEFAULT_CAPACITY = 16;
    private T[] teams;
    private int size;
    private Class<? extends Team> clazz;
    
    public TeamList(Class<? extends Team> class1)
    {
        this(class1, TeamList.DEFAULT_CAPACITY);
    }
    
    public TeamList(Class<? extends Team> class1, int capacity)
    {
        clazz = class1;
        teams = makeArray(capacity);
        size = 0;
    }
    
    @SuppressWarnings("unchecked")
    private T[] makeArray(int capacity)
    {
        return (T[]) Array.newInstance(clazz, capacity);
    }
    
    /**
     * If list is empty or not
     * 
     * @return true if list is empty
     */
    public boolean isEmpty()
    {
        return size == 0;
    }
    
    /**
     * Size of the list
     * 
     * @return Size of list
     */
    public int getSize()
    {
        return size;
    }
    
    /**
     * Gets the team at the given index
     * 
     * @param index Index of the team in the list
     * @return The team at the given index
     * @throws IndexOutOfBoundsException If the index is out of range
     */
    public T getTeam(int index) throws IndexOutOfBoundsException
    {
        return teams[index];
    }
    
    /**
     * Replace the team at the index with the given team
     * 
     * @param index Index of team to be replaced
     * @param team The replacing team
     * @return The team being replaced
     * @throws IndexOutOfBoundsException If the index is out of range
     */
    public T setTeam(int index, T team) throws IndexOutOfBoundsException
    {
        if (team == null)
        {
            return null;
        }
        
        T tmp = teams[index];
        teams[index] = team;
        return tmp;
    }
    
    /**
     * Adds the team to the end of the list
     * 
     * @param team Team to be added
     * @return If the team was able to be added
     */
    public boolean addTeam(T team)
    {
        boolean expanded = expandArray();
        if (!expanded)
        {
            return false;
        }
        
        teams[size] = team;
        size++;
        return true;
    }
    
    // False if there is no more space to place an entry
    private boolean expandArray()
    {
        // Return false if already at max capacity
        if (teams.length == TeamList.MAX_CAPACITY)
        {
            return false;
        }
        
        // Return true if the array is not full
        if (size != teams.length)
        {
            return true;
        }
        
        T[] array;
        
        // Doubling doesn't put array over max capacity
        if (teams.length < TeamList.MAX_CAPACITY / 2)
        {
            array = makeArray(teams.length * 2);
        }
        // Doubling puts array of max capacity
        else
        {
            array = makeArray(TeamList.MAX_CAPACITY);
        }
        
        // Place teams in new array
        for (int i = 0; i < size; i++)
        {
            array[i] = teams[i];
        }
        teams = array;
        
        return true;
    }
    
    /**
     * Removes the given team from the list
     * 
     * @param team Team to be removed
     * @return The team being removed, null if empty or can't find
     */
    public T removeTeam(T team)
    {
        if (team == null)
        {
            return null;
        }
        
        // Find and shift teams in array
        for (int i = 0; i < size; i++)
        {
            // Find team to be removed
            if (teams[i].equals(team))
            {
                return removeTeam(i);
            }
            
        }
        
        // Team wasn't found
        return null;
    }
    
    /**
     * Remove team at given index
     * 
     * @param index Index of team to be removed
     * @return Team at given index
     * @throws IndexOutOfBoundsException If the index is out of range
     */
    public T removeTeam(int index) throws IndexOutOfBoundsException
    {
        if (index < 0 || index >= size)
        {
            throw new IndexOutOfBoundsException();
        }
        
        // Find and shift teams in array
        T remove = teams[index];
        for (int i = index; i < size; i++)
        {
            // Shift all the teams left
            if (remove != null)
            {
                teams[i - 1] = teams[i];
            }
            
        }
        size--;
        reduceArray();
        return remove;
    }
    
    private void reduceArray()
    {
        // Return false if already at max capacity
        if (teams.length == TeamList.DEFAULT_CAPACITY)
        {
            return;
        }
        
        T[] array;
        
        // Halving doesn't put array over max capacity
        if (teams.length > TeamList.DEFAULT_CAPACITY * 2)
        {
            array = makeArray(teams.length / 2);
        }
        // Doubling puts array of max capacity
        else
        {
            array = makeArray(TeamList.DEFAULT_CAPACITY);
        }
        
        // Place teams in new array
        for (int i = 0; i < size; i++)
        {
            array[i] = teams[i];
        }
        teams = array;
    }
    
    /**
     * All the teams in an array
     * 
     * @return Array of all the teams
     */
    public T[] toArray()
    {
        @SuppressWarnings("unchecked")
        T[] array = (T[]) new Object[size];
        
        // Place each team in the list into an array with appropriate size
        for (int i = 0; i < array.length; i++)
        {
            array[i] = teams[i];
        }
        
        return array;
    }
    
    /**
     * Lists the teams in a string format
     * 
     * @return List of the teams
     */
    public String toString()
    {
        StringBuilder builder = new StringBuilder("[");
        for (int i = 0; i < size; i++)
        {
            // Add team to string
            builder.append(teams[i].toString());
            
            // Add separator
            if (i != size - 1)
            {
                builder.append(", ");
            }
            else
            {
                builder.append("]");
            }
        }
        return builder.toString();
    }
}
