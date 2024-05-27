package control.game;


import javafx.scene.input.KeyCode;
import model.userInterface.Game;
import model.Player;
import resources.Constants_DefaultValues;
import resources.Constants_ExceptionMessages;
import resources.Constants_Keymapping;
import resources.Constants_Scenes;

import java.util.Set;


/**
 * The PlayerController handles player movement.
 */
public class PlayerController
{
    private static volatile PlayerController instance;
    private Game game;
    private Player player;
    private int positionX;
    private int positionY;
    
    
    private PlayerController (Game game, Player player)
    {
        this.game = game;
        this.player = player;
    }
    
    
    public static synchronized void initialize (Game game, Player player)
    {
        if (instance == null)
        {
            instance = new PlayerController(game, player);
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
        if (game.getCurrentShowable().getId() == Constants_Scenes.IDENTIFIER_MAP && this.player != null)
        {
            boolean diagonal = false;
            if (pressedKeys.size() > 1) diagonal = true;
            
            if (pressedKeys.contains(Constants_Keymapping.moveUP)) moveUP(diagonal);
            if (pressedKeys.contains(Constants_Keymapping.moveLEFT)) moveLEFT(diagonal);
            if (pressedKeys.contains(Constants_Keymapping.moveDOWN)) moveDOWN(diagonal);
            if (pressedKeys.contains(Constants_Keymapping.moveRIGHT)) moveRIGHT(diagonal);
            
            changePosition();
        }
    }
    
    
    public void moveUP (boolean isDiagonal)
    {
        int deltaY = Constants_DefaultValues.DEFAULT_SPEED * Constants_DefaultValues.SPEED_MULTIPLIER;
        if (isDiagonal) deltaY = (int) (deltaY * Constants_DefaultValues.ADJUST_DIAGONAL_MOVEMENT);
        this.positionY -= deltaY;
    }
    
    
    public void moveDOWN (boolean isDiagonal)
    {
        int deltaY = Constants_DefaultValues.DEFAULT_SPEED * Constants_DefaultValues.SPEED_MULTIPLIER;
        if (isDiagonal) deltaY = (int) (deltaY * Constants_DefaultValues.ADJUST_DIAGONAL_MOVEMENT);
        this.positionY += deltaY;
    }
    
    
    public void moveRIGHT (boolean isDiagonal)
    {
        int deltaX = Constants_DefaultValues.DEFAULT_SPEED * Constants_DefaultValues.SPEED_MULTIPLIER;
        if (isDiagonal) deltaX = (int) (deltaX * Constants_DefaultValues.ADJUST_DIAGONAL_MOVEMENT);
        this.positionX += deltaX;
    }
    
    
    public void moveLEFT (boolean isDiagonal)
    {
        int deltaX = Constants_DefaultValues.DEFAULT_SPEED * Constants_DefaultValues.SPEED_MULTIPLIER;
        if (isDiagonal) deltaX = (int) (deltaX * Constants_DefaultValues.ADJUST_DIAGONAL_MOVEMENT);
        this.positionX -= deltaX;
    }
    
    
    public void changePosition ()
    {
        // TODO: OutputImageView.setImagePosition(this.player.getPlayerImage(), this.positionX, this.positionY);
    }
}
