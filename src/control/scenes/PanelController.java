package control.scenes;


import javafx.scene.image.Image;
import model.panel.Panel;
import model.panel.Tile;
import model.userInterface.Game;
import resources.constants.Constants_DefaultValues;
import resources.constants.Constants_ExceptionMessages;
import resources.constants.scenes.Constants_Map;
import resources.constants.Constants_Panel;
import utility.PanelAndTileLoader;

import java.util.HashMap;


public class PanelController
{
    private static Game game;
    private Panel panel;
    private String resourceFolderPath;
    HashMap<Character, Image> mapOfImagesWithCorrelatingChars;
    char[][] charArray;
    
    private int tileSize;
    private int maxColumns;
    private int maxRows;
    
    
    private PanelController (Game game)
    {
        this.game = game;
    }
    
    
    public static synchronized void initialize (Game game)
    {
        if (PanelController.game == null)
        {
            PanelController.game = game;
        } else
        {
            throw new IllegalStateException(Constants_ExceptionMessages.ALREADY_INITIALIZED);
        }
    }
    
    
    public PanelController (String resourceFolderPath, int tileSize, int maxRows, int maxColumns)
    {
        this.resourceFolderPath = resourceFolderPath;
        this.tileSize = tileSize;
        this.maxRows = maxRows;
        this.maxColumns = maxColumns;
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
    
    
    public boolean isCoordinateOccupied (double x, double y) throws Exception
    {
        int row = getTileIndexFromPositionX(x);
        int column = getTileIndexFromPositionY(y);
        
        // Checks whether indices out of bounds
        if (row < Constants_Panel.MIN_TILE_INDEX || column < Constants_Panel.MIN_TILE_INDEX ||
                row > maxRows || column > maxColumns) throw new Exception("Out of bounds."); // TODO: Custom exception
        
        if (isTileOccupied(row, column)) return true; // Check whether tile with correlating coordinates is occupied
        return false;
    }
    
    
    public int getTileIndexFromPositionX (double position)
    {
        return (int) (position -
                ((game.getCurrentShowable().getScene().getWidth() / Constants_Panel.HALFING) -
                        (maxRows * tileSize / Constants_Panel.HALFING)));
    }
    
    
    public int getTileIndexFromPositionY (double position)
    {
        return (int) (position -
                ((game.getCurrentShowable().getScene().getHeight() / Constants_Panel.HALFING) -
                        (maxColumns * tileSize / Constants_Panel.HALFING)));
    }
    
    
    public void showPanel()
    {
    
    }
}
