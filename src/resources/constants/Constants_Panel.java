package resources.constants;


public interface Constants_Panel
{
    // Default values
    boolean DEFAULT_ISOCCUPIED = false;
    boolean DEFAULT_ISINTERACTABLE = false;
    int INDEX_WITH_MAX_VALUE = 0;
    int HALFING = 2;
    int MIN_TILE_INDEX = 0;
    
    // Suffices
    String BACKGROUND_SUFFIX = "_b";
    String OBSTACLE_SUFFIX = "_o";
    String ENEMY_SUFFIX = "_e";
    String COLLECTABLE_SUFFIX = "_c";
    
    
    // Restriction values
    int MINIMUM_ARRAY_VALUE = 0;
    int IMAGE_CHAR_POSITION = 0;
    // Map related
    int TILE_SIZE_MAP = 16;
    int TILE_ROWS_MAP = 20;
    int TILE_COLUMNS_MAP = 30;
    // Fight related
    int TILE_SIZE_FIGHT = 16;
    int TILE_ROWS_FIGHT = 20;
    int TILE_COLUMNS_FIGHT = 30;
    
    
    int GAMEPANEL_FRAMES_PER_SECOND = 60;
    int MILLISECONDS_OF_SLEEP = 1000;
    int ITERATION_STARTING_VALUE = 0;
}
