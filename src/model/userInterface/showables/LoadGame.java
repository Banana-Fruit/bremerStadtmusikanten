package model.userInterface.showables;


import javafx.scene.Scene;
import resources.constants.Constants_ExceptionMessages;
import resources.constants.Constants_MainMenu;


public class LoadGame extends Showable
{
    private static volatile LoadGame instance;
    
    private LoadGame (Scene scene)
    {
        super(scene);
        init();
    }
    
    
    public static synchronized void initialize (Scene scene)
    {
        if (instance == null)
        {
            instance = new LoadGame(scene);
        } else
        {
            throw new IllegalStateException(Constants_ExceptionMessages.ALREADY_INITIALIZED);
        }
    }
    
    
    public static LoadGame getInstance ()
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