package control.scenes;


import javafx.stage.Stage;
import model.userInterface.Game;
import resources.Constants_ExceptionMessages;
import resources.Constants_Game;


/**
 * This controller is responsible for scene switching
 */
public class SceneController implements Runnable
{
    private static volatile SceneController instance;
    private static Game game;
    private Stage stage;
    
    
    private SceneController (Game game, Stage stage)
    {
        this.game = game;
        this.stage = stage;
    }
    
    
    public static synchronized void initialize (Game game, Stage stage)
    {
        if (instance == null)
        {
            instance = new SceneController(game, stage);
        } else
        {
            throw new IllegalStateException(Constants_ExceptionMessages.ALREADY_INITIALIZED);
        }
    }
    
    
    // Method to retrieve the Singleton instance without parameters
    public static SceneController getInstance ()
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
        while (true)
        {
            try
            {
                Thread.sleep(Constants_Game.THREAD_SLEEP_DEFAULT_TIME);
            } catch (InterruptedException e)
            {
                throw new RuntimeException(e);
            }
            
            this.stage.setScene(game.getCurrentShowable().getScene());
        }
    }
}
