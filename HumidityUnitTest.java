

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class HumidityUnitTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class HumidityUnitTest
{
    public static final double VARIANCE = 0.0001;
    
    StorageLocation loc1 = new StorageLocation("WA01Issaquah", 25);
    StorageUnit unit1= loc1.getUnit(8,0);
    
    /**
     * Default constructor for test class HumidityUnitTest
     */
    public HumidityUnitTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }
    
    @Test
    public void testConstrSetsAndGets()
    {
        assertEquals(40,    unit1.getHumLvl());
        assertEquals(1015,  unit1.calcPrice(),      VARIANCE);
        unit1.setHumLvl(25);
        assertEquals(1035,  unit1.calcPrice(),      VARIANCE);
    }
    
     @Test (expected = IllegalArgumentException.class)
    public void testTempBounds()    {
        unit1.setHumLvl(19);
    }
    
}
