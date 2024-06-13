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
}