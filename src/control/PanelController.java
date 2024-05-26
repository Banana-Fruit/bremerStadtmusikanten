package control;


import model.panel.Panel;
import model.userInterface.Game;
import resources.Constants_Map;

import java.awt.*;


public class PanelController
{
    private Game game;
    private Panel panel;
    private String resourceFolderPath;
    
    public PanelController (Game game, String resourceFolderPath, Panel panel)
    {
        this.panel = panel;
        this.resourceFolderPath = resourceFolderPath;
        init();
    }
    
    
    private void init()
    {
        // TODO: Do something with resource folder path
    }
    
    
    /**
     * Gets Image from char and map.
     */ // TODO: FIX this.
    /*public void draw(Graphics2D graphics2D, char[][] tileChars)
    {
        for (int row = Constants_Map.MINIMUM_ARRAY_VALUE; row < gamePanel.getMaxScreenRow(); row++)
        {
            for (int column = Constants_Map.MINIMUM_ARRAY_VALUE; column < gamePanel.getMaxScreenColumn(); column++)
            {
                char tileChar = tileChars[column][row];
                Image tileImage = tileImages.get(tileChar);
                if (tileImage != null)
                {
                    graphics2D.drawImage(tileImage, column * gamePanel.getTileSize(),
                            row * gamePanel.getTileSize(), gamePanel.getTileSize(), gamePanel.getTileSize(), null);
                }
            }
        }
    }*/
    
    
    // TODO: FIX methods.
    /*public boolean isObstacle(int x, int y)
    {
        char tileChar = buildingTile.getTileCharAt(x / gamePanel.tileSize, y / gamePanel.tileSize);
        return Character.isLowerCase(tileChar);
    }
    
    
    public boolean isTileOccupied (int x, int y)
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


/* ------------------------------------------ GAME PANEL CLASS FOR REFERENCE ------------------------------------------
package model.map;


import control.scenes.MapController;
import model.panel.Panel;
import resources.Constants_Map;

import java.awt.*;


public class GamePanel extends Panel
{
    private final int originalTileSize = Constants_Map.TILE_SIZE;
    private final int scale = Constants_Map.GAMEPANEL_SCALE;
    private final int tileSize = originalTileSize * scale;
    private final int maxScreenColumn = Constants_Map.MAX_SCREEN_COLUMN;
    private final int maxScreenRow = Constants_Map.MAX_SCREEN_ROW;
    private final int screenWidth = tileSize * maxScreenColumn;
    private final int screenHeight = tileSize * maxScreenRow;
    
    private MapController tileM = new MapController(this);
    private Thread gameThread;
    
    
    // constructor
    public GamePanel ()
    {
        super();
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        //this.setDoubleBuffered(true);
        this.setFocusable(true);
    }
    
    
    // getter methods
    public int getMaxScreenColumn ()
    {
        return maxScreenColumn;
    }
    
    
    public int getMaxScreenRow ()
    {
        return maxScreenRow;
    }
    
    
    public int getTileSize ()
    {
        return tileSize;
    }
}
*/
