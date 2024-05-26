package model.map;

import control.MapController;
import resources.Constants_Map;

import javax.swing.*;
import java.awt.*;


public class GamePanel extends JPanel implements Runnable
{
    // attributes
    private final int originalTileSize = Constants_Map.TILE_SIZE;
    private final int scale = Constants_Map.GAMEPANEL_SCALE;
    private final int tileSize = originalTileSize * scale;
    private final int maxScreenColumn = Constants_Map.MAX_SCREEN_COLUMN;
    private final int maxScreenRow = Constants_Map.MAX_SCREEN_ROW;
    private final int screenWidth = tileSize * maxScreenColumn;
    private final int screenHeight = tileSize * maxScreenRow;

    private int FPS = Constants_Map.GAMEPANEL_FRAMES_P_SECOND;
    private MapController tileM = new MapController(this);
    private Thread gameThread;



    // constructor
    public GamePanel()
    {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
    }


    public void startGameThread()
    {
        gameThread = new Thread(this);
        gameThread.start();
    }


    @Override
    public void run()
    {
        while (gameThread != null)
        {
            //update(); to move a player on the Map
            repaint();

            try
            {
                Thread.sleep(Constants_Map.MILLISECONDS_OF_SLEEP / FPS);
            } catch (InterruptedException e)
            {
                throw new RuntimeException(e);
            }
        }
    }

    //TODO: Player auf Map einbinden

    /*
    public void update()
    {
        int newPlayerX = playerX;
        int newPlayerY = playerY;

        if (keyH.downPressed)
        {
            newPlayerY += playerSpeed;
        } else if (keyH.rightPressed)
        {
            newPlayerX += playerSpeed;
        } else if (keyH.upPressed)
        {
            newPlayerY -= playerSpeed;
        } else if (keyH.leftPressed)
        {
            newPlayerX -= playerSpeed;
        }

        if (tileM.canMoveTo(newPlayerX, newPlayerY))
        {
            playerX = newPlayerX;
            playerY = newPlayerY;
        }
    }

     */


    public void paintComponent(Graphics graphics)
    {
        super.paintComponent(graphics);
        Graphics2D g2 = (Graphics2D) graphics;
        tileM.drawMap(g2);
        tileM.drawBuilding(g2);
        g2.setColor(Color.white);
        //g2.fillRect(playerX, playerY, tileSize, tileSize);
        g2.dispose();
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
