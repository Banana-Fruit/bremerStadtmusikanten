package model.userInterface.showables;


import javafx.scene.Scene;
import resources.constants.Constants_ExceptionMessages;
import resources.constants.Constants_MainMenu;


public class MainMenu extends Showable
{
    private static volatile MainMenu instance;
    
    
    private MainMenu (Scene scene)
    {
        super(scene);
        init();
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
    
    
    private void init ()
    {
        setBackground(Constants_MainMenu.PATH_BACKGROUND_IMAGE);
    }
}
