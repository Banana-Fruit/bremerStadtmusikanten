package control.events;


import model.userInterface.Game;
import resources.constants.Constants_ExceptionMessages;


public class MouseController implements Runnable
{
    private static volatile MouseController instance;
    private static Game game;
    
    
    private MouseController (Game game)
    {
        this.game = game;
    }
    
    
    public static synchronized void initialize (Game game)
    {
        if (instance == null)
        {
            instance = new MouseController(game);
        } else
        {
            throw new IllegalStateException(Constants_ExceptionMessages.ALREADY_INITIALIZED);
        }
    }
    
    
    // Method to retrieve the Singleton instance without parameters
    public static MouseController getInstance ()
    {
        if (instance == null)
        {
            throw new IllegalStateException(Constants_ExceptionMessages.SINGLETON_NOT_INITIALIZED);
        }
        return instance;
    }
    
    
    @Override
    public void run ()
    {
    
    }
}
