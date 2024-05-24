package control;


import javafx.scene.input.KeyCode;
import model.showables.Game;

import java.util.HashSet;
import java.util.Set;


public class MouseController implements Runnable
{
    private static volatile MouseController instance;
    private Game game;
    
    
    private MouseController()
    {
    }
    
    
    public static MouseController getInstance()
    {
        if (instance == null)
        {
            synchronized (MouseController.class)
            {
                if (instance == null)
                {
                    instance = new MouseController();
                }
            }
        }
        return instance;
    }
    
    
    public void setGame(Game game)
    {
        this.game = game;
    }
    
    
    @Override
    public void run()
    {
    
    }
}
