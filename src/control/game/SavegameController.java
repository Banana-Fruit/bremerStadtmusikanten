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
    private static SavegameController instance = null;
    private final String savegameDirectory = Constants_Savegame.SAVEGAME_DIRECTORY;

    private SavegameController() {}

    public static SavegameController getInstance()
    {
        if (instance == null)
        {
            instance = new SavegameController();
        }
        return instance;
    }

    public void saveToFile(Savegame savegame, String fileName)
    {
        JSONObject json = new JSONObject();
        json.put(Constants_Savegame.JSON_OBJECT_INVENTORY_GOLD, savegame.getGold());
        json.put(Constants_Savegame.JSON_OBJECT_INVENTORY_COAL, savegame.getCoal());
        json.put(Constants_Savegame.JSON_OBJECT_INVENTORY_BRICKS, savegame.getStone());
        json.put(Constants_Savegame.JSON_OBJECT_PLAYER_NAME, savegame.getPlayerName());

        try (FileWriter file = new FileWriter(savegameDirectory + fileName))
        {
            file.write(json.toString(Constants_Savegame.INDENT_FACTOR));
            file.flush();
            System.out.println(Constants_Savegame.MESSAGE_SAVEGAME_SAVED);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public Savegame loadFromFile(String fileName)
    {
        try (FileReader reader = new FileReader(savegameDirectory + fileName))
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

    //Todo: main löschen, war nur zum Ausprobieren da
    public static void main(String[] args)
    {
        SavegameController savegameController = SavegameController.getInstance();

        Savegame savegame = new Savegame(100, 50, 200, "Player1");
        savegameController.saveToFile(savegame, "savegame.json");

        Savegame loadedSavegame = savegameController.loadFromFile("savegame.json");
        if (loadedSavegame != null)
        {
            System.out.println("Spielstand geladen:");
            System.out.println("Gold: " + loadedSavegame.getGold());
            System.out.println("Kohle: " + loadedSavegame.getCoal());
            System.out.println("Stein: " + loadedSavegame.getStone());
            System.out.println("Spielername: " + loadedSavegame.getPlayerName());
        }
    }
}