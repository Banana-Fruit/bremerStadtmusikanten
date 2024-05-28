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
    
    
    public double getPositionX ()
    {
        return positionX;
    }
    
    
    public double getPositionY ()
    {
        return positionY;
    }
}