package utility;


import javafx.scene.image.Image;
import model.panel.Tile;
import resources.constants.Constants_Panel;
import resources.constants.Constants_Resources;

import java.io.*;
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
    public static char[][] getCharacterArrayUsingTileFile (String loaderFilePath, int maxRows, int maxColumns)
    {
        char[][] characterArray = new char[maxRows][maxColumns];
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(loaderFilePath)))
        {
            String line;
            int row = Constants_Panel.MIN_TILE_INDEX;
            for (int lineIndex = Constants_Panel.FILE_READER_STARTING_LINE;
                 row < maxRows && (line = bufferedReader.readLine()) != null; lineIndex++)
            {
                if (lineIndex == Constants_Panel.BIOME_IDENTIFIER_LINE) continue;
                char[] characters = line.toCharArray();
                for (int column = Constants_Panel.MIN_TILE_INDEX; column < maxColumns && column < characters.length; column++)
                {
                    characterArray[row][column] = characters[column];
                }
                row++;
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return characterArray;
    }
    
    
    public static HashMap<Character, Image> getMapWithCharsAndImages (String imageFolderPath)
    {
        HashMap<Character, Image> currentMapOfCharsWithImages = new HashMap<>();
        
        // Create a File object from the image folder path
        File folder = new File(imageFolderPath);
        String[] arrayOfFileNames = folder.list();
        
        // Check if the folder is valid and contains files
        if (arrayOfFileNames == null)
            System.out.println("The folder path is invalid or contains no files: " + imageFolderPath); // TODO: Custom exception
        
        for (String currentFileName : arrayOfFileNames)
        {
            if (!currentFileName.endsWith(Constants_Resources.PNG_SUFFIX)) continue; // Only .png files are tracked
            
            // Use the first letter of the file name as the character value
            char keyChar = currentFileName.charAt(Constants_Panel.IMAGE_CHAR_POSITION);
            
            // Construct the full path of the image file
            File imageFile = new File(folder, currentFileName);
            String imagePath = imageFile.toURI().toString();
            
            // Load the image
            Image image = new Image(imagePath);
            currentMapOfCharsWithImages.put(keyChar, image);
        }
        
        return currentMapOfCharsWithImages;
    }
    
    
    public static Tile[][] getTileArray (HashMap<Character, Image> characterImageHashMap, char[][] charArray, int maxRows, int maxColumns)
    {
        Tile[][] tileArray = new Tile[maxRows][maxColumns];
        for (int row = Constants_Panel.MIN_TILE_INDEX; row < maxRows; row++)
        {
            for (int column = Constants_Panel.MIN_TILE_INDEX; column < maxColumns; column++)
            {
                if (charArray[row][column] == Constants_Panel.IGNORE_LOADER_FILE_VALUE) continue;
                tileArray[row][column] = new Tile(characterImageHashMap.get(charArray[row][column]));
            }
        }
        return tileArray;
    }
}
