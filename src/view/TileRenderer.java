package view;


import model.map.GamePanel;
import resources.Constants_Map;

import java.awt.*;
import java.util.HashMap;


/**
 * Shows images in tiles.
 */
public class TileRenderer
{
    // attributes
    private GamePanel gamePanel;
    private HashMap<Character, Image> tileImages;


    // constructor
    public TileRenderer(GamePanel gamePanel, HashMap<Character, Image> tileImages)
    {
        this.gamePanel = gamePanel;
        this.tileImages = tileImages;
    }


    public void draw(Graphics2D graphics2D, char[][] tileChars)
    {
        for (int row = Constants_Map.MINIMUM_ARRAY_VALUE; row < gamePanel.getMaxScreenRow(); row++)
        {
            for (int column = Constants_Map.MINIMUM_ARRAY_VALUE; column < gamePanel.getMaxScreenColumn(); column++)
            {
                char tileChar = tileChars[column][row];
                Image tileImage = tileImages.get(tileChar);
                if (tileImage != null)
                {
                    graphics2D.drawImage(tileImage, column * gamePanel.getTileSize(),
                            row * gamePanel.getTileSize(), gamePanel.getTileSize(), gamePanel.getTileSize(), null);
                }
            }
        }
    }
}
