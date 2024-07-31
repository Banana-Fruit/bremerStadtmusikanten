package model;


import view.OutputImageView;


public class Unit
{
    private String name;
    private int health, shield, mana, meele, ranged, ammo, dodge, magicresist, RangeOfMotion, initiative, magicDamage, myAttack;
    private double positionX, positionY;
    
    OutputImageView unitView;
    
    
    public Unit (String name, int health, int shield, int mana, int meele, int ranged, int ammo, int dodge, int magicresist,
                 int RangeOfMotion, int initiative, int magicDamage, int myAttack, double positionX, double positionY, OutputImageView unitView)
    {
        this.name = name;
        this.health = health;
        this.shield = shield;
        this.mana = mana;
        this.meele = meele;
        this.ranged = ranged;
        this.ammo = ammo;
        this.dodge = dodge;
        this.magicresist = magicresist;
        this.RangeOfMotion = RangeOfMotion;
        this.initiative = initiative;
        this.magicDamage = magicDamage;
        this.myAttack = myAttack;
        this.positionX = positionX;
        this.positionY = positionY;
        this.unitView = unitView;
    }
    
    
    public double getPositionX ()
    {
        return positionX;
    }
    
    
    public void setPositionX (double positionX)
    {
        this.positionX = positionX;
    }
    
    
    public double getPositionY ()
    {
        return positionY;
    }
    
    
    public void setPositionY (double positionY)
    {
        this.positionY = positionY;
    }
    
    
    public int getMyAttack ()
    {
        return myAttack;
    }
    
    
    public void setMyAttack (int myAttack)
    {
        this.myAttack = myAttack;
    }
    
    
    public void setName (String name)
    {
        this.name = name;
    }
    
    
    public int getMagicDamage ()
    {
        return magicDamage;
    }
    
    
    public void setMagicDamage (int magicDamage)
    {
        this.magicDamage = magicDamage;
    }
    
    
    public void setHealth (int health)
    {
        this.health = health;
    }
    
    
    public void setShield (int shield)
    {
        this.shield = shield;
    }
    
    
    public void setMana (int mana)
    {
        this.mana = mana;
    }
    
    
    public void setMeele (int meele)
    {
        this.meele = meele;
    }
    
    
    public void setRanged (int ranged)
    {
        this.ranged = ranged;
    }
    
    
    public void setAmmo (int ammo)
    {
        this.ammo = ammo;
    }
    
    
    public void setDodge (int dodge)
    {
        this.dodge = dodge;
    }
    
    
    public void setMagicresist (int magicresist)
    {
        this.magicresist = magicresist;
    }
    
    
    public void setRangeOfMotion (int mvmspeed)
    {
        this.RangeOfMotion = mvmspeed;
    }
    
    
    public void setInitiative (int initiative)
    {
        this.initiative = initiative;
    }
    
    
    public String getName ()
    {
        return name;
    }
    
    
    public int getHealth ()
    {
        return health;
    }
    
    
    public int getShield ()
    {
        return shield;
    }
    
    
    public int getMana ()
    {
        return mana;
    }
    
    
    public int getMeele ()
    {
        return meele;
    }
    
    
    public int getRanged ()
    {
        return ranged;
    }
    
    
    public int getAmmo ()
    {
        return ammo;
    }
    
    
    public int getDodge ()
    {
        return dodge;
    }
    
    
    public int getMagicresist ()
    {
        return magicresist;
    }
    
    
    public int getRangeOfMotion ()
    {
        return RangeOfMotion;
    }
    
    
    public int getInitiative ()
    {
        return initiative;
    }
    
    
    public OutputImageView getUnitView ()
    {
        return unitView;
    }
}