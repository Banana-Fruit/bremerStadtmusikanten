package utility;


import model.showables.Map;
import resources.Constants_Map;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URISyntaxException;
import java.util.HashMap;


public class MapLoader
{
    public static char[][] loadCharFileOfMapFromPath (String path)
    {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path)))
        {
            char[][] tileChars = Map.getInstance().getTileCharArray();
            
            String line;
            for(int row = Constants_Map.MINIMUM_ARRAY_VALUE;
                row < Constants_Map.MAX_SCREEN_ROW && (line = bufferedReader.readLine()) != null; row++)
            {
                char[] characters = line.toCharArray();
                for (int column = Constants_Map.MINIMUM_ARRAY_VALUE;
                     column < Constants_Map.MAX_SCREEN_COLUMN && column < characters.length; column++)
                {
                    tileChars[column][row] = characters[column];
                }
            }
            return tileChars;
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            return null;
        }
        
        /* TODO: Leave code here in case ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑ doesnt work.
        InputStream inputStream = getClass().getResourceAsStream(path);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        
        char[][] tileChars = Map.getInstance().getTileCharArray();
        int row = Constants_Map.MINIMUM_ARRAY_VALUE;
        try
        {
            String line;
            while ((line = bufferedReader.readLine()) != null && row < Constants_Map.MAX_SCREEN_ROW)
            {
                char[] characters = line.toCharArray();
                for (int column = Constants_Map.MINIMUM_ARRAY_VALUE; column < Constants_Map.MAX_SCREEN_COLUMN && column < characters.length; column++)
                {
                    tileChars[column][row] = characters[column];
                }
                row++;
            }
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        } finally
        {
            try
            {
                bufferedReader.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }*/
    }
    
    
    public static HashMap<Character, Image> setMapWithCharsAndImages (String path)
    {
        try
        {
            // retrieve list of all image files in the path
            String[] imageFiles = new File(getClass().getResource(path).toURI()).list();
            HashMap<Character, Image> currentMapOfCharsWithImages = new HashMap<>();
            
            if (imageFiles != null)
            {
                for (String fileName : imageFiles)
                {
                    // only png images
                    if (fileName.endsWith(Constants_Map.ONLY_PNG))
                    {
                        // Use the first letter of the file name as the character value
                        char c = fileName.charAt(Constants_Map.MINIMUM_ARRAY_VALUE);
                        Image image = new Image(new BufferedImage(new ImageIO.read(new File(path + fileName))));
                        currentMapOfCharsWithImages.put(c, image);
                    }
                }
            }
            return currentMapOfCharsWithImages;
        } catch (URISyntaxException e)
        {
            throw new RuntimeException(e);
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        } finally
        {
            return null;
        }
    }
}
