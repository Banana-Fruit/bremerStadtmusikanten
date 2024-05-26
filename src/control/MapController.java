package control;

import model.map.GamePanel;
import model.showables.Map;
import resources.Constants_Map;
import utility.TileLoader;
import utility.TileRenderer;

import java.awt.*;


public class MapController
{
    private GamePanel gamePanel;
    private final TileLoader tilePathLoader;
    private final TileLoader tileBuildingLoader;
    private final Map mapTile;
    public Map buildingTile;


    // constructor
    public MapController(GamePanel gamePanel)
    {
        this.gamePanel = gamePanel;
        tileBuildingLoader = new TileLoader();
        tileBuildingLoader.loadTileImages(Constants_Map.PATH_TILE_BUILDING);
        tilePathLoader = new TileLoader();
        tilePathLoader.loadTileImages(Constants_Map.PATH_TILE);
        mapTile = new Map();
        mapTile.loadMap(Constants_Map.PATH_MAP);
        buildingTile = new Map();
        buildingTile.loadMap(Constants_Map.PATH_BUILDING_TILE);
    }


    public void drawMap(Graphics2D graphics2D)
    {
        TileRenderer renderer = new TileRenderer(gamePanel, tilePathLoader.getTileImages());
        renderer.draw(graphics2D, mapTile.getTileCharArray());
    }


    public void drawBuilding(Graphics2D graphics2D)
    {
        TileRenderer renderer = new TileRenderer(gamePanel, tileBuildingLoader.getTileImages());
        renderer.draw(graphics2D, buildingTile.getTileCharArray());
    }


    /*public boolean isObstacle(int x, int y)
    {
        char tileChar = buildingTile.getTileCharAt(x / gamePanel.tileSize, y / gamePanel.tileSize);
        return Character.isLowerCase(tileChar);
    }*/


    /*public boolean canMoveTo(int x, int y)
    {
        // Überprüfen, ob der Spieler innerhalb des Spielfelds bleibt
        if (x < 0 || y < 0 || x + gamePanel.tileSize > gamePanel.screenWidth || y + gamePanel.tileSize > gamePanel.screenHeight)
            return false;

        // Überprüfen, ob der Spieler über ein Hindernis läuft
        if (isObstacle(x, y) ||                   // Obere linke Ecke
                isObstacle(x + gamePanel.tileSize - 1, y) || // Obere rechte Ecke
                isObstacle(x, y + gamePanel.tileSize - 1) || // Untere linke Ecke
                isObstacle(x + gamePanel.tileSize - 1, y + gamePanel.tileSize - 1)) { // Untere rechte Ecke
            return false;
        }
        return true;
    }*/
}
