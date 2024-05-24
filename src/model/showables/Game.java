package model.showables;


import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import model.Player;
import resources.Constants_Game;
import resources.Constants_ResourceLocations;
import resources.Constants_Scenes;

import java.util.HashMap;


public class Game
{
    Showable currentShowable;
    HashMap<Integer, Showable> mapOfShowables;
    Player player;
    String gameTitle = Constants_Game.GAME_TITLE;
    
    
    public Game()
    {
        this.mapOfShowables = new HashMap<>();
        init();
    }
    
    
    private void init()
    {
        // Add showable
        addShowable(new MainMenu()), Constants_Scenes.MAIN_MENU_IDENTIFIER);
        
        // Set initial showable
        switchShowable(Constants_Scenes.MAIN_MENU_IDENTIFIER);
    }
    
    
    public void setPlayer(Player player)
    {
        this.player = player;
    }
    
    
    public void switchShowable(int identifier)
    {
        this.currentShowable = this.mapOfShowables.get(identifier);
    }
    
    
    public void addShowable(Showable showable, int identifier)
    {
        this.mapOfShowables.put(identifier, showable);
    }
    
    
    public Showable getCurrentShowable()
    {
        return currentShowable;
    }
    
    
    public Player getPlayer()
    {
        return player;
    }
    
    
    public String getGameTitle()
    {
        return gameTitle;
    }
    
    
    public void removeShowable(int identifier)
    {
        this.mapOfShowables.remove(identifier);
    }
}
