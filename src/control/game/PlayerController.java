package control.game;


import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import model.Coordinates;
import model.showables.Map;
import model.userInterface.Game;
import model.Player;
import resources.constants.Constants_DefaultValues;
import resources.constants.Constants_ExceptionMessages;
import resources.constants.Constants_Game;
import resources.constants.Constants_Keymapping;
import resources.constants.scenes.Constants_Map;
import resources.constants.scenes.Constants_Scenes;
import view.OutputImageView;

import java.util.Set;


/**
 * The PlayerController handles player movement.
 */
public class PlayerController implements Runnable
{
    private static volatile PlayerController instance = null;
    private Coordinates currentPlayerPosition;
    private Coordinates newPlayerPosition;
    private OutputImageView playerView;
    
    
    private PlayerController (Coordinates currentPlayerPosition)
    {
        this.currentPlayerPosition = currentPlayerPosition;
        this.newPlayerPosition = currentPlayerPosition;
        this.playerView = new OutputImageView(new Image(Constants_Map.PLAYER_VIEW_STANDARD), Constants_Map.PLAYER_SIZE);
        Game.getInstance().getCurrentShowable().addChildToPane(playerView);
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
    
    
    @Override
    public void run ()
    {
        while (true)
        {
            if (!this.currentPlayerPosition.isEqual(this.newPlayerPosition))
            {
                setCurrentPlayerPosition(newPlayerPosition);
            }
            try
            {
                Thread.sleep(Constants_Game.THREAD_SLEEP_DEFAULT_TIME); // Sleep for ~16ms to target ~60 updates per second
            } catch (InterruptedException e)
            {
                Thread.currentThread().interrupt(); // Preserve interrupt status
                break; // Exit the loop if interrupted
            }
        }
    }
    
    
    public void handleKeyPresses (Set<KeyCode> pressedKeys)
    {
        if (Game.getInstance().getCurrentShowable() == Map.getInstance().getShowable() &&
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
    
    
    private void moveUP (boolean isDiagonal)
    {
        int deltaY = Constants_DefaultValues.DEFAULT_SPEED * Constants_DefaultValues.SPEED_MULTIPLIER;
        if (isDiagonal) deltaY = (int) (deltaY * Constants_DefaultValues.ADJUST_DIAGONAL_MOVEMENT);
        this.newPlayerPosition.setPositionY(this.currentPlayerPosition.getPositionY() - deltaY);
    }
    
    
    private void moveDOWN (boolean isDiagonal)
    {
        int deltaY = Constants_DefaultValues.DEFAULT_SPEED * Constants_DefaultValues.SPEED_MULTIPLIER;
        if (isDiagonal) deltaY = (int) (deltaY * Constants_DefaultValues.ADJUST_DIAGONAL_MOVEMENT);
        this.newPlayerPosition.setPositionY(this.currentPlayerPosition.getPositionY() + deltaY);
    }
    
    
    private void moveRIGHT (boolean isDiagonal)
    {
        int deltaX = Constants_DefaultValues.DEFAULT_SPEED * Constants_DefaultValues.SPEED_MULTIPLIER;
        if (isDiagonal) deltaX = (int) (deltaX * Constants_DefaultValues.ADJUST_DIAGONAL_MOVEMENT);
        this.newPlayerPosition.setPositionX(this.currentPlayerPosition.getPositionX() + deltaX);
    }
    
    
    private void moveLEFT (boolean isDiagonal)
    {
        int deltaX = Constants_DefaultValues.DEFAULT_SPEED * Constants_DefaultValues.SPEED_MULTIPLIER;
        if (isDiagonal) deltaX = (int) (deltaX * Constants_DefaultValues.ADJUST_DIAGONAL_MOVEMENT);
        this.newPlayerPosition.setPositionX(this.currentPlayerPosition.getPositionX() - deltaX);
    }
    
    
    private void setCurrentPlayerPosition (Coordinates playerPosition)
    {
        this.currentPlayerPosition = playerPosition;
        playerView.setCoordinates(new Coordinates(this.currentPlayerPosition.getPositionX(), this.currentPlayerPosition.getPositionY()));
    }
}
