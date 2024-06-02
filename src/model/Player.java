package model;


import control.game.PlayerController;
import javafx.scene.image.Image;
import resources.constants.Constants_ExceptionMessages;

import java.util.List;


/**
 * An image with a hitbox that moves on the map
 */
public class Player
{
    private static Player instance = null;
    private List inventory; // TODO: Holds artefacts
    
    
    private Player ()
    {
    }
    
    
    public static synchronized void initialize ()
    {
        if (instance == null)
        {
            instance = new Player();
        } else
        {
            throw new IllegalStateException(Constants_ExceptionMessages.ALREADY_INITIALIZED);
        }
    }
    
    
    // Method to retrieve the Singleton instance without parameters
    public static Player getInstance ()
    {
        if (instance == null)
        {
            throw new IllegalStateException(Constants_ExceptionMessages.SINGLETON_NOT_INITIALIZED);
        }
        return instance;
    }
}
