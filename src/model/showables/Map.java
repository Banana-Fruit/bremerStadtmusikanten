package model.showables;


import javafx.scene.Scene;
import resources.Constants_ExceptionMessages;
import resources.Constants_Map;
import resources.Constants_Scenes;


public class Map extends Showable
{
    private static volatile Map instance;
    private char[][] tileCharArray = new char[Constants_Map.MAX_SCREEN_COLUMN][Constants_Map.MAX_SCREEN_ROW];
    
    
    private Map(Scene scene)
    {
        super(scene, Constants_Scenes.IDENTIFIER_MAP);
    }
    
    
    public static synchronized void initialize(Scene scene)
    {
        if (instance == null)
        {
            instance = new Map(scene);
        } else
        {
            throw new IllegalStateException(Constants_ExceptionMessages.SINGLETON_ALREADY_INITIALIZED);
        }
    }
    
    
    public static Map getInstance()
    {
        if (instance == null)
        {
            throw new IllegalStateException(Constants_ExceptionMessages.SINGLETON_NOT_INITIALIZED);
        }
        return instance;
    }
    
    
    public void setTileCharArray (char[][] tileCharArray)
    {
        this.tileCharArray = tileCharArray;
    }
    
    
    public char[][] getTileCharArray ()
    {
        return this.tileCharArray;
    }
    
    
    public char getTileCharAt(int x, int y)
    {
        try
        {
            return tileCharArray[x][y];
        } catch (Exception e)
        {
            e.printStackTrace(); // TODO: Custom Exception
        } finally
        {
            return Constants_Map.EMPTY_CHAR_SPACE;
        }
    }
}
