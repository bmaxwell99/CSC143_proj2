
/**
 * This object represents any individual Temperature Controlled storage unit present in a location
 *
 * @author Brian Maxwell
 * @version 4.14
 */
public class TemperatureUnit extends StorageUnit
{
    /**temperature of the Storage Unit*/
    private int tempLvl;

    /**
     * Constructor for objects of class TemperatureUnit
     * 
     * @param  width    the width to be set
     * @param  height   the height to be set
     * @param  length   the length to be set
     * @param  myLoc    the Location this storage unit is at
     * @param  tempLvl  the temperature of the Unit
     * 
     */
    public TemperatureUnit(double width, double height, double length, StorageLocation myLoc, int tempLvl)
    {
        super(width, height, length, myLoc);
        setTempLvl(tempLvl);
    }

    /**
     * calculates the price of the price of the unit
     *
     * @return    the price of the unit
     */
    public double calcPrice()
    {
        double price = getWidth() * getLength() * getHeight() + this.getMyLoc().getBasePrice();
        if (tempLvl <= 49 || tempLvl >=65)    { 
            price += 30;
        }
        return price;
    }
    
     /**
     * a setter for the tempLvl variable
     *
     * @param  tempLvl   the temperature level to be set
     *
     */
    public void setTempLvl(int tempLvl)
    {
        if (tempLvl >= 45 & tempLvl <= 70)  {
            this.tempLvl = tempLvl;
        }
        else {
            throw new IllegalArgumentException("Temperature level must be a number between 45 and 70");
        }
    }

    /**
     * a getter for units temperature level
     *
     * @return    the temperature level of the unit
     */
    public int getTempLvl()
    {
        return tempLvl;
    }

}
