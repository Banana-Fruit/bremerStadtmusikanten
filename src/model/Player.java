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
