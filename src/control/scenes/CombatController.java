package control.scenes;


import javafx.application.Platform;
import model.userInterface.showables.Combat;
import resources.constants.Constants_Combat;
import resources.constants.Constants_Game;
import resources.constants.Constants_Resources;


public class CombatController
{
    public static void startCombat (String loaderFileName)
    {
        setNewArena(loaderFileName);
        try
        {
            Thread.sleep(Constants_Game.THREAD_SLEEP_DEFAULT_TIME);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        SceneController.getInstance().switchShowable(Combat.getInstance().getShowable());
    }
    
    
    private static void setNewArena (String loaderFileName)
    {
        Combat.getInstance().setPanel(
                PanelController.getInstance().getAndShowPanelUsingChars(Combat.getInstance().getPane(),
                        Constants_Resources.СOMBAT_LOADER_FILES_FOLDER, loaderFileName, Constants_Combat.TILE_SIZE,
                        Constants_Combat.MAX_ROWS, Constants_Combat.MAX_COLUMNS
                )
        );
        Combat.getInstance().addTileButtons();
    }
}
