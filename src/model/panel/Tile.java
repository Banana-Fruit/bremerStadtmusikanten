package model.panel;


import javafx.scene.image.Image;
import resources.constants.Constants_Panel;


/**
 * Tiles contain images, position information, and whether it is occupied (i.e. player can not move on it).
 */
public class Tile
{
    private final Image backgroundImage;
    private final Image interactableImage;
    private boolean isInteractable = Constants_Panel.DEFAULT_isINTERACTABLE;
    
    
    public Tile (Image backgroundImage, Image interactableImage)
    {
        this.backgroundImage = backgroundImage;
        this.interactableImage = interactableImage;
        init();
    }
    
    
    private void init ()
    {
        if(interactableImage != null) isInteractable = !Constants_Panel.DEFAULT_isINTERACTABLE;
    }
    
    
    public Image getBackgroundImage ()
    {
        return backgroundImage;
    }
    
    
    public Image getInteractableImage ()
    {
        return interactableImage;
    }
    
    
    public boolean getInteractable ()
    {
        return isInteractable;
    }
}
