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


    /**
     * Getter-method to access of the attribute teamMember.
     *
     * @author Jule Degener
     * @return The attribute teamMember is returned.
     * @precondition none
     * @postcondition Access of the attribute teamMember.
     */
    public ArrayList<Unit> getTeamMembers ()
    {
        return teamMember;
    }


    /**
     * Getter-method to access of the attribute listOfArtifacts.
     *
     * @author Jule Degener
     * @return The attribute listOfArtifacts is returned.
     * @precondition none
     * @postcondition Access of the attribute listOfArtifacts.
     */
    public ArrayList<Artifact> getListOfArtifacts ()
    {
        return listOfArtifacts;
    }


    /**
     * Getter-method to access of the attribute magicSkill.
     *
     * @author Jule Degener
     * @return The attribute magicSkill is returned.
     * @precondition none
     * @postcondition Access of the attribute magicSkill.
     */
    public int getMagicSkill ()
    {
        return magicSkill;
    }


    /**
     * Setter-method to set the value of the attribute magicSkill.
     *
     * @author Jule Degener
     * @param magicSkill new Integer value of the attribute magicSkill.
     * @precondition none
     * @postcondition Parameter bonusHealth is the current value of the attribute magicSkill.
     */
    public void setMagicSkill (int magicSkill)
    {
        this.magicSkill = magicSkill;
    }
}
