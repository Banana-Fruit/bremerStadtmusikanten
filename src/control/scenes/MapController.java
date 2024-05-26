package control.scenes;

import model.showables.Map;
import resources.Constants_Map;
import utility.MyIO;

import java.awt.*;


public class MapController implements Runnable
{
    private GamePanel gamePanel;
    private final TileLoader tilePathLoader;
    private final TileLoader tileBuildingLoader;
    public Map buildingTile;
    private char[][] tileCharArray = new char[Constants_Map.MAX_SCREEN_COLUMN][Constants_Map.MAX_SCREEN_ROW];


    public MapController ()
    {
    
    }
    
    
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
}
