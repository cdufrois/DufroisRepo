package dufrois.individuals;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import dufrois.common.Player;
import dufrois.teams.Team;

public class TeamTest
{
    private Team one;
    private Team two;
    private Team three;
    private Team four;
    
    @Before
    public void setUp()
    {
        one = new Team("One");
        two = new Team("Two", 2);
        
        Player[] playas = { new Player("me"), new Player("her") };
        three = new Team("Three", playas);
        
        Player[] others = { new Player("Christian"), new Player("Paul"), new Player("Dufrois") };
        four = new Team("Four", 4, others);
    }
    
    @Test
    public void testGetName()
    {
        assertEquals("One", one.getName());
        assertEquals("Two", two.getName());
        assertEquals("Three", three.getName());
        assertEquals("Four", four.getName());
    }
    
    @Test
    public void testGetSeed()
    {
        assertTrue(0 == one.getSeed());
        assertTrue(2 == two.getSeed());
        assertTrue(0 == three.getSeed());
        assertTrue(4 == four.getSeed());
    }
    
    @Test
    public void testGetNumPlayers()
    {
        assertTrue(0 == one.getNumPlayers());
        assertTrue(0 == two.getNumPlayers());
        assertTrue(2 == three.getNumPlayers());
        assertTrue(3 == four.getNumPlayers());
    }
    
    @Test
    public void testGetPlayer()
    {
        try
        {
            one.getPlayer(0);
            fail("Didn't throw index out of bounds exception");
        }
        catch (Exception e)
        {
            assertTrue(e instanceof IndexOutOfBoundsException);
        }
        try
        {
            two.getPlayer(0);
            fail("Didn't throw index out of bounds exception");
        }
        catch (Exception e)
        {
            assertTrue(e instanceof IndexOutOfBoundsException);
        }
        
        assertTrue(2 == three.getNumPlayers());
        assertTrue(3 == four.getNumPlayers());
    }
    
    @Test
    public void testSetName()
    {
        one.setName("1");
        assertEquals("1", one.getName());
        two.setName("2");
        assertEquals("2", two.getName());
        three.setName("3");
        assertEquals("3", three.getName());
        four.setName("4");
        assertEquals("4", four.getName());
    }
    
    
}
