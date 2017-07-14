package dufrois.individuals;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PlayerTest
{
    private Player me;
    private Player her;
    
    /**
     * Add more comments
     */
    @Before
    public void setUp()
    {
        me = new Player("Dufrois", 12);
        
        her = new Player("Danielle");
    }
    
    @Test
    public void testGetName()
    {
        assertEquals("Dufrois", me.getName());
        assertEquals("Danielle", her.getName());
    }
    
    @Test
    public void testGetNumber()
    {
        assertTrue(12 == me.getNumber());
        assertTrue(-1 == her.getNumber());
    }
    
    @Test
    public void testSetName()
    {
        me.setName("Chris");
        assertEquals("Chris", me.getName());
        her.setName("Faust");
        assertEquals("Faust", her.getName());
    }
    
    @Test
    public void testSetNumber()
    {
        me.setNumber(1);
        assertTrue(1 == me.getNumber());
        her.setNumber(3);
        assertTrue(3 == her.getNumber());
    }
    
    @Test
    public void testEquals()
    {
        // Same object
        assertTrue(me.equals(me));
        assertTrue(her.equals(her));
        
        // Null
        Player nothing = null;
        assertFalse(me.equals(nothing));
        assertFalse(her.equals(nothing));
        
        // Other object
        Integer num = new Integer(1);
        assertFalse(me.equals(num));
        assertFalse(her.equals(num));
        
        // Different name
        Player difName = new Player("Faust", 12);
        assertFalse(me.equals(difName));
        difName = new Player("Faust", -1);
        assertFalse(her.equals(difName));
        
        // Different number
        Player difNum = new Player("Dufrois", 3);
        assertFalse(me.equals(difNum));
        difNum = new Player("Danielle", 3);
        assertFalse(her.equals(difNum));
        
        // Same both
        Player samePer = new Player("Dufrois", 12);
        assertTrue(me.equals(samePer));
        samePer = new Player("Danielle", -1);
        assertTrue(her.equals(samePer));
    }
}
