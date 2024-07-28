package control.scenes;


import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import model.Coordinate;
import model.panel.Panel;
import resources.constants.Constants_DefaultValues;
import resources.constants.Constants_ExceptionMessages;
import resources.constants.Constants_Panel;
import utility.PanelAndTileLoader;
import view.PanelView;

import java.util.HashMap;


/**
 * The panel controller handles the loading of the panel as well as allowing player movement.
 *
 * @author Michael Markov
 */
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
    
    
    /**
     * Adds a panel to the pane, based on the given parameters, and returns it.
     * This method returns a panel with occupancy data.
     *
     * @param pane Pane to which the panel will be added.
     * @param pathToLoaderFile Path to where the Loader File is located.
     * @param pathToTileResourceFolder Path to folder with tile images.
     * @param tileSize Size of each Tile.
     * @param maxRows Amount of maximum rows.
     * @param maxColumns Amount of maximum columns.
     * @return Panel to be returned.
     * @author Michael Markov
     */
    public Panel getAndShowPanel (Pane pane, String pathToLoaderFile, String pathToTileResourceFolder, String pathToTileData,
                                  int tileSize, int maxRows, int maxColumns)
    {
        try
        {
            // Create panel from loader file name
            Panel panel = initializePanel(pathToLoaderFile, pathToTileResourceFolder, pathToTileData, tileSize, maxRows, maxColumns);
            // Add each tile to the pane with the right position
            PanelView.addTilesToPane(panel, pane);
            return panel;
        } catch (Exception e)
        {
            e.printStackTrace();
            // Consider throwing a custom exception or handling it appropriately
            return null;
        }
    }
    
    
    /**
     * Initializes a Panel by using Strings for a map using specified parameters and resources.
     * @author Jonas Helfer
     * @param pathToLoaderFile The path to the loader file.
     * @param pathToTileResourceFolder The path to the resource folder.
     * @param tileSize The size of each tile in pixels.
     * @param maxRows The maximum number of rows in the panel.
     * @param maxColumns The maximum number of columns in the panel.
     * @precondition The PanelAndTileLoader class is properly initialized and functional.
     *               The Constants_Panel class is properly defined with the FILE_PANEL constant.
     *               The specified path and loader file exist and are accessible.
     *               The maxRows and maxColumns are integers.
     *               The tileSize is a positive integer.
     * @postcondition A new Panel object is created and initialized with the specified parameters.
     *                The Panel contains a tile array based on the loader file and resource images.
     * @return A new Panel object initialized with the specified parameters and resources.
     */
    private Panel initializePanel (String pathToLoaderFile, String pathToTileResourceFolder, String pathToTileData,
                                   int tileSize, int maxRows, int maxColumns)
    {
        // Create array of characters
        int[][] intArray = PanelAndTileLoader.getIntegerArrayUsingLoaderFile(pathToLoaderFile, maxRows, maxColumns);
        
        // Put Integers in correlation to images
        HashMap<Integer, Image> mapOfCharactersWithCorrelatingImages = PanelAndTileLoader.getMapWithIntegersAndImages(
                pathToTileResourceFolder);
        
        // Replace integers in array through tiles with images (create tileArray) and return panel
        return new Panel(
                PanelAndTileLoader.getTileArray(mapOfCharactersWithCorrelatingImages, pathToTileData, intArray,
                        maxRows, maxColumns), tileSize, maxRows, maxColumns);
    }


    /**
     * Determines whether a given coordinate on the panel is occupied or out of bounds.
     * @author Micheal Markov, Jonas Helfer
     * @param panel The Panel object representing the game map.
     * @param coordinate The Coordinate object to check for occupation.
     * @precondition The Panel object is properly initialized with valid dimensions and tile.
     *               The Constants_Panel class is properly defined with MIN_TILE_INDEX and OBSTACLE_TILE_DEFAULT_VALUE constants.
     *               The getCoordinateFromPanelTile and isObstacle methods are properly implemented and accessible.
     * @postcondition The method returns true if the coordinate is out of bounds or occupied by an obstacle,
     *                false otherwise.
     * @return boolean True if the coordinate is occupied or out of bounds, false otherwise.
     */
    public boolean isCoordinateOccupied (Panel panel, Coordinate coordinate)
    {
        // Get the minimum and maximum coordinates of the panel
        Coordinate minimumCoordinate = getCoordinateFromPanelTile(panel, Constants_Panel.MIN_TILE_INDEX, Constants_Panel.MIN_TILE_INDEX);
        Coordinate maximumCoordinate = getCoordinateFromPanelTile(panel, panel.getMaxRows(), panel.getMaxColumns());
        
        // Checks whether indices out of bounds
        if (coordinate.getPositionX() < minimumCoordinate.getPositionX() ||
                coordinate.getPositionY() < minimumCoordinate.getPositionY() ||
                coordinate.getPositionX() > maximumCoordinate.getPositionX() ||
                coordinate.getPositionY() > maximumCoordinate.getPositionY())
        {
            return true;
        }

        // Check if the tile at the given coordinate is occupied by an obstacle
        int x = (int) coordinate.getPositionX();
        int y = (int) coordinate.getPositionY();
        int tileSize = panel.getTileSize();
        // Check all four corners of the tile
        // The player is running over an obstacle
        return isObstacle(panel, x, y) ||
                isObstacle(panel, x + tileSize - Constants_Panel.OBSTACLE_TILE_DEFAULT_VALUE, y) ||
                isObstacle(panel, x, y + tileSize - Constants_Panel.OBSTACLE_TILE_DEFAULT_VALUE) ||
                isObstacle(panel, x + tileSize - Constants_Panel.OBSTACLE_TILE_DEFAULT_VALUE, y + tileSize
                        - Constants_Panel.OBSTACLE_TILE_DEFAULT_VALUE);
        // Not occupied if method reached end
    }
    
    
    /**
     * Checks whether there is an obstacle in the way to block the movement in that direction.
     *
     * @param panel Panel on which the vertical movement has to be checked.
     * @param currentPlayerPosition The current position the player is at.
     * @param newPlayerPosition The new position the player wants to be at.
     * @return Boolean that answers to whether the vertical movement is blocked by an object.
     * @author Michael Markov, Jonas Helfer
     */
    public boolean isVerticalMoveBlocked (Panel panel, Coordinate currentPlayerPosition, Coordinate newPlayerPosition)
    {
        double yMovement = newPlayerPosition.getPositionY() - currentPlayerPosition.getPositionY();
        if (yMovement == Constants_DefaultValues.ZERO) return false; // Checks whether the current and the new position are equal
        
        double newY = newPlayerPosition.getPositionY();
        Coordinate newVerticalPosition = new Coordinate(currentPlayerPosition.getPositionX(), newY);
        return isCoordinateOccupied(panel, newVerticalPosition); // Checks whether the new position on the vertical side is occupied
    }
    
    
    /**
     * Checks whether there is an obstacle in the way to block the movement in that direction.
     *
     * @param panel Panel on which the vertical movement has to be checked.
     * @param currentPlayerPosition The current position the player is at.
     * @param newPlayerPosition The new position the player wants to be at.
     * @return Boolean that answers to whether the horizontal movement is blocked by an object.
     * @author Michael Markov, Jonas Helfer
     */
    public boolean isHorizontalMoveBlocked (Panel panel, Coordinate currentPlayerPosition, Coordinate newPlayerPosition)
    {
        double xMovement = newPlayerPosition.getPositionX() - currentPlayerPosition.getPositionX();
        if (xMovement == Constants_DefaultValues.ZERO) return false; // Checks whether the current and the new position are equal
        
        double newX = newPlayerPosition.getPositionX();
        Coordinate newHorizontalPosition = new Coordinate(newX, currentPlayerPosition.getPositionY());
        return isCoordinateOccupied(panel, newHorizontalPosition); // Checks whether the new position on the horizontal side is occupied
    }


    /**
     * Determines whether a specific position on the panel is occupied by an obstacle.
     * @author Jonas Helfer
     * @param panel The Panel object representing the game map.
     * @param x The x-coordinate to check for an obstacle.
     * @param y The y-coordinate to check for an obstacle.
     * @precondition The Panel object is properly initialized with valid dimensions and tiles.
     *               The getTileIndicesFromCoordinates method is properly implemented and accessible.
     *               The x and y coordinates are within the bounds of the panel.
     * @postcondition The method returns true if the specified position contains an obstacle,
     *                false otherwise.
     * @return boolean True if the position is occupied by an obstacle, false otherwise.
     */
    private boolean isObstacle(Panel panel, int x, int y) {
        // Get the tile indices corresponding to the given coordinates
        Coordinate tileIndices = getTileIndicesFromCoordinates(panel, new Coordinate(x, y));

        // Check if the tile at the calculated indices is occupied
        return panel.getTileAt((int) tileIndices.getPositionY(), (int) tileIndices.getPositionX()).getOccupied();
    }




    /**
     * Following method can be utilised to return pile indices from the stage location. The indices will be returned as
     * an instance of Coordinate.
     *
     * @param panel Panel from which the tile indices have to be determined.
     * @param coordinate Coordinate at which the tile lies.
     * @return Indices of the panel.
     * @author Michael Markov
     */
    public Coordinate getTileIndicesFromCoordinates (Panel panel, Coordinate coordinate)
    {
        Coordinate nullPosition = getNullPositionOfPanelInRelationToScreenSize(panel); // Gets the null position
        Coordinate currentCoordinate = new Coordinate(coordinate.getPositionX() - nullPosition.getPositionX(),
                coordinate.getPositionY() - nullPosition.getPositionY()); // Adds removes null position values from coordinates
        // Divide by tile size and cut off decimal points
        currentCoordinate.setPositionX((int) (currentCoordinate.getPositionX() / panel.getTileSize()));
        currentCoordinate.setPositionY((int) (currentCoordinate.getPositionY() / panel.getTileSize()));
        
        return currentCoordinate;
    }
    
    
    /**
     * Returns an instance of Coordinates with the coordinates of the Tile at the parameter indices.
     *
     * @param panel Panel from which the tile coordinates have to be determined.
     * @param rowIndex Index of the row from which the vertical coordinate can be determined.
     * @param columnIndex Index of the columns from which the horizontal coordinate can be determined.
     * @return Coordinates at which the Tile lies.
     * @author Michael Markov
     */
    public Coordinate getCoordinateFromPanelTile (Panel panel, int rowIndex, int columnIndex)
    {
        Coordinate nullPosition = getNullPositionOfPanelInRelationToScreenSize(panel); // Get null position
        // Add indices multiplied by the panel size to the null position
        double tileX = nullPosition.getPositionX() + (columnIndex * panel.getTileSize());
        double tileY = nullPosition.getPositionY() + (rowIndex * panel.getTileSize());
        
        return new Coordinate(tileX, tileY);
    }
    
    
    /**
     * Returns the coordinates of the upper left corner of the upper left tile which can also be caled the
     * "NullPosition". The postion is returned via an instance of Coordinate.
     *
     * @param panel Panel from which the null position has to be determined.
     * @return Coordinates of the null position.
     * @author Michael Markov
     */
    private Coordinate getNullPositionOfPanelInRelationToScreenSize (Panel panel)
    {
        // The null position is retrieved by dividing the screen size by two and subtracting the panel size multiplied by the tile size
        double x = ((Screen.getPrimary().getBounds().getWidth() / Constants_Panel.DIVIDE_BY_VALUE_TO_GET_HALF) -
                ((double) panel.getMaxColumns() * panel.getTileSize() /
                        Constants_Panel.DIVIDE_BY_VALUE_TO_GET_HALF));
        double y = ((Screen.getPrimary().getBounds().getHeight() / Constants_Panel.DIVIDE_BY_VALUE_TO_GET_HALF) -
                ((double) panel.getMaxRows() * panel.getTileSize() /
                        Constants_Panel.DIVIDE_BY_VALUE_TO_GET_HALF));
        
        return new Coordinate(x, y);
    }
    
    
    public static PanelController getInstance ()
    {
        if (instance == null)
        {
            throw new IllegalStateException(Constants_ExceptionMessages.SINGLETON_NOT_INITIALIZED);
        }
        return instance;
    }
}
