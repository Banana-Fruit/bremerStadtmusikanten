package control.game;


import control.BuildingController;
import control.events.KeyboardController;
import control.scenes.MapController;
import control.scenes.PanelController;
import control.scenes.SceneController;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import model.Coordinate;
import model.player.Inventory;
import model.userInterface.showables.Map;
import model.userInterface.Game;
import resources.constants.Constants_ExceptionMessages;
import resources.constants.Constants_Game;
import resources.constants.Constants_Keymapping;
import resources.constants.Constants_Resources;
import resources.constants.scenes.Constants_Map;
import view.OutputImageView;

import java.util.Set;


/**
 * The PlayerController handles player movement.
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

    //Todo: Nur zum testen die Methode eingefügt
    public void setPlayerInventory()
    {
        Inventory.getInstanceOfInventory().setInventoryGold(10);
        Inventory.getInstanceOfInventory().setInventoryBeer(10);
        Inventory.getInstanceOfInventory().setInventoryBrick(10);
        Inventory.getInstanceOfInventory().setInventoryEssence(10);
        Inventory.getInstanceOfInventory().setInventoryWood(10);
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
    public void run() {
        while (true) {
            try {
                Platform.runLater(() -> checkMissionStart());
                Thread.sleep(Constants_Game.THREAD_SLEEP_DEFAULT_TIME);
                if (!this.currentPlayerPosition.isEqual(this.newPlayerPosition)) {
                    // Check if vertical movement is possible
                    boolean canMoveVertically = !PanelController.getInstance().isVerticalMoveBlocked(Map.getInstance().getPanel(), currentPlayerPosition, newPlayerPosition);
                    if (canMoveVertically) {
                        this.currentPlayerPosition.setPositionY(this.newPlayerPosition.getPositionY());
                    }

                    // Check if horizontal movement is possible
                    boolean canMoveHorizontally = !PanelController.getInstance().isHorizontalMoveBlocked(Map.getInstance().getPanel(), currentPlayerPosition, newPlayerPosition);
                    if (canMoveHorizontally) {
                        this.currentPlayerPosition.setPositionX(this.newPlayerPosition.getPositionX());
                    }

                    // Update player position
                    setPlayerPosition(this.currentPlayerPosition);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            } catch (Exception e) {
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

    public void checkMissionStart()
    {
        Coordinate current = PanelController.getInstance().getTileIndicesFromCoordinates(Map.getInstance().getPanel(), currentPlayerPosition);


        // Uebergang zu Mission 1
        if (current.getPositionX() == 0.0 && Map.getInstance().getCurrentMapName().equals("City"))
        {

            switchToMission("Mission_1", 24, 49);
        }
        // Return to City from Mission 1
        else if (current.getPositionX() > 45 && current.getPositionY() < 7 && Map.getInstance().getCurrentMapName().equals("Mission_1"))
        {
            switchToMission("City", 25, 25);
            Platform.runLater(()-> BuildingController.getInstance().addButtons());

        }
    }

    private void switchToMission(String newMap, int tileX, int tileY)
    {
        Map.getInstance().setCurrentMapName(newMap);
        System.out.println("Switching to map: " + newMap);

        Platform.runLater(() -> {
            Map.getInstance().getPane().getChildren().remove(playerView);
            SceneController.getInstance().switchShowable(Map.getInstance());
            MapController.getInstance().setNewMap(newMap);
            Map.getInstance().getPane().getChildren().add(playerView);
            setPlayerPosition(new Coordinate(PanelController.getInstance().getCoordinateFromPanelTile(Map.getInstance().getPanel(), tileX, tileY)));
        });
    }
}
