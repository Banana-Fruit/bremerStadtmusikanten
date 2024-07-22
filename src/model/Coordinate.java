package model;


/**
 * Holds two variables with two positions. Can be used to store any doubles which are position related.
 *
 * @author Michael Markov
 */
public class Coordinate
{
    double positionX;
    double positionY;
    
    
    public Coordinate (double positionX, double positionY)
    {
        this.positionX = positionX;
        this.positionY = positionY;
    }
    
    
    public Coordinate (Coordinate coordinate)
    {
        this(coordinate.getPositionX(), coordinate.getPositionY());
    }
    
    
    public void setPositionX (double positionX)
    {
        this.positionX = positionX;
    }
    
    
    public void setPositionY (double positionY)
    {
        this.positionY = positionY;
    }
    
    
    public double getPositionX ()
    {
        return positionX;
    }
    
    
    public double getPositionY ()
    {
        return positionY;
    }
    
    
    /**
     * Compares given coordinates with local coordinates and returns a boolean. If equal, the returned value is true.
     *
     * @param coordinate
     * @return
     * @author Michael Markov
     */
    public boolean isEqual (Coordinate coordinate)
    {
        return this.positionX == coordinate.positionX && this.positionY == coordinate.positionY;
    }
}
