package control.scenes;


import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.userInterface.Game;
import model.userInterface.showables.Showable;
import resources.GameMenuBar;
import resources.constants.Constants_ExceptionMessages;
import resources.constants.Constants_MainMenu;


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
    
    
    public Pane getBasePane ()
    {
        Pane pane = new Pane();
        // creates a Menu bar with two points (game and settings) and add two menuItems to the point game
        pane.getChildren().add(GameMenuBar.createMenuBarWithTwoPoints(stage, Constants_MainMenu.MENUBAR_GAME,
                Constants_MainMenu.MENUBAR_SETTING, Constants_MainMenu.MENUBAR_CLOSE,
                Constants_MainMenu.MENUBAR_LOAD));
        return pane;
    }
    
    
    public void switchShowable(Showable showable)
    {
        Game.getInstance().setCurrentShowable(showable);
        this.stage.setScene(showable.getScene());
        this.stage.setFullScreen(true);
    }
}