package model;


public class Savegame
{
    private int gold;
    private int coal;
    private int stone;
    private String playerName;
    
    
    public Savegame (int gold, int coal, int stone, String playerName)
    {
        this.gold = gold;
        this.coal = coal;
        this.stone = stone;
        this.playerName = playerName;
    }
    
    // Getter und Setter
    
    
    public int getGold ()
    {
        return gold;
    }
    
    
    public void setGold (int gold)
    {
        this.gold = gold;
    }
    
    
    public int getCoal ()
    {
        return coal;
    }
    
    
    public void setCoal (int coal)
    {
        this.coal = coal;
    }
    
    
    public int getStone ()
    {
        return stone;
    }
    
    
    public void setStone (int stone)
    {
        this.stone = stone;
    }
    
    
    public String getPlayerName ()
    {
        return playerName;
    }
    
    
    public void setPlayerName (String playerName)
    {
        this.playerName = playerName;
    }
}
