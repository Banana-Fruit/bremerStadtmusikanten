package model.userInterface;


import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import model.Player;
import model.showables.MainMenu;
import model.showables.Showable;
import resources.Constants_Game;
import resources.Constants_Scenes;

import java.util.HashMap;


/**
 * Creates a game, that contains all scenery in its mapOfShowables attribute. Each Showable has an integer key that can
 * be accessed from the Constants. Upon instantiation, a MainMenu will be created and stored inside the list.
 */
public class Game
{
    private Showable currentShowable;
    private HashMap<Integer, Showable> mapOfShowables;
    private Player player;
    private String gameTitle = Constants_Game.GAME_TITLE;
    
    
    public Game ()
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
