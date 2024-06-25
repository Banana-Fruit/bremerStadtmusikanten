package resources.constants;

public interface Constants_Savegame
{
    //Integer
    int INDENT_FACTOR = 4;
    int SAVE_GOLD = 100;
    int SAVE_COAL = 50;
    int SAVE_STONE = 200;

    //Strings
    String SAVE_PLAYER_NAME = "Player1";
    String SAVE_FILE = "savegame.json";
    String LOAD_GAME = "Spielstand geladen:";
    String OUTPUT_GOLD = "Gold: ";
    String OUTPUT_COAL = "Kohle: ";
    String OUTPUT_STONE = "Stein: ";
    String OUTPUT_NAME = "Spielername: ";
    String MESSAGE_SAVEGAME_SAVED = "Spielstand wurde erfolgreich gespeichert.";
    String SAVEGAME_DIRECTORY = "src/resources/Savegame";
    String JSON_OBJECT_INVENTORY_GOLD = "gold";
    String JSON_OBJECT_INVENTORY_COAL = "coal";
    String JSON_OBJECT_INVENTORY_BRICKS = "bricks";
    String JSON_OBJECT_PLAYER_NAME = "playername";



}
