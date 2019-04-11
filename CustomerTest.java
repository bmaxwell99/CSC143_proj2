

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class CustomerTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class CustomerTest
{
    public static final double DOLLAR_VARIANCE = 0.0001;
    
    /**
     * Default constructor for test class CustomerTest
     */
    public CustomerTest()
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
    public void testConstrSetsAndGets() {
        Customer cust1 = new Customer("Joe Schmoe", "1234567890");
        assertEquals("Joe Schmoe",      cust1.getName());
        assertEquals("1234567890",  cust1.getPNumber());
        assertEquals(0,                 cust1.getBalance(), DOLLAR_VARIANCE);
    }
    
    @Test
    public void testChargeAndCreditAccount()    {
        Customer cust1 = new Customer("Joe Schmoe", "1234567899");
        assertEquals(39.99, cust1.chargeAccount(39.99),     DOLLAR_VARIANCE);
        assertEquals(39.99, cust1.getBalance(),             DOLLAR_VARIANCE);
        assertEquals(19.99, cust1.creditAccount(19.99),     DOLLAR_VARIANCE);
        assertEquals(20,    cust1.getBalance(),             DOLLAR_VARIANCE);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testConstrName()
    {
        Customer cust1 = new Customer("123 123", "1234567890");        
    }

    @Test (expected = IllegalArgumentException.class)
    public void testConstrPNumber()
    {
        Customer cust1 = new Customer("Joe Scmoe", "123-456-7890");        
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testChargeNeg(){
        Customer cust1 = new Customer("Joe Scmoe", "1234567890");
        cust1.chargeAccount(-.01);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testCreditNeg(){
        Customer cust1 = new Customer("Joe Scmoe", "1234567899");
        cust1.chargeAccount(.01);
        cust1.creditAccount(-.01);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testCreditLessThanBalance(){
        Customer cust1 = new Customer("Joe Scmoe", "1234567890");
        cust1.chargeAccount(24.99);
        cust1.creditAccount(25);
    }
}
