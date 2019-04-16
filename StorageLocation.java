
/**
 * This object holds all information related to any instance of the Storage 
 * Location
 *
 * @author (Brian Maxwell)
 * @version (4)
 */
public class StorageLocation
{
    // instance variables
    private String designation;
    private StorageUnit[][] storageUnits;
    private Customer[] customers;
    private int numCustomers;
    private double basePrice;

    /**
     * Constructor for objects of class StorageLocation
     * 
     * @param   designation     the name of the store location to be instantiated
     */
    public StorageLocation(String designation, double basePrice)
    {
        setDesignation(designation);
        setBasePrice(basePrice);        
        this.customers = new Customer[100];
        this.storageUnits = new StorageUnit[12][];
        for(int row = 0; row <= 11; row++) {
            if(row <= 6)    {
                storageUnits[row] = new StorageUnit[10];
                for(int col = 0; col <= 9; col++)   {
                    storageUnits[row][col] = new StandardUnit(9, 14, 22, this);

                }
            }
            if(row > 6 & row <= 9)  {
                storageUnits[row] = new StorageUnit[8];
                for(int col = 0; col <= 7; col++)   {
                    storageUnits[row][col] = new HumidityUnit(9, 14, 22, this, 40);
                }

            }
            if( row > 9) {
                storageUnits[row] = new StorageUnit[6];
                for(int col = 0; col <= 5; col++)   {
                    storageUnits[row][col] = new TemperatureUnit(9, 14, 22, this, 55);
                }

            }
        }

    }

    /**
     * a getter method for Designation
     *
     * @return    the designation for the store
     */
    public String getDesignation()
    {
        return designation;
    }

    /**
     * a setter for the designation variable
     *
     * @param  designation  the designation to be set
     */
    public void setDesignation(String designation)
    {
        if(designation.matches("^(A[KLRZ]|C[AOT]|D[CE]|FL|GA|HI|I[ADLN]|K[SY]|LA|M[ADEINOST]|N[CDEHJMVY]|O[HKR]|P[AR]|RI|S[CD]|T[NX]|UT|V[AIT]|W[AIVY])(\\d{2})(?i)([a-z]+)$"))
        {
            this.designation = designation;
        }
        else {
            throw new IllegalArgumentException("Designation MUST start with state abrev, followed by two digits, followed by city name.");
        }
    }

    /**
     * a getter for basePrice variable
     *
     * @return    the baseprice of the store
     */
    public double getBasePrice()
    {
        return basePrice;
    }

    /**
     * a setter for the basePrice variable
     *
     * @param  basePrice  the baseprice to be set
     */
    public void setBasePrice(double basePrice)
    {
        if( basePrice >= 0) {
            this.basePrice = basePrice;
        }
        else{
            throw new IllegalArgumentException("base price cannot be a negative number");
        }

    }

    /**
     * a getter method for any particular Storage  Unit
     * 
     * @param   row     the index of the row to be retrieved
     * @param   col     the index of the column to be retrieved
     * @return          the Storage Unit at the specified indexes
     */
    public StorageUnit getUnit(int row, int col)
    {
        if( row >= 0 & row < 12) {
            if( col >= 0 & col < storageUnits[row].length) {
                return storageUnits[row][col];
            }
            else    {
                throw new IllegalArgumentException("col must be between 0 and" + (storageUnits[row].length - 1));
            }
        }
        else    {
            throw new IllegalArgumentException("row must be of the index between 0 and 11");
        }
    }

    /**
     * adds a new customer to the array Customers
     *
     * @param   name        Customer name to be added
     * @param   pNumber     Customer phone number to be added
     * 
     */
    public void addCustomer(String name, String pNumber)
    {
        customers[numCustomers] = new Customer(name, pNumber);
        numCustomers += 1;
        //if num_customers reaches 100, create new array of customers and add the previous one to it
        if(numCustomers == 100)    {
            incCustArray();
        }
    }

