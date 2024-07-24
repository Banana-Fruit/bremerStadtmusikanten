package resources.constants;


public interface Constants_Panel
{
    // Default values
    boolean DEFAULT_isOCCUPIED = false;
    int INDEX_WITH_MAX_VALUE = 0;
    int DIVIDE_BY_VALUE_TO_GET_HALF = 2;
    int MIN_TILE_INDEX = 0;
    int IMAGE_CHAR_POSITION = 0;
    int BIOME_IDENTIFIER_LINE = 0;
    int OBSTACLE_TILE_DEFAULT_VALUE = 1;
    
    // Ignore values
    char IGNORE_LOADER_FILE_VALUE = ' ';
    String EMPTY_STRING = "";
    int FILE_READER_STARTING_LINE = 0;

    String FILE_PANEL = "src/resources/assets/map/grasslands/";
    String INVALID_FILE_PATH = "The folder path is invalid or contains no files: ";
    String FILE_PATH_GRASS = "resources/assets/map/grasslands/019_grass.png";
    String FILE_PATH_TILE_DATA = "/resources/assets/map/loaderFiles/TileData/";

}
