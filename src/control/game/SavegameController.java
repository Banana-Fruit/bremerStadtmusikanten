package control.game;


import model.Savegame;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.json.JSONException;
import resources.constants.Constants_Savegame;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class SavegameController
{
    // Singleton instance of SavegameController
    private static SavegameController instance = null;

    // Directory path for saving game files
    private final String saveGameDirectory = Constants_Savegame.SAVEGAME_DIRECTORY;


    /**
     * Private constructor for SavegameController.
     * @author Jonas Helfer
     * @precondition None
     * @postcondition A new SavegameController instance is created
     */
    private SavegameController ()
    {
    }


    /**
     * Saves the game state to a file in JSON format.
     * @author Jonas Helfer
     * @param savegame The Savegame object containing the game state
     * @param fileName The name of the file to save the game state
     * @precondition savegame is not null and fileName is a valid file name
     * @postcondition The game state is saved to a file in the specified directory
     */
    public void saveToFile (Savegame savegame, String fileName)
    {
        JSONObject json = new JSONObject();
        json.put(Constants_Savegame.JSON_OBJECT_INVENTORY_GOLD, savegame.getGold());
        json.put(Constants_Savegame.JSON_OBJECT_INVENTORY_COAL, savegame.getCoal());
        json.put(Constants_Savegame.JSON_OBJECT_INVENTORY_BRICKS, savegame.getStone());
        json.put(Constants_Savegame.JSON_OBJECT_PLAYER_NAME, savegame.getPlayerName());
        
        try (FileWriter file = new FileWriter(saveGameDirectory + fileName))
        {
            file.write(json.toString(Constants_Savegame.INDENT_FACTOR));
            file.flush();
            System.out.println(Constants_Savegame.MESSAGE_SAVEGAME_SAVED);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }


    /**
     * Loads a game state from a file.
     * @author Jonas Helfer
     * @param fileName The name of the file to load the game state from
     * @return A Savegame object containing the loaded game state, or null if loading fails
     * @precondition fileName is a valid file name and the file exists
     * @postcondition The game state is loaded from the file and returned as a Savegame object
     */
    public Savegame loadFromFile (String fileName)
    {
        try (FileReader reader = new FileReader(saveGameDirectory + fileName))
        {
            JSONTokener tokener = new JSONTokener(reader);
            JSONObject json = new JSONObject(tokener);
            
            int gold = json.getInt(Constants_Savegame.JSON_OBJECT_INVENTORY_GOLD);
            int coal = json.getInt(Constants_Savegame.JSON_OBJECT_INVENTORY_COAL);
            int stone = json.getInt(Constants_Savegame.JSON_OBJECT_INVENTORY_BRICKS);
            String playerName = json.getString(Constants_Savegame.JSON_OBJECT_PLAYER_NAME);
            
            return new Savegame(gold, coal, stone, playerName);
        } catch (IOException | JSONException e)
        {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * Returns the singleton instance of SavegameController.
     * @author Jonas Helfer
     * @return The singleton instance of SavegameController
     * @precondition None
     * @postcondition The singleton instance of SavegameController is returned
     */
    public static SavegameController getInstance ()
    {
        if (instance == null)
        {
            instance = new SavegameController();
        }
        return instance;
    }
}