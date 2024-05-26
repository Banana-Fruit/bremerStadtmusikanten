package model.showables;


import javafx.scene.Scene;
import resources.Constants_Map;
import resources.Constants_Scenes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class Map extends Showable
{
    
    private int maxScreenColumns = Constants_Scenes.MAX_SCREEN_COLUMNS_MAP;
    private int maxScreenRows = Constants_Scenes.MAX_SCREEN_ROWS_MAP;
    private char[][] tileChars = new char[maxScreenColumns][maxScreenRows];
    
    public Map(Scene scene)
    {
        super(scene, Constants_Scenes.IDENTIFIER_MAP);
    }
    
    
    public void loadMap(String path)
    {
        InputStream inputStream = getClass().getResourceAsStream(path);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        
        int row = Constants_Map.ZERO;
        try
        {
            String line;
            while ((line = bufferedReader.readLine()) != null && row < maxScreenRows)
            {
                char[] characters = line.toCharArray();
                for (int column = Constants_Map.ZERO; column < maxScreenColumns && column < characters.length; column++)
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
        }
    }
    
    
    public char[][] getTileChars()
    {
        return this.tileChars;
    }
    
    
    public char getTileCharAt(int x, int y)
    {
        if (x < Constants_Map.ZERO || x >= tileChars.length || y < Constants_Map.ZERO || y >=
                tileChars[Constants_Map.ZERO].length)
        {
            return Constants_Map.EMPTY_CHAR_SPACE; // Return an empty space if coordinates are out of bounds
        }
        return tileChars[x][y];
    }
}
