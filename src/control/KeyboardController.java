package control;


import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.showables.Game;
import resources.Constants_Game;
import resources.Constants_Keymapping;

import java.util.HashSet;
import java.util.Set;


public class KeyboardController implements Runnable
{
    private static volatile KeyboardController instance;
    private final Set<KeyCode> pressedKeys = new HashSet<>();
    private Game game;
    
    
    
    private KeyboardController() {}
    
    
    public static KeyboardController getInstance() {
        if (instance == null) {
            synchronized (KeyboardController.class) {
                if (instance == null) {
                    instance = new KeyboardController();
                }
            }
        }
        return instance;
    }
    
    
    @Override
    public void run()
    {
        while (true)
        {
            // Puts Thread to sleep from time to time
            try
            {
                Thread.sleep(Constants_Game.THREAD_SLEEP_DEFAULT_TIME);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            
            // Add event listeners for key presses and releases
            this.game.getCurrentShowable().getScene().setOnKeyPressed(new EventHandler<KeyEvent>()
            {
                @Override
                public void handle(KeyEvent keyEvent)
                {
                    pressedKeys.add(keyEvent.getCode());
                }
            });
            this.game.getCurrentShowable().getScene().setOnKeyReleased(new EventHandler<KeyEvent>()
            {
                @Override
                public void handle(KeyEvent keyEvent)
                {
                    pressedKeys.remove(keyEvent.getCode());
                }
            });
            
            Platform.runLater(new Runnable()
            {
                @Override
                public void run()
                {
                    routeToPlayerController(pressedKeys);
                }
            });
        }
    }
    
    
    public void routeToPlayerController(Set<KeyCode> pressedKeys)
    {
        Set<KeyCode> playerRelatedKeys = new HashSet<>();
        for (KeyCode currentCode : Constants_Keymapping.PLAYER_KEYS)
        {
            if (pressedKeys.contains(currentCode)) playerRelatedKeys.add(currentCode);
        }
        if (!playerRelatedKeys.isEmpty()) PlayerController.getInstance().handleKeyPresses(playerRelatedKeys);
    }
    
    
    public void setGame(Game game)
    {
        this.game = game;
    }
}
