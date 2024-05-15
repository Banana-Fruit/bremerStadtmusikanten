package control;


import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.Player;
import resources.Constants_Keymapping;

import java.util.HashSet;
import java.util.Set;


public class KeyboardController implements Runnable
{
    private final Set<KeyCode> pressedKeys = new HashSet<>();
    Scene scene;
    Player player;
    
    
    public KeyboardController(Scene scene, Player player)
    {
        this.scene = scene;
        this.player = player;
    }
    
    
    @Override
    public void run()
    {
        while (true)
        {
            // Add event listeners for key presses and releases
            this.scene.setOnKeyPressed(new EventHandler<KeyEvent>()
            {
                @Override
                public void handle(KeyEvent keyEvent)
                {
                    pressedKeys.add(keyEvent.getCode());
                }
            });
            this.scene.setOnKeyReleased(new EventHandler<KeyEvent>()
            {
                @Override
                public void handle(KeyEvent keyEvent)
                {
                    pressedKeys.remove(keyEvent.getCode());
                }
            });
            
            try
            {
                Thread.sleep(15);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
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
        PlayerController playerController = new PlayerController(this.player);
        if (!playerRelatedKeys.isEmpty()) playerController.handleKeyPresses(playerRelatedKeys);
    }
}
