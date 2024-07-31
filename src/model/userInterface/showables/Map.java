package model.userInterface.showables;


import javafx.scene.Scene;
import model.panel.Panel;
import resources.constants.Constants_ExceptionMessages;


/**
 * Map class which contains the scene.
 *
 * @author Michael Markov
 */
public class Map extends Showable
{
    private static volatile Map instance;
    private Panel panel;
    private String currentMapName;
    
    
    private Map (Scene scene)
    {
        super(scene);
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
    
    
    public void setPanel (Panel panel)
    {
        this.panel = panel;
    }
    
    
    public Panel getPanel ()
    {
        return panel;
    }
    
    
    public void setCurrentMapName (String mapName)
    {
        this.currentMapName = mapName;
    }
    
    
    public String getCurrentMapName ()
    {
        return currentMapName;
    }
}
