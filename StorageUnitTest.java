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
public class StorageUnitTest
{
    
    public static final double VARIANCE = 0.0001;
    
    /**
     * Default constructor for test class StorageUnitTest
     */
    public StorageUnitTest()
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
        StorageUnit unit1 = new StorageUnit(9, 14, 22, 34.99, RoomType.STANDARD);
        
        assertEquals(9,         unit1.getWidth(),      VARIANCE);
        assertEquals(14,        unit1.getHeight(),     VARIANCE);
        assertEquals(22,        unit1.getLength(),     VARIANCE);
        assertEquals(34.99,     unit1.getPrice(),      VARIANCE);
        
        assertEquals(RoomType.STANDARD,     unit1.getRoomType());
    }

    @Test
    public void testRentingAndReleaseAbilities()  
    {
        StorageUnit unit1 = new StorageUnit(9, 14, 22, 34.99, RoomType.STANDARD);
        Customer cust1 = new Customer("Joe Schmoe", "1234567899");
        LocalDate date1 = LocalDate.now();
        
        //test rentUnitTo and getters
        unit1.rentUnitTo(cust1, date1);
        assertEquals(cust1,     unit1.getCustomer());
        assertEquals(date1,     unit1.getRentalDate());
        assertEquals(34.99,     unit1.getPrice(),      VARIANCE);
        
        //test release
        unit1.releaseUnit();
        assertEquals(null,     unit1.getCustomer());
        assertEquals(null,     unit1.getRentalDate());
        
        //test rentUnitToWithPrice
        unit1.rentUnitTo(cust1, date1, 25);
        assertEquals(25,     unit1.getPrice(),  VARIANCE);
        
        
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testConstrWidthNeg()    {
        StorageUnit unit1 = new StorageUnit(-9, 14, 22, 34.99, RoomType.STANDARD);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testConstrHeightNeg()    {
        StorageUnit unit1 = new StorageUnit(9, -14, 22, 34.99, RoomType.STANDARD);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testConstrLengthNeg()    {
        StorageUnit unit1 = new StorageUnit(9, 14, -22, 34.99, RoomType.STANDARD);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testConstrPriceNeg()    {
        StorageUnit unit1 = new StorageUnit(9, 14, 22, -34.99, RoomType.STANDARD);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testRentedForNeg()    {
        StorageUnit unit1 = new StorageUnit(9, 14, 22, 34.99, RoomType.STANDARD);
        unit1.setRentedFor(-.01);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testConstrRoomTypeNull()    {
        StorageUnit unit1 = new StorageUnit(9, 14, 22, 34.99, null);
    }
}
