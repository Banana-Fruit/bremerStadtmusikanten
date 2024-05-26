package model.panel;


import javafx.scene.image.Image;


/**
 * Tiles contain images and position information
 */
public class Tile
{
    private Image image;
    private char imageChar;
    private boolean isOccupied = false;
    
    public Tile(char imageChar)
    {
        this.imageChar = imageChar;
        init();
    }
    
    
    private void init()
    {
        getImageFromChar();
        setOccupied();
    }
    
    
    private void getImageFromChar ()
    {
    
    }
    
    
    public void setOccupied ()
    {
    
    }
}
