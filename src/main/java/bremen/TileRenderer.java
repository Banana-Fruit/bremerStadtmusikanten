package bremen;

import java.awt.*;
import java.util.HashMap;

public class TileRenderer
{
    private GamePanel gp;
    private HashMap<Character, Image> tileImages;



    public TileRenderer(GamePanel gp, HashMap<Character, Image> tileImages)
    {
        this.gp = gp;
        this.tileImages = tileImages;
    }

    public void draw(Graphics2D g2, char[][] tileChars)
    {
        for (int row = 0; row < gp.maxScreenRow; row++)
        {
            for (int col = 0; col < gp.maxScreenCol; col++)
            {
                char tileChar = tileChars[col][row];
                Image tileImage = tileImages.get(tileChar);
                if (tileImage != null)
                {
                    g2.drawImage(tileImage, col * gp.tileSize, row * gp.tileSize, gp.tileSize, gp.tileSize, null);
                }
            }
        }
    }

}