
/**
 * This object represents any individual Standard storage unit present in a location
 *
 * @author Brian Maxwell
 * @version 4.14
 */
public class StandardUnit extends StorageUnit
{

    /**
     * Constructor for objects of class StandardUnit
     */
    public StandardUnit(double width, double height, double length, StorageLocation myLoc)
    {
        super(width, height, length, myLoc);
    }

    /**
     * calculates the price of the price of the unit
     *
     * @return    the price of the unit
     */
    public double calcPrice()
    {
         return this.getMyLoc().getBasePrice() + 75;
    }
}
