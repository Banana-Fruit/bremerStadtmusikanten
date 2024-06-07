package model.panel;


import javafx.scene.image.Image;
import resources.constants.Constants_Panel;


/**
 * Tiles contain images, position information, and whether it is occupied (i.e. player can not move on it).
 */
public class Tile
{
    private Image backgroundImage;
    private Image interactableImage;
    private Image singleImage;
    private boolean isOccupied = Constants_Panel.DEFAULT_ISOCCUPIED;
    private boolean isInteractable = Constants_Panel.DEFAULT_ISINTERACTABLE;
    
    
    public Tile (Image image, boolean isOccupied)
    {
        this.isOccupied = isOccupied;
        this.singleImage = image;
        init();
    }
    
    
    public Tile (Image backgroundImage, Image interactableImage)
    {
        this.backgroundImage = backgroundImage;
        this.interactableImage = interactableImage;
        this.isOccupied = !Constants_Panel.DEFAULT_ISOCCUPIED; // If an interactable is available, the panel is marked as occupied
    }
    
    
    private void init ()
    {
        if(isOccupied)
        {
            this.interactableImage = singleImage;
        } else
        {
            this.backgroundImage = singleImage;
        }
    }
    
    
    public Image getBackgroundImage ()
    {
        return backgroundImage;
    }
    
    
    public Image getInteractableImage ()
    {
        return interactableImage;
    }
    
    
    public boolean getOccupied ()
    {
        return isOccupied;
    }
}
