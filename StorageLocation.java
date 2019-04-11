
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
    private StorageUnit[][] storageUnits = new StorageUnit[12][20];
    private Customer[] customers = new Customer[100];
    private int num_customers;

    /**
     * Constructor for objects of class StorageLocation
     * 
     * @param   designation     the name of the store location to be instantiated
     */
    public StorageLocation(String designation)
    {
        if(designation.matches("^(A[KLRZ]|C[AOT]|D[CE]|FL|GA|HI|I[ADLN]|K[SY]|LA|M[ADEINOST]|N[CDEHJMVY]|O[HKR]|P[AR]|RI|S[CD]|T[NX]|UT|V[AIT]|W[AIVY])(\\d{2})(?i)([a-z]+)$"))
        {
            this.designation = designation;
        }
        else {
            throw new IllegalArgumentException("Designation MUST start with state abrev, followed by two digits, followed by city name.");
        }

        for(int row = 0; row < 10; row++) {
            for(int col = 0; col < 20; col++)   {
                storageUnits[row][col] = new StorageUnit(9, 14, 22, 34.99, RoomType.STANDARD);

            }
        }

        for(int col = 0; col < 20; col++)   {
            storageUnits[10][col] = new StorageUnit(9, 14, 22, 44.99, RoomType.HUMIDITY_CONTROLLED);
        }

        for(int col = 0; col < 20; col++)   {
            storageUnits[11][col] = new StorageUnit(9, 14, 22, 54.99, RoomType.TEMPERATURE_CONTROLLED);
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
     * a getter method for any particular Storage  Unit
     * 
     * @param   row     the index of the row to be retrieved
     * @param   col     the index of the column to be retrieved
     * @return          the Storage Unit at the specified indexes
     */
    public StorageUnit getStorageUnit(int row, int col)
    {
        if( row >= 0 & row < 12) {
            if( col >= 0 & row < 20) {
                return storageUnits[row][col];
            }
            else    {
                throw new IllegalArgumentException("col must be of the index between 0 and 19");
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
        customers[num_customers] = new Customer(name, pNumber);
        num_customers += 1;
        //if num_customers reaches 100, create new array of customers and add the previous one to it
        if(num_customers == 100)    {
            Customer[] temp = new Customer[(customers.length + 100)];
            for(int i = 0; i < customers.length; i++)    {
                temp[i] = customers[i];
            }
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
        if (index >= 0 & index < num_customers) {
            return customers[index];
        }
        else    {
            throw new IllegalArgumentException("input must be a positive number less than the num_customers on file");
        }
    }

    /**
     * gets the number of the customers currently in the array customers
     *
     * @return    the number of customers
     */
    public int getNumCustomers()
    {
        return num_customers;
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
                if (stor.getCustomer() != null)    {
                    stor.getCustomer().chargeAccount(stor.getPrice());
                }
            }
        }
    }

    /**
     * retreives an array of storage units owned by any particular customer, and or any particular room type;
     * retreives an array of all unoccupied units if both parameters are null.
     *
     * @param  cust         the customer whose units are being retrieved, null ignores customers for the search
     * @param  type         the roomtype to be searched for, null ignores room type for the search
     * @return              the array of Storage Units owned
     */
    public StorageUnit[] getUnits(Customer cust, RoomType type)
    {
        StorageUnit[] units = new StorageUnit[240];
        int unitsIndex = 0;
        for (StorageUnit[] row : storageUnits) {
            for (StorageUnit stor : row)   {
                //searches for a specific customer with a specific roomtype
                if( cust != null & type != null) {
                    if(stor.getCustomer().equals(cust) & stor.getRoomType().equals(type))   {
                        units[unitsIndex] = stor;
                        unitsIndex += 1;
                    }
                }
                //searches for a specific customer
                if( cust != null & type == null)   {
                    if(stor.getCustomer().equals(cust)) {
                        units[unitsIndex] = stor;
                        unitsIndex += 1;
                    }
                }
                //searches for a specific roomtype
                if( cust == null & type != null)   {
                    if(stor.getCustomer().equals(null) & stor.getRoomType().equals(type)) {
                        units[unitsIndex] = stor;
                        unitsIndex += 1;
                    }
                }
                //searches for all empty units
                else    {
                    if(stor.getCustomer().equals(null)) {
                        units[unitsIndex] = stor;
                        unitsIndex += 1;
                    }
                }
            }
        }
        return units;
    }

}

