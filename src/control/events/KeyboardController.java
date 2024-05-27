package control.events;


import control.game.PlayerController;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.userInterface.Game;
import resources.Constants_ExceptionMessages;
import resources.Constants_Game;
import resources.Constants_Keymapping;

import java.util.HashSet;
import java.util.Set;


/**
 * The KeyboardController listens to key presses, and routes them to other controllers.
 */
public class KeyboardController implements Runnable
{
    private static volatile KeyboardController instance;
    private final Set<KeyCode> pressedKeys = new HashSet<>();
    private static Game game;
    
    
    private KeyboardController(Game game)
    {
        this.game = game;
    }
    
    
    public static synchronized void initialize(Game game)
    {
        if (instance == null)
        {
            instance = new KeyboardController(game);
        } else
        {
            throw new IllegalStateException(Constants_ExceptionMessages.SINGLETON_ALREADY_INITIALIZED);
        }
    }
    
    
    // Method to retrieve the Singleton instance without parameters
    public static KeyboardController getInstance()
    {
        if (instance == null)
        {
            throw new IllegalStateException(Constants_ExceptionMessages.SINGLETON_NOT_INITIALIZED);
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
}
