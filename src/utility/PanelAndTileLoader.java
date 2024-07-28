package utility;


import javafx.scene.image.Image;
import model.panel.Tile;
import resources.constants.Constants_DefaultValues;
import resources.constants.Constants_Panel;
import resources.constants.Constants_Resources;

import java.io.*;
import java.util.HashMap;
import java.util.Map;


/**
 * Responsible for loading panels consisting of tiles from a loader file.
 *
 * @author Michael Markov
 */
public class PanelAndTileLoader
{
    /**
     * Reads a tile file and converts it into a 2D integer array representing the map. The required formatting of the
     * picture name is
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
                String[] parts = line.split(Character.toString(Constants_Panel.SPLITTER_CHARACTER));
                
                for (int column = Constants_Panel.MIN_TILE_INDEX; column < maxColumns && column < parts.length; column++)
                {
                    if (parts[column] != null && !parts[column].isEmpty() && !parts[column].equals(Constants_Panel.EMPTY_STRING))
                    {
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
    public static Tile[][] getTileArray (HashMap<Integer, Image> integerImageHashMap, String pathToTileData, int[][] intArray, int maxRows, int maxColumns)
    {
        Tile[][] tileArray = new Tile[maxRows][maxColumns];
        Map<Integer, Boolean> occupancyData = null;
        try
        {
            occupancyData = readOccupancyData(pathToTileData);
        } catch (IOException e)
        {
            e.printStackTrace();
            return tileArray;
        }
        for (int row = Constants_DefaultValues.START_FOR_LOOP; row < maxRows; row++)
        {
            for (int column = Constants_DefaultValues.START_FOR_LOOP; column < maxColumns; column++)
            {
                int intValue = intArray[row][column];
                if (intValue == Constants_Panel.IGNORE_LOADER_FILE_VALUE) continue;
                if (integerImageHashMap.containsKey(intValue))
                {
                    tileArray[row][column] = new Tile(integerImageHashMap.get(intValue));
                }
                if (occupancyData != null && occupancyData.containsKey(intValue))
                {
                    tileArray[row][column].setOccupied(occupancyData.get(intValue));
                }
            }
        }
        return tileArray;
    }
    
    
    /**
     * Creates a map of integer keys to Image objects based on image files in a specified folder.
     *
     * @param pathToTileResources The path to the folder containing the image files.
     * @return A HashMap mapping integer keys to Image objects.
     * @author Jonas Helfer
     */
    public static HashMap<Integer, Image> getMapWithIntegersAndImages (String pathToTileResources)
    {
        HashMap<Integer, Image> currentMapOfCharsWithImages = new HashMap<>();
        File folder = new File(pathToTileResources);
        String[] arrayOfFileNames = folder.list();
        
        if (arrayOfFileNames == null)
        {
            System.out.println(Constants_Panel.INVALID_FILE_PATH + pathToTileResources);
            return currentMapOfCharsWithImages;
        }
        
        for (String currentFileName : arrayOfFileNames)
        {
            if (!currentFileName.endsWith(Constants_Resources.PNG_SUFFIX)) continue;
            StringBuilder numericPartBuilder = new StringBuilder();
            for (char c : currentFileName.toCharArray())
            {
                if (Character.isDigit(c))
                {
                    numericPartBuilder.append(c);
                } else
                {
                    break;
                }
            }
            if (numericPartBuilder.length() == Constants_Panel.STARTING_POINT) continue;
            int keyInt = Integer.parseInt(numericPartBuilder.toString());
            File imageFile = new File(folder, currentFileName);
            String imagePath = imageFile.toURI().toString();
            Image image = new Image(imagePath);
            currentMapOfCharsWithImages.put(keyInt, image);
        }
        
        return currentMapOfCharsWithImages;
    }
    
    
    /**
     * Reads the Occupancy file and creates a map of tile IDs to their occupancy status.
     *
     * @return A Map of Integer keys (tile IDs) to Boolean values (occupancy status).
     * @throws IOException If there's an error reading the file.
     * @author Jonas Helfer
     */
    public static Map<Integer, Boolean> readOccupancyData (String pathToTileData) throws IOException
    {
        Map<Integer, Boolean> occupancyData = new HashMap<>();
        
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(pathToTileData)))
        {
            String line;
            while ((line = bufferedReader.readLine()) != null)
            {
                StringBuilder numericPartBuilder = new StringBuilder();
                for (char c : line.toCharArray())
                {
                    if (Character.isDigit(c))
                    {
                        numericPartBuilder.append(c);
                    } else
                    {
                        break;
                    }
                }
                if (numericPartBuilder.length() == Constants_Panel.STARTING_POINT) continue;
                int tileId = Integer.parseInt(numericPartBuilder.toString());
                
                if ((line = bufferedReader.readLine()) != null)
                {
                    boolean isOccupied = Boolean.parseBoolean(line.trim());
                    occupancyData.put(tileId, isOccupied);
                }
            }
        }
        
        return occupancyData;
    }
}
