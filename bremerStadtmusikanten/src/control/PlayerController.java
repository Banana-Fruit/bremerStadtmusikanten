package control;


import javafx.scene.input.KeyCode;
import model.Player;
import resources.Constants_Keymapping;

import java.util.Set;


public class PlayerController
{
    static Player player;
    
    public PlayerController(Player player)
    {
        this.player = player;
    }
    
    
    public void handleKeyPresses(Set<KeyCode> pressedKeys)
    {
        boolean diagonal = false;
        if (pressedKeys.size() > 1) diagonal = true;
        
        if (pressedKeys.contains(Constants_Keymapping.moveUP))
        {
            player.moveUP(diagonal);
        }
        if (pressedKeys.contains(Constants_Keymapping.moveLEFT))
        {
            player.moveLEFT(diagonal);
        }
        if (pressedKeys.contains(Constants_Keymapping.moveDOWN))
        {
            player.moveDOWN(diagonal);
        }
        if (pressedKeys.contains(Constants_Keymapping.moveRIGHT))
        {
            player.moveRIGHT(diagonal);
        }
    }
}
