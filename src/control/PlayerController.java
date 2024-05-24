package control;


import javafx.scene.input.KeyCode;
import model.showables.Game;
import model.user.Player;
import resources.Constants_DefaultValues;
import resources.Constants_Game;
import resources.Constants_Keymapping;
import resources.Constants_Scenes;

import java.util.Set;


public class PlayerController
{
    private static volatile PlayerController instance;
    private Game game;
    private Player player;
    private int positionX;
    private int positionY;
    
    
    private PlayerController()
    {
    }
    
    
    public static PlayerController getInstance()
    {
        if (instance == null)
        {
            synchronized (PlayerController.class)
            {
                if (instance == null)
                {
                    instance = new PlayerController();
                }
            }
        }
        return instance;
    }
    
    
    public void handleKeyPresses(Set<KeyCode> pressedKeys)
    {
        if(game.getCurrentShowable().getId() == Constants_Scenes.IDENTIFIER_MAP && this.player != null)
        {
            boolean diagonal = false;
            if (pressedKeys.size() > 1) diagonal = true;
            
            if (pressedKeys.contains(Constants_Keymapping.moveUP)) moveUP(diagonal);
            if (pressedKeys.contains(Constants_Keymapping.moveLEFT)) moveLEFT(diagonal);
            if (pressedKeys.contains(Constants_Keymapping.moveDOWN)) moveDOWN(diagonal);
            if (pressedKeys.contains(Constants_Keymapping.moveRIGHT)) moveRIGHT(diagonal);
            
            changePosition();
        }
    }
    
    
    public void moveUP(boolean isDiagonal)
    {
        int deltaY = Constants_DefaultValues.DEFAULT_SPEED * Constants_DefaultValues.SPEED_MULTIPLIER;
        if (isDiagonal) deltaY = (int) (deltaY * Constants_DefaultValues.ADJUST_DIAGONAL_MOVEMENT);
        this.positionY -= deltaY;
    }
    
    
    public void moveDOWN(boolean isDiagonal)
    {
        int deltaY = Constants_DefaultValues.DEFAULT_SPEED * Constants_DefaultValues.SPEED_MULTIPLIER;
        if (isDiagonal) deltaY = (int) (deltaY * Constants_DefaultValues.ADJUST_DIAGONAL_MOVEMENT);
        this.positionY += deltaY;
    }
    
    
    public void moveRIGHT(boolean isDiagonal)
    {
        int deltaX = Constants_DefaultValues.DEFAULT_SPEED * Constants_DefaultValues.SPEED_MULTIPLIER;
        if (isDiagonal) deltaX = (int) (deltaX * Constants_DefaultValues.ADJUST_DIAGONAL_MOVEMENT);
        this.positionX += deltaX;
    }
    
    
    public void moveLEFT(boolean isDiagonal)
    {
        int deltaX = Constants_DefaultValues.DEFAULT_SPEED * Constants_DefaultValues.SPEED_MULTIPLIER;
        if (isDiagonal) deltaX = (int) (deltaX * Constants_DefaultValues.ADJUST_DIAGONAL_MOVEMENT);
        this.positionX -= deltaX;
    }
    
    
    public void changePosition()
    {
        ImageController.changeImagePosition(this.player.getPlayerImage(), this.positionX, this.positionY);
    }
    
    
    public void setGame(Game game)
    {
        this.game = game;
    }
    
    
    public void setPlayer(Player player)
    {
        this.player = player;
    }
}
