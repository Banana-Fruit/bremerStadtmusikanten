package control.scenes;


import control.BuildingController;
import control.game.PlayerController;
import control.game.UnitController;
import javafx.application.Platform;
import javafx.scene.control.TableColumn;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import model.Coordinate;
import model.Unit;
import model.player.Inventory;
import model.userInterface.Game;
import model.userInterface.showables.Map;
import resources.constants.Constants_ExceptionMessages;
import resources.constants.Constants_Popup;
import resources.constants.Constants_Resources;
import resources.constants.scenes.Constants_Map;
import utility.popup.Popup;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;


/**
 * Responsible for player movement and interaction solely on the map. A map controller contains one map instance and is
 * a Singleton. It is responsible to do pane switches, if the player leaves a specific part of the map.
 *
 * @author Michael Markov
 */
public class MapController
{
    private static volatile MapController instance = null;
    private boolean dialogShown = false;
    private final HashMap<ImageView, String> rewardViews = new HashMap<>();
    
    
    private MapController ()
    {
    }
    
    
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
    
    
    /**
     * Loads a new map onto an existing map scene by loading a new panel from the loader file. The loader file should be
     * loacted in resources/assets/.
     *
     * @param loaderFileName Name of the LoaderFile that will determine the map.
     * @author Michael Markov
     */
    public void setNewMap (String loaderFileName, String biomeName)
    {
        String pathToLoaderFile = Constants_Resources.PATH_TO_LOADER_FILES_MAP + loaderFileName;
        String pathToTileResources = Constants_Resources.PATH_TO_MAP + biomeName;
        String pathToTileData = pathToTileResources + Constants_Resources.TILE_DATA_NAME;
        
        // Change panel
        Map.getInstance().setPanel(
                PanelController.getInstance().getAndShowPanel(Map.getInstance().getPane(), pathToLoaderFile,
                        pathToTileResources, pathToTileData, Constants_Map.TILE_SIZE, Constants_Map.MAX_ROWS, Constants_Map.MAX_COLUMNS));
    }


    /**
     * Checks if the player has reached a position to start a mission or return to the city.
     * This method handles the transition between different maps based on the player's position.
     * @author Jonas Helfer
     * @precondition The player's position is up-to-date and the current map is properly set
     * @postcondition The game switches to the appropriate map and sets up the necessary elements for the new map
     */
    public void checkMissionStart ()
    {
        Coordinate current = PanelController.getInstance().getTileIndicesFromCoordinates(Map.getInstance().getPanel(), PlayerController.getInstance().getCurrentPlayerPosition());
        
        
        // Uebergang zu Mission 1
        if (current.getPositionX() == Constants_Map.PLAYER_AT_LEFT_BORDER && Map.getInstance().getCurrentMapName().equals(Constants_Map.MAP_NAME_CITY))
        {
            switchToMission(Constants_Map.MAP_NAME_MISSION_1, Constants_Map.PLAYER_SPAWN_MISSION_1_POSITION_X, Constants_Map.PLAYER_SPAWN_MISSION_1_POSITION_Y);
            UnitController.getInstance().addUnitsMission1();
            addRewardsMission1();
        }
        // Return to City from Mission 1
        else if (current.getPositionX() > Constants_Map.PLAYER_FINISH_MISSION_1_POSITION_X && current.getPositionY() < Constants_Map.PLAYER_FINISH_MISSION_1_POSITION_Y && Map.getInstance().getCurrentMapName().equals(Constants_Map.MAP_NAME_MISSION_1))
        {
            switchToMission(Constants_Map.MAP_NAME_CITY, Constants_Map.PLAYER_SPAWN_CITY_POSITION_X, Constants_Map.PLAYER_SPAWN_CITY_POSITION_Y);
            Platform.runLater(() -> BuildingController.getInstance().addButtons());
        }
    }


