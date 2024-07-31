package model.player;


public class Artifact
{
    private int bonusShield;
    private int bonusHealth;
    private int bonusCloseCombat;
    private int bonusRangeCombat;
    
    
    public Artifact (int bonusShield, int bonusHealth, int bonusCloseCombat, int bonusRangeCombat)
    {
        this.bonusShield = bonusShield;
        this.bonusHealth = bonusHealth;
        this.bonusCloseCombat = bonusCloseCombat;
        this.bonusRangeCombat = bonusRangeCombat;
    }

    
    
    // getter methods
    public int getBonusShield ()
    {
        return bonusShield;
    }
    
    
    public int getBonusHealth ()
    {
        return bonusHealth;
    }
    
    
    public int getBonusCloseCombat ()
    {
        return bonusCloseCombat;
    }
    
    
    public int getBonusRangeCombat ()
    {
        return bonusCloseCombat;
    }
    
    
    // setter methods
    public void setBonusShield (int bonusShield)
    {
        this.bonusShield = bonusShield;
    }
    
    
    public void setBonusHealth (int bonusHealth)
    {
        this.bonusHealth = bonusHealth;
    }
    
    
    public void setBonusCloseCombat (int bonusCloseCombat)
    {
        this.bonusCloseCombat = bonusCloseCombat;
    }
    
    
    public void setBonusRangeCombat (int bonusRangeCombat)
    {
        this.bonusRangeCombat = bonusRangeCombat;
    }
}
