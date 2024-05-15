package utility;


import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javafx.scene.image.Image;


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


    public static Image getImageFromPath (String path)
    {
        Image image = new Image(path);
        return image;
    }
}