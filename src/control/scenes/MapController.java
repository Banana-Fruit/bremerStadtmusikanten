package control.scenes;

import control.BuildingController;
import control.game.PlayerController;
import control.game.UnitController;
import javafx.application.Platform;
import model.Coordinate;
import model.Unit;
import model.userInterface.showables.Map;
import resources.constants.Constants_ExceptionMessages;
import resources.constants.Constants_Resources;
import resources.constants.scenes.Constants_Map;

import java.util.HashMap;
import java.util.Map.Entry;

/**
 * Responsible for player movement and interaction solely on the map.
 * A map controller contains one map instance and is a Singleton.
 * It is responsible to do pane switches, if the player leaves a specific part of the map.
 */
public class MapController
{
    private static volatile MapController instance = null;
    private boolean dialogShown = false;


    private MapController () {}


    public static synchronized void initialize ()
    {
        if (instance == null)
        {
            instance = new MapController();
        } else
        {
            throw new IllegalStateException(Constants_ExceptionMessages.ALREADY_INITIALIZED);
        }
    }


    public void setNewMap (String loaderFileName)
    {
        Map.getInstance().setPanel(
                PanelController.getInstance().getAndShowPanel(Map.getInstance().getPane(),
                        Constants_Resources.MAP_LOADER_FILES_FOLDER_JONAS_MAP, loaderFileName, Constants_Map.TILE_SIZE,
                        Constants_Map.MAX_ROWS, Constants_Map.MAX_COLUMNS
                )
        );
    }

    public void checkMissionStart()
    {
        Coordinate current = PanelController.getInstance().getTileIndicesFromCoordinates(Map.getInstance().getPanel(), PlayerController.getInstance().getCurrentPlayerPosition());


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
            Platform.runLater(() -> BuildingController.getInstance().addButtons());
        }
    }

    private void switchToMission(String newMap, int tileX, int tileY)
    {
        Map.getInstance().setCurrentMapName(newMap);
        System.out.println(Constants_Map.CONSOLE_PRINT_SWITCHING_MAP + newMap);

        Platform.runLater(() -> {
            PlayerController playerController = PlayerController.getInstance();
            Map.getInstance().getPane().getChildren().remove(playerController.getPlayerView());
            SceneController.getInstance().switchShowable(Map.getInstance());
            setNewMap(newMap);
            Map.getInstance().getPane().getChildren().add(playerController.getPlayerView());
            playerController.setPlayerPosition(new Coordinate(PanelController.getInstance().getCoordinateFromPanelTile(Map.getInstance().getPanel(), tileX, tileY)));
        });
    }

    //TODO: Implement Combat start logic
    private void checkCombatStart()
    {
        PlayerController playerController = PlayerController.getInstance();
        Coordinate current = PanelController.getInstance().getTileIndicesFromCoordinates(Map.getInstance().getPanel(), playerController.getCurrentPlayerPosition());

        for (Entry<Coordinate, Unit> entry : UnitController.getInstance().getUnitPositions().entrySet())
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
        //TODO: Implement Combat
        UnitController.getInstance().removeUnit(coordinate);
    }

    public void checkProximityToUnits()
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
        PlayerController playerController = PlayerController.getInstance();
        Coordinate currentPlayerPosition = playerController.getCurrentPlayerPosition();

        for (Entry<Coordinate, Unit> entry : UnitController.getInstance().getUnitPositions().entrySet())
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
        SceneController.getInstance().createYesOrNoButton(Constants_Map.HEADER_JOIN_FIGHT, this::checkCombatStart);
    }

    // Method to retrieve the Singleton instance without parameters
    public static MapController getInstance ()
    {
        if (instance == null)
        {
            throw new IllegalStateException(Constants_ExceptionMessages.SINGLETON_NOT_INITIALIZED);
        }
        return instance;
    }
}
