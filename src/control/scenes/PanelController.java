package control.scenes;


import javafx.scene.image.Image;
import model.Coordinates;
import model.panel.Panel;
import model.panel.Tile;
import model.userInterface.Game;
import resources.constants.Constants_DefaultValues;
import resources.constants.Constants_ExceptionMessages;
import resources.constants.Constants_Panel;
import utility.PanelAndTileLoader;
import view.PanelView;

import java.util.HashMap;


public class PanelController
{
    private static volatile Game game;
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
    
    
    /**
     * Initializer loads the images from the path.
     */
    private void init ()
    {
        this.mapOfImagesWithCorrelatingChars = PanelAndTileLoader.getMapWithCharsAndImagesFromPath(resourceFolderPath);
        this.charArray = PanelAndTileLoader.loadCharFileOfMapFromPath(resourceFolderPath);
        this.panel = new Panel(getPanelArrayFromMapAndCharArray(this.mapOfImagesWithCorrelatingChars, this.charArray));
    }
    
    
    /**
     * Can be used to change the resource path, resulting with different images being loaded.
     *
     * @param resourceFolderPath
     */
    public void changePanel (String resourceFolderPath)
    {
        this.resourceFolderPath = resourceFolderPath;
        this.mapOfImagesWithCorrelatingChars = PanelAndTileLoader.getMapWithCharsAndImagesFromPath(resourceFolderPath);
        this.charArray = PanelAndTileLoader.loadCharFileOfMapFromPath(resourceFolderPath);
        this.panel = new Panel(getPanelArrayFromMapAndCharArray(this.mapOfImagesWithCorrelatingChars, this.charArray));
    }
    
    
    /**
     * Creates a panel array by using a Map with images and their correlating chars, and the structure of the char array
     * to create an array based on the char arrays structure, but with Tiles.
     *
     * @param mapOfImagesWithCorrelatingChars
     * @param charArray
     * @return
     */
    private Tile[][] getPanelArrayFromMapAndCharArray (HashMap<Character, Image> mapOfImagesWithCorrelatingChars,
                                                       char[][] charArray)
    {
        Tile[][] tileArray = new Tile[charArray.length - Constants_DefaultValues.LENGTH_TO_SIZE_SUBTRACTOR]
                [charArray[Constants_Panel.INDEX_WITH_MAX_VALUE].length - Constants_DefaultValues.LENGTH_TO_SIZE_SUBTRACTOR];
        
        for (int row = Constants_Panel.MINIMUM_ARRAY_VALUE; row < charArray.length; row++)
        {
            for (int column = Constants_Panel.MINIMUM_ARRAY_VALUE;
                 column < charArray[Constants_Panel.INDEX_WITH_MAX_VALUE].length;
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
    
    
    /**
     * Checks whether a specific tile in the panel is occupied.
     *
     * @param tileRow
     * @param tileColumn
     * @return
     */
    public boolean isTileOccupied (int tileRow, int tileColumn)
    {
        return this.panel.getTileAt(tileRow, tileColumn).getOccupied();
    }
    
    
    /**
     * Accepts coordinates, and checks whether the tile at that coordinate is occupied.
     *
     * @param x
     * @param y
     * @return
     * @throws Exception
     */
    public boolean isCoordinateOccupied (double x, double y) throws Exception
    {
        int rowIndex = getTileIndexFromPositionX(x);
        int columnIndex = getTileIndexFromPositionY(y);
        
        // Checks whether indices out of bounds
        if (rowIndex < Constants_Panel.MIN_TILE_INDEX || columnIndex < Constants_Panel.MIN_TILE_INDEX ||
                rowIndex > maxRows || columnIndex > maxColumns)
            throw new Exception("Out of bounds."); // TODO: Custom exception
        
        if (isTileOccupied(rowIndex, columnIndex))
            return true; // Check whether tile with correlating coordinates is occupied
        return false;
    }
    
    
    /**
     * Converts a position value on the x-axis, to a tile index on the x-axis.
     *
     * @param position
     * @return
     */
    private int getTileIndexFromPositionX (double position)
    {
        double index = (position -
                (game.getCurrentShowable().getScene().getWidth() / (double) Constants_Panel.HALFING) -
                ((double) maxRows * (double) tileSize / (double) Constants_Panel.HALFING));
        return (int) index; // Conversion cuts of decimal places, leaving the exact index
    }
    
    
    /**
     * Converts a position value on the y-axis, to a tile index on the y-axis.
     *
     * @param position
     * @return
     */
    private int getTileIndexFromPositionY (double position)
    {
        double index = (position -
                ((game.getCurrentShowable().getScene().getHeight() / (double) Constants_Panel.HALFING) -
                        ((double) maxColumns * (double) tileSize / (double) Constants_Panel.HALFING)));
        return (int) index; // Conversion cuts of decimal places, leaving the exact index
    }
    
    
    /**
     * Returns an instance of Coordinates with the coordinates of the Tile at the parameter indices.
     *
     * @param rowIndex
     * @param columnIndex
     * @return
     */
    private Coordinates getCoordinatesFromPanelTile (int rowIndex, int columnIndex)
    {
        return new Coordinates(getPositionXFromTileIndex(rowIndex), getPositionYFromTileIndex(columnIndex));
    }
    
    
    /**
     * Converts a tile index value on the x-axis, to position on the x-axis.
     *
     * @param index
     * @return
     */
    public double getPositionXFromTileIndex (int index)
    {
        double position = ((game.getCurrentShowable().getScene().getWidth() / Constants_Panel.HALFING) -
                (((double) maxRows * (double) tileSize) / ((double) Constants_Panel.HALFING)) + (index * tileSize));
        return position;
    }
    
    
    /**
     * Converts a tile index value on the y-axis, to position on the y-axis.
     *
     * @param index
     * @return
     */
    public double getPositionYFromTileIndex (int index)
    {
        double position = ((game.getCurrentShowable().getScene().getHeight() / Constants_Panel.HALFING) -
                (((double) maxColumns * (double) tileSize) / ((double) Constants_Panel.HALFING)) + (index * tileSize));
        return position;
    }
    
    
    /**
     * Makes the panel visible on the window.
     */
    public void showPanel ()
    {
        PanelView.addTilesToPane(this, panel, game.getCurrentShowable().getPane());
    }
}
