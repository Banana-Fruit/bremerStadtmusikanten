package model.player;


public class Unit
{
    private int shield;
    private int health;
    private int closeCombat;
    private int rangeCombat;
    private int evade;
    private int magicResidence;
    private int rangeOfMotion;
    private int proactive;
    private int magic;


    public Unit (int shield, int health, int closeCombat, int rangeCombat, int evade, int magicResidence,
                 int rangeOfMotion, int proactive, int magic)
    {
        this.shield = shield;
        this.health = health;
        this.closeCombat = closeCombat;
        this.rangeCombat = rangeCombat;
        this.evade = evade;
        this.magicResidence = magicResidence;
        this.rangeOfMotion = rangeOfMotion;
        this.proactive = proactive;
        this.magic = magic;
    }





    // getter methods
    public int getShield ()
    {
        return shield;
    }


    public int getHealth ()
    {
        return health;
    }


    public int getCloseCombat ()
    {
        return closeCombat;
    }


    public int getRangeCombat ()
    {
        return rangeCombat;
    }


    public int getEvade ()
    {
        return evade;
    }

    public int getMagicResidence ()
    {
        return magicResidence;
    }


    public int getRangeOfMotion ()
    {
        return rangeOfMotion;
    }


    public int getProactive ()
    {
        return proactive;
    }


    public int getMagic ()
    {
        return magic;
    }


    // setter methods
    public void setShield (int shield)
    {
        this.shield = shield;
    }


    public void setHealth (int health)
    {
        this. health = health;
    }


    public void setCloseCombat (int closeCombat)
    {
        this.closeCombat = closeCombat;
    }


    public void setRangeCombat (int rangeCombat)
    {
        this.rangeCombat = rangeCombat;
    }


    public void setEvade (int evade)
    {
        this.evade = evade;
    }


    public void setMagicResidence (int magicResidence)
    {
        this.magicResidence = magicResidence;
    }


    public void setRangeOfMotion (int rangeOfMotion)
    {
        this.rangeOfMotion = rangeOfMotion;
    }


    public void setProactive (int proactive)
    {
        this.proactive = proactive;
    }


    public void setMagic (int magic)
    {
        this.magic = magic;
    }
}
