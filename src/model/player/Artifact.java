package model.player;


/**
 * The class Artifact contains all attributes and methods to create a functional artifact.
 *
 * @author Jule Degener
 */
public class Artifact
{
    private int bonusShield;
    private int bonusHealth;
    private int bonusCloseCombat;
    private int bonusRangeCombat;
    private int levelOfUpgrade;


    /**
     * Constructor to create an instance of artifact.
     *
     * @author Jule Degener
     * @param bonusShield Attribute bonusShield
     * @param bonusHealth Attribute bonusHealth
     * @param bonusCloseCombat Attribute bonusCloseCombat
     * @param bonusRangeCombat Attribute bonusRangeCombat
     * @param levelOfUpgrade Attribute level of upgrade
     * @precondition none
     * @postcondition An instance of artifact is created.
     */
    public Artifact (int bonusShield, int bonusHealth, int bonusCloseCombat, int bonusRangeCombat, int levelOfUpgrade)
    {
        this.bonusShield = bonusShield;
        this.bonusHealth = bonusHealth;
        this.bonusCloseCombat = bonusCloseCombat;
        this.bonusRangeCombat = bonusRangeCombat;
        this.levelOfUpgrade = levelOfUpgrade;
    }


    /**
     * Getter-method to access of the attribute bonusShield.
     *
     * @author Jule Degener
     * @return The attribute bonusShield is returned.
     * @precondition none
     * @postcondition Access of the attribute bonusShield.
     */
    public int getBonusShield ()
    {
        return bonusShield;
    }


    /**
     * Getter-method to access of the attribute bonusHealth.
     *
     * @author Jule Degener
     * @return The attribute bonusHealth is returned.
     * @precondition none
     * @postcondition Access of the attribute bonusHealth.
     */
    public int getBonusHealth ()
    {
        return bonusHealth;
    }


    /**
     * Getter-method to access of the attribute bonusCloseCombat.
     *
     * @author Jule Degener
     * @return The attribute bonusCloseCombat is returned.
     * @precondition none
     * @postcondition Access of the attribute bonusCloseCombat.
     */
    public int getBonusCloseCombat ()
    {
        return bonusCloseCombat;
    }


    /**
     * Getter-method to access of the attribute bonusRangeCombat.
     *
     * @author Jule Degener
     * @return The attribute bonusRangeCombat is returned.
     * @precondition none
     * @postcondition Access of the attribute bonusRangeCombat.
     */
    public int getBonusRangeCombat ()
    {
        return bonusCloseCombat;
    }


    /**
     * Getter-method to access of the attribute levelOfUpgrade.
     *
     * @author Jule Degener
     * @return The attribute levelOfUpgrade is returned.
     * @precondition none
     * @postcondition Access of the attribute levelOfUpgrade.
     */
    public int getLevelOfUpgrade ()
    {
        return levelOfUpgrade;
    }


    /**
     * Setter-method to set the value of the attribute bonusShield.
     *
     * @author Jule Degener
     * @param bonusShield new Integer value of the attribute bonusShield.
     * @precondition none
     * @postcondition Parameter bonusShield is the current value of the attribute bonusShield.
     */
    public void setBonusShield (int bonusShield)
    {
        this.bonusShield = bonusShield;
    }


    /**
     * Setter-method to set the value of the attribute bonusHealth.
     *
     * @author Jule Degener
     * @param bonusHealth new Integer value of the attribute bonusHealth.
     * @precondition none
     * @postcondition Parameter bonusHealth is the current value of the attribute bonusHealth.
     */
    public void setBonusHealth (int bonusHealth)
    {
        this.bonusHealth = bonusHealth;
    }


    /**
     * Setter-method to set the value of the attribute bonusCloseCombat.
     *
     * @author Jule Degener
     * @param bonusCloseCombat new Integer value of the attribute bonusCloseCombat.
     * @precondition none
     * @postcondition Parameter bonusCloseCombat is the current value of the attribute bonusCloseCombat.
     */
    public void setBonusCloseCombat (int bonusCloseCombat)
    {
        this.bonusCloseCombat = bonusCloseCombat;
    }


    /**
     * Setter-method to set the value of the attribute bonusRangeCombat.
     *
     * @author Jule Degener
     * @param bonusRangeCombat new Integer value of the attribute bonusRangeCombat.
     * @precondition none
     * @postcondition Parameter bonusRangeCombat is the current value of the attribute bonusRangeCombat.
     */
    public void setBonusRangeCombat (int bonusRangeCombat)
    {
        this.bonusRangeCombat = bonusRangeCombat;
    }


    /**
     * Setter-method to set the value of the attribute levelOfUpgrade.
     *
     * @author Jule Degener
     * @param levelOfUpgrade new Integer value of the attribute levelOfUpgrade.
     * @precondition none
     * @postcondition Parameter levelOfUpgrade is the current value of the attribute levelOfUpgrade.
     */
    public void setLevelOfUpgrade (int levelOfUpgrade)
    {
        this.levelOfUpgrade = levelOfUpgrade;
    }
}
