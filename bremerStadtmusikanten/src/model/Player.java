package model;


import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;


public class Player
{
    private int positionX = 0;
    private int positionY = 0;
    private int hitboxRadius = 10;
    private Image playerImage;

    public Player(int positionX, int positionY, int hitboxRadius, Image image)
    {
        this.positionX = positionX;
        this.positionY = positionY;
        this.hitboxRadius = hitboxRadius;
        this.playerImage = image;
    }
}
