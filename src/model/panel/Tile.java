package model.panel;


import javafx.scene.image.Image;
import resources.Constants_TileMap;


/**
 * Tiles contain images, position information, and whether it is occupied (i.e. player can not move on it).
 */
public class Tile
{
    private Image image;
    private boolean isOccupied = Constants_TileMap.DEFAULT_isOccupied;
    
    
    public Tile (Image image)
    {
        this.image = image;
    }
    
    
    public Image getImage ()
    {
        return image;
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
