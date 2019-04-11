

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class StorageLocationTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class StorageLocationTest
{
    /**
     * Default constructor for test class StorageLocationTest
     */
    public StorageLocationTest()
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
    public void testConstrAndGets() {
        StorageLocation loc1 = new StorageLocation("WA01Issaquah");
        assertEquals("WA01Issaquah", loc1.getDesignation());
        assertEquals(RoomType.STANDARD, loc1.getStorageUnit(0, 0).getRoomType());
        
        //tests the customer related adders/getters
        loc1.addCustomer("Joe Schmoe", "12345367899");
        assertEquals("Joe Schmoe", loc1.getCustomer(0).getName());
        assertEquals(1, loc1.getNumCustomers());
    }
    
    @Test
    public void testAccrueRent()    
    {
    }  
    
    @Test (expected = IllegalArgumentException.class)
    public void testConstrDesignation() {
        StorageLocation loc1 = new StorageLocation("23Issaquah");
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testGetStrgRowNeg() {
        StorageLocation loc1 = new StorageLocation("WA23Issaquah");
        loc1.getStorageUnit(-1,0);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testGetStrgColNeg() {
        StorageLocation loc1 = new StorageLocation("WA23Issaquah");
        loc1.getStorageUnit(0,-1);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testGetCustIndexNeg() {
        StorageLocation loc1 = new StorageLocation("WA23Issaquah");
        loc1.getCustomer(-1);
    }
}
