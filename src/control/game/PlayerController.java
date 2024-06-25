package control.game;


import control.BuildingController;
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
import java.util.HashMap;
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
    private boolean dialogShown = false;
    
    
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


    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(Constants_Game.THREAD_SLEEP_DEFAULT_TIME);

                if (!this.currentPlayerPosition.isEqual(this.newPlayerPosition)) {
                    Coordinate targetPosition = new Coordinate(this.newPlayerPosition.getPositionX(), this.newPlayerPosition.getPositionY());

                    // Check if vertical movement is possible
                    if (!PanelController.getInstance().isVerticalMoveBlocked(Map.getInstance().getPanel(), this.currentPlayerPosition, targetPosition)) {
                        this.currentPlayerPosition.setPositionY(targetPosition.getPositionY());
                    }

                    // Check if horizontal movement is possible
                    if (!PanelController.getInstance().isHorizontalMoveBlocked(Map.getInstance().getPanel(), this.currentPlayerPosition, targetPosition)) {
                        this.currentPlayerPosition.setPositionX(targetPosition.getPositionX());
                    }

                    // Update player position
                    setPlayerPosition(new Coordinate(this.currentPlayerPosition.getPositionX(), this.currentPlayerPosition.getPositionY()));

                    // Check for mission start and proximity to units
                    Platform.runLater(() -> {
                        checkMissionStart();
                        checkProximityToUnits();
                    });
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            } catch (Exception e) {
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
        if (current.getPositionX() == Constants_Map.PLAYER_AT_LEFT_BORDER && Map.getInstance().getCurrentMapName().equals(Constants_Map.MAP_NAME_CITY))
        {
            switchToMission(Constants_Map.MAP_NAME_MISSION_1, Constants_Map.PLAYER_SPAWN_MISSION_1_POSITION_X, Constants_Map.PLAYER_SPAWN_MISSION_1_POSITION_Y);
            UnitController.getInstance().addUnitsMission1();
        }
        // Return to City from Mission 1
        else if (current.getPositionX() > Constants_Map.PLAYER_FINISH_MISSION_1_POSITION_X && current.getPositionY()
                < Constants_Map.PLAYER_FINISH_MISSION_1_POSITION_Y && Map.getInstance().getCurrentMapName().equals(Constants_Map.MAP_NAME_MISSION_1))
        {
            switchToMission(Constants_Map.MAP_NAME_CITY, Constants_Map.PLAYER_SPAWN_CITY_POSITION_X, Constants_Map.PLAYER_SPAWN_CITY_POSITION_Y);
            Platform.runLater(()-> BuildingController.getInstance().addButtons());

        }
    }

    private void switchToMission(String newMap, int tileX, int tileY)
    {
        Map.getInstance().setCurrentMapName(newMap);
        System.out.println(Constants_Map.CONSOLE_PRINT_SWITCHING_MAP + newMap);

        Platform.runLater(() -> {
            Map.getInstance().getPane().getChildren().remove(playerView);
            SceneController.getInstance().switchShowable(Map.getInstance());
            MapController.getInstance().setNewMap(newMap);
            Map.getInstance().getPane().getChildren().add(playerView);
            setPlayerPosition(new Coordinate(PanelController.getInstance().getCoordinateFromPanelTile(Map.getInstance().getPanel(), tileX, tileY)));
        });
    }

    //TODO brauchen position für kampf start. Muss noch SwitchToCombat aufrufen
    private void checkCombatStart()
    {
        Coordinate current = PanelController.getInstance().getTileIndicesFromCoordinates(Map.getInstance().getPanel(), currentPlayerPosition);

        for (HashMap.Entry<Coordinate, Unit> entry : UnitController.getInstance().getUnitPositions().entrySet())
        {
            Coordinate unitPosition = PanelController.getInstance().getTileIndicesFromCoordinates(Map.getInstance().getPanel(), entry.getKey());

            // Check proximity
            if (Math.abs(current.getPositionX() - unitPosition.getPositionX()) <= Constants_Map.PROXIMITY &&
                    Math.abs(current.getPositionY() - unitPosition.getPositionY()) <= Constants_Map.PROXIMITY) {
                startCombat(entry.getKey());
                return; // Assuming only one combat can start per move
            }
        }
    }


    private void startCombat(Coordinate coordinate)
    {
        //Todo: Implement Combat
        UnitController.getInstance().removeUnit(coordinate);
    }
    private void checkProximityToUnits()
    {
        if (isNear())
        {
            if (!dialogShown)
            {
                dialogShown = true;
                showDialog();
            }
        } else
        {
            dialogShown = false;
        }
    }

    public boolean isNear()
    {
        double distanceThreshold = Constants_Map.DISTANCE_THRESHOLD; // Define an appropriate threshold value
        for (java.util.Map.Entry<Coordinate, Unit> entry : UnitController.getInstance().getUnitPositions().entrySet())
        {
            Coordinate unitPosition = entry.getKey();

            double distance = Math.sqrt(
                    Math.pow(unitPosition.getPositionX() - currentPlayerPosition.getPositionX(), Constants_Map.DISTANCE_TO_UNIT_POW) +
                            Math.pow(unitPosition.getPositionY() - currentPlayerPosition.getPositionY(), Constants_Map.DISTANCE_TO_UNIT_POW)
            );
            if (distance < distanceThreshold)
            {
                return true;
            }
        }
        return false;
    }

    private void showDialog()
    {
        if (SceneController.getInstance().isDialogShown())
        {
            return;
        }
        SceneController.getInstance().createYesOrNoButton(Constants_Map.HEADER_JOIN_FIGHT, () -> {
            checkCombatStart();
        });
    }

    public static void addUnitToTheTeam (Unit unit)
    {
        ArrayList<Unit> team = Player.getInstance().getTeamMembers();

        team.add(unit);

        //System.out.println(team.toString());
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
}
