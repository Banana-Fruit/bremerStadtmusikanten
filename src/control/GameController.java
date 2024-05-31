package control;


import control.events.KeyboardController;
import control.events.MouseController;
import control.scenes.MapController;
import control.scenes.SceneController;
import javafx.stage.Stage;
import model.userInterface.Game;
import resources.constants.Constants_ExceptionMessages;


public class GameController implements Runnable
{
    private static volatile GameController instance;
    private Stage stage;
    
    
    private GameController (Stage stage)
    {
        this.stage = stage;
        init();
    }
    
    
    public static void initialize (Stage stage)
    {
        if (instance == null)
        {
            instance = new GameController(stage);
        } else
        {
            throw new IllegalStateException(Constants_ExceptionMessages.ALREADY_INITIALIZED);
        }
    }
    
    
    public static GameController getInstance()
    {
        if (instance == null)
        {
            throw new IllegalStateException(Constants_ExceptionMessages.SINGLETON_NOT_INITIALIZED);
        }
        return instance;
    }
    
    
    public void init ()
    {
        KeyboardController.initialize();
        MouseController.initialize();
        SceneController.initialize(stage);
        MapController.initialize();
        
        new Thread(KeyboardController.getInstance()).start();
        new Thread(MouseController.getInstance()).start();
        new Thread(SceneController.getInstance()).start();
        
        this.stage.setTitle(Game.getInstance().getGameTitle());
        this.stage.show();
    }
    
    
    @Override
    public void run ()
    {
        // Should put Threads to sleep and notify them of changes, to wake them
    }
}
