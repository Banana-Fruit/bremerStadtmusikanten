package model.showables;


import javafx.scene.Scene;
import model.panel.Panel;
import resources.constants.Constants_ExceptionMessages;
import resources.constants.scenes.Constants_Scenes;


public class Map extends Showable
{
    private static volatile Map instance;
    Panel panel;
    
    
    private Map (Scene scene)
    {
        super(scene, Constants_Scenes.IDENTIFIER_MAP);
    }
    
    
    public static synchronized void initialize (Scene scene)
    {
        if (instance == null)
        {
            instance = new Map(scene);
        } else
        {
            throw new IllegalStateException(Constants_ExceptionMessages.ALREADY_INITIALIZED);
        }
    }
    
    
    public static Map getInstance ()
    {
        if (instance == null)
        {
            throw new IllegalStateException(Constants_ExceptionMessages.SINGLETON_NOT_INITIALIZED);
        }
        return instance;
    }
}
