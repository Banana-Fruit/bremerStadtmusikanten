package resources.constants;


public interface Constants_Resources
{
    // Locations
    String MAP_LOADER_FILES_FOLDER = "src/resources/assets/map/background/loaderFiles/";
    String MAP_LOADER_FILES_FOLDER_JONAS_MAP = "src/resources/assets/map/loaderFiles/";
    String СOMBAT_LOADER_FILES_FOLDER = "src/resources/assets/combat/loaderFiles/";
    String COMBAT_BACKGROUND_PATH = "resources/assets/combat/grass.png";
    
    // Folder names
    String LOADER_FILES_FOLDER = "loaderFiles/";
    
    // Suffices
    String PNG_SUFFIX = ".png";
    String LOADER_FILE_SUFFIX = ".dat";
    
    String[][] MAP_LOADER_ARRAY = new String[][]
            {
            {"", "1", ""},
            {"Mission_1", "City", "Mission_2"},
            {"", "4", ""}
    };
    String COMBAT_NAME = "main.dat";
}
