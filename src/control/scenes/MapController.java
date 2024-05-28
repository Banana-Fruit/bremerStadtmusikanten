package control.scenes;


import model.userInterface.Game;
import resources.constants.Constants_ExceptionMessages;
import resources.constants.Constants_Game;
import resources.constants.scenes.Constants_Map;
import view.OutputImageView;


/**
 * Responsible for player movement and interaction solely on the map.
 * A map controller contains one map instance and is a Singleton.
 * It is responsible to do pane switches, if the player leaves a specific part of the map.
 */
public class MapController implements Runnable
{
    private static volatile MapController instance;
    private static volatile Game game;
    private PanelController panelController;
    private OutputImageView playerView;
    
    
    private MapController (Game game, PanelController panelController)
    {
        this.game = game;
        this.panelController = panelController;
    }
    
    
    public static synchronized void initialize (Game game, PanelController panelController)
    {
        if (instance == null)
        {
            instance = new MapController(game, panelController);
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
    
    
    @Override
    public void run ()
    {
        try
        {
            Thread.sleep(Constants_Game.THREAD_SLEEP_DEFAULT_TIME);
        } catch (InterruptedException e)
        {
            throw new RuntimeException(e);
        }
        
        //update(); to move a player on the Map
        //repaint();
    }
    
    
    public OutputImageView getPlayerView ()
    {
        return playerView;
    }
}
