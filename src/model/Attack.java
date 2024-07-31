package model;

/**
 * The Attack class represents an attack in the game with attributes
 * for attack range and type (magic or ranged).
 *
 * @author Jonas Stamminger
 */
public class Attack
{
    private boolean isRanged, isMagic;
    private int AtkRange;

    /**
     * Constructs a new Attack with the specified range, magic status, and ranged status.
     *
     * @author Jonas Stamminger
     * @param AtkRange The range of the attack.
     * @param isMagic True if the attack is a magic attack, false otherwise.
     * @param isRanged True if the attack is a ranged attack, false otherwise.
     */
    public Attack (int AtkRange, boolean isMagic, boolean isRanged)
    {
        this.AtkRange = AtkRange;
        this.isMagic = isMagic;
        this.isRanged = isRanged;
    }

    /**
     * Getter and setter methods for each parameter of any given attack
     *
     * @author Jonas Stamminger
     */
    
    public boolean getRanged ()
    {
        return isRanged;
    }
    
    
    public void setRanged (boolean ranged)
    {
        isRanged = ranged;
    }
    
    
    public boolean getMagic ()
    {
        return isMagic;
    }
    
    
    public void setMagic (boolean magic)
    {
        isMagic = magic;
    }
    
    
    public int getAtkRange ()
    {
        return AtkRange;
    }
    
    
    public void setAtkRange (int atkRange)
    {
        AtkRange = atkRange;
    }
}
