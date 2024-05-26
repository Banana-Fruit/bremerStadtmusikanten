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
