package model.showables;


import javafx.scene.Scene;
import resources.Constants_Scenes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class Map extends Showable
{
    private int maxScreenColumns = Constants_Scenes.MAX_SCREEN_COLUMNS;
    private int maxScreenRows = Constants_Scenes.MAX_SCREEN_ROWS;
    private char[][] tileChars = new char[maxScreenColumns][maxScreenRows];
    
    public Map(Scene scene)
    {
        super(scene);
    }
    
    public void loadMap(String path)
    {
        InputStream is = getClass().getResourceAsStream(path);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        
        int row = 0;
        try
        {
            String line;
            while ((line = br.readLine()) != null && row < maxScreenRows)
            {
                char[] characters = line.toCharArray();
                for (int col = 0; col < maxScreenColumns && col < characters.length; col++)
                {
                    tileChars[col][row] = characters[col];
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
                br.close();
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
        if (x < 0 || x >= tileChars.length || y < 0 || y >= tileChars[0].length)
        {
            return ' '; // Return an empty space if coordinates are out of bounds
        }
        return tileChars[x][y];
    }
}
