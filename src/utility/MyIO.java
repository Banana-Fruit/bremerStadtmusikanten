package utility;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import resources.Constants_Map;


public class MyIO
{
    private static final String DATE_TIME_FORMAT = "dd-MM-yyyy HH:mm:ss:SSS";
    private static final String PROMPT = "-> ";
    
    private static boolean timeStamp = true;
    private static boolean verboseMode = true;
    
    
    public static void print(String text)
    {
        if (verboseMode)
        {
            if (timeStamp)
            {
                System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT)) + PROMPT + text);
            } else
            {
                System.out.println(text);
            }
        }
    }
    
    
    public static void print(String text, ConsoleColor color)
    {
        System.out.print(color);
        print(text);
        System.out.print(ConsoleColor.RESET);
    }
    
    
    public static void print(String text, ConsoleColor color, ConsoleColor backgroundColor)
    {
        System.out.print(color);
        System.out.print(backgroundColor);
        print(text);
        System.out.print(ConsoleColor.RESET);
    }
    
    
    public static void setTimeStamp(boolean timeStamp)
    {
        MyIO.timeStamp = timeStamp;
    }
    
    
    public void loadMap(String path)
    {
        InputStream inputStream = getClass().getResourceAsStream(path);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        
        int row = Constants_Map.MINIMUM_ARRAY_VALUE;
        try
        {
            String line;
            while ((line = bufferedReader.readLine()) != null && row < Constants_Map.MAX_SCREEN_ROW)
            {
                char[] characters = line.toCharArray();
                for (int column = Constants_Map.MINIMUM_ARRAY_VALUE; column < Constants_Map.MAX_SCREEN_COLUMN && column < characters.length; column++)
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
}