    /**
     * Switches the game to a new map and repositions the player.
     * This method handles the transition between maps, including updating the UI and player position.
     * @author Jonas Helfer
     * @param newMap The name of the new map to switch to
     * @param tileX The x-coordinate of the tile where the player should spawn on the new map
     * @param tileY The y-coordinate of the tile where the player should spawn on the new map
     * @precondition newMap is a valid map name, and tileX and tileY are valid coordinates on the new map
     * @postcondition The game switches to the new map, and the player is positioned at the specified coordinates
     */
    private void switchToMission (String newMap, int tileX, int tileY)
    {
        Map.getInstance().setCurrentMapName(newMap);
        System.out.println(Constants_Map.CONSOLE_PRINT_SWITCHING_MAP + newMap);
        
        Platform.runLater(() ->
        {
            PlayerController playerController = PlayerController.getInstance();
            Map.getInstance().getPane().getChildren().remove(playerController.getPlayerView());
            SceneController.getInstance().switchShowable(Map.getInstance());
            setNewMap(newMap, Constants_Resources.BIOME_NAME_GRASSLANDS);
            Map.getInstance().getPane().getChildren().add(playerController.getPlayerView());
            playerController.setPlayerPosition(new Coordinate(PanelController.getInstance().getCoordinateFromPanelTile(Map.getInstance().getPanel(), tileX, tileY)));
        });
    }


    /**
     * Checks if the player is in proximity to start a combat.
     * TODO: Implement Combat start logic
     * @author Jonas Helfer
     * @precondition The player's position and unit positions are up-to-date
     * @postcondition If the player is near a unit, combat is initiated
     */
    private void checkCombatStart ()
    {
        PlayerController playerController = PlayerController.getInstance();
        Coordinate current = PanelController.getInstance().getTileIndicesFromCoordinates(Map.getInstance().getPanel(), playerController.getCurrentPlayerPosition());
        
        for (Entry<Coordinate, Unit> entry : UnitController.getInstance().getUnitPositions().entrySet())
        {
            Coordinate unitPosition = PanelController.getInstance().getTileIndicesFromCoordinates(Map.getInstance().getPanel(), entry.getKey());
            
            // Check proximity
            if (Math.abs(current.getPositionX() - unitPosition.getPositionX()) <= Constants_Map.PROXIMITY && Math.abs(current.getPositionY() - unitPosition.getPositionY()) <= Constants_Map.PROXIMITY)
            {
                startCombat(entry.getKey());
                return; // Assuming only one combat can start per move
            }
        }
    }


    /**
     * Initiates combat with a unit at the specified coordinate.
     * @author Jonas Helfer
     * @param coordinate The coordinate of the unit to engage in combat
     * @precondition A unit exists at the specified coordinate
     * @postcondition The unit is removed from the map and combat is started
     */
    private void startCombat (Coordinate coordinate)
    {
        UnitController.getInstance().removeUnit(coordinate);
        CombatController.getInstance().startCombat(Constants_Resources.LOADER_FILE_NAME_COMBAT, Constants_Resources.BIOME_NAME_GRASSLANDS);
    }


    /**
     * Checks if the player is near any units and shows a dialog if so.
     * @author Jonas Helfer
     * @precondition The player's position and unit positions are up-to-date
     * @postcondition A dialog is shown if the player is near a unit and hasn't been shown before
     */
    public void checkProximityToUnits ()
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


    /**
     * Checks if the player is within a certain distance of any unit.
     * @author Jonas Helfer
     * @return true if the player is near a unit, false otherwise
     * @precondition The player's position and unit positions are up-to-date
     * @postcondition The proximity of the player to units is determined
     */
    public boolean isNear ()
    {
        double distanceThreshold = Constants_Map.DISTANCE_THRESHOLD;
        PlayerController playerController = PlayerController.getInstance();
        Coordinate currentPlayerPosition = playerController.getCurrentPlayerPosition();
        
        for (Entry<Coordinate, Unit> entry : UnitController.getInstance().getUnitPositions().entrySet())
        {
            Coordinate unitPosition = entry.getKey();
            
            double distance = Math.sqrt(Math.pow(
                    unitPosition.getPositionX() - currentPlayerPosition.getPositionX(),
                    Constants_Map.DISTANCE_TO_UNIT_POW) +
                    Math.pow(unitPosition.getPositionY() -
                                    currentPlayerPosition.getPositionY(),
                    Constants_Map.DISTANCE_TO_UNIT_POW));
            if (distance < distanceThreshold)
            {
                return true;
            }
        }
        return false;
    }
    
    
    /**
     * Shows dialog for an action. Either to confirm a choice, or to ask the player something.
     *
     * @author Michael Markov
     */
    private void showDialog ()
    {
        // Popup appears asking for combat start
        Popup.createPopupWithAction(Game.getInstance().getCurrentShowable().getPane(), Constants_Map.HEADER_JOIN_FIGHT, new Runnable()
        {
            @Override
            public void run ()
            {
            
            }
        }, new Runnable()
        {
            @Override
            public void run ()
            {
                CombatController.getInstance().startCombat(Constants_Resources.LOADER_FILE_NAME_COMBAT, Constants_Resources.BIOME_NAME_GRASSLANDS);
            }
        }, Constants_Popup.NO, Constants_Popup.YES,
                Constants_Popup.TEXT_TO_BUTTONS_SPACING,
                Constants_Popup.POPUP_WIDTH,
                Constants_Popup.POPUP_HEIGHT,
                Constants_Popup.defaultBackgroundColor);
    }


