import java.time.LocalDate;

/**
 * This abstarct object represents any individual storage unit present in a location
 *
 * @author Brian Maxwell
 * @version 4.14
 */
public abstract class StorageUnit
{
    /**Width of the Storage Unit*/
    private double width;
    /**Height of the Storage Unit*/
    private double height;
    /**Length of the Storage Unit*/
    private double length;    
    //private double price;
    /**Customer renting this Storage Unit*/
    private Customer rentedBy;
    /**Date rental will begin upon*/
    private LocalDate rentalDate;
    /**Location of this Storage Unit*/
    private StorageLocation myLoc;

    /**
     * Constructor for objects of class StorageUnit
     * 
     * @param  width    the width to be set
     * @param  height   the height to be set
     * @param  length   the length to be set
     * @param  myLoc    the Location this storage unit is at
     * 
     */
    public StorageUnit(double width, double height, double length, StorageLocation myLoc)
    {
        setWidth(width);
        setHeight(height);
        setLength(length);
        setMyLoc(myLoc);
        //setPrice(price);
    }

    /**
     * a setter for the width variable
     *
     * @param  width   the width to be set
     *
     */
    private void setWidth(double width)
    {
        if (width > 0)  {
            this.width = width;
        }
        else {
            throw new IllegalArgumentException("Width must be a non zero positive number");
        }
    }

    /**
     * a setter for the height variable
     *
     * @param  height   the height to be set
     *
     */
    private void setHeight(double height)
    {
        if (height > 0 )    {
            this.height = height;
        }
        else    {
            throw new IllegalArgumentException("Height must be a non zero postive number");
        }
    }

    /**
     * a setter for the length variable
     *
     * @param  length   the length to be set
     *
     */
    private void setLength(double length)
    {
        if (length > 0) {
            this.length = length;
        }
        else    {
            throw new IllegalArgumentException("Length must be a non zero positive number");
        }
    }

    // /**
     // * a setter for the price variable
     // *
     // * @param  price   the price to be set
     // *
     // */
    // private void setPrice(double price)
    // {
        // this.price = price;
    // }
    
     /**
     * the method that will calculate the price of the unit based on which unit type it is
     *
     * @return    the price of the unit to be set
     */
    public abstract double calcPrice();
    
     // /**
     // * a getter for price variable
     // *
     // * @return    the price of the unit
     // */
    // public double getPrice()   {
        // return price;
    // }
    
    /**
     * a setter for the myLoc variable
     *
     * @param  myLoc   the lcoation to be set
     *
     */
    private void setMyLoc(StorageLocation myLoc)
    {
        this.myLoc = myLoc;
    }
    
    /**
     * a getter for units width
     *
     * @return    the width of the unit
     */
    public double getWidth()
    {
        return width;
    }

    /**
     * a getter for units height
     *
     * @return    the height of the unit
     */
    public double getHeight()
    {
        return height;
    }

    /**
     * a getter for units length
     *
     * @return    the length of the unit
     */
    public double getLength()
    {
        return length;
    }

    /**
     * a getter for units location
     *
     * @return    the location of the unit
     */
    public StorageLocation getMyLoc()
    {
        return myLoc;
    }

    /**
     * a getter for Customer renting the unit
     *
     * @return    the customer renting the unit
     */
    public Customer getRentedBy()
    {
        return rentedBy;
    }

    /**
     * a getter for the rental start date
     *
     * @return    the date the rental starts
     */
    public LocalDate getRentalDate()
    {
        return rentalDate;
    }

    /**
     * this method rents this unit to a given customer at a given date
     *
     * @param  cust     the customer to be renting the unit
     * @param  startDate    the date to start renting the unit
     */
    public void rentUnitTo(Customer cust, LocalDate startDate)
    {
        this.rentedBy = cust;
        this.rentalDate = startDate;
        if(cust != null)    {
            cust.incUnitsRented();
        }
    }    

    /**
     * releases the unit and clears the current renter
     *
     */
    public void releaseUnit()
    {
        this.getRentedBy().decUnitsRented();
        this.rentUnitTo(null, null);
    }

}
