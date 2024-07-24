package resources.constants.scenes;


import utility.ValueConversion;



public interface Constants_Map
{
    // File Management
    String PLAYER_VIEW_STANDARD = "resources/assets/map/player/esel.png";
    String UNIT_VIEW_STANDARD = "resources/assets/map/unit.png";
    
    
    int TILE_SIZE = 19;
    int MAX_ROWS = 50;
    int MAX_COLUMNS = 50;
    int UNIT_SIZE = 21;
    
    // Player
    int STARTPOSITION_X = 10;
    int STARTPOSITION_Y = 25;
    float PLAYER_SPEED = 1;
    float SPEED_MULTIPLIER = 6f;
    double PLAYER_SIZE = ValueConversion.getDiagonalSizeFromSquareLength(TILE_SIZE);
    double ADJUST_DIAGONAL_MOVEMENT = Math.sqrt(2) / 2;

    // Map
    String MAP_NAME_MISSION_1 = "Mission_1";
    String MAP_NAME_CITY = "City";
    String MAP_NAME_COMBAT = "Kampf";
    String CONSOLE_PRINT_SWITCHING_MAP = "Switching to map: ";
    String HEADER_JOIN_FIGHT = "Kampf betreten?";

    //Rewards
    String WOOD = "wood";
    String BEER = "beer";
    String BRICK = "brick";
    String ESSENCE = "essence";
    String GOLD = "gold";



    //Positioncheck

    double PLAYER_AT_LEFT_BORDER = 0.0;
    int PLAYER_SPAWN_MISSION_1_POSITION_X = 24;
    int PLAYER_SPAWN_MISSION_1_POSITION_Y = 49;
    int PLAYER_SPAWN_CITY_POSITION_X = 25;
    int PLAYER_SPAWN_CITY_POSITION_Y = 25;
    int PLAYER_FINISH_MISSION_1_POSITION_X = 45;
    int PLAYER_FINISH_MISSION_1_POSITION_Y = 7;


    double DISTANCE_THRESHOLD = 22.0;
    double PROXIMITY = 1.0;
    double DISTANCE_TO_UNIT_POW = 2.0;



    String REWARD_IMAGE_PATH = "resources/assets/map/grasslands/184_bag_path.png";

    double REWARD_COLLECTION_RADIUS = 16;
    int REWARD_GOLD_AMOUNT = 5;
    int REWARD_BEER_AMOUNT = 5;
    int REWARD_BRICK_AMOUNT = 5;
    int REWARD_ESSENCE_AMOUNT = 5;
    int REWARD_WOOD_AMOUNT = 5;
    int REWARD_1_POSITION_X = 40;
    int REWARD_1_POSITION_Y = 35;
    int REWARD_2_POSITION_X = 42;
    int REWARD_2_POSITION_Y = 8;
    int REWARD_3_POSITION_X = 30;
    int REWARD_3_POSITION_Y = 2;
    int REWARD_4_POSITION_X = 8;
    int REWARD_4_POSITION_Y = 5;
    int REWARD_5_POSITION_X = 2;
    int REWARD_5_POSITION_Y = 24;
    String ARROW_LEFT = "resources/assets/map/grasslands/186_arrow_left.png";
    String UNKNOWN_REWARD = "Unknown reward type: ";
}