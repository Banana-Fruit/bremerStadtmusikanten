package model;


import javafx.scene.Scene;
import resources.Constants_Scenes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class TileMap
{
    private int maxScreenColumns;
    private int maxScreenRows;
    private char[][] tileChars = new char[maxScreenColumns][maxScreenRows];
    
    
    public TileMap(int maxScreenColumns, int maxScreenRows)
    {
        this.maxScreenColumns = maxScreenColumns;
        this.maxScreenRows = maxScreenRows;
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
}
