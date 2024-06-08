package control.scenes;


import javafx.scene.image.Image;
import model.Coordinate;
import model.panel.Panel;
import model.userInterface.Game;
import resources.constants.Constants_ExceptionMessages;
import resources.constants.Constants_Panel;
import resources.constants.Constants_Resources;
import utility.PanelAndTileLoader;
import view.PanelView;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;


public class PanelController
{
    private static volatile PanelController instance = null;
    
    
    private PanelController ()
    {
    }
    
    
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
     * Initializer returns a panel with background images, if available.
     */
    public Panel initializePanel (String pathToLoaderFileFolder, String loaderFileName, int tileSize, int maxRows, int maxColumns)
    {
        // Create Array of characters
        char charArray[][] = PanelAndTileLoader.getCharacterArrayUsingTileFile(
                pathToLoaderFileFolder + loaderFileName, maxRows, maxColumns);
        
        // Get biome name
        String biomeName = new String();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(
                pathToLoaderFileFolder + loaderFileName)))
        {
            String line;
            for (int i = 0; (line = bufferedReader.readLine()) != null; i++)
            {
                if (i != Constants_Panel.BIOME_IDENTIFIER_LINE) continue;
                biomeName = line;
                break;
            }
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        // Create path to resource folder with biome
        String pathToResourceFolder =
                pathToLoaderFileFolder.replace(Constants_Resources.LOADER_FILES_FOLDER, Constants_Panel.EMPTY_STRING)
                + biomeName;
        
        // Put characters in correlation to images
        HashMap<Character, Image> mapOfCharactersWithCorrelatingImages =
                PanelAndTileLoader.getMapWithCharsAndImages(pathToResourceFolder);
        
        return new Panel(PanelAndTileLoader.getTileArray(
                mapOfCharactersWithCorrelatingImages, charArray, maxRows, maxColumns),
                tileSize, maxRows, maxColumns);
    }
    
    
    /**
     * Checks whether a specific tile in the panel is occupied.
     *
     * @param panel
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
     * @param panel
     * @param coordinate
     * @return
     * @throws Exception
     */
    public boolean isCoordinateOccupied (Panel panel, Coordinate coordinate) throws Exception
    {
        int rowIndex = getTileIndexFromPositionX(panel, coordinate.getPositionX());
        int columnIndex = getTileIndexFromPositionY(panel, coordinate.getPositionY());
        
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
                (Game.getInstance().getCurrentShowable().getScene().getWidth() / (double) Constants_Panel.DIVIDE_BY_VALUE_TO_GET_HALF) -
                ((double) panel.getMaxRows() * (double) panel.getTileSize() / (double) Constants_Panel.DIVIDE_BY_VALUE_TO_GET_HALF));
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
                ((Game.getInstance().getCurrentShowable().getScene().getHeight() / (double) Constants_Panel.DIVIDE_BY_VALUE_TO_GET_HALF) -
                        ((double) panel.getMaxColumns() * (double) panel.getTileSize() / (double) Constants_Panel.DIVIDE_BY_VALUE_TO_GET_HALF)));
        return (int) index; // Conversion cuts of decimal places, leaving the exact index
    }
    
    
    /**
     * Returns an instance of Coordinates with the coordinates of the Tile at the parameter indices.
     *
     * @param rowIndex
     * @param columnIndex
     * @return
     */
    private Coordinate getCoordinateFromPanelTile (Panel panel, int rowIndex, int columnIndex)
    {
        return new Coordinate(getPositionXFromTileIndex(panel, rowIndex), getPositionYFromTileIndex(panel, columnIndex));
    }
    
    
    /**
     * Converts a tile index value on the x-axis, to position on the x-axis.
     *
     * @param index
     * @return
     */
    public double getPositionXFromTileIndex (Panel panel, int index)
    {
        double position = ((Game.getInstance().getCurrentShowable().getScene().getWidth() / Constants_Panel.DIVIDE_BY_VALUE_TO_GET_HALF) -
                (((double) panel.getMaxRows() * (double) panel.getTileSize()) / ((double) Constants_Panel.DIVIDE_BY_VALUE_TO_GET_HALF)) + (index * panel.getTileSize()));
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
        double position = ((Game.getInstance().getCurrentShowable().getScene().getHeight() / Constants_Panel.DIVIDE_BY_VALUE_TO_GET_HALF) -
                (((double) panel.getMaxColumns() * (double) panel.getTileSize()) / ((double) Constants_Panel.DIVIDE_BY_VALUE_TO_GET_HALF)) + (index * panel.getTileSize()));
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
