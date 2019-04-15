import java.time.LocalDate;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class StorageUnitTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class StandardUnitTest
{
    
    public static final double VARIANCE = 0.0001;
    
    StorageLocation loc1 = new StorageLocation("WA01Issaquah", 25);
    StorageUnit unit1= loc1.getUnit(0,0);
    
    /**
     * Default constructor for test class StorageUnitTest
     */
    public StandardUnitTest()
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
        
        assertEquals(9,         unit1.getWidth(),      VARIANCE);
        assertEquals(14,        unit1.getHeight(),     VARIANCE);
        assertEquals(22,        unit1.getLength(),     VARIANCE);
        assertEquals(100,       unit1.calcPrice(),     VARIANCE);
        assertEquals(loc1,      unit1.getMyLoc());
    }

    @Test
    public void testRentingAndReleaseAbilities()  
    {
        Customer cust1 = new Customer("Joe Schmoe", "1234567899");
        LocalDate date1 = LocalDate.now();
        
        //test rentUnitTo and getters
        unit1.rentUnitTo(cust1, date1);
        assertEquals(cust1,     unit1.getRentedBy());
        assertEquals(date1,     unit1.getRentalDate());
        
        //test release
        unit1.releaseUnit();
        assertEquals(null,     unit1.getRentedBy());
        assertEquals(null,     unit1.getRentalDate());        
        
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testConstrWidthNeg()    {
        StorageUnit unit2 = new StandardUnit(-9, 14, 22, loc1);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testConstrHeightNeg()    {
        StorageUnit unit2 = new StandardUnit(9, -14, 22, loc1);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testConstrLengthNeg()    {
        StorageUnit unit2 = new StandardUnit(9, 14, -22, loc1);
    }
    
}
