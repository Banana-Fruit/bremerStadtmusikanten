package control.scenes;


import model.userInterface.showables.Combat;
import resources.constants.Constants_Combat;
import resources.constants.Constants_ExceptionMessages;
import resources.constants.Constants_Resources;


/**
 * The combat controller is responsible for handling the combat arena.
 */
public class CombatController
{
    private static volatile CombatController instance = null;
    
    
    private CombatController ()
    {
    }
    
    
    public static synchronized void initialize ()
    {
        if (instance == null)
        {
            instance = new CombatController();
        } else
        {
            throw new IllegalStateException(Constants_ExceptionMessages.ALREADY_INITIALIZED);
        }
    }
    
    
    public static CombatController getInstance ()
    {
        if (instance == null)
        {
            throw new IllegalStateException(Constants_ExceptionMessages.SINGLETON_NOT_INITIALIZED);
        }
        return instance;
    }
    
    
    /**
     * Method switches to combat scene by loading a new arena.
     *
     * @param loaderFileName Name of the LoaderFile that will determine the arena.
     * @author Michael Markov
     */
    public void startCombat (String loaderFileName, String biomeName)
    {
        setNewArena(loaderFileName, biomeName); // Changes arena
        SceneController.getInstance().switchShowable(Combat.getInstance().getShowable()); // Switches scene to arena
    }
    
    
    /**
     * Sets a new arena for the map with the loader file name. Uses the folder resources/assets/combat/loaderFiles/
     *
     * @param loaderFileName Name of the LoaderFile that will determine the arena.
     * @param biomeName      Name of the biome.
     * @author Michael Markov
     */
    public void setNewArena (String loaderFileName, String biomeName)
    {
        String pathToLoaderFile = Constants_Resources.PATH_TO_LOADER_FILES_COMBAT + loaderFileName;
        String pathToTileResources = Constants_Resources.PATH_TO_COMBAT + biomeName;
        String pathToTileData = pathToTileResources + Constants_Resources.TILE_DATA_NAME;
        
        // Change panel
        Combat.getInstance().setPanel(
                PanelController.getInstance().getAndShowPanel(Combat.getInstance().getPane(), pathToLoaderFile,
                        pathToTileResources, pathToTileData, Constants_Combat.TILE_SIZE, Constants_Combat.MAX_ROWS,
                        Constants_Combat.MAX_COLUMNS));
        
        // Add buttons
        Combat.getInstance().addTileButtons();
    }
}
