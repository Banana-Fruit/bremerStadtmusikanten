package model.panel;


import resources.constants.Constants_DefaultValues;
import resources.constants.Constants_Panel;


/**
 * Panel consists of Tiles. It has local variables like tile size maximum rows and columns.
 *
 * @author Michael Markov
 */
public class Panel
{
    private final Tile[][] originArray;
    private final Tile[][] tileArray;
    private final int tileSize;
    private final int maxRows;
    private final int maxColumns;
    
    
    public Panel (Tile[][] tileArray, int tileSize, int maxRows, int maxColumns)
    {
        this.originArray = tileArray;
        this.tileSize = tileSize;
        this.tileArray = new Tile[maxRows][maxColumns];
        this.maxRows = maxRows;
        this.maxColumns = maxColumns;
        init();
    }
    
    
    /**
     * Initializer for the panel.
     *
     * @author Michael Markov
     */
    private void init ()
    {
        setTileArrayToRestrictedOriginArray();
    }
    
    
    /**
     * Restricts array of tiles to a specific amount of rows and columns.
     *
     * @author Michael Markov
     */
    private void setTileArrayToRestrictedOriginArray ()
    {
        for (int row = Constants_Panel.MIN_TILE_INDEX; row < maxRows; row++) // Runs through all rows till maxRows
        {
            for (int column = Constants_Panel.MIN_TILE_INDEX; column < maxColumns; column++) // Runs through all columns till maxColumns
            {
                if (row < originArray.length && column < originArray[row].length)
                {
                    tileArray[row][column] = originArray[row][column]; // Adds tile to array
                } else
                {
                    tileArray[row][column] = null; // Handle out-of-bounds by setting to null or another default value
                }
            }
        }
    }
    
    
    /**
     * Returns Tile at a specific index.
     *
     * @param row Row that the tile is located at.
     * @param column Column that the tile is located at.
     * @return Tile, specific to the row and column.
     * @author Michael Markov
     */
    public Tile getTileAt (int row, int column)
    {
        return tileArray[row][column];
    }
    
    
    public int getMaxArrayRows ()
    {
        return maxRows - Constants_DefaultValues.LENGTH_TO_SIZE_SUBTRACTOR;
    }
    
    
    public int getMaxArrayColumns ()
    {
        return maxColumns - Constants_DefaultValues.LENGTH_TO_SIZE_SUBTRACTOR;
    }
    
    
    public int getMaxColumns ()
    {
        return maxColumns;
    }
    
    
    public int getMaxRows ()
    {
        return maxRows;
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
