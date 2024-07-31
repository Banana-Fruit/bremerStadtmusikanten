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
    private final Image image;
    private boolean isOccupied = Constants_Panel.DEFAULT_isOCCUPIED;
    
    
    public Tile (Image image, boolean isOccupied)
    {
        this.image = image;
        this.isOccupied = isOccupied;
    }

    
    public Image getImage ()
    {
        return image;
    }
    
    
    public boolean getOccupied ()
    {
        return isOccupied;
    }
}
