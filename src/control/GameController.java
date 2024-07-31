package control;


import control.game.PlayerController;
import control.game.UnitController;
import control.scenes.*;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.player.Player;
import model.userInterface.showables.*;
import model.userInterface.Game;
import resources.constants.Constants_ExceptionMessages;
import resources.constants.Constants_Resources;
import resources.constants.scenes.Constants_MainMenu;
import resources.constants.scenes.Constants_Map;


/**
 * The game controller is supposed to handle everything related to the game. Meaning starting a new game.
 *
 * @author Michael Markov
 */
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
    
    
    /**
     * Private initializer for the GameController. Initializes all the controllers required, for the application to
     * work.
     *
     * @author Michael Markov
     */
    private void init ()
    {
        this.stage.setTitle(Game.getInstance().getGameTitle());
        
        // Initialise controllers
        SceneController.initialize(this.stage);
        KeyboardController.initialize();
        PanelController.initialize();
        MapController.initialize();
        PlayerController.initialize();
        BuildingController.initialize();
        DisplayController.initialize();
        UnitController.initialize();
        MultiplayerController.initialize();
        ArenaController.initialize();
        
        // Initialise base models
        Combat.initialize(new Scene(SceneController.getInstance().getBasePane()));
        MainMenu.initialize(new Scene(SceneController.getInstance().getBasePane()));
        LoadGame.initialize(new Scene(SceneController.getInstance().getBasePane(),
                Constants_MainMenu.SCENE_WIDTH, Constants_MainMenu.SCENE_HEIGHT));
        Map.initialize(new Scene(SceneController.getInstance().getBasePane()));
        Settings.initialize(new Scene(SceneController.getInstance().getBasePane()));
        
        // Switch scene to main menu
        SceneController.getInstance().switchShowable(MainMenu.getInstance());
        this.stage.show();
    }
    
    
    /**
     * Method used to create a new game. It initializes controllers and switches scenes.
     * Additionally, the necessary threads will be started.
     *
     * @author Michael Markov
     */
    public void newGame ()
    {
        SceneController.getInstance().switchShowable(Map.getInstance()); // Switch to map
        Map.getInstance().setCurrentMapName(Constants_Map.MAP_NAME_CITY);
        // Set starting map
        MapController.getInstance().setNewMap(Constants_Map.MAP_NAME_CITY, Constants_Resources.BIOME_NAME_GRASSLANDS);
        Player.initialize();
        
        BuildingController.getInstance().addButtons();
        // Add player to map
        PlayerController.getInstance().addPlayer(PanelController.getInstance().getCoordinateFromPanelTile(
                Map.getInstance().getPanel(), Constants_Map.STARTPOSITION_X, Constants_Map.STARTPOSITION_Y));
        // Define player inventory
        PlayerController.getInstance().setPlayerInventory();
        // Add inventory to pane
        Map.getInstance().getPane().getChildren().add(DisplayController.createInventory());
        
        new Thread(KeyboardController.getInstance()).start();
        new Thread(PlayerController.getInstance()).start();
    }
}
