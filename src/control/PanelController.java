package control;


import javafx.scene.image.Image;
import model.panel.Panel;
import model.panel.Tile;
import model.userInterface.Game;
import resources.Constants_DefaultValues;
import resources.Constants_Map;
import resources.Constants_Panel;
import utility.PanelAndTileLoader;

import java.util.HashMap;


public class PanelController
{
    private static Game game;
    private Panel panel;
    private String resourceFolderPath;
    HashMap<Character, Image> mapOfImagesWithCorrelatingChars;
    char[][] charArray;
    
    private final int tileSize = Constants_Map.TILE_SIZE * Constants_Map.GAMEPANEL_SCALE; // Actual Tile size
    private final int maxScreenColumn = Constants_Map.MAX_SCREEN_COLUMN;
    private final int maxScreenRow = Constants_Map.MAX_SCREEN_ROW;
    private final int screenWidth = tileSize * maxScreenColumn;
    private final int screenHeight = tileSize * maxScreenRow;
    
    
    public PanelController (Game game, String resourceFolderPath) // TODO: Remove Game game and make a static initializer
    {
        this.game = game;
        this.resourceFolderPath = resourceFolderPath;
        init();
    }
    
    
    private void init ()
    {
        this.mapOfImagesWithCorrelatingChars = PanelAndTileLoader.getMapWithCharsAndImagesFromPath(resourceFolderPath);
        this.charArray = PanelAndTileLoader.loadCharFileOfMapFromPath(resourceFolderPath);
        this.panel = new Panel(getPanelArrayFromMapAndCharArray(this.mapOfImagesWithCorrelatingChars, this.charArray));
    }
    
    
    private Tile[][] getPanelArrayFromMapAndCharArray (HashMap<Character, Image> mapOfImagesWithCorrelatingChars,
                                                       char[][] charArray)
    {
        Tile[][] tileArray = new Tile[charArray.length - Constants_DefaultValues.LENGTH_TO_SIZE_SUBTRACTOR]
                [charArray[Constants_Panel.MAX_COLUMNS_ARRAY_INDEX].length - Constants_DefaultValues.LENGTH_TO_SIZE_SUBTRACTOR];
        
        for (int row = Constants_Map.MINIMUM_ARRAY_VALUE; row < charArray.length; row++)
        {
            for (int column = Constants_Map.MINIMUM_ARRAY_VALUE;
                 column < charArray[Constants_Panel.MAX_COLUMNS_ARRAY_INDEX].length;
                 column++)
            {
                char tileChar = charArray[column][row];
                javafx.scene.image.Image tileImage = mapOfImagesWithCorrelatingChars.get(tileChar);
                if (tileImage != null)
                {
                    tileArray[column][row] = new Tile(tileImage); // TODO: Determine method for isOccupied
                }
            }
        }
        
        return tileArray;
    }
    
    
    public boolean isTileOccupied (int tileRow, int tileColumn)
    {
        return this.panel.getTileAt(tileRow, tileColumn).getOccupied();
    }
    
    
    public boolean isCoordinateOccupied (int x, int y)
    {
        // Überprüfen, ob der Spieler innerhalb des Spielfelds bleibt
        if (x < 0 || y < 0 || x + gamePanel.tileSize > gamePanel.screenWidth || y + gamePanel.tileSize > gamePanel.screenHeight)
            return false;
        
        // Überprüfen, ob der Spieler über ein Hindernis läuft
        if (isTileOccupied(x, y) ||                   // Obere linke Ecke
                isTileOccupied(x + gamePanel.tileSize - 1, y) || // Obere rechte Ecke
                isTileOccupied(x, y + gamePanel.tileSize - 1) || // Untere linke Ecke
                isTileOccupied(x + gamePanel.tileSize - 1, y + gamePanel.tileSize - 1))
        { // Untere rechte Ecke
            return false;
        }
        return true;
    }
}
