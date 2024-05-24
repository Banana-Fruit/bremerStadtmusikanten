package model;


import java.awt.*;


public class GamePanel extends JPanel implements Runnable
{
    // attributes
    final int originalTileSize = 16;
    final int scale = 3;
    final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 30;
    final int maxScreenRow = 20;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;
    int FPS = 60;
    TileManager tileM = new TileManager(this);
    Thread gameThread;
    KeyHandler keyH = new KeyHandler();
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;
    
    
    // constructor
    public GamePanel()
    {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
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
            update();
            repaint();
            
            try
            {
                Thread.sleep(1000 / FPS);
            } catch (InterruptedException e)
            {
                throw new RuntimeException(e);
            }
        }
    }
    
    
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
    
    
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        tileM.drawMap(g2);
        tileM.drawBuilding(g2);
        g2.setColor(Color.white);
        g2.fillRect(playerX, playerY, tileSize, tileSize);
        g2.dispose();
    }
}
