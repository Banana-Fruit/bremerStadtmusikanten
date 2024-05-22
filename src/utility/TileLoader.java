package Jonas;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;

public class TileLoader
{
    private final HashMap<Character, Image> tileImages;

    public TileLoader()
    {
        tileImages = new HashMap<>();
    }

    public void loadTileImages(String path)
    {
        try
        {
            // Liste aller Bilddateien im angegebenen Pfad abrufen
            String[] imageFiles = new File(getClass().getResource(path).toURI()).list();

            if (imageFiles != null)
            {
                for (String fileName : imageFiles)
                {
                    // Nur PNG-Bilder berücksichtigen
                    if (fileName.endsWith(".png"))
                    {
                        char c = fileName.charAt(0); // Verwenden Sie den ersten Buchstaben des Dateinamens als Zeichenwert
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

    public HashMap<Character, Image> getTileImages()
    {
        return tileImages;
    }
}