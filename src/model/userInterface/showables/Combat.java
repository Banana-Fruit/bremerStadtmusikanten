package model.userInterface.showables;


import javafx.scene.Scene;
import model.panel.Panel;
import resources.constants.Constants_Combat;
import resources.constants.Constants_ExceptionMessages;
import resources.constants.Constants_Resources;


public class Combat extends Showable
{
    private static volatile Combat instance;
    private Panel panel;
    private String currentMapName;
    
    
    private Combat (Scene scene)
    {
        super(scene);
        init();
    }
    
    
    private void init ()
    {
        setBackground(Constants_Resources.COMBAT_BACKGROUND_PATH);
    }
    
    
    public static synchronized void initialize (Scene scene)
    {
        if (instance == null)
        {
            instance = new Combat(scene);
        } else
        {
            throw new IllegalStateException(Constants_ExceptionMessages.ALREADY_INITIALIZED);
        }
    }
    
    
    public static Combat getInstance ()
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
        this.currentMapName = mapName; // Neu hinzugefügt
    }
    
    
    public String getCurrentMapName ()
    {
        return currentMapName; // Neu hinzugefügt
    }
}
