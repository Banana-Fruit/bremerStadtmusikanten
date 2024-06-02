package control.scenes;


import model.showables.Map;
import resources.constants.Constants_ExceptionMessages;
import resources.constants.scenes.Constants_Map;


/**
 * Responsible for player movement and interaction solely on the map.
 * A map controller contains one map instance and is a Singleton.
 * It is responsible to do pane switches, if the player leaves a specific part of the map.
 */
public class MapController
{
    private static volatile MapController instance = null;
    private final int tileSize = Constants_Map.TILE_SIZE;
    private final int maxRows = Constants_Map.MAX_ROWS;
    private final int maxColumns = Constants_Map.MAX_COLUMNS;
    
    
    private MapController ()
    {
        init();
    }
    
    
    private void init ()
    {
        setNewMap(Constants_Map.PLAYER_VIEW_STANDARD);
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
    
    
    // Method to retrieve the Singleton instance without parameters
    public static MapController getInstance ()
    {
        if (instance == null)
        {
            throw new IllegalStateException(Constants_ExceptionMessages.SINGLETON_NOT_INITIALIZED);
        }
        return instance;
    }
    
    
    public void setNewMap (String path)
    {
        Map.getInstance().setPanel(PanelController.getInstance().initializePanel(path, tileSize, maxRows, maxColumns));
    }
}
