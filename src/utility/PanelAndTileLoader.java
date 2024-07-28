package utility;


import javafx.scene.image.Image;
import model.panel.Tile;
import resources.constants.Constants_DefaultValues;
import resources.constants.Constants_Panel;
import resources.constants.Constants_Resources;

import java.io.*;
import java.util.HashMap;


/**
 * Responsible for loading panels consisting of tiles from a loader file.
 *
 * @author Michael Markov
 */
public class PanelAndTileLoader
{
    /**
     * Reads a tile file and converts it into a 2D integer array representing the map.
     * The pictures should contain an individual number at the beginning of the file name.
     * If a non-number char appears, the numbers afterward will be ignored.
     *
     * @param loaderFilePath The path to the tile file.
     * @param maxRows        The maximum number of rows in the map.
     * @param maxColumns     The maximum number of columns in the map.
     * @return A 2D integer array representing the map.
     * @author Jonas Helfer, Michael Markov
     */
    public static int[][] getIntegerArrayUsingLoaderFile (String loaderFilePath, int maxRows, int maxColumns)
    {
        int[][] integerArray = new int[maxRows][maxColumns];
        
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(loaderFilePath)))
        {
            String line;
            for (int row = Constants_Panel.MIN_TILE_INDEX; row < maxRows && (line = bufferedReader.readLine()) != null; row++)
            {
                // Splits the line into String arrays separated by the splitter character
                String[] parts = line.split(Character.toString(Constants_Panel.SPLITTER_CHARACTER));
                
                for (int column = Constants_Panel.MIN_TILE_INDEX; column < maxColumns && column < parts.length; column++)
                {
                    if (parts[column] != null && !parts[column].isEmpty() && !parts[column].equals(Constants_Panel.EMPTY_STRING))
                    {
                        // Parses each string into an integer and adds it to the right position in the integer array
                        integerArray[row][column] = Integer.parseInt(parts[column].trim());
                    }
                }
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        
        return integerArray;
    }
    
    
    /**
     * Creates a 2D array of Tile objects based on the provided integer array and image map.
     *
     * @param integerImageHashMap A map of integer keys to Image objects.
     * @param intArray            The 2D integer array representing the map.
     * @param maxRows             The maximum number of rows in the map.
     * @param maxColumns          The maximum number of columns in the map.
     * @return A 2D array of Tile objects.
     * @author Jonas Helfer
     */
    public static Tile[][] getTileArray (HashMap<Integer, Image> integerImageHashMap, HashMap<Integer, Boolean> occupancyData,
                                         int[][] intArray, int maxRows, int maxColumns)
    {
        Tile[][] tileArray = new Tile[maxRows][maxColumns];
        
        // Loop through all possible rows
        for (int row = Constants_DefaultValues.START_FOR_LOOP; row < maxRows; row++)
        {
            // Loop through all possible columns
            for (int column = Constants_DefaultValues.START_FOR_LOOP; column < maxColumns; column++)
            {
                int intValue = intArray[row][column];
                if (intValue == Constants_Panel.IGNORE_LOADER_FILE_VALUE) continue;
                if (integerImageHashMap.containsKey(intValue))
                {
                    // Add to current row and column the tile with the image that has the integer value assigned in the map
                    tileArray[row][column] = new Tile(integerImageHashMap.get(intValue));
                }
                if (occupancyData != null && occupancyData.containsKey(intValue))
                {
                    // Set tile of the current row and column to the occupancy data of the current row and column
                    tileArray[row][column].setOccupied(occupancyData.get(intValue));
                }
            }
        }
        
        return tileArray;
    }
    
    
    /**
     * Creates a map of integer keys to Image objects based on image files in a specified folder.
     * The pictures should contain an individual number at the beginning of the file name.
     * If a non-number char appears, the numbers afterward will be ignored.
     * The image type has to be a .png format.
     *
     * @param pathToTileResources The path to the folder containing the image files.
     * @return A HashMap mapping integer keys to Image objects.
     * @author Jonas Helfer, Michael Markov
     */
    public static HashMap<Integer, Image> getMapWithIntegersAndImages (String pathToTileResources)
    {
        HashMap<Integer, Image> currentMapOfCharsWithImages = new HashMap<>();
        File folder = new File(pathToTileResources);
        String[] arrayOfFileNames = folder.list(); // Get array of names of contents of the folder
        
        // If empty return error code
        if (arrayOfFileNames == null)
        {
            System.out.println(Constants_Panel.INVALID_FILE_PATH + pathToTileResources);
            return currentMapOfCharsWithImages;
        }
        
        // Run through contents of folder
        for (String currentFileName : arrayOfFileNames)
        {
            if (!currentFileName.endsWith(Constants_Resources.PNG_SUFFIX)) continue; // If not a picture skip the name
            
            StringBuilder numericPartBuilder = new StringBuilder();
            for (char c : currentFileName.toCharArray()) // Turns the String of the current file name into char array to iterate through
            {
                if (Character.isDigit(c))
                {
                    numericPartBuilder.append(c); // Add if digit
                } else
                {
                    break; // If non-digit character detected, stop adding numbers
                }
            }
            if (numericPartBuilder.isEmpty()) continue; // If is empty, the program will not bother executing the followed lines
            
            // Get key Integer by parsing String into Integer
            int keyInt = Integer.parseInt(numericPartBuilder.toString());
            
            // Get full image path
            File imageFile = new File(folder, currentFileName);
            String imagePath = imageFile.toURI().toString();
            Image image = new Image(imagePath);
            
            // Add entry
            currentMapOfCharsWithImages.put(keyInt, image);
        }
        
        return currentMapOfCharsWithImages;
    }
    
    
    /**
     * Reads the Occupancy file and creates a map of tile IDs to their occupancy status.
     * The pictures should contain an individual number at the beginning of the file name.
     * If a non-number char appears, the numbers afterward will be ignored.
     *
     * @return A Map of Integer keys (tile IDs) to Boolean values (occupancy status).
     * @throws IOException If there's an error reading the file.
     * @author Jonas Helfer, Michael Markov
     */
    public static HashMap<Integer, Boolean> readOccupancyData (String pathToTileData)
    {
        HashMap<Integer, Boolean> occupancyData = new HashMap<>();
        
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(pathToTileData)))
        {
            String line;
            while ((line = bufferedReader.readLine()) != null) // As long as the read line is not empty
            {
                StringBuilder numericPartBuilder = new StringBuilder();
                for (char c : line.toCharArray()) // Turns the String of the current file name into char array to iterate through
                {
                    if (Character.isDigit(c))
                    {
                        numericPartBuilder.append(c); // Add if digit
                    } else
                    {
                        break; // If non-digit character detected, stop adding numbers
                    }
                }
                if (numericPartBuilder.length() == Constants_Panel.STARTING_POINT) continue; // If no numbers detected skip current file
                int tileId = Integer.parseInt(numericPartBuilder.toString()); // Parse digits to Integer
                
                if ((line = bufferedReader.readLine()) != null) // Next line after file name is supposed to be a Boolean value
                {
                    boolean isOccupied = Boolean.parseBoolean(line.trim()); // Remove whitespaces and parse to Boolean
                    occupancyData.put(tileId, isOccupied); // Add entry to Map
                }
            }
        } catch (FileNotFoundException e)
        {
            throw new RuntimeException(e);
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        
        return occupancyData;
    }
}
