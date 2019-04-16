import java.lang.IllegalArgumentException;

/**
 * This class represents the Customer structure as owned by the StorageLocation
 *
 * @author Brian Maxwell
 * @version 4.5
 */
public class Customer
{
    /**Name of the customer*/
    private String name;
    /**Phone number of the customer*/
    private String pNumber;
    /**Balance of the Customer*/
    private double balance;
    /**Number of Units the customer has rented*/
    private int unitsRented;

    /**
     * Constructor for objects of class Customer
     * 
     * @param   name   the name to set this customer to
     * @param   pNumber   the phone number to set this customer to
     * 
     */
    public Customer(String name, String pNumber)
    {
        setName(name);
        setPNumber(pNumber);
        balance = 0.0;
        unitsRented = 0;
    }

    /**
     * a setter for this customers name
     *
     * @param   name   the name to set this customer to
     */
    public void setName(String name)
    {
        if (!(name.matches("(?i)[a-z0-9]*(\\s)[a-z0-9]*"))){
            throw new IllegalArgumentException("Names must be made of a first and last name");
        }
        this.name = name;
    }

    /**
     * a setter for this customers phonenumber
     *
     * @param   pNumber   the phone number to set this customer to
     */
    public void setPNumber(String pNumber)
    {
        if (pNumber.matches("[\\d]{10,11}")){
            this.pNumber = pNumber;
        }
        else{
            throw new IllegalArgumentException("Phone numbers must be ONLY digits, area code optional");
        }
    }

    /**
     * a getter for this customers name
     *
     * @return    name of the customer
     */
    public String getName()
    {
        return name;
    }

    /**
     * a getter for this customers phone number
     *
     * @return    phone number of the customer
     */
    public String getPNumber()
    {
        return pNumber;
    }

    /**
     * a getter for this customers current balance
     *
     * @return    balance
     */
    public double getBalance()
    {
        return balance;
    }

    /**
     * this increases the balance on this customers account
     *
     *    
     * @param   amount  the amount to increase the balance
     * @return  the updated balance
     */
    public double chargeAccount(double amount)
    {
        if(amount < 0 ) {
            throw new IllegalArgumentException("Charge must be a positive number");
        }
        else {balance += amount;}
        return this.getBalance();
    }

    /**
     * this decreases the balance on this customers account
     *
     * @param   amount  the amount to decrease the balance
     * @return  the updated balance
     */
    public double creditAccount(double amount)
    {
        if (amount < 0 ) {
            throw new IllegalArgumentException("Credit must be a positive number");
        }
        else if (amount >= this.balance) {
            throw new IllegalArgumentException("Credit cannot be greater than current balance");
        }
        else {
            balance -= amount;
        }
        return this.getBalance();
    }

    /**
     * A getter for the units rented variable
     *
     * @return    the number of units rented by this customer
     */
    public int getUnitsRented()
    {
        return unitsRented;
    }
    
    /**
     * An incrementor method for the units rented variable
     *
     */
    public void incUnitsRented()
    {
        unitsRented += 1;
    }

    /**
     * An decremenor method for the units rented variable
     *
     */
    public void decUnitsRented()
    {
        if(unitsRented == 0)    {
            throw new IllegalArgumentException("Customers can't have negative units rented");
        }
        unitsRented -= 1;
    }

}