    /**
     * Adds rewards for Mission 1 to the game map.
     * This method is called to set up the rewards for the first mission.
     * @author Jonas Helfer
     * @precondition The game map is initialized and ready to accept rewards.
     *               The Constants_Map class contains all necessary constants for reward positions.
     *               The PanelController, Map, and Inventory classes are properly initialized.
     * @postcondition Rewards for Mission 1 are added to the game map at predefined locations.
     *                The rewardViews HashMap is updated with new reward ImageViews and their types.
     */
    public void addRewardsMission1 ()
    {
        Platform.runLater(() ->
        {
            HashMap<Coordinate, String> rewards = getRewardLocationsForMission1(); // Get predefined reward locations and types
            
            for (HashMap.Entry<Coordinate, String> entry : rewards.entrySet())
            {
                setRewardPosition(entry.getKey(), entry.getValue());
            }
        });
    }


    /**
     * the reward locations and types for Mission 1.
     * @author Jonas Helfer
     * @precondition The Constants_Map class contains all necessary constants for reward positions.
     * @postcondition A HashMap is created and populated with predefined reward locations and types.
     * @return A HashMap containing Coordinate objects as keys and reward types as String values.
     */
    private HashMap<Coordinate, String> getRewardLocationsForMission1 ()
    {
        HashMap<Coordinate, String> rewardLocations = new HashMap<>();
        
        rewardLocations.put(new Coordinate(Constants_Map.REWARD_1_POSITION_X, Constants_Map.REWARD_1_POSITION_Y), Constants_Map.WOOD);
        rewardLocations.put(new Coordinate(Constants_Map.REWARD_2_POSITION_X, Constants_Map.REWARD_2_POSITION_Y), Constants_Map.BEER);
        rewardLocations.put(new Coordinate(Constants_Map.REWARD_3_POSITION_X, Constants_Map.REWARD_3_POSITION_Y), Constants_Map.BRICK);
        rewardLocations.put(new Coordinate(Constants_Map.REWARD_4_POSITION_X, Constants_Map.REWARD_4_POSITION_Y), Constants_Map.ESSENCE);
        rewardLocations.put(new Coordinate(Constants_Map.REWARD_5_POSITION_X, Constants_Map.REWARD_5_POSITION_Y), Constants_Map.GOLD);
        
        return rewardLocations;
    }


    /**
     * Sets the position of a reward on the game map.
     * @author Jonas Helfer
     * @param tileCoordinate The coordinate of the tile where the reward should be placed.
     * @param rewardType The type of reward to be placed.
     * @precondition The PanelController, Map, and Constants_Map classes are properly initialized.
     *               The tileCoordinate is a valid coordinate within the game map.
     *               The rewardType is a valid string representing one of the possible reward types.
     *               The Constants_Map.REWARD_IMAGE_PATH points to a valid image resource.
     * @postcondition new ImageView representing the reward is created and added to the game map at the specified position.
     * The rewardViews HashMap is updated with the new reward.
     */
    private void setRewardPosition (Coordinate tileCoordinate, String rewardType)
    {
        Coordinate coordinate = new Coordinate(PanelController.getInstance().getCoordinateFromPanelTile(Map.getInstance().getPanel(), (int) tileCoordinate.getPositionX(), (int) tileCoordinate.getPositionY()));
        ImageView rewardView = new ImageView(Constants_Map.REWARD_IMAGE_PATH);
        rewardView.setFitWidth(Constants_Map.TILE_SIZE);
        rewardView.setFitHeight(Constants_Map.TILE_SIZE);
        rewardView.setLayoutX(coordinate.getPositionX());
        rewardView.setLayoutY(coordinate.getPositionY());
        
        Map.getInstance().getPane().getChildren().add(rewardView);
        rewardViews.put(rewardView, rewardType);
    }


