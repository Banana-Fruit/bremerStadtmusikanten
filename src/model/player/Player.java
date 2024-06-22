package model.player;


import resources.constants.Constants_ExceptionMessages;
import resources.constants.Constants_Player_Units;

import java.util.List;


/**
 * An image with a hitbox that moves on the map
 */
public class Player
{
    private static Player instance = null;
    private List inventory; // TODO: Holds artefacts
    private Unit[] teamMember = new Unit [Constants_Player_Units.NUMBER_OF_TEAM_MEMBER];
    private int magicSkill = Constants_Player_Units.MAGIC_SKILL_OF_PLAYER;
    
    
    private Player ()
    {
    }
    
    
    public static synchronized void initialize ()
    {
        if (instance == null)
        {
            instance = new Player();
        } else
        {
            throw new IllegalStateException(Constants_ExceptionMessages.ALREADY_INITIALIZED);
        }
    }
    
    
    // Method to retrieve the Singleton instance without parameters
    public static Player getInstance ()
    {
        if (instance == null)
        {
            throw new IllegalStateException(Constants_ExceptionMessages.SINGLETON_NOT_INITIALIZED);
        }
        return instance;
    }


    public Unit[] getTeamMember ()
    {
        return teamMember;
    }


    public int getMagicSkill ()
    {
        return magicSkill;
    }


    public void setMagicSkill (int magicSkill)
    {
        this.magicSkill = magicSkill;
    }
}
