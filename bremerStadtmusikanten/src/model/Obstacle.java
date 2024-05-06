package model;


import java.awt.*;


public class Obstacle
{
    private int positionX = 0;
    private int positionY = 0;
    private int imageOffsetX = 0;
    private int imageOffsetY = 0;
    private int hitboxLengthX = 0;
    private int hitboxLengthY = 0;
    private Image obstacleImage;

    public Obstacle(Image obstacleImage, int positionX, int positionY, int hitboxLengthX, int hitboxLengthY)
    {
        this.obstacleImage = obstacleImage;
        this.positionX = positionX;
        this.positionY = positionY;
        this.hitboxLengthX = hitboxLengthX;
        this.hitboxLengthY = hitboxLengthY;
        determineImageOffset(obstacleImage);
    }


    private void determineImageOffset(Image image)
    {

    }
}
