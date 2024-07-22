package model.panel;


import javafx.scene.image.Image;
import resources.constants.Constants_Panel;


/**
 * Tiles contain images, position information, and whether it is occupied (i.e. player can not move on it).
 *
 * @author Michael Markov
 */
public class Tile
{
    private final Image backgroundImage;
    private boolean isOccupied = Constants_Panel.DEFAULT_isOCCUPIED;
    
    
    public Tile (Image backgroundImage)
    {
        this.backgroundImage = backgroundImage;
    }
    
    
    public void setOccupied (boolean occupied)
    {
        isOccupied = occupied;
    }
    
    
    public Image getBackgroundImage ()
    {
        return backgroundImage;
    }
    
    
    public boolean getOccupied ()
    {
        return isOccupied;
    }
}
