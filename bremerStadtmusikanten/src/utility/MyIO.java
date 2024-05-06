package utility;


import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class MyIO
{
    private static final String DATE_TIME_FORMAT = "dd-MM-yyyy HH:mm:ss:SSS";
    private static final String PROMPT = "-> ";

    private static boolean timeStamp = true;
    private static boolean verboseMode = true;


    public static void print (String text)
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


    public static void print (String text, ConsoleColor color)
    {
        System.out.print(color);
        print(text);
        System.out.print(ConsoleColor.RESET);
    }


    public static void print (String text, ConsoleColor color, ConsoleColor backgroundColor)
    {
        System.out.print(color);
        System.out.print(backgroundColor);
        print(text);
        System.out.print(ConsoleColor.RESET);
    }


    public static void setTimeStamp (boolean timeStamp)
    {
        MyIO.timeStamp = timeStamp;
    }


    public static Image loadImage (String path)
    {
        try
        {
            File file = new File(path);
            return ImageIO.read(file);
        } catch (IOException e)
        {
            e.printStackTrace();
            return null;
        }
    }
}