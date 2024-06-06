package control;


import control.events.KeyboardController;
import control.events.MouseController;
import control.game.PlayerController;
import control.scenes.MapController;
import control.scenes.PanelController;
import control.scenes.SceneController;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Coordinate;
import model.Player;
import model.showables.MainMenu;
import model.showables.Map;
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
    
    
    public void init ()
    {
        SceneController.initialize(stage);
        KeyboardController.initialize();
        MouseController.initialize();
        Player.initialize();
        PlayerController.initialize(new Coordinate(Constants_Map.STARTPOSITION_X, Constants_Map.STARTPOSITION_Y));
        Map.initialize(new Scene(new Pane()));
        PanelController.initialize();
        MapController.initialize();
        
        new Thread(KeyboardController.getInstance()).start();
        new Thread(MouseController.getInstance()).start();
        new Thread(SceneController.getInstance()).start();
        new Thread(PlayerController.getInstance()).start();
        
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
