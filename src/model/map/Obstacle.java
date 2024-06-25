package model.map;


import resources.constants.Constants_DefaultValues;

import java.awt.*;


/**
 * Obstacles cannot be passed by the player on the map
 */
public class Obstacle
{
    private int positionX = Constants_DefaultValues.ZERO;
    private int positionY = Constants_DefaultValues.ZERO;
    private int imageOffsetX = Constants_DefaultValues.ZERO;
    private int imageOffsetY = Constants_DefaultValues.ZERO;
    private int hitboxLengthX = Constants_DefaultValues.ZERO;
    private int hitboxLengthY = Constants_DefaultValues.ZERO;
    private Image obstacleImage;
    
    
    public Obstacle (Image obstacleImage, int positionX, int positionY, int hitboxLengthX, int hitboxLengthY)
    {
        this.obstacleImage = obstacleImage;
        this.positionX = positionX;
        this.positionY = positionY;
        this.hitboxLengthX = hitboxLengthX;
        this.hitboxLengthY = hitboxLengthY;
        determineImageOffset(obstacleImage);
    }
    
    
    private void determineImageOffset (Image image)
    {
    
    }
}
