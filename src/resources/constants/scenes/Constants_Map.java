package resources.constants.scenes;


import utility.ValueConversion;


public interface Constants_Map
{
    // File Management
    String PLAYER_VIEW_STANDARD = "resources/assets/map/player/esel.png";
    
    
    int TILE_SIZE = 16;
    int MAX_ROWS = 50;
    int MAX_COLUMNS = 50;
    
    // Player
    int STARTPOSITION_X = 10;
    int STARTPOSITION_Y = 25;
    float PLAYER_SPEED = 1;
    float SPEED_MULTIPLIER = 6f;
    double PLAYER_SIZE = ValueConversion.getDiagonalSizeFromSquareLength(16);
    double ADJUST_DIAGONAL_MOVEMENT = Math.sqrt(2) / 2;

    // Map
    String MAP_NAME_MISSION_1 = "Mission_1";
    String MAP_NAME_CITY = "City";
    String CONSOLE_PRINT_SWITCHING_MAP = "Switching to map: ";

    //Positioncheck

    double PLAYER_AT_LEFT_BORDER = 0.0;
    int PLAYER_SPAWN_MISSION_1_POSITION_X = 24;
    int PLAYER_SPAWN_MISSION_1_POSITION_Y = 49;
    int PLAYER_SPAWN_CITY_POSITION_X = 25;
    int PLAYER_SPAWN_CITY_POSITION_Y = 25;
    int PLAYER_FINISH_MISSION_1_POSITION_X = 45;
    int PLAYER_FINISH_MISSION_1_POSITION_Y = 7;





}