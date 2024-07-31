package model.userInterface;


import javafx.stage.Stage;
import model.userInterface.showables.Showable;
import resources.constants.Constants_ExceptionMessages;
import resources.constants.Constants_Game;


/**
 * Creates a game, that contains all scenery in its mapOfShowables attribute. Each Showable has an integer key that can
 * be accessed from the Constants. Upon instantiation, a MainMenu will be created and stored inside the list.
 *
 * @author Michael Markov
 */
public class Game
{
    private static volatile Game instance;
    private Showable currentShowable = new Showable();
    private final String gameTitle;
    private final Stage stage;
    
    
    private Game (Stage stage)
    {
        this.stage = stage;
        this.gameTitle = Constants_Game.GAME_TITLE;
    }
    
    
    public static synchronized void initialize (Stage stage)
    {
        if (instance == null)
        {
            instance = new Game(stage);
        } else
        {
            throw new IllegalStateException(Constants_ExceptionMessages.ALREADY_INITIALIZED);
        }
    }
    
    
    // Method to retrieve the Singleton instance without parameters
    public static Game getInstance ()
    {
        if (instance == null)
        {
            throw new IllegalStateException(Constants_ExceptionMessages.SINGLETON_NOT_INITIALIZED);
        }
        return instance;
    }
    
    
    public void setCurrentShowable (Showable showable)
    {
        this.currentShowable = showable;
        stage.setScene(showable.getScene());
    }
    
    
    public Showable getCurrentShowable ()
    {
        return currentShowable;
    }
    
    
    public String getGameTitle ()
    {
        return gameTitle;
    }
}
