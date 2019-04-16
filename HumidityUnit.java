
/**
 * This object represents any individual Humidity Controlled storage unit present in a location
 *
 * @author Brian Maxwell
 * @version 4.14
 */
public class HumidityUnit extends StorageUnit
{
    /**Humidity of the Storage Unit*/
    private int humLvl;

    /**
     * Constructor for objects of class HumidityUnit
     * 
     * @param  width    the width to be set
     * @param  height   the height to be set
     * @param  length   the length to be set
     * @param  myLoc    the Location this storage unit is at
     * @param  humLvl   the humidity of the Unit
     * 
     */
    public HumidityUnit(double width, double height, double length, StorageLocation myLoc, int humLvl)
    {
        super(width, height, length, myLoc);
        setHumLvl(humLvl);
    }

    /**
     * calculates the price of the price of the unit
     *
     * @return    the price of the unit
     */
    public double calcPrice()
    {
        double price = getWidth() * getLength() * 5 + this.getMyLoc().getBasePrice();
        if (humLvl <= 29)    { 
            price += 20;
        }
        return price;
    }
    
     /**
     * a setter for the humLvl variable
     *
     * @param  humLvl   the humdity level to be set
     *
     */
    public void setHumLvl(int humLvl)
    {
        if (humLvl >= 20 & humLvl <= 60)  {
            this.humLvl = humLvl;
        }
        else {
            throw new IllegalArgumentException("HumidityLevel must be a number between 20 and 60");
        }
    }

    /**
     * a getter for units humidity level
     *
     * @return    the humidity level of the unit
     */
    public int getHumLvl()
    {
        return humLvl;
    }

}
