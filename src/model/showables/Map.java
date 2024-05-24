package model.showables;


import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Collectable;
import model.Obstacle;
import model.Player;
import resources.Constants_DefaultValues;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;


public class Map extends Showable
{
    private char[][] tileChars;
    private int maxScreenCol;
    private int maxScreenRow;
    
    public Map(int maxScreenCol, int maxScreenRow)
    {
        this.maxScreenCol = maxScreenCol;
        this.maxScreenRow = maxScreenRow;
        tileChars = new char[maxScreenCol][maxScreenRow];
    }
    
    public void loadMap(String path)
    {
        InputStream is = getClass().getResourceAsStream(path);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        
        int row = 0;
        try
        {
            String line;
            while ((line = br.readLine()) != null && row < maxScreenRow)
            {
                char[] characters = line.toCharArray();
                for (int col = 0; col < maxScreenCol && col < characters.length; col++)
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
        return tileChars;
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
