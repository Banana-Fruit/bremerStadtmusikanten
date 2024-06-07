package utility;


import javafx.scene.image.Image;
import model.panel.Tile;
import resources.constants.Constants_Panel;
import resources.constants.Constants_Resources;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;


public class PanelAndTileLoader
{
    /**
     * Returns a character array based on the path of a character map file. Limits the amount of readings to
     * maxRows/maxColumns, or when a reading is null.
     *
     * @param loaderFilePath
     * @param maxRows
     * @param maxColumns
     * @return
     */
    public static char[][] getCharacterArray (String loaderFilePath, int maxRows, int maxColumns)
    {
        char[][] characterArray = new char[maxRows][maxColumns];
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(loaderFilePath)))
        {
            String line;
            
            // Loop continues reading lines until either the limit of rows is reached, or the read line is null
            for (int row = Constants_Panel.MIN_TILE_INDEX; row < maxRows && (line = bufferedReader.readLine()) != null; row++)
            {
                char[] characters = line.toCharArray(); // Converts currently read line into an array of characters
                
                // Loop through columns until either the limit of columns is reached, or the read line is null
                for (int column = Constants_Panel.MIN_TILE_INDEX; column < maxColumns && column < characters.length; column++)
                {
                    characterArray[column][row] = characters[column];
                }
            }
            return characterArray;
        } catch (FileNotFoundException e)
        {
            return null;
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
    
    
    public static HashMap<Character, Image> getMapWithCharsAndImages (String imageFolderPath)
    {
        try
        {
            // Retrieve list of all image files in the imageFolderPath
            String[] arrayOfFileNames = new File(new URI(imageFolderPath)).list();
            HashMap<Character, Image> currentMapOfCharsWithImages = new HashMap<>();
            
            if (arrayOfFileNames != null)
            {
                for (String currentFileName : arrayOfFileNames)
                {
                    if (currentFileName.endsWith(Constants_Resources.PNG_SUFFIX)) // Only .png files are tracked
                    {
                        System.out.println(currentFileName);
                        // Use the first letter of the file name as the character value
                        currentMapOfCharsWithImages.put(currentFileName.charAt(Constants_Panel.IMAGE_CHAR_POSITION),
                                new Image((String) (imageFolderPath + currentFileName)));
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
        }
    }
    
    
    public static Tile[][] getTileArray (HashMap<Character, Image> backgroundMap, char[][] backgroundArray,
                                         HashMap<Character, Image> interactableMap, char[][] interactableArray,
                                         int maxRows, int maxColumns)
    {
        Tile[][] tileArray = new Tile[maxRows][maxColumns];
        
        for (int row = Constants_Panel.MIN_TILE_INDEX; row < maxRows; row++)
        {
            for (int column = Constants_Panel.MIN_TILE_INDEX; column < maxColumns; column++)
            {
                if (backgroundArray[row][column] == Constants_Panel.ignoreLoaderFileValue) continue;
                if (interactableArray[row][column] == Constants_Panel.ignoreLoaderFileValue) continue;
                tileArray[row][column] = new Tile(backgroundMap.get(backgroundArray[row][column]),
                        interactableMap.get(interactableArray[row][column]));
            }
        }
        
        return tileArray;
    }
}
