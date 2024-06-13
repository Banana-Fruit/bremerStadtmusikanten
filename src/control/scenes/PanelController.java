package control.scenes;


import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import model.Coordinate;
import model.panel.Panel;
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
     * Adds a panel to the pane, based on the given parameters, and returns it.
     *
     * @param pane
     * @param pathToLoaderFileFolder
     * @param loaderFileName
     * @param tileSize
     * @param maxRows
     * @param maxColumns
     * @return
     */
    public Panel getAndShowPanel (Pane pane, String pathToLoaderFileFolder, String loaderFileName, int tileSize, int maxRows, int maxColumns)
    {
        try
        {
            Panel panel = initializePanel_JonasMap(pathToLoaderFileFolder, loaderFileName, tileSize, maxRows, maxColumns);
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
     * Returns a panel based on the given parameters.
     *
     * @param pathToLoaderFileFolder
     * @param loaderFileName
     * @param tileSize
     * @param maxRows
     * @param maxColumns
     * @return
     * @author Michael Markov
     */
    private Panel initializePanel (String pathToLoaderFileFolder, String loaderFileName, int tileSize, int maxRows, int maxColumns)
    {
        // Create array of characters
        char[][] charArray = PanelAndTileLoader.getCharacterArrayUsingTileFile(pathToLoaderFileFolder + loaderFileName, maxRows, maxColumns);
        
        // Create path to resource folder with biome
        String pathToResourceFolder = pathToLoaderFileFolder.replace(Constants_Resources.LOADER_FILES_FOLDER, Constants_Panel.EMPTY_STRING) + getBiomeName(pathToLoaderFileFolder + loaderFileName);
        
        // Put characters in correlation to images
        HashMap<Character, Image> mapOfCharactersWithCorrelatingImages = PanelAndTileLoader.getMapWithCharsAndImages(pathToResourceFolder);
        
        return new Panel(
                PanelAndTileLoader.getTileArray(mapOfCharactersWithCorrelatingImages, charArray, maxRows, maxColumns),
                tileSize, maxRows, maxColumns
        );
    }

    private Panel initializePanel_JonasMap (String pathToLoaderFileFolder, String loaderFileName, int tileSize, int maxRows, int maxColumns)
    {
        // Create array of characters
        int[][] intArray = PanelAndTileLoader.getCharacterArrayUsingTileFile_JonasMap(pathToLoaderFileFolder + loaderFileName, maxRows, maxColumns);
        // Create path to resource folder with biome
        String pathToResourceFolder = "src/resources/assets/jonas";
        // Put characters in correlation to images
        HashMap<Integer, Image> mapOfCharactersWithCorrelatingImages = PanelAndTileLoader.getMapWithIntegersAndImages_JonasMap(pathToResourceFolder);
        return new Panel(
                PanelAndTileLoader.getTileArray_JonasMap(mapOfCharactersWithCorrelatingImages, intArray, maxRows, maxColumns),
                tileSize, maxRows, maxColumns
        );
    }



    private String getBiomeName (String pathToLoaderFile)
    {
        String biomeName = new String();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(pathToLoaderFile)))
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
        return biomeName;
    }
    
    
    /**
     * Checks whether a specific tile in the panel is occupied.
     *
     * @param panel
     * @param tileRow
     * @param tileColumn
     * @return
     * @author Michael Markov
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
     * @author Michael Markov
     */
    public boolean isCoordinateOccupied (Panel panel, Coordinate coordinate)
    {
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
        
        // Check whether tile with correlating coordinates is occupied

        // Überprüfe die obere linke Ecke
        int x = (int) coordinate.getPositionX();
        int y = (int) coordinate.getPositionY();
        int tileSize = panel.getTileSize();
        if (isObstacle(panel, x, y) ||
                // Überprüfe die obere rechte Ecke
                isObstacle(panel, x + tileSize - Constants_Panel.OBSTACLE_TILE_DEFAULT_VALUE, y) ||
                // Überprüfe die untere linke Ecke
                isObstacle(panel, x, y + tileSize - Constants_Panel.OBSTACLE_TILE_DEFAULT_VALUE) ||
                // Überprüfe die untere rechte Ecke
                isObstacle(panel, x + tileSize - Constants_Panel.OBSTACLE_TILE_DEFAULT_VALUE, y + tileSize - Constants_Panel.OBSTACLE_TILE_DEFAULT_VALUE))
        {
            return true; // Der Spieler läuft über ein Hindernis
        }
        return false; // Not occupied if method reached end
    }

    private boolean isObstacle(Panel panel, int x, int y) {
        // Erhalte die Indizes der Kachel, die sich an den angegebenen Koordinaten befindet
        Coordinate tileIndices = getTileIndicesFromCoordinates(panel, new Coordinate(x, y));

        // Überprüfe, ob die Kachel besetzt ist
        return panel.getTileAt((int) tileIndices.getPositionY(), (int) tileIndices.getPositionX()).getOccupied();
    }
    
    
    private Coordinate getTileIndicesFromCoordinates (Panel panel, Coordinate coordinate)
    {
        Coordinate nullPosition = getNullPositionOfPanelInRelationToScreenSize(panel);
        Coordinate currentCoordinate = new Coordinate(coordinate.getPositionX() - nullPosition.getPositionX(),
                coordinate.getPositionY() - nullPosition.getPositionY());
        currentCoordinate.setPositionX((int)(currentCoordinate.getPositionX() / panel.getTileSize()));
        currentCoordinate.setPositionY((int)(currentCoordinate.getPositionY() / panel.getTileSize()));
        System.out.println(currentCoordinate.getPositionX() + " " + currentCoordinate.getPositionY());
        return currentCoordinate;
    }
    
    
    /**
     * Returns an instance of Coordinates with the coordinates of the Tile at the parameter indices.
     *
     * @param rowIndex
     * @param columnIndex
     * @return
     * @author Michael Markov
     */
    public Coordinate getCoordinateFromPanelTile (Panel panel, int rowIndex, int columnIndex)
    {
        Coordinate nullPosition = getNullPositionOfPanelInRelationToScreenSize(panel);
        double tileX = nullPosition.getPositionX() + (columnIndex * panel.getTileSize());
        double tileY = nullPosition.getPositionY() + (rowIndex * panel.getTileSize());
        return new Coordinate(tileX, tileY);
    }
    
    
    private Coordinate getNullPositionOfPanelInRelationToScreenSize (Panel panel)
    {
        double x = ((Screen.getPrimary().getBounds().getWidth() / Constants_Panel.DIVIDE_BY_VALUE_TO_GET_HALF) -
                ((double) panel.getMaxColumns() * panel.getTileSize() /
                        Constants_Panel.DIVIDE_BY_VALUE_TO_GET_HALF));
        double y = ((Screen.getPrimary().getBounds().getHeight() / Constants_Panel.DIVIDE_BY_VALUE_TO_GET_HALF) -
                ((double) panel.getMaxRows() * panel.getTileSize() /
                        Constants_Panel.DIVIDE_BY_VALUE_TO_GET_HALF));
        //System.out.println(x + " " + y);
        return new Coordinate(x, y);
    }
}
