package model;


import javafx.scene.image.Image;
import resources.Constants_DefaultValues;


/**
 * An image with a hitbox that moves on the map
 */
public class Player
{
    private int positionX = 0;
    private int positionY = 0;
    private int hitboxRadius = 10;
    private Image playerImage;


    public Player (int positionX, int positionY, int hitboxRadius, Image image)
    {
        this.positionX = positionX;
        this.positionY = positionY;
        this.hitboxRadius = hitboxRadius;
        this.playerImage = image;
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


    public Image getPlayerImage ()
    {
        return playerImage;
    }


    public int getPositionX ()
    {
        return positionX;
    }


    public int getPositionY ()
    {
        return positionY;
    }
}
