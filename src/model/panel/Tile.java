package model.panel;


import javafx.scene.image.Image;
import resources.constants.Constants_Panel;


/**
 * Tiles contain images, position information, and whether it is occupied (i.e. player can not move on it).
 */
public class Tile
{
    private Image image;
    private boolean isOccupied = Constants_Panel.DEFAULT_ISOCCUPIED;
    private boolean isInteractable = Constants_Panel.DEFAULT_ISINTERACTABLE;
    
    
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
