package utility;

import resources.Constants_Map;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;


public class TileLoader
{
    // attributes
    private final HashMap<Character, Image> tileImages;


    // constructor
    public TileLoader()
    {
        tileImages = new HashMap<>();
    }


    public void loadTileImages(String path)
    {
        try
        {
            // retrieve list of all image files in the path
            String[] imageFiles = new File(getClass().getResource(path).toURI()).list();

            if (imageFiles != null)
            {
                for (String fileName : imageFiles)
                {
                    // only png images
                    if (fileName.endsWith(Constants_Map.ONLY_PNG))
                    {
                        // Use the first letter of the file name as the character value
                        char c = fileName.charAt(Constants_Map.MINIMUM_ARRAY_VALUE);
                        Image image = ImageIO.read(getClass().getResourceAsStream(path + fileName));
                        tileImages.put(c, image);
                    }
                }
            }
        } catch (URISyntaxException e)
        {
            throw new RuntimeException(e);
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }


    // getter-method for the Tile image
    public HashMap<Character, Image> getTileImages()
    {
        return tileImages;
    }
}