    /**
     * Checks and collects rewards when the player moves over them.
     * This method iterates through all rewards on the map and collects them if the player is in range.
     * @author Jonas Helfer
     * @precondition The reward is properly initialized and contains all current rewards.
     *               The PlayerController, DisplayController, and related.
     * @postcondition Rewards that the player has moved over are collected, removed from the map,
     *                an's inventory is updated accordingly.
     */
    public void checkRewardCollection ()
    {
        Iterator<HashMap.Entry<ImageView, String>> iterator = rewardViews.entrySet().iterator();
        while (iterator.hasNext())
        {
            HashMap.Entry<ImageView, String> entry = iterator.next();
            ImageView rewardView = entry.getKey();
            String rewardType = entry.getValue();
            
            if (isPlayerOnReward(rewardView))
            {
                collectReward(rewardView, rewardType);
                DisplayController.getInstance().updateInventory();
                iterator.remove();
            }
        }
    }


    /**
     * Determines if the player is within the collection radius of a reward.
     * @author Jonas Helfer
     * @param rewardView The ImageView representing the reward to check.
     * @precondition The PlayerController is properly initialized and can provide the current player position.
     *               The rewardView parameter is not null and represents a valid reward on the map.
     * @postcondition The method returns true if the player is within the collection radius of the reward, false otherwise.
     * @return boolean True if the player is on the reward, false otherwise.
     */
    private boolean isPlayerOnReward (ImageView rewardView)
    {
        Coordinate playerPosition = PlayerController.getInstance().getCurrentPlayerPosition();
        double playerX = playerPosition.getPositionX();
        double playerY = playerPosition.getPositionY();
        double rewardX = rewardView.getLayoutX();
        double rewardY = rewardView.getLayoutY();
        
        return Math.abs(playerX - rewardX) < Constants_Map.REWARD_COLLECTION_RADIUS && Math.abs(playerY - rewardY) < Constants_Map.REWARD_COLLECTION_RADIUS;
    }


    /**
     * Collects a reward, removing it from the map and updating the player's inventory.
     * @author Jonas Helfer
     * @param rewardView The ImageView of the reward to be collected.
     * @param rewardType The type of the reward being collected.
     * @precondition The Map and Inventory instances are properly initialized.
     *               The rewardView is a valid ImageView present on the map.
     *               The rewardType is a valid string representing one of the possible reward types.
     * @postcondition The reward is removed from the map.
     *                The player's inventory is updated based on the collected reward type.
     * @throws IllegalArgumentException if an unknown reward type is encountered.
     */
    private void collectReward (ImageView rewardView, String rewardType)
    {
        Pane mapPane = Map.getInstance().getPane();
        mapPane.getChildren().remove(rewardView);
        
        // Update the player's inventory based on the reward type
        Inventory inventory = Inventory.getInstanceOfInventory();
        switch (rewardType)
        {
            case Constants_Map.GOLD:
                inventory.setInventoryGold(inventory.getInventoryGold() + Constants_Map.REWARD_GOLD_AMOUNT);
                break;
            case Constants_Map.BEER:
                inventory.setInventoryBeer(inventory.getInventoryBeer() + Constants_Map.REWARD_BEER_AMOUNT);
                break;
            case Constants_Map.BRICK:
                inventory.setInventoryBrick(inventory.getInventoryBrick() + Constants_Map.REWARD_BRICK_AMOUNT);
                break;
            case Constants_Map.ESSENCE:
                inventory.setInventoryEssence(inventory.getInventoryEssence() + Constants_Map.REWARD_ESSENCE_AMOUNT);
                break;
            case Constants_Map.WOOD:
                inventory.setInventoryWood(inventory.getInventoryWood() + Constants_Map.REWARD_WOOD_AMOUNT);
                break;
            default:
                throw new IllegalArgumentException(Constants_Map.UNKNOWN_REWARD + rewardType);
        }
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
