package utility;


import javafx.scene.image.Image;
import model.panel.Tile;
import resources.constants.Constants_DefaultValues;
import resources.constants.Constants_Panel;
import resources.constants.Constants_Resources;

import java.io.*;
import java.util.HashMap;
import java.util.Map;


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
            System.out.println(Constants_Panel.INVALID_FILE_PATH + imageFolderPath); // TODO: Custom exception
        
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

    //---------------------JONAS_MAP---------------------
    public static int[][] getCharacterArrayUsingTileFile_JonasMap(String loaderFilePath, int maxRows, int maxColumns)
    {
        int[][] integerArray = new int[maxRows][maxColumns];
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(loaderFilePath)))
        {
            int row = Constants_Panel.MIN_TILE_INDEX;
            for (int lineIndex = Constants_Panel.FILE_READER_STARTING_LINE; row < maxRows; lineIndex++)
            {
                if (lineIndex == Constants_Panel.BIOME_IDENTIFIER_LINE) continue;
                char[] characters = bufferedReader.readLine().toCharArray();
                int column = Constants_Panel.MIN_TILE_INDEX;
                StringBuilder numberBuilder = new StringBuilder();
                for (char currentChar : characters)
                {
                    if (Character.isDigit(currentChar))
                    {
                        numberBuilder.append(currentChar);
                    } else if (currentChar == ' ' && numberBuilder.length() > Constants_DefaultValues.ZERO)
                    {
                        integerArray[row][column++] = Integer.parseInt(numberBuilder.toString());
                        numberBuilder.setLength(Constants_DefaultValues.ZERO);
                    }
                }
                if (numberBuilder.length() > Constants_DefaultValues.ZERO)
                {
                    integerArray[row][column] = Integer.parseInt(numberBuilder.toString());
                }
                row++;
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return integerArray;
    }


    public static Tile[][] getTileArray_JonasMap(HashMap<Integer, Image> integerImageHashMap, int[][] intArray, int maxRows, int maxColumns)
    {
        Tile[][] tileArray = new Tile[maxRows][maxColumns];
        Map<Integer, Boolean> occupancyData = null;
        try
        {
            occupancyData = readOccupancyData_JonasMap();
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
                } else
                {
                    tileArray[row][column] = new Tile(new Image(Constants_Panel.FILE_PATH_GRASS));
                }
                if (occupancyData != null && occupancyData.containsKey(intValue))
                {
                    tileArray[row][column].setOccupied(occupancyData.get(intValue));
                }
            }
        }
        return tileArray;
    }


    public static HashMap<Integer, Image> getMapWithIntegersAndImages_JonasMap(String imageFolderPath)
    {
        HashMap<Integer, Image> currentMapOfCharsWithImages = new HashMap<>();
        File folder = new File(imageFolderPath);
        String[] arrayOfFileNames = folder.list();
        if (arrayOfFileNames == null)
        {
            System.out.println(Constants_Panel.INVALID_FILE_PATH + imageFolderPath);
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
                } else {
                    break;
                }
            }
            if (numericPartBuilder.length() == 0) continue;
            int keyInt = Integer.parseInt(numericPartBuilder.toString());
            File imageFile = new File(folder, currentFileName);
            String imagePath = imageFile.toURI().toString();
            Image image = new Image(imagePath);
            currentMapOfCharsWithImages.put(keyInt, image);
        }
        return currentMapOfCharsWithImages;
    }


    public static Map<Integer, Boolean> readOccupancyData_JonasMap() throws IOException
    {
        Map<Integer, Boolean> occupancyData = new HashMap<>();
        InputStream is = PanelAndTileLoader.class.getResourceAsStream(Constants_Panel.FILE_PATH_TILE_DATA);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line;
        while ((line = br.readLine()) != null)
        {
            int tileId = Integer.parseInt(line.trim().substring(Constants_DefaultValues.ZERO, Constants_DefaultValues.THREE));
            if ((line = br.readLine()) != null)
            {
                boolean isOccupied = Boolean.parseBoolean(line.trim());
                occupancyData.put(tileId, isOccupied);
            }
        }
        br.close();
        return occupancyData;
    }
}
