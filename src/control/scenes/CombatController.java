package control.scenes;


import model.userInterface.showables.Combat;
import resources.constants.Constants_Combat;
import resources.constants.Constants_Resources;


/**
 * The combat controller is responsible for handling the combat arena.
 */
public class CombatController
{
    /**
     * Method switches to combat scene by loading a new arena.
     *
     * @param loaderFileName
     * @author Michael Markov
     */
    public static void startCombat (String loaderFileName)
    {
        setNewArena(loaderFileName); // Changes arena
        SceneController.getInstance().switchShowable(Combat.getInstance().getShowable()); // Switches scene to arena
    }
    
    
    /**
     * Sets a new arena for the map with the loader file name. Uses the folder resources/assets/combat/loaderFiles/
     *
     * @param loaderFileName
     * @author Michael Markov
     */
    private static void setNewArena (String loaderFileName)
    {
        // Arena consists of panel and buttons
        Combat.getInstance().setPanel(
                PanelController.getInstance().getAndShowPanelUsingChars(Combat.getInstance().getPane(),
                        Constants_Resources.СOMBAT_LOADER_FILES_FOLDER, loaderFileName, Constants_Combat.TILE_SIZE,
                        Constants_Combat.MAX_ROWS, Constants_Combat.MAX_COLUMNS
                )
        );
        Combat.getInstance().addTileButtons();
    }
}
