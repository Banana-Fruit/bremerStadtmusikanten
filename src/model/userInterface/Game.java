package model.userInterface;


import control.scenes.MapController;
import control.scenes.PanelController;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import model.Player;
import model.showables.MainMenu;
import model.showables.Showable;
import resources.constants.Constants_ExceptionMessages;
import resources.constants.Constants_Game;
import resources.constants.scenes.Constants_Scenes;

import java.util.HashMap;


/**
 * Creates a game, that contains all scenery in its mapOfShowables attribute. Each Showable has an integer key that can
 * be accessed from the Constants. Upon instantiation, a MainMenu will be created and stored inside the list.
 */
public class Game
{
    private static volatile Game instance;
    private Showable currentShowable;
    private HashMap<Integer, Showable> mapOfShowables;
    private Player player;
    private String gameTitle = Constants_Game.GAME_TITLE;
    
    
    private Game ()
    {
        this.mapOfShowables = new HashMap<>();
        init();
    }
    
    
    private void init ()
    {
        MainMenu.initialize(new Scene(new Pane()));
        addShowable(MainMenu.getInstance(), Constants_Scenes.IDENTIFIER_MAINMENU); // Add showable
        switchShowable(Constants_Scenes.IDENTIFIER_MAINMENU); // Set initial showable
    }
    
    
    public static synchronized void initialize ()
    {
        if (instance == null)
        {
            instance = new Game();
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
    
    
    public void setPlayer (Player player)
    {
        this.player = player;
    }
    
    
    public void switchShowable (int identifier)
    {
        this.currentShowable = this.mapOfShowables.get(identifier);
    }
    
    
    public void addShowable (Showable showable, int identifier)
    {
        this.mapOfShowables.put(identifier, showable);
    }
    
    
    public Showable getCurrentShowable ()
    {
        return currentShowable;
    }
    
    
    public Player getPlayer ()
    {
        return player;
    }
    
    
    public String getGameTitle ()
    {
        return gameTitle;
    }
    
    
    public void removeShowable (int identifier)
    {
        this.mapOfShowables.remove(identifier);
    }
}
