package control;


import control.events.KeyboardController;
import control.events.MouseController;
import control.game.PlayerController;
import control.scenes.MainMenuController;
import control.scenes.MapController;
import control.scenes.SceneController;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Coordinates;
import model.Player;
import model.showables.MainMenu;
import model.userInterface.Game;
import resources.constants.Constants_ExceptionMessages;
import resources.constants.scenes.Constants_Map;


public class GameController implements Runnable
{
    private static volatile GameController instance = null;
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
    
    
    private void init ()
    {
        SceneController.initialize(this.stage);
        KeyboardController.initialize();
        MainMenuController.initialize();
        MainMenu.initialize(new Scene(new Pane()));
        MainMenuController.getInstance().startMainMenu(this.stage);
        
        new Thread(SceneController.getInstance()).start();
        
        Game.getInstance().setCurrentShowable(MainMenu.getInstance());
        this.stage.setTitle(Game.getInstance().getGameTitle());
        this.stage.show();
    }
    
    
    @Override
    public void run ()
    {
        // Should put Threads to sleep and notify them of changes, to wake them
    }
}
