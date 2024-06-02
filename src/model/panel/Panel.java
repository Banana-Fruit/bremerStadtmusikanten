package model.panel;


import resources.constants.Constants_DefaultValues;
import resources.constants.Constants_Panel;


/**
 * Panel consists of Tiles
 */
public class Panel
{
    private Tile[][] originArray;
    private Tile[][] tileArray;
    private int tileSize;
    private int maxRows;
    private int maxColumns;
    
    
    public Panel (Tile[][] tileArray, int tileSize, int maxRows, int maxColumns)
    {
        this.originArray = tileArray;
        this.tileSize = tileSize;
        this.maxRows = maxRows;
        this.tileArray = new Tile[maxRows][maxColumns];
        this.maxColumns = maxColumns;
        init();
    }
    
    
    private void init ()
    {
        setTileArrayToRestrictedOriginArray();
    }
    
    
    private void setTileArrayToRestrictedOriginArray ()
    {
        for (int row = Constants_Panel.ITERATION_STARTING_VALUE; row < maxRows; row++)
        {
            for (int column = Constants_Panel.ITERATION_STARTING_VALUE; column < maxColumns; column++)
            {
                if (row < originArray.length && column < originArray[row].length)
                {
                    tileArray[row][column] = originArray[row][column];
                } else
                {
                    tileArray[row][column] = null; // Handle out-of-bounds by setting to null or another default value
                }
            }
        }
    }
    
    
    public Tile getTileAt (int row, int column)
    {
        return tileArray[row][column];
    }
    
    
    public int getMaxRows ()
    {
        return tileArray.length - Constants_DefaultValues.LENGTH_TO_SIZE_SUBTRACTOR;
    }
    
    
    public int getMaxColumns ()
    {
        return tileArray[Constants_Panel.INDEX_WITH_MAX_VALUE].length - Constants_DefaultValues.LENGTH_TO_SIZE_SUBTRACTOR;
    }
    
    
    public Tile[][] getTileArray ()
    {
        return tileArray;
    }
    
    
    public int getTileSize ()
    {
        return tileSize;
    }
}
