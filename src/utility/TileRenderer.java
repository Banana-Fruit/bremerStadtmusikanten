package utility;


import model.map.GamePanel;
import resources.Constants_Map;

import java.awt.*;
import java.util.HashMap;


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
            for (int col = Constants_Map.MINIMUM_ARRAY_VALUE; col < gamePanel.getMaxScreenColumn(); col++)
            {
                char tileChar = tileChars[col][row];
                Image tileImage = tileImages.get(tileChar);
                if (tileImage != null)
                {
                    graphics2D.drawImage(tileImage, col * gamePanel.getTileSize(),
                            row * gamePanel.getTileSize(), gamePanel.getTileSize(), gamePanel.getTileSize(), null);
                }
            }
        }
    }


}
