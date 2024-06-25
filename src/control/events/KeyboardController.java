package control.events;


import control.game.PlayerController;
import control.scenes.SceneController;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.userInterface.Game;
import resources.constants.Constants_ExceptionMessages;
import resources.constants.Constants_Game;
import resources.constants.Constants_Keymapping;

import java.util.HashSet;
import java.util.Set;


/**
 * The KeyboardController listens to key presses, and routes them to other controllers.
 */
public class KeyboardController implements Runnable
{
    private static volatile KeyboardController instance = null;
    private final Set<KeyCode> pressedKeys = new HashSet<>();
    
    
    private KeyboardController () {}
    
    
    public static synchronized void initialize ()
    {
        if (instance == null)
        {
            instance = new KeyboardController();
        } else
        {
            throw new IllegalStateException(Constants_ExceptionMessages.ALREADY_INITIALIZED);
        }
    }
    
    
    @Override
    public void run ()
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
            Game.getInstance().getCurrentShowable().getScene().setOnKeyPressed(new EventHandler<KeyEvent>()
            {
                @Override
                public void handle (KeyEvent keyEvent)
                {
                    pressedKeys.add(keyEvent.getCode());
                }
            });
            Game.getInstance().getCurrentShowable().getScene().setOnKeyReleased(new EventHandler<KeyEvent>()
            {
                @Override
                public void handle (KeyEvent keyEvent)
                {
                    pressedKeys.remove(keyEvent.getCode());
                }
            });
            if (SceneController.getInstance().isDialogShown())
            {
                pressedKeys.clear(); // Clear pressed keys if a dialog is shown
                continue; // Skip processing keys if dialog is shown
            }
            Platform.runLater(new Runnable()
            {
                @Override
                public void run ()
                {
                    if(!pressedKeys.isEmpty())
                    {
                        routeToPlayerController(pressedKeys);
                    }
                }
            });
        }
    }
    
    
    public void routeToPlayerController (Set<KeyCode> pressedKeys)
    {
        Set<KeyCode> playerRelatedKeys = new HashSet<>();
        for (KeyCode currentCode : Constants_Keymapping.PLAYER_KEYS)
        {
            if (pressedKeys.contains(currentCode)) playerRelatedKeys.add(currentCode);
        }
        if (!playerRelatedKeys.isEmpty()) PlayerController.getInstance().handleKeyPresses(playerRelatedKeys);
    }


    public static KeyboardController getInstance ()
    {
        if (instance == null)
        {
            throw new IllegalStateException(Constants_ExceptionMessages.SINGLETON_NOT_INITIALIZED);
        }
        return instance;
    }
}
