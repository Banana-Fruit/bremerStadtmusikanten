package model.player;


import resources.constants.Constants_ExceptionMessages;
import resources.constants.Constants_Player_Units;
import model.Unit;

import java.util.ArrayList;
import java.util.List;


/**
 * The player has an inventory, skills, artifacts and more related things to the player.
 *
 * @author Michael Markov
 */
public class Player
{
    private static Player instance = null;
    private ArrayList<Artifact> listOfArtifacts;
    private ArrayList<Unit> teamMember = new ArrayList<>();
    private int magicSkill = Constants_Player_Units.MAGIC_SKILL_OF_PLAYER;
    
    
    private Player ()
    {
    }
    
    
    public static synchronized void initialize ()
    {
        instance = new Player();
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
    
    
    public ArrayList<Unit> getTeamMembers ()
    {
        return teamMember;
    }
    
    
    public ArrayList<Artifact> getListOfArtifacts ()
    {
        return listOfArtifacts;
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
