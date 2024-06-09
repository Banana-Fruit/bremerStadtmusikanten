package control;


import control.events.KeyboardController;
import control.events.MouseController;
import control.game.PlayerController;
import control.scenes.MainMenuController;
import control.scenes.MapController;
import control.scenes.PanelController;
import control.scenes.SceneController;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Coordinate;
import model.Player;
import model.userInterface.showables.MainMenu;
import model.userInterface.showables.Map;
import model.userInterface.Game;
import resources.constants.Constants_ExceptionMessages;
import resources.constants.Constants_MainMenu;
import resources.constants.scenes.Constants_Map;
import view.OutputImageView;


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
    
    
    public static GameController getInstance ()
    {
        if (instance == null)
        {
            throw new IllegalStateException(Constants_ExceptionMessages.SINGLETON_NOT_INITIALIZED);
        }
        return instance;
    }
    
    
    private void init ()
    {
        this.stage.setTitle(Game.getInstance().getGameTitle());
        this.stage.setFullScreen(true);
        
        SceneController.initialize(this.stage);
        KeyboardController.initialize();
        MouseController.initialize();
        MainMenu.initialize(new Scene(new Pane(), Constants_MainMenu.SCENE_WIDTH, Constants_MainMenu.SCENE_HEIGHT));
        MainMenuController.initialize();
        MainMenuController.getInstance().startMainMenu(this.stage);
        PanelController.initialize();
        MapController.initialize();
        PlayerController.initialize();

        Map.initialize(new Scene(new Pane()));
        Player.initialize();
        
        MapController.getInstance().setNewMap("main.dat");
        PlayerController.getInstance().addPlayer(PanelController.getInstance().getCoordinateFromPanelTile(
                Map.getInstance().getPanel(), Constants_Map.STARTPOSITION_X, Constants_Map.STARTPOSITION_Y));
        System.out.println("HI");
        //Map durch MainMenu ersetzt
        Game.getInstance().setCurrentShowable(MainMenu.getInstance().getShowable());
        SceneController.getInstance().setScene(Game.getInstance().getCurrentShowable().getScene());
        
        new Thread(KeyboardController.getInstance()).start();
        new Thread(PlayerController.getInstance()).start();

        this.stage.setScene(Game.getInstance().getCurrentShowable().getScene());
        this.stage.show();
    }
}
