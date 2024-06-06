package control;


import control.events.KeyboardController;
import control.events.MouseController;
import control.scenes.MainMenuController;
import control.scenes.SceneController;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.showables.MainMenu;
import model.userInterface.Game;
import resources.constants.Constants_ExceptionMessages;


public class GameController implements Runnable
{
    private static volatile GameController instance = null;
    ObjectProperty<Stage> stageProperty;
    
    
    private GameController (Stage stage)
    {
        this.stageProperty = new SimpleObjectProperty<Stage>(stage);
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
        SceneController.initialize(stageProperty.get());
        KeyboardController.initialize();
        MouseController.initialize();
        MainMenu.initialize(new Scene(new Pane()));
        MainMenuController.initialize();
        
        
        new Thread(SceneController.getInstance()).start();
        
        Game.getInstance().setCurrentShowable(MainMenu.getInstance());
        this.stageProperty.get().setTitle(Game.getInstance().getGameTitle());
        this.stageProperty.get().show();
    }
    
    
    @Override
    public void run ()
    {
        // Should put Threads to sleep and notify them of changes, to wake them
    }
    
    
    public Property<Stage> getStageProperty ()
    {
        return this.stageProperty;
    }
}
