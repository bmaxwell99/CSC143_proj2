

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class TemperatureUnitTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class TemperatureUnitTest
{
    public static final double VARIANCE = 0.0001;
    
    StorageLocation loc1 = new StorageLocation("WA01Issaquah", 25);
    StorageUnit unit1= loc1.getUnit(10,0);
    
    /**
     * Default constructor for test class TemperatureUnitTest
     */
    public TemperatureUnitTest()
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
    public void testCntrGetSet()    {
        assertEquals(55,    unit1.getTempLvl());
        assertEquals(2797,  unit1.calcPrice(),      VARIANCE);
        unit1.setTempLvl(45);
        assertEquals(2827,  unit1.calcPrice(),      VARIANCE);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testTempBounds()    {
        unit1.setTempLvl(30);
    }
    
}
