package model.showables;


import javafx.scene.Scene;
import resources.constants.Constants_ExceptionMessages;
import resources.constants.scenes.Constants_Scenes;


public class MainMenu extends Showable
{
    private static volatile MainMenu instance;
    
    
    private MainMenu (Scene scene)
    {
        super(scene);
    }
    
    
    public static synchronized void initialize (Scene scene)
    {
        if (instance == null)
        {
            instance = new MainMenu(scene);
        } else
        {
            throw new IllegalStateException(Constants_ExceptionMessages.ALREADY_INITIALIZED);
        }
    }
    
    
    public static MainMenu getInstance ()
    {
        if (instance == null)
        {
            throw new IllegalStateException(Constants_ExceptionMessages.SINGLETON_NOT_INITIALIZED);
        }
        return instance;
    }
}
