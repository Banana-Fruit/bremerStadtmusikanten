package model;


public class Attack
{
    private boolean isRanged, isMagic;
    private int AtkRange;
    
    
    public Attack (int AtkRange, boolean isMagic, boolean isRanged)
    {
        this.AtkRange = AtkRange;
        this.isMagic = isMagic;
        this.isRanged = isRanged;
    }
    
    
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
