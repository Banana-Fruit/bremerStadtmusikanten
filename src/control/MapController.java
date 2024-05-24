package control;


import java.awt.*;
import java.util.HashMap;


public class MapController
{
    private GamePanel gp;
    private final TileLoader tilePathLoader;

    private final TileLoader tileBuildingLoader;
    private final TileMap mapTile;
    public TileMap buildingTile;

    public TileManager(GamePanel gp)
    {
        this.gp = gp;
        tileBuildingLoader = new TileLoader();
        tileBuildingLoader.loadTileImages("/Assets/building/gre/");
        tilePathLoader = new TileLoader();
        tilePathLoader.loadTileImages("/Assets/path/");
        mapTile = new TileMap(gp.maxScreenCol, gp.maxScreenRow);
        mapTile.loadMap("/Map.txt");
        buildingTile = new TileMap(gp.maxScreenCol, gp.maxScreenRow);
        buildingTile.loadMap("/Building.txt");
    }

    public void drawMap(Graphics2D g2)
    {
        TileRenderer renderer = new TileRenderer(gp, tilePathLoader.getTileImages());
        renderer.draw(g2, mapTile.getTileChars());
    }


    public void drawBuilding(Graphics2D g2)
    {
        TileRenderer renderer = new TileRenderer(gp, tileBuildingLoader.getTileImages());
        renderer.draw(g2, buildingTile.getTileChars());
    }


    public boolean isObstacle(int x, int y)
    {
        char tileChar = buildingTile.getTileCharAt(x / gp.tileSize, y / gp.tileSize);
        return Character.isLowerCase(tileChar);
    }


    public boolean canMoveTo(int x, int y)
    {
        // Überprüfen, ob der Spieler innerhalb des Spielfelds bleibt
        if (x < 0 || y < 0 || x + gp.tileSize > gp.screenWidth || y + gp.tileSize > gp.screenHeight)
            return false;

        // Überprüfen, ob der Spieler über ein Hindernis läuft
        if (isObstacle(x, y) ||                   // Obere linke Ecke
                isObstacle(x + gp.tileSize - 1, y) || // Obere rechte Ecke
                isObstacle(x, y + gp.tileSize - 1) || // Untere linke Ecke
                isObstacle(x + gp.tileSize - 1, y + gp.tileSize - 1)) { // Untere rechte Ecke
            return false;
        }
        return true;
    }


    //________________________Tile Renderer ------------------------------------------------


    private GamePanel gp;
    private HashMap<Character, Image> tileImages;



    public TileRenderer(GamePanel gp, HashMap<Character, Image> tileImages)
    {
        this.gp = gp;
        this.tileImages = tileImages;
    }

    public void draw(Graphics2D g2, char[][] tileChars)
    {
        for (int row = 0; row < gp.maxScreenRow; row++)
        {
            for (int col = 0; col < gp.maxScreenCol; col++)
            {
                char tileChar = tileChars[col][row];
                Image tileImage = tileImages.get(tileChar);
                if (tileImage != null)
                {
                    g2.drawImage(tileImage, col * gp.tileSize, row * gp.tileSize, gp.tileSize, gp.tileSize, null);
                }
            }
        }
    }
}
