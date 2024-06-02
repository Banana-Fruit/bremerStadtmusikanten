package control.scenes;


import javafx.application.Platform;
import javafx.stage.Stage;
import model.userInterface.Game;
import resources.constants.Constants_ExceptionMessages;
import resources.constants.Constants_Game;


/**
 * This controller is responsible for scene switching
 */
public class SceneController implements Runnable
{
    private static SceneController instance = null;
    private Stage stage;
    private volatile boolean running = true;
    
    
    private SceneController (Stage stage)
    {
        this.stage = stage;
    }
    
    
    public static synchronized void initialize (Stage stage)
    {
        if (instance == null)
        {
            instance = new SceneController(stage);
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
    
    
    // Method to stop the running thread gracefully
    public void stop ()
    {
        running = false;
    }
    
    
    @Override
    public void run ()
    {
        while (running)
        {
            try
            {
                Thread.sleep(Constants_Game.THREAD_SLEEP_DEFAULT_TIME);
            } catch (InterruptedException e)
            {
                Thread.currentThread().interrupt(); // Restore the interrupted status
                throw new RuntimeException(e);
            }
            Platform.runLater(() ->
            {
                stage.setScene(Game.getInstance().getCurrentShowable().getScene());
            });
        }
    }
}