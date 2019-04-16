import java.time.LocalDate;

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
    public static final double VARIANCE = 0.0001;
    
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
        StorageLocation loc1 = new StorageLocation("WA01Issaquah", 25);
        assertEquals("WA01Issaquah", loc1.getDesignation());
        assertEquals(25, loc1.getBasePrice(), VARIANCE);
        
        //tests the customer related adders/getters
        loc1.addCustomer("Joe Schmoe", "12345367899");
        assertEquals("Joe Schmoe", loc1.getCustomer(0).getName());
        assertEquals(1, loc1.getNumCustomers());
    }
    
    @Test
    public void testAccrueRent()    
    {
        StorageLocation loc1 = new StorageLocation("WA01Issaquah", 25); 
        Customer cust1 = new Customer("Joe Schmoe", "12345367899");
        
        //Rents unit and check balance 
        loc1.addCustomer(cust1);
        loc1.getUnit(0,0).rentUnitTo(cust1, LocalDate.now());
        loc1.accrueRent();
        assertEquals(100,    loc1.getCustomer(0).getBalance(), VARIANCE);
        
        //Rents second unit and checks 5% discount
        loc1.getUnit(0,1).rentUnitTo(cust1, LocalDate.now());
        loc1.accrueRent();
        assertEquals(290,   loc1.getCustomer(0).getBalance(),    VARIANCE);

    }  
    
    @Test
    public void testGetUnit()    
    {
        StorageLocation loc1 = new StorageLocation("WA01Issaquah", 25);  
        assertTrue(loc1.getUnit(0,0) instanceof StandardUnit);
        assertTrue(loc1.getUnit(9,0) instanceof HumidityUnit);
        assertTrue(loc1.getUnit(10,0) instanceof TemperatureUnit);
    }  
    
    @Test
    public void testGetUnitByConditions()    
    {
        StorageLocation loc1 = new StorageLocation("WA01Issaquah", 25); 
        Customer cust1 = new Customer("Joe Schmoe", "12345367899");
        loc1.addCustomer(cust1);
        loc1.getUnit(0,0).rentUnitTo(cust1, LocalDate.now());
        StandardUnit stan = (StandardUnit) loc1.getUnit(0,5);
        
        //checks each logical pathway
        assertEquals(cust1, loc1.getUnit(cust1, null)[0].getRentedBy());
        assertEquals(69, loc1.getUnit(null, stan).length);
        assertEquals(105, loc1.getUnit(null, null).length);
        assertEquals(1, loc1.getUnit(cust1, stan).length);
    }
    
    @Test
    public void testIncCustArray()
    {
        StorageLocation loc1 = new StorageLocation("WA01Issaquah", 25); 
        for(int i = 0; i < 205; i++)    {
            Customer cust = new Customer("Joe Schmoe" + i, "1234567899");
            loc1.addCustomer(cust);
        }
        assertEquals("Joe Schmoe200", loc1.getCustomer(200).getName());
        
    }
    
    
    @Test (expected = IllegalArgumentException.class)
    public void testImproperDesignation() {
        StorageLocation loc1 = new StorageLocation("23Issaquah" , 25);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testGetUnitRowNeg() {
        StorageLocation loc1 = new StorageLocation("WA23Issaquah", 25);
        loc1.getUnit(-1,0);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testGetUnitColNeg() {
        StorageLocation loc1 = new StorageLocation("WA23Issaquah", 25);
        loc1.getUnit(0,-1);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testGetUnitColStan() {
        StorageLocation loc1 = new StorageLocation("WA23Issaquah", 25);
        loc1.getUnit(0,10);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testGetUnitColHum() {
        StorageLocation loc1 = new StorageLocation("WA23Issaquah", 25);
        loc1.getUnit(9, 8);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testGetUnitColTemp() {
        StorageLocation loc1 = new StorageLocation("WA23Issaquah", 25);
        loc1.getUnit(10, 6);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testGetCustIndexNeg() {
        StorageLocation loc1 = new StorageLocation("WA23Issaquah", 25);
        loc1.getCustomer(-1);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testBasePriceNeg() {
        StorageLocation loc1 = new StorageLocation("WA23Issaquah", -25);
    }
}
