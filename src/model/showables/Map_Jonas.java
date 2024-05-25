package model.showables;


import resources.Constants_Map;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class Map_Jonas
{
    // attributes
    private char[][] tileChars;
    private int maxScreenColumn;
    private int maxScreenRow;


    // constructor
    public Map_Jonas (int maxScreenColumn, int maxScreenRow)
    {
        this.maxScreenColumn = maxScreenColumn;
        this.maxScreenRow = maxScreenRow;
        tileChars = new char[maxScreenColumn][maxScreenRow];
    }


    public void loadMap(String path)
    {
        InputStream inputStream = getClass().getResourceAsStream(path);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        int row = Constants_Map.ZERO;
        try
        {
            String line;
            while ((line = bufferedReader.readLine()) != null && row < maxScreenRow)
            {
                char[] characters = line.toCharArray();
                for (int column = Constants_Map.ZERO; column < maxScreenColumn && column < characters.length; column++)
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


    // getter methods
    public char[][] getTileChars()
    {
        return tileChars;
    }


    public char getTileCharAt(int x, int y)
    {
        if (x < Constants_Map.ZERO || x >= tileChars.length || y < Constants_Map.ZERO || y >=
                tileChars[Constants_Map.ZERO].length)
        {
            return ' '; // Return an empty space if coordinates are out of bounds
        }
        return tileChars[x][y];
    }
}
