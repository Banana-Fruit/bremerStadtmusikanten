package resources.constants.scenes;


import utility.ValueConversion;


public interface Constants_Map
{
    // File Management
    String PLAYER_VIEW_STANDARD = "resources/assets/map/player/esel.png";
    
    
    int TILE_SIZE = 40;
    int MAX_ROWS = 20;
    int MAX_COLUMNS = 30;
    
    // Player
    int STARTPOSITION_X = 2;
    int STARTPOSITION_Y = 2;
    float PLAYER_SPEED = 1;
    double PLAYER_SIZE = ValueConversion.getDiagonalSizeFromSquareLength(40);
    float SPEED_MULTIPLIER = 5f;
    double ADJUST_DIAGONAL_MOVEMENT = Math.sqrt(2) / 2;
    int DEFAULT_SPEED = 1;
}