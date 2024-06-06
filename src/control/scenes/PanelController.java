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
    private static volatile PanelController instance = null;
    
    
    private PanelController () {}
    
    
    public static synchronized void initialize ()
    {
        if (PanelController.instance == null)
        {
            PanelController.instance = new PanelController();
        } else
        {
            throw new IllegalStateException(Constants_ExceptionMessages.ALREADY_INITIALIZED);
        }
    }
    
    
    public static PanelController getInstance ()
    {
        if (instance == null)
        {
            throw new IllegalStateException(Constants_ExceptionMessages.SINGLETON_NOT_INITIALIZED);
        }
        return instance;
    }
    
    
    /**
     * Initializer loads the images from the path.
     */
    public Panel initializePanel (String backgroundLoaderFile, String interactibleLoaderFile,
                                  int tileSize, int maxRows, int maxColumns)
    {
        HashMap<Character, Image> mapOfImagesWithCorrelatingChars = PanelAndTileLoader.getMapWithCharsAndImagesFromPath(resourceFolderPath);
        char charArray[][] = PanelAndTileLoader.loadCharFileOfMapFromPath(resourceFolderPath);
        return new Panel(getTileArrayFromMapAndCharArray(mapOfImagesWithCorrelatingChars, charArray), tileSize, maxRows, maxColumns);
    }
    
    
    /**
     * Creates a panel array by using a Map with images and their correlating chars, and the structure of the char array
     * to create an array based on the char arrays structure, but with Tiles.
     *
     * @param mapOfImagesWithCorrelatingChars
     * @param charArray
     * @return
     */
    private Tile[][] getTileArrayFromMapAndCharArray (HashMap<Character, Image> mapOfImagesWithCorrelatingChars,
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
    public boolean isTileOccupied (Panel panel, int tileRow, int tileColumn)
    {
        return panel.getTileAt(tileRow, tileColumn).getOccupied();
    }
    
    
    /**
     * Accepts coordinates, and checks whether the tile at that coordinate is occupied.
     *
     * @param x
     * @param y
     * @return
     * @throws Exception
     */
    public boolean isCoordinateOccupied (Panel panel, double x, double y) throws Exception
    {
        int rowIndex = getTileIndexFromPositionX(panel, x);
        int columnIndex = getTileIndexFromPositionY(panel, y);
        
        // Checks whether indices out of bounds
        if (rowIndex < Constants_Panel.MIN_TILE_INDEX || columnIndex < Constants_Panel.MIN_TILE_INDEX ||
                rowIndex > panel.getMaxRows() || columnIndex > panel.getMaxColumns())
            throw new Exception("Out of bounds."); // TODO: Custom exception
        
        if (isTileOccupied(panel, rowIndex, columnIndex))
            return true; // Check whether tile with correlating coordinates is occupied
        return false;
    }
    
    
    /**
     * Converts a position value on the x-axis, to a tile index on the x-axis.
     *
     * @param position
     * @return
     */
    private int getTileIndexFromPositionX (Panel panel, double position)
    {
        double index = (position -
                (Game.getInstance().getCurrentShowable().getScene().getWidth() / (double) Constants_Panel.HALFING) -
                ((double) panel.getMaxRows() * (double) panel.getTileSize() / (double) Constants_Panel.HALFING));
        return (int) index; // Conversion cuts of decimal places, leaving the exact index
    }
    
    
    /**
     * Converts a position value on the y-axis, to a tile index on the y-axis.
     *
     * @param position
     * @return
     */
    private int getTileIndexFromPositionY (Panel panel, double position)
    {
        double index = (position -
                ((Game.getInstance().getCurrentShowable().getScene().getHeight() / (double) Constants_Panel.HALFING) -
                        ((double) panel.getMaxColumns() * (double) panel.getTileSize() / (double) Constants_Panel.HALFING)));
        return (int) index; // Conversion cuts of decimal places, leaving the exact index
    }
    
    
    /**
     * Returns an instance of Coordinates with the coordinates of the Tile at the parameter indices.
     *
     * @param rowIndex
     * @param columnIndex
     * @return
     */
    private Coordinates getCoordinatesFromPanelTile (Panel panel, int rowIndex, int columnIndex)
    {
        return new Coordinates(getPositionXFromTileIndex(panel, rowIndex), getPositionYFromTileIndex(panel, columnIndex));
    }
    
    
    /**
     * Converts a tile index value on the x-axis, to position on the x-axis.
     *
     * @param index
     * @return
     */
    public double getPositionXFromTileIndex (Panel panel, int index)
    {
        double position = ((Game.getInstance().getCurrentShowable().getScene().getWidth() / Constants_Panel.HALFING) -
                (((double) panel.getMaxRows() * (double) panel.getTileSize()) / ((double) Constants_Panel.HALFING)) + (index * panel.getTileSize()));
        return position;
    }
    
    
    /**
     * Converts a tile index value on the y-axis, to position on the y-axis.
     *
     * @param index
     * @return
     */
    public double getPositionYFromTileIndex (Panel panel, int index)
    {
        double position = ((Game.getInstance().getCurrentShowable().getScene().getHeight() / Constants_Panel.HALFING) -
                (((double) panel.getMaxColumns() * (double) panel.getTileSize()) / ((double) Constants_Panel.HALFING)) + (index * panel.getTileSize()));
        return position;
    }
    
    
    /**
     * Makes the panel visible on the window.
     */
    public void showPanel (Panel panel)
    {
        PanelView.addTilesToPane(panel, Game.getInstance().getCurrentShowable().getPane());
    }
}
