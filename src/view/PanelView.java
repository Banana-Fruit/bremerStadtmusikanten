package view;


import model.showables.Map;
import resources.constants.scenes.Constants_Map;

import java.awt.*;


public class PanelView
{
    public void showTiles (Graphics graphics)
    {
        super.paint(graphics);
        Graphics2D g2 = (Graphics2D) graphics;
        drawMap(g2);
        drawBuilding(g2);
        g2.setColor(Color.white);
        //g2.fillRect(playerX, playerY, tileSize, tileSize);
        g2.dispose();
    }
    
    
    public void drawMap (Graphics2D graphics2D)
    {
        TileRenderer renderer = new TileRenderer(gamePanel, tilePathLoader.getMapWithCharsAndImages());
        renderer.draw(graphics2D, mapTile.getTileCharArray());
    }
    
    
    public void drawBuilding (Graphics2D graphics2D)
    {
        TileRenderer renderer = new TileRenderer(gamePanel, tileBuildingLoader.getMapWithCharsAndImages());
        renderer.draw(graphics2D, buildingTile.getTileCharArray());
    }
}
