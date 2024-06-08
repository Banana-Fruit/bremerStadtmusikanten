package control.scenes;


import model.userInterface.showables.Map;
import resources.constants.Constants_ExceptionMessages;
import resources.constants.Constants_Resources;
import resources.constants.scenes.Constants_Map;


/**
 * Responsible for player movement and interaction solely on the map.
 * A map controller contains one map instance and is a Singleton.
 * It is responsible to do pane switches, if the player leaves a specific part of the map.
 */
public class MapController
{
    private static volatile MapController instance = null;
    
    
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
    
    
    // Method to retrieve the Singleton instance without parameters
    public static MapController getInstance ()
    {
        if (instance == null)
        {
            throw new IllegalStateException(Constants_ExceptionMessages.SINGLETON_NOT_INITIALIZED);
        }
        return instance;
    }
    
    
    public void setNewMap (String loaderFileName)
    {
        Map.getInstance().setPanel(
                PanelController.getInstance().initializePanel(Constants_Resources.MAP_LOADER_FILES_FOLDER,
                        loaderFileName, Constants_Map.TILE_SIZE, Constants_Map.MAX_ROWS, Constants_Map.MAX_COLUMNS
                )
        );
    }
}
