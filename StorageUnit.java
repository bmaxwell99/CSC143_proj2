import java.time.LocalDate;

/**
 * This object is represents any individual storage unit present in a location
 *
 * @author Brian Maxwell
 * @version 4.5
 */
public class StorageUnit
{
    private double width;
    private double height;
    private double length;    
    private double price;
    private Customer rentedBy;
    private double rentedFor;
    private RoomType roomType;
    private LocalDate rentalDate;

    /**
     * Constructor for objects of class StorageUnit
     */
    public StorageUnit(double width, double height, double length, double price, RoomType roomtype)
    {
        setWidth(width);
        setHeight(height);
        setLength(length);
        setPrice(price);
        setRoomType(roomtype);
        rentedFor = -1;
    }

    /**
     * a setter for the width variable
     *
     * @param  double   the width to be set
     *
     */
    public void setWidth(double width)
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
     * @param  double   the height to be set
     *
     */
    public void setHeight(double height)
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
     * @param  double   the length to be set
     *
     */
    public void setLength(double length)
    {
        if (length > 0) {
            this.length = length;
        }
        else    {
            throw new IllegalArgumentException("Length must be a non zero positive number");
        }
    }

    /**
     * a setter for the price variable
     *
     * @param  double   the price to be set
     *
     */
    public void setPrice(double price)
    {
        if (price > 0)  {
            this.price = price;
        }
        else {
            throw new IllegalArgumentException("Price must be a non zero positive number");
        }
    }
    
    /**
     * a setter for the rentedFor variable
     *
     * @param  double   the price to be set
     *
     */
    public void setRentedFor(double price)
    {
        if (price > 0)  {
            this.price = price;
        }
        else {
            throw new IllegalArgumentException("Price must be a non zero positive number");
        }
    }

    /**
     * a setter for the roomType variable
     *
     * @param  RoomType   the roomType to be set
     *
     */
    public void setRoomType(RoomType roomType)
    {
        if(roomType != null) {
            this.roomType = roomType;
        }
        else    {
            throw new IllegalArgumentException("Room Type cannot be null");
        }
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
     * a getter for Price
     *
     * @return    the price of the unit, either default or what it is being rented for
     */
    public double getPrice()
    {
        double price;
        if(rentedFor == -1) {
            price = this.price;
        }
        else {
            price = this.rentedFor;
        }
        return price;
    }

    /**
     * a getter for units type
     *
     * @return    the roomType of the unit
     */
    public RoomType getRoomType()
    {
        return roomType;
    }

    /**
     * a getter for Customer renting the unit
     *
     * @return    the customer renting the unit
     */
    public Customer getCustomer()
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
     * this method rents this unit to a given customer at a given date at a
     * specified price
     *
     * @param  Customer     the customer to be renting the unit
     * @param  LocalDate    the date to start renting the unit
     * @param  double       the price to rent the unit for
     */
    public void rentUnitTo(Customer cust, LocalDate startDate, double price)
    {
        this.rentedBy = cust;
        this.rentalDate = startDate;
        setRentedFor(price);
    }    

    /**
     * this method rents this unit to a given customer at a given date at the
     * standard price
     *
     * @param  Customer     the customer to be renting the unit
     * @param  LocalDate    the date to start renting the unit
     */
    public void rentUnitTo(Customer cust, LocalDate startDate)
    {
        rentUnitTo(cust, startDate, this.price);
    }

    /**
     * releases the unit and clears the current renter
     *
     */
    public void releaseUnit()
    {
        this.rentUnitTo(null, null);
        this.rentedFor = -1;
    }

}
