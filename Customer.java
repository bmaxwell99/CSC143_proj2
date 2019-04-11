import java.lang.IllegalArgumentException;

/**
 * This class represents the Customer structure as owned by the StorageLocation
 *
 * @author Brian Maxwell
 * @version 4.5
 */
public class Customer
{
    private String name;
    private String pNumber;
    private Double balance;

    /**
     * Constructor for objects of class Customer
     */
    public Customer(String name, String pNumber)
    {
        setName(name);
        setPNumber(pNumber);
        balance = 0.0;
    }

    /**
     * a setter for this customers name
     *
     * @param   name   the name to set this customer to
     */
    public void setName(String name)
    {
        if (!(name.matches("(?i)[a-z]*(\\s)[a-z]*"))){
            throw new IllegalArgumentException("Names must be made of a first and last name, letters only.");
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
     */
    public double chargeAccount(double amount)
    {
        if(amount < 0 ) {
            throw new IllegalArgumentException("Charge must be a positive number");
        }
        else {balance += amount;}
        return amount;
    }

    /**
     * this decreases the balance on this customers account
     *
     * @param   amount  the amount to decrease the balance
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
        return amount;
    }

}