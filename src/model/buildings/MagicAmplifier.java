package model.buildings;


import control.BuildingController;
import model.Coordinate;
import model.player.Player;
import resources.constants.Constants_Player_Units;
import resources.constants.scenes.Constants_Building;


/**
 * The class MagicAmplifier contains all functional methods from the building MagicAmplifier.
 * The class extends from the abstract class Building.
 *
 * @author Jule Degener
 */
public class MagicAmplifier extends Building
{
    /**
     * Instance of MagicAmplifier as attribute of the class to create a singleton
     *
     * @author Jule Degener
     */
    private static final MagicAmplifier INSTANCE_OF_MAGICAMPLIFIER = new MagicAmplifier
            (Constants_Building.NAME_MAGICAMPLIFIER, Constants_Building.MAGIC_AMPLIFIER_GOLD,
                    Constants_Building.MAGIC_AMPLIFIER_BRICK, Constants_Building.MAGIC_AMPLIFIER_WOOD,
                    Constants_Building.MAGIC_AMPLIFIER_BEER, Constants_Building.MAGIC_AMPLIFIER_ESSENCE,
                    false, new Coordinate(Constants_Building.MAGIC_AMPLIFIER_POSITION_X,
                    Constants_Building.MAGIC_AMPLIFIER_POSITION_Y));


    /**
     * Constructor to create an instance of MagicAmplifier.
     *
     * @author Jule Degener, Jonas Helfer
     * @param name Name of the building
     * @param numberOfGold gold-price of the building
     * @param numberOfBrick brick-price of the building
     * @param numberOfWood wood-price of the building
     * @param numberOfBeer beer-price of the building
     * @param numberOfEssence essence-price of the building
     * @param isUnlocked boolean-value to see if a building is unlocked
     * @param positionUpperLeft Coordinate from the building
     * @precondition none
     * @postcondition An instance of the MagicAmplifier is created.
     */
    private MagicAmplifier (String name, int numberOfGold, int numberOfBrick, int numberOfWood,
                            int numberOfBeer, int numberOfEssence, boolean isUnlocked,
                            Coordinate positionUpperLeft)
    {
        super(name, numberOfGold, numberOfBrick, numberOfWood, numberOfBeer, numberOfEssence, isUnlocked,
                positionUpperLeft);
    }


    /**
     * Override method from the abstract class Building to unlock a building
     *
     * @author Jule Degener
     * @precondition An instance of BuildingController must exist.
     * @postcondition Status is known if a building is unlocked.
     */
    @Override
    public void unlockBuilding ()
    {
        BuildingController.getInstance().checkIfBuildingIsUnlock(getInstanceOfMagicamplifier());
    }


    /**
     * Method to upgrade the magic skill of the player.
     *
     * @author Jule Degener
     * @precondition An instance of the Player must exist.
     * @postcondition The magic skill of the player is upgraded.
     */
    public void pushMagicSkillOfPlayer ()
    {
        System.out.printf(Constants_Building.MAGIC_SKILL_BEFORE, Player.getInstance().getMagicSkill());
        Player.getInstance().setMagicSkill(Player.getInstance().getMagicSkill() +
                Constants_Player_Units.MAGIC_SKILL_BONUS);
        System.out.printf(Constants_Building.MAGIC_SKILL_AFTER, Player.getInstance().getMagicSkill());
    }


    /**
     * Getter-method to have access of the instance of the MagicAmplifier
     *
     * @author Jule Degener
     * @return The instance of the MagicAmplifier is returned.
     * @precondition none
     * @postcondition Access of the instance of the MagicAmplifier
     */
    public static MagicAmplifier getInstanceOfMagicamplifier ()
    {
        return INSTANCE_OF_MAGICAMPLIFIER;
    }
}