    /**
     * Overloaded method adds a new customer to the array Customers
     *
     * @param   cust    Customer object to be added
     * 
     */
    public void addCustomer(Customer cust)
    {
        customers[numCustomers] = cust;
        numCustomers += 1;
        //if num_customers reaches 100, create new array of customers and add the previous one to it
        if(numCustomers % 100 == 0)    {
            incCustArray();
        }
    }

    /**
     * helper method that increases the size of the Customer array when it reaches capacity
     *
     * 
     */
    private void incCustArray()
    {
        {
            Customer[] temp = new Customer[(customers.length + 100)];
            //is this what you had in mind for Block Copy?
            System.arraycopy(customers, 0, temp, 0, customers.length);
            customers = temp;
        }
    }    

    /**
     * Get the customer at the given index
     * 
     * @param  index     the given index
     * @return           the customer at the given index
     */
    public Customer getCustomer(int index)
    {
        if (index >= 0 & index < numCustomers) {
            return customers[index];
        }
        else    {
            throw new IllegalArgumentException("input must be a positive number less than the number of customers on file");
        }
    }

    /**
     * gets the number of the customers currently in the array customers
     *
     * @return    the number of customers
     */
    public int getNumCustomers()
    {
        return numCustomers;
    }

    /**
     * charges every occupied storage unit's monthly rent to the customer who owns it
     *
     */
    public void accrueRent()
    {
        for(StorageUnit[] row : storageUnits) {
            for(StorageUnit stor : row)   {
                //checks to make sure occupied
                if (stor.getRentedBy() != null)    {
                    //checks to see if owner has two or more units
                    if(stor.getRentedBy().getUnitsRented() > 1)   {
                        stor.getRentedBy().chargeAccount(stor.calcPrice() * .95);
                    }
                    else    {
                        stor.getRentedBy().chargeAccount(stor.calcPrice());

                    }
                }
            }
        }
    }

    /**
     * retreives an array of storage units owned by any particular customer, and or any particular room type;
     * retreives an array of all unoccupied units if both parameters are null.
     *
     * @param  cust         the customer whose units are being retrieved, null ignores customers for the search
     * @param  type         the StorageUnit type to be searched for, null ignores room type for the search
     * @return              the array of Storage Units owned
     */
    public StorageUnit[] getUnit(Customer cust, StorageUnit type)
    {
        StorageUnit[] units = new StorageUnit[240];
        int unitsIndex = 0;
        for (StorageUnit[] row : storageUnits) { 
            for (StorageUnit stor : row)   {
                //searches for a specific customer with a specific roomtype
                if( cust != null & type != null) 
                {
                    if(stor.getRentedBy() != null)
                    {
                        if(stor.getRentedBy().equals(cust) & stor.getClass().equals(type.getClass()))   {
                            units[unitsIndex] = stor;
                            unitsIndex += 1;
                        }
                    }
                }
                //searches for a specific customer
                if( cust != null & type == null)   {
                    if(stor.getRentedBy() != null)  
                    {
                        if(stor.getRentedBy().equals(cust)) {
                            units[unitsIndex] = stor;
                            unitsIndex += 1;
                        }
                    }
                }
                //searches for a specific StorageUnit type
                if( cust == null & type != null)   {
                    if(stor.getRentedBy() == null & stor.getClass().equals(type.getClass())) {
                        units[unitsIndex] = stor;
                        unitsIndex += 1;
                    }
                }
                //searches for all empty units
                else if (cust == null & type == null)    {
                    if(stor.getRentedBy() == null) {
                        units[unitsIndex] = stor;
                        unitsIndex += 1;
                    }
                }
            }
        }
        StorageUnit[] units2 = new StorageUnit[unitsIndex];   
        for(int i= 0; i < unitsIndex; i++)  {
            units2[i] = units[i];
        }
        return units2;
    }

}

