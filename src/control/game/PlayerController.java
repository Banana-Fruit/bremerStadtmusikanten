package control.game;


import control.scenes.PanelController;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import model.Coordinate;
import model.userInterface.showables.Map;
import model.userInterface.Game;
import model.Player;
import resources.constants.Constants_ExceptionMessages;
import resources.constants.Constants_Game;
import resources.constants.Constants_Keymapping;
import resources.constants.scenes.Constants_Map;
import view.OutputImageView;

import java.util.Set;


/**
 * The PlayerController handles player movement.
 */
public class PlayerController implements Runnable
{
    private static volatile PlayerController instance = null;
    private Coordinate currentPlayerPosition = new Coordinate(Constants_Map.STARTPOSITION_X, Constants_Map.STARTPOSITION_Y);
    private Coordinate newPlayerPosition = new Coordinate(Constants_Map.STARTPOSITION_X, Constants_Map.STARTPOSITION_Y);
    private final OutputImageView playerView;
    
    
    private PlayerController ()
    {
        this.playerView = new OutputImageView(new Image(Constants_Map.PLAYER_VIEW_STANDARD), Constants_Map.PLAYER_SIZE);
    }
    
    
    public static synchronized void initialize ()
    {
        if (instance == null)
        {
            instance = new PlayerController();
        } else
        {
            throw new IllegalStateException(Constants_ExceptionMessages.ALREADY_INITIALIZED);
        }
    }
    
    
    public void addPlayer (Coordinate playerPosition)
    {
        Map.getInstance().getPane().getChildren().add(playerView);
        setPlayerPosition(playerPosition);
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
            try
            {
                Thread.sleep(Constants_Game.THREAD_SLEEP_DEFAULT_TIME);
                
                if (!this.currentPlayerPosition.isEqual(this.newPlayerPosition)) // If player tries to move
                {
                    if(!PanelController.getInstance().isCoordinateOccupied(Map.getInstance().getPanel(),
                            newPlayerPosition)) // If coordinate is not occupied
                    {
                         setPlayerPosition(newPlayerPosition); // Player moves
                    } else
                    {
                        setPlayerPosition(currentPlayerPosition); // Otherwise player does not move
                    }
                }
            } catch (InterruptedException e)
            {
                Thread.currentThread().interrupt(); // Preserve interrupt status
                break; // Exit the loop if interrupted
            } catch (Exception e)
            {
                throw new RuntimeException(e);
            }
        }
    }
    
    
    public void handleKeyPresses (Set<KeyCode> pressedKeys)
    {
        if (Game.getInstance().getCurrentShowable() == Map.getInstance().getShowable())
        {
            boolean isDiagonal = pressedKeys.size() > 1;
            
            if (pressedKeys.contains(Constants_Keymapping.moveUP)) moveUP(isDiagonal);
            if (pressedKeys.contains(Constants_Keymapping.moveLEFT)) moveLEFT(isDiagonal);
            if (pressedKeys.contains(Constants_Keymapping.moveDOWN)) moveDOWN(isDiagonal);
            if (pressedKeys.contains(Constants_Keymapping.moveRIGHT)) moveRIGHT(isDiagonal);
        }
    }
    
    
    private void moveUP (boolean isDiagonal)
    {
        double deltaY = Constants_Map.PLAYER_SPEED * Constants_Map.SPEED_MULTIPLIER;
        if (isDiagonal) deltaY = (deltaY * Constants_Map.ADJUST_DIAGONAL_MOVEMENT);
        this.newPlayerPosition.setPositionY(this.currentPlayerPosition.getPositionY() - deltaY);
    }
    
    
    private void moveDOWN (boolean isDiagonal)
    {
        double deltaY = Constants_Map.PLAYER_SPEED * Constants_Map.SPEED_MULTIPLIER;
        if (isDiagonal) deltaY = (deltaY * Constants_Map.ADJUST_DIAGONAL_MOVEMENT);
        this.newPlayerPosition.setPositionY(this.currentPlayerPosition.getPositionY() + deltaY);
    }
    
    
    private void moveRIGHT (boolean isDiagonal)
    {
        double deltaX = Constants_Map.PLAYER_SPEED * Constants_Map.SPEED_MULTIPLIER;
        if (isDiagonal) deltaX = (deltaX * Constants_Map.ADJUST_DIAGONAL_MOVEMENT);
        this.newPlayerPosition.setPositionX(this.currentPlayerPosition.getPositionX() + deltaX);
    }
    
    
    private void moveLEFT (boolean isDiagonal)
    {
        double deltaX = Constants_Map.PLAYER_SPEED * Constants_Map.SPEED_MULTIPLIER;
        if (isDiagonal) deltaX = (deltaX * Constants_Map.ADJUST_DIAGONAL_MOVEMENT);
        this.newPlayerPosition.setPositionX(this.currentPlayerPosition.getPositionX() - deltaX);
    }
    
    
    private void setPlayerPosition (Coordinate playerPosition)
    {
        this.currentPlayerPosition = new Coordinate(playerPosition);
        this.newPlayerPosition = new Coordinate(playerPosition);
        playerView.setCoordinates(playerPosition);
    }
}
