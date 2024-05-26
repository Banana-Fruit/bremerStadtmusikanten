package control.scenes;

import model.map.GamePanel;
import model.showables.Map;
import resources.Constants_Map;
import utility.MyIO;
import view.TileRenderer;

import java.awt.*;


public class MapController implements Runnable
{
    private GamePanel gamePanel;
    private final TileLoader tilePathLoader;
    private final TileLoader tileBuildingLoader;
    public Map buildingTile;
    private char[][] tileCharArray = new char[Constants_Map.MAX_SCREEN_COLUMN][Constants_Map.MAX_SCREEN_ROW];


    // constructor
    public MapController(GamePanel gamePanel)
    {
        this.gamePanel = gamePanel;
        tileBuildingLoader = new TileLoader();
        tileBuildingLoader.setMapWithCharsAndImages(Constants_Map.PATH_TILE_BUILDING);
        tilePathLoader = new TileLoader();
        tilePathLoader.setMapWithCharsAndImages(Constants_Map.PATH_TILE);
        MyIO.loadMapFromPath(Constants_Map.PATH_MAP);
        MyIO.loadMapFromPath(Constants_Map.PATH_BUILDING_TILE);
    }


    public void drawMap(Graphics2D graphics2D)
    {
        TileRenderer renderer = new TileRenderer(gamePanel, tilePathLoader.getMapWithCharsAndImages());
        renderer.draw(graphics2D, mapTile.getTileCharArray());
    }


    public void drawBuilding(Graphics2D graphics2D)
    {
        TileRenderer renderer = new TileRenderer(gamePanel, tileBuildingLoader.getMapWithCharsAndImages());
        renderer.draw(graphics2D, buildingTile.getTileCharArray());
    }
    
    
    @Override
    public void run ()
    {
        try
        {
            Thread.sleep(Constants_Map.MILLISECONDS_OF_SLEEP / Constants_Map.GAMEPANEL_FRAMES_PER_SECOND);
        } catch (InterruptedException e)
        {
            throw new RuntimeException(e);
        }
        
        //update(); to move a player on the Map
        //repaint();
    }
    
    
    public void paintComponent (Graphics graphics)
    {
        super.paint(graphics);
        Graphics2D g2 = (Graphics2D) graphics;
        drawMap(g2);
        drawBuilding(g2);
        g2.setColor(Color.white);
        //g2.fillRect(playerX, playerY, tileSize, tileSize);
        g2.dispose();
    }
    

    /*public boolean isObstacle(int x, int y)
    {
        char tileChar = buildingTile.getTileCharAt(x / gamePanel.tileSize, y / gamePanel.tileSize);
        return Character.isLowerCase(tileChar);
    }*/


    /*public boolean isTileOccupied (int x, int y)
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
