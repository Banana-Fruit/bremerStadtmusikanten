package model;


public class Coordinates
{
    double positionX;
    double positionY;
    
    
    public Coordinates (double positionX, double positionY)
    {
        this.positionX = positionX;
        this.positionY = positionY;
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
    
    
    public boolean isEqual (Coordinates coordinates)
    {
        if (this.positionX == coordinates.positionX && this.positionY == coordinates.positionY) return true;
        return false;
    }
}
