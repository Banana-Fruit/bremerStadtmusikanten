package control;


import control.events.KeyboardController;
import control.events.MouseController;
import control.game.CombatController;
import control.game.PlayerController;
import control.game.UnitController;
import control.scenes.*;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.userInterface.showables.Combat;
import model.userInterface.showables.LoadGame;
import model.userInterface.showables.MainMenu;
import model.userInterface.Game;
import model.userInterface.showables.Map;
import resources.constants.Constants_ExceptionMessages;
import resources.constants.scenes.Constants_MainMenu;


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
        
        // Initialise controllers
        SceneController.initialize(this.stage);
        KeyboardController.initialize();
        MouseController.initialize();
        PanelController.initialize();
        MapController.initialize();
        PlayerController.initialize();
        BuildingController.initialize();
        DisplayController.initialize();
        UnitController.initialize();
        CombatController.initialize();

        // Initialise models
        Combat.initialize(new Scene(SceneController.getInstance().getBasePane()));
        MainMenu.initialize(new Scene(SceneController.getInstance().getBasePane()));
        LoadGame.initialize(new Scene(SceneController.getInstance().getBasePane(),
                Constants_MainMenu.SCENE_WIDTH, Constants_MainMenu.SCENE_HEIGHT));
        Map.initialize(new Scene(SceneController.getInstance().getBasePane()));

        SceneController.getInstance().switchShowable(MainMenu.getInstance());
        this.stage.show();
    }
}
