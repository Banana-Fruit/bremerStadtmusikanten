package control.game;


import control.scenes.MapController;
import control.scenes.PanelController;
import control.scenes.SceneController;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import model.Coordinate;
import model.Unit;
import model.player.Inventory;
import model.player.Player;
import model.userInterface.showables.Map;
import model.userInterface.Game;
import resources.constants.Constants_DefaultValues;
import resources.constants.Constants_ExceptionMessages;
import resources.constants.Constants_Game;
import resources.constants.Constants_Keymapping;
import resources.constants.scenes.Constants_Map;
import view.OutputImageView;

import java.util.ArrayList;
import java.util.Set;


/**
 * The PlayerController handles player movement, inventory and attributes.
 *
 * @author Michael Markov
 */
public class PlayerController implements Runnable
{
    private static volatile PlayerController instance = null;
    private Coordinate currentPlayerPosition;
    private Coordinate newPlayerPosition;
    private final OutputImageView playerView;
    
    
    private PlayerController ()
    {
        this.playerView = new OutputImageView(new Image(Constants_Map.PLAYER_VIEW_STANDARD), Constants_Map.PLAYER_SIZE);
        this.currentPlayerPosition = new Coordinate(0, 0); // Initial position
        this.newPlayerPosition = new Coordinate(0, 0); // Initial position
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
    
    
    public void setPlayerInventory ()
    {
        Inventory.getInstanceOfInventory().setInventoryGold(Constants_DefaultValues.DEFAULT_INVENTORY_VALUE);
        Inventory.getInstanceOfInventory().setInventoryBeer(Constants_DefaultValues.DEFAULT_INVENTORY_VALUE);
        Inventory.getInstanceOfInventory().setInventoryBrick(Constants_DefaultValues.DEFAULT_INVENTORY_VALUE);
        Inventory.getInstanceOfInventory().setInventoryEssence(Constants_DefaultValues.DEFAULT_INVENTORY_VALUE);
        Inventory.getInstanceOfInventory().setInventoryWood(Constants_DefaultValues.DEFAULT_INVENTORY_VALUE);
    }
    
    
    /**
     * Adds player image to the map.
     *
     * @param playerPosition Gives the playerPosition where the player has to be added.
     * @author Michael Markov
     */
    public void addPlayer (Coordinate playerPosition)
    {
        Map.getInstance().getPane().getChildren().add(playerView);
        setPlayerPosition(playerPosition);
    }
    
    
    /**
     * Updates player position (moves the player).
     *
     * @author Michael Markov
     */
    public void updatePlayer ()
    {
        playerView.setCoordinates(currentPlayerPosition);
    }


    /**
     * Continuously updates the player's position and checks for game events.
     * This method runs in a separate thread to handle player movement and trigger various game checks.
     *
     * @author Michael Markov, Jonas Helfer
     * @precondition The thread has been properly initialized and started.
     * @postcondition Player position is updated, and relevant game checks are performed.
     */
    @Override
    public void run ()
    {
        while (true)
        {
            try
            {
                Thread.sleep(Constants_Game.THREAD_SLEEP_DEFAULT_TIME);
                
                if (!this.currentPlayerPosition.isEqual(this.newPlayerPosition))
                {
                    Coordinate targetPosition = new Coordinate(this.newPlayerPosition.getPositionX(), this.newPlayerPosition.getPositionY());
                    
                    // Check if vertical movement is possible
                    if (PanelController.getInstance().isVerticalMoveBlocked(Map.getInstance().getPanel(), this.currentPlayerPosition, targetPosition))
                    {
                        newPlayerPosition.setPositionY(currentPlayerPosition.getPositionY());
                    }
                    
                    // Check if horizontal movement is possible
                    if (PanelController.getInstance().isHorizontalMoveBlocked(Map.getInstance().getPanel(), this.currentPlayerPosition, targetPosition))
                    {
                        newPlayerPosition.setPositionX(currentPlayerPosition.getPositionX());
                    }
                    
                    // Update player position
                    setPlayerPosition(newPlayerPosition);
                    
                    // Check for mission start and proximity to units
                    Platform.runLater(() ->
                    {
                        MapController.getInstance().checkMissionStart();
                        MapController.getInstance().checkProximityToUnits();
                        MapController.getInstance().checkRewardCollection();
                    });
                }
            } catch (InterruptedException e)
            {
                System.out.println(Constants_ExceptionMessages.PLAYER_CONTROLLER_THREAD_WAS_INTERRUPTED);
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
    
    
    /**
     * Method gets a list of pressed keys, and runs methods that the keys are mapped to.
     *
     * @param pressedKeys The set of key presses recorded at the time.
     * @author Michael Markov
     */
    public void handleKeyPresses (Set<KeyCode> pressedKeys)
    {
        if (Game.getInstance().getCurrentShowable() == Map.getInstance().getShowable())
        {
            if (SceneController.getInstance().isDialogShown())
            {
                return; // Ignore key presses if a dialog is open
            }
            
            // If more than one movement key is pressed, it will be considered an atempted diagonal movement
            boolean isDiagonal = pressedKeys.size() > Constants_DefaultValues.ONE;
            
            // Run methods base on the pressed keys
            if (pressedKeys.contains(Constants_Keymapping.moveUP)) moveUP(isDiagonal);
            if (pressedKeys.contains(Constants_Keymapping.moveLEFT)) moveLEFT(isDiagonal);
            if (pressedKeys.contains(Constants_Keymapping.moveDOWN)) moveDOWN(isDiagonal);
            if (pressedKeys.contains(Constants_Keymapping.moveRIGHT)) moveRIGHT(isDiagonal);
        }
    }
    
    
    /**
     * Method moves the character up. If isDiagonal is true, then the movement speed will be adjusted towards the
     * diagonal movement.
     *
     * @param isDiagonal Clarifies whether the currently requested movement is diagonal.
     * @author Michael Markov
     */
    private void moveUP (boolean isDiagonal)
    {
        // Speed multiplier increases/decreases the movement speed
        double deltaY = Constants_Map.PLAYER_SPEED * Constants_Map.SPEED_MULTIPLIER;
        // If movement is diagonal, then the speed of the movement will be adjusted
        if (isDiagonal) deltaY = deltaY * Constants_Map.ADJUST_DIAGONAL_MOVEMENT;
        // New player position will be updated
        this.newPlayerPosition.setPositionY(this.currentPlayerPosition.getPositionY() - deltaY);
    }
    
    
    /**
     * Method moves the character down. If isDiagonal is true, then the movement speed will be adjusted towards the
     * diagonal movement.
     *
     * @param isDiagonal Clarifies whether the currently requested movement is diagonal.
     * @author Michael Markov
     */
    private void moveDOWN (boolean isDiagonal)
    {
        // Speed multiplier increases/decreases the movement speed
        double deltaY = Constants_Map.PLAYER_SPEED * Constants_Map.SPEED_MULTIPLIER;
        // If movement is diagonal, then the speed of the movement will be adjusted
        if (isDiagonal) deltaY = deltaY * Constants_Map.ADJUST_DIAGONAL_MOVEMENT;
        // New player position will be updated
        this.newPlayerPosition.setPositionY(this.currentPlayerPosition.getPositionY() + deltaY);
    }
    
    
    /**
     * Method moves the character right. If isDiagonal is true, then the movement speed will be adjusted towards the
     * diagonal movement.
     *
     * @param isDiagonal Clarifies whether the currently requested movement is diagonal.
     * @author Michael Markov
     */
    private void moveRIGHT (boolean isDiagonal)
    {
        // Speed multiplier increases/decreases the movement speed
        double deltaX = Constants_Map.PLAYER_SPEED * Constants_Map.SPEED_MULTIPLIER;
        // If movement is diagonal, then the speed of the movement will be adjusted
        if (isDiagonal) deltaX = deltaX * Constants_Map.ADJUST_DIAGONAL_MOVEMENT;
        // New player position will be updated
        this.newPlayerPosition.setPositionX(this.currentPlayerPosition.getPositionX() + deltaX);
    }
    
    
    /**
     * Method moves the character left. If isDiagonal is true, then the movement speed will be adjusted towards the
     * diagonal movement.
     *
     * @param isDiagonal Clarifies whether the currently requested movement is diagonal.
     * @author Michael Markov
     */
    private void moveLEFT (boolean isDiagonal)
    {
        // Speed multiplier increases/decreases the movement speed
        double deltaX = Constants_Map.PLAYER_SPEED * Constants_Map.SPEED_MULTIPLIER;
        // If movement is diagonal, then the speed of the movement will be adjusted
        if (isDiagonal) deltaX = deltaX * Constants_Map.ADJUST_DIAGONAL_MOVEMENT;
        // New player position will be updated
        this.newPlayerPosition.setPositionX(this.currentPlayerPosition.getPositionX() - deltaX);
    }
    
    
    /**
     * Used to set the player position to a specific coordinate using the instance of Coordinate.
     * Sets the value of the current and new player position to a new value.
     *
     * @param playerPosition The position the player has to have.
     * @author Michael Markov
     */
    public void setPlayerPosition (Coordinate playerPosition)
    {
        this.currentPlayerPosition = new Coordinate(playerPosition);
        this.newPlayerPosition = new Coordinate(playerPosition);
        playerView.setCoordinates(playerPosition);
    }
    
    
    public static void addUnitToTheTeam (Unit unit)
    {
        ArrayList<Unit> team = Player.getInstance().getTeamMembers();
        team.add(unit);
    }
    
    
    /**
     * Method to retrieve the Singleton instance.
     */
    public static PlayerController getInstance ()
    {
        if (instance == null)
        {
            throw new IllegalStateException(Constants_ExceptionMessages.SINGLETON_NOT_INITIALIZED);
        }
        return instance;
    }
    
    
    /**
     * Getter for the current player position. Retrieves an instance of Coordinate.
     *
     * @return The coordinates with the current player position
     * @author Michael Markov
     */
    public Coordinate getCurrentPlayerPosition ()
    {
        return currentPlayerPosition;
    }
    
    
    /**
     * Getter for player view. Retrieves an instance of OutputImageView.
     *
     * @return The playerView.
     * @author Michael Markov
     */
    public OutputImageView getPlayerView ()
    {
        return playerView;
    }
}
