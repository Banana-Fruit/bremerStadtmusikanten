package control;


import control.events.KeyboardController;
import control.game.PlayerController;
import control.scenes.MapController;
import control.scenes.PanelController;
import control.scenes.SceneController;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Coordinate;
import model.Player;
import model.userInterface.showables.Map;
import model.userInterface.Game;
import resources.constants.Constants_ExceptionMessages;
import resources.constants.scenes.Constants_Map;


public class GameController
{
    private static volatile GameController instance = null;
    private final Stage stage;
    
    
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
        //MainMenuController.initialize();
        //MainMenu.initialize(new Scene(new Pane()));
        //MainMenuController.getInstance().startMainMenu(this.stage);
        Map.initialize(new Scene(new Pane()));
        PanelController.initialize();
        MapController.initialize();
        MapController.getInstance().setNewMap("main.dat");
        Player.initialize();
        PlayerController.initialize(new Coordinate(Constants_Map.STARTPOSITION_X, Constants_Map.STARTPOSITION_Y));
        
        new Thread(SceneController.getInstance()).start();
        
        Game.getInstance().setCurrentShowable(Map.getInstance());
        this.stage.setTitle(Game.getInstance().getGameTitle());
        this.stage.show();
    }
}
