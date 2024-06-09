package control.scenes;


import javafx.scene.Scene;
import javafx.stage.Stage;
import model.userInterface.showables.Showable;
import resources.constants.Constants_ExceptionMessages;


/**
 * This controller is responsible for scene switching
 */
public class SceneController
{
    private static SceneController instance = null;
    private final Stage stage;
    
    
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
    
    
    public void setScene (Scene scene)
    {
        this.stage.setScene(scene);
    }
}