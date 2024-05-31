package control.game;


import javafx.scene.input.KeyCode;
import model.Coordinates;
import model.userInterface.Game;
import model.Player;
import resources.constants.Constants_DefaultValues;
import resources.constants.Constants_ExceptionMessages;
import resources.constants.Constants_Keymapping;
import resources.constants.scenes.Constants_Scenes;

import java.util.Set;


/**
 * The PlayerController handles player movement.
 */
public class PlayerController
{
    private static volatile PlayerController instance;
    private Coordinates playerPosition;
    
    
    private PlayerController (Coordinates playerPosition)
    {
        this.playerPosition = playerPosition;
    }
    
    
    public static synchronized void initialize (Coordinates playerPosition)
    {
        if (instance == null)
        {
            instance = new PlayerController(playerPosition);
        } else
        {
            throw new IllegalStateException(Constants_ExceptionMessages.ALREADY_INITIALIZED);
        }
    }
    
    
    // Method to retrieve the Singleton instance without parameters
    public static PlayerController getInstance ()
    {
        if (instance == null)
        {
            throw new IllegalStateException(Constants_ExceptionMessages.SINGLETON_NOT_INITIALIZED);
        }
        return instance;
    }
    
    
    public void handleKeyPresses (Set<KeyCode> pressedKeys)
    {
        if (Game.getInstance().getCurrentShowable().getId() == Constants_Scenes.IDENTIFIER_MAP &&
                Player.getInstance() != null)
        {
            boolean diagonal = false;
            if (pressedKeys.size() > 1) diagonal = true;
            
            if (pressedKeys.contains(Constants_Keymapping.moveUP)) moveUP(diagonal);
            if (pressedKeys.contains(Constants_Keymapping.moveLEFT)) moveLEFT(diagonal);
            if (pressedKeys.contains(Constants_Keymapping.moveDOWN)) moveDOWN(diagonal);
            if (pressedKeys.contains(Constants_Keymapping.moveRIGHT)) moveRIGHT(diagonal);
        }
    }
    
    
    public void moveUP (boolean isDiagonal)
    {
        int deltaY = Constants_DefaultValues.DEFAULT_SPEED * Constants_DefaultValues.SPEED_MULTIPLIER;
        if (isDiagonal) deltaY = (int) (deltaY * Constants_DefaultValues.ADJUST_DIAGONAL_MOVEMENT);
        this.playerPosition.setPositionY(this.playerPosition.getPositionY() - deltaY);
    }
    
    
    public void moveDOWN (boolean isDiagonal)
    {
        int deltaY = Constants_DefaultValues.DEFAULT_SPEED * Constants_DefaultValues.SPEED_MULTIPLIER;
        if (isDiagonal) deltaY = (int) (deltaY * Constants_DefaultValues.ADJUST_DIAGONAL_MOVEMENT);
        this.playerPosition.setPositionY(this.playerPosition.getPositionY() + deltaY);
    }
    
    
    public void moveRIGHT (boolean isDiagonal)
    {
        int deltaX = Constants_DefaultValues.DEFAULT_SPEED * Constants_DefaultValues.SPEED_MULTIPLIER;
        if (isDiagonal) deltaX = (int) (deltaX * Constants_DefaultValues.ADJUST_DIAGONAL_MOVEMENT);
        this.playerPosition.setPositionX(this.playerPosition.getPositionX() + deltaX);
    }
    
    
    public void moveLEFT (boolean isDiagonal)
    {
        int deltaX = Constants_DefaultValues.DEFAULT_SPEED * Constants_DefaultValues.SPEED_MULTIPLIER;
        if (isDiagonal) deltaX = (int) (deltaX * Constants_DefaultValues.ADJUST_DIAGONAL_MOVEMENT);
        this.playerPosition.setPositionX(this.playerPosition.getPositionX() - deltaX);
    }
    
    
    public void setPlayerPosition (Coordinates playerPosition)
    {
        this.playerPosition = playerPosition;
    }
    
    
    public Coordinates getPlayerPosition ()
    {
        return playerPosition;
    }
}
