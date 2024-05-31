package control.scenes;


import control.game.PlayerController;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Player;
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
    private OutputImageView playerView;
    
    
    private MapController ()
    {
        init();
    }
    
    
    private void init()
    {
        playerView = new OutputImageView(new Image(Constants_Map.PLAYER_VIEW_STANDARD));
        Game.getInstance().getCurrentShowable().addChildToPane(playerView);
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
        
        update();
    }
    
    
    private void update ()
    {
        changePlayerPosition();
    }
    
    
    private void changePlayerPosition ()
    {
        playerView.updatePosition(PlayerController.getInstance().getPlayerPosition().getPositionX(),
                PlayerController.getInstance().getPlayerPosition().getPositionY());
    }
    
    
    public OutputImageView getPlayerView ()
    {
        return playerView;
    }
}
