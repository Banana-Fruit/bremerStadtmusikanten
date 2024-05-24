package control;


import javafx.scene.input.KeyCode;
import model.Player;
import model.showables.Game;
import resources.Constants_DefaultValues;
import resources.Constants_Keymapping;

import java.util.Set;


public class PlayerController
{
    private Game game;
    private Player player;
    private int positionX;
    private int positionY;
    
    
    
    public PlayerController(Game game)
    {
        this.game = game;
    }
    
    
    public void handleKeyPresses(Set<KeyCode> pressedKeys)
    {
        boolean diagonal = false;
        if (pressedKeys.size() > 1) diagonal = true;
        
        if (pressedKeys.contains(Constants_Keymapping.moveUP)) moveUP(diagonal);
        if (pressedKeys.contains(Constants_Keymapping.moveLEFT)) moveLEFT(diagonal);
        if (pressedKeys.contains(Constants_Keymapping.moveDOWN)) moveDOWN(diagonal);
        if (pressedKeys.contains(Constants_Keymapping.moveRIGHT)) moveRIGHT(diagonal);
        
        changePosition();
    }
    
    
    public void moveUP (boolean isDiagonal)
    {
        int deltaY = 1 * Constants_DefaultValues.SPEED_MULTIPLIER;
        if (isDiagonal) deltaY = (int) (deltaY * Constants_DefaultValues.ADJUST_DIAGONAL_MOVEMENT);
        this.positionY = this.positionY - deltaY;
    }
    
    
    public void moveDOWN (boolean isDiagonal)
    {
        int deltaY = 1 * Constants_DefaultValues.SPEED_MULTIPLIER;
        if (isDiagonal) deltaY = (int) (deltaY * Constants_DefaultValues.ADJUST_DIAGONAL_MOVEMENT);
        this.positionY = this.positionY + deltaY;
    }
    
    
    public void moveRIGHT (boolean isDiagonal)
    {
        int deltaX = 1 * Constants_DefaultValues.SPEED_MULTIPLIER;
        if (isDiagonal) deltaX = (int) (deltaX * Constants_DefaultValues.ADJUST_DIAGONAL_MOVEMENT);
        this.positionX = this.positionX + deltaX;
    }
    
    
    public void moveLEFT (boolean isDiagonal)
    {
        int deltaX = 1 * Constants_DefaultValues.SPEED_MULTIPLIER;
        if (isDiagonal) deltaX = (int) (deltaX * Constants_DefaultValues.ADJUST_DIAGONAL_MOVEMENT);
        this.positionX = this.positionX - deltaX;
    }
    
    
    public void changePosition()
    {
        ImageController.changeImagePosition(this.player.getPlayerImage(), this.positionX, this.positionY);
    }
}
