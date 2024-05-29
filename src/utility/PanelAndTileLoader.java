package utility;


import javafx.scene.image.Image;
import model.showables.Map;
import resources.constants.scenes.Constants_Map;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;


public class PanelAndTileLoader
{
    public static char[][] loadCharFileOfMapFromPath (String path)
    {
        /*try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path)))
        {
            char[][] tileChars = Map.getInstance().getTileCharArray();
            
            String line;
            for (int row = Constants_Map.MINIMUM_ARRAY_VALUE;
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
        } finally
        {
            return null;
        }*/
        
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
        return null;
    }
    
    
    public static HashMap<Character, Image> getMapWithCharsAndImagesFromPath (String imageFolderPath)
    {
        /*try
        {
            // retrieve list of all image files in the imageFolderPath
            String[] arrayOfFileNames = new File(new URI(imageFolderPath)).list();
            HashMap<Character, Image> currentMapOfCharsWithImages = new HashMap<>();
            
            if (arrayOfFileNames != null)
            {
                for (String currentFileName : arrayOfFileNames)
                {
                    // only png images
                    if (currentFileName.endsWith(Constants_Map.ONLY_PNG))
                    {
                        // Use the first letter of the file name as the character value
                        char c = currentFileName.charAt(Constants_Map.IMAGE_CHAR_POSITION);
                        String fullPath = (String) (imageFolderPath + currentFileName);
                        Image image = new Image(fullPath);
                        currentMapOfCharsWithImages.put(c, image);
                    }
                }
            }
            return currentMapOfCharsWithImages;
        } catch (URISyntaxException e)
        {
            throw new RuntimeException(e);
        } finally
        {
            return null;
        }*/
        return null;
    }
}
