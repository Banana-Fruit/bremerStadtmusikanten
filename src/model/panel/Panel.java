package model.panel;


/**
 * Panel consists of Tiles
 */
public class Panel
{
    private Tile[][] tileArray;
    
    public Panel (int rows, int columns)
    {
        tileArray = new Tile[rows][columns];
    }
    
    
    public Tile[][] getTileArray ()
    {
        return tileArray;
    }
    
    
    public Tile getTileAt (int row, int column)
    {
        return tileArray[row][column];
    }
}
