package model.panel;


import javafx.scene.image.Image;


/**
 * Tiles contain images, position information, and whether it is occupied (i.e. player can not move on it).
 */
public class Tile
{
    private Image image;
    private int positionX;
    private int positionY;
    private boolean isOccupied;
    
    
    public Tile (String imagePath, int positionX, int positionY, boolean isOccupied)
    {
        image = new Image(imagePath);
        this.positionX = positionX;
        this.positionY = positionY;
        this.isOccupied = isOccupied;
    }
    
    
    public Image getImage ()
    {
        return image;
    }
    
    
    public int getPositionX ()
    {
        return positionX;
    }
    
    
    public int getPositionY ()
    {
        return positionY;
    }
    
    
    public void setOccupied (boolean occupied)
    {
        isOccupied = occupied;
    }
    
    
    public boolean getOccupied ()
    {
        return isOccupied;
    }
}
