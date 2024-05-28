package resources.constants;


public interface Constants_Panel
{
    // Default values
    final boolean DEFAULT_isOccupied = false;
    final int MAX_COLUMNS_ARRAY_INDEX = 0;
    final int HALFING = 2;
    final int MIN_TILE_INDEX = 0;
    
    
    // Paths
    final String PNG_SUFFIX = ".png";
    final String PATH_TILE_BUILDING = "/src/resources/assets/tileMap";
    final String LOADER_FILE_NAME = "tileInfo.dat";
    
    
    // Restriction values
    final int MINIMUM_ARRAY_VALUE = 0;
    final int IMAGE_CHAR_POSITION = 0;
    // Map related
    final int TILE_SIZE_MAP = 16;
    final int TILE_ROWS_MAP = 20;
    final int TILE_COLUMNS_MAP = 30;
    // Fight related
    final int TILE_SIZE_FIGHT = 16;
    final int TILE_ROWS_FIGHT = 20;
    final int TILE_COLUMNS_FIGHT = 30;
    
    
    final int GAMEPANEL_FRAMES_PER_SECOND = 60;
    final int MILLISECONDS_OF_SLEEP = 1000;
}
