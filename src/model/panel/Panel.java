package model.panel;


import resources.constants.Constants_DefaultValues;
import resources.constants.Constants_Panel;


/**
 * Panel consists of Tiles
 */
public class Panel
{
    private Tile[][] tileArray;
    
    
    public Panel (Tile[][] tileArray)
    {
        this.tileArray = tileArray;
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
}
