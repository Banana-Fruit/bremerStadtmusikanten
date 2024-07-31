package model;


public class Savegame
{
    //The amount of gold the player has in the savegame.
    private int gold;

    //The amount of coal the player has in the savegame.
    private int coal;


    //The amount of stone the player has in the savegame.
    private int stone;

    //The name of the player in the savegame.
    private String playerName;


    /**
     * Constructs a new Savegame with the specified resources and player name.
     * @author Jonas Helfer
     * @param gold The amount of gold.
     * @param coal The amount of coal.
     * @param stone The amount of stone.
     * @param playerName The name of the player.
     */
    public Savegame (int gold, int coal, int stone, String playerName)
    {
        this.gold = gold;
        this.coal = coal;
        this.stone = stone;
        this.playerName = playerName;
    }


    // Getter und Setter

    /**
     * Gets the amount of gold in the savegame.
     * @author Jonas Helfer
     * @return The amount of gold.
     */
    public int getGold ()
    {
        return gold;
    }


    /**
     * Sets the amount of gold in the savegame.
     * @author Jonas Helfer
     * @param gold The new amount of gold.
     */
    public void setGold (int gold)
    {
        this.gold = gold;
    }


    /**
     * Gets the amount of coal in the savegame.
     * @author Jonas Helfer
     * @return The amount of coal.
     */
    public int getCoal ()
    {
        return coal;
    }


    /**
     * Sets the amount of coal in the savegame.
     * @author Jonas Helfer
     * @param coal The new amount of coal.
     */
    public void setCoal (int coal)
    {
        this.coal = coal;
    }


    /**
     * Gets the amount of stone in the savegame.
     * @author Jonas Helfer
     * @return The amount of stone.
     */
    public int getStone ()
    {
        return stone;
    }


    /**
     * Sets the amount of stone in the savegame.
     * @author Jonas Helfer
     * @param stone The new amount of stone.
     */
    public void setStone (int stone)
    {
        this.stone = stone;
    }


    /**
     * Gets the player's name in the savegame.
     * @author Jonas Helfer
     * @return The player's name.
     */
    public String getPlayerName ()
    {
        return playerName;
    }


    /**
     * Sets the player's name in the savegame.
     * @author Jonas Helfer
     * @param playerName The new player name.
     */
    public void setPlayerName (String playerName)
    {
        this.playerName = playerName;
    }
}
