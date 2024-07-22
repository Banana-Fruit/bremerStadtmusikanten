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
    
    
    public void addPlayer (Coordinate playerPosition)
    {
        Map.getInstance().getPane().getChildren().add(playerView);
        setPlayerPosition(playerPosition);
    }
    
    
    public void updatePlayer ()
    {
        Map.getInstance().getPane().getChildren().remove(playerView);
        addPlayer(currentPlayerPosition);
    }
    
    
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
                    if (!PanelController.getInstance().isVerticalMoveBlocked(Map.getInstance().getPanel(), this.currentPlayerPosition, targetPosition))
                    {
                        this.currentPlayerPosition.setPositionY(targetPosition.getPositionY());
                    }
                    
                    // Check if horizontal movement is possible
                    if (!PanelController.getInstance().isHorizontalMoveBlocked(Map.getInstance().getPanel(), this.currentPlayerPosition, targetPosition))
                    {
                        this.currentPlayerPosition.setPositionX(targetPosition.getPositionX());
                    }
                    
                    // Update player position
                    setPlayerPosition(new Coordinate(this.currentPlayerPosition.getPositionX(), this.currentPlayerPosition.getPositionY()));
                    
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
                Thread.currentThread().interrupt();
                break;
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
    
    
    public void handleKeyPresses (Set<KeyCode> pressedKeys)
    {
        if (Game.getInstance().getCurrentShowable() == Map.getInstance().getShowable())
        {
            if (SceneController.getInstance().isDialogShown())
            {
                return; // Ignore key presses if a dialog is open
            }
            
            boolean isDiagonal = pressedKeys.size() > Constants_DefaultValues.ONE;
            
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
     * @param isDiagonal
     * @author Michael Markov
     */
    private void moveUP (boolean isDiagonal)
    {
        double deltaY = Constants_Map.PLAYER_SPEED * Constants_Map.SPEED_MULTIPLIER;
        if (isDiagonal) deltaY = deltaY * Constants_Map.ADJUST_DIAGONAL_MOVEMENT;
        this.newPlayerPosition.setPositionY(this.currentPlayerPosition.getPositionY() - deltaY);
    }
    
    
    /**
     * Method moves the character down. If isDiagonal is true, then the movement speed will be adjusted towards the
     * diagonal movement.
     *
     * @param isDiagonal
     * @author Michael Markov
     */
    private void moveDOWN (boolean isDiagonal)
    {
        double deltaY = Constants_Map.PLAYER_SPEED * Constants_Map.SPEED_MULTIPLIER;
        if (isDiagonal) deltaY = deltaY * Constants_Map.ADJUST_DIAGONAL_MOVEMENT;
        this.newPlayerPosition.setPositionY(this.currentPlayerPosition.getPositionY() + deltaY);
    }
    
    
    /**
     * Method moves the character right. If isDiagonal is true, then the movement speed will be adjusted towards the
     * diagonal movement.
     *
     * @param isDiagonal
     * @author Michael Markov
     */
    private void moveRIGHT (boolean isDiagonal)
    {
        double deltaX = Constants_Map.PLAYER_SPEED * Constants_Map.SPEED_MULTIPLIER;
        if (isDiagonal) deltaX = deltaX * Constants_Map.ADJUST_DIAGONAL_MOVEMENT;
        this.newPlayerPosition.setPositionX(this.currentPlayerPosition.getPositionX() + deltaX);
    }
    
    
    /**
     * Method moves the character left. If isDiagonal is true, then the movement speed will be adjusted towards the
     * diagonal movement.
     *
     * @param isDiagonal
     * @author Michael Markov
     */
    private void moveLEFT (boolean isDiagonal)
    {
        double deltaX = Constants_Map.PLAYER_SPEED * Constants_Map.SPEED_MULTIPLIER;
        if (isDiagonal) deltaX = deltaX * Constants_Map.ADJUST_DIAGONAL_MOVEMENT;
        this.newPlayerPosition.setPositionX(this.currentPlayerPosition.getPositionX() - deltaX);
    }
    
    
    /**
     * Used to set the player position to a specific coordinate using the instance of Coordinate.
     *
     * @param playerPosition
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
     * Getter for the current player position. Retrieves an instance of COordinate.
     *
     * @return
     * @author Michael Markov
     */
    public Coordinate getCurrentPlayerPosition ()
    {
        return currentPlayerPosition;
    }
    
    
    /**
     * Getter for player view. Retrieves an instance of OutputImageView.
     *
     * @return
     */
    public OutputImageView getPlayerView ()
    {
        return playerView;
    }
}
