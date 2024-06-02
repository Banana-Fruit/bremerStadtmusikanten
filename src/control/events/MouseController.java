package control.events;


import model.userInterface.Game;
import resources.constants.Constants_ExceptionMessages;


public class MouseController implements Runnable
{
    private static volatile MouseController instance = null;
    
    
    private MouseController () {}
    
    
    public static synchronized void initialize ()
    {
        if (instance == null)
        {
            instance = new MouseController();
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
