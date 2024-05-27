package model;


import javafx.scene.image.Image;

import java.util.List;


/**
 * An image with a hitbox that moves on the map
 */
public class Player
{
    private Image playerImage;
    private List inventory; // TODO: Holds artefacts
    
    
    public Player (Image image)
    {
        this.playerImage = image;
    }
    
    
    public Image getPlayerImage ()
    {
        return playerImage;
    }
}
