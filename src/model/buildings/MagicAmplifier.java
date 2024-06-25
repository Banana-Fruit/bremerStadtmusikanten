package model.buildings;


import control.BuildingController;
import model.Coordinate;
import model.player.Player;
import resources.constants.Constants_Player_Units;
import resources.constants.scenes.Constants_Building;


public class MagicAmplifier extends Building
{
    private static final MagicAmplifier INSTANCE_OF_MAGICAMPLIFIER = new MagicAmplifier
            (Constants_Building.NAME_MAGICAMPLIFIER, Constants_Building.MAGIC_AMPLIFIER_GOLD,
            Constants_Building.MAGIC_AMPLIFIER_BRICK, Constants_Building.MAGIC_AMPLIFIER_WOOD,
            Constants_Building.MAGIC_AMPLIFIER_BEER, Constants_Building.MAGIC_AMPLIFIER_ESSENCE, false, new Coordinate(Constants_Building.MAGIC_AMPLIFIER_POSITION_X,Constants_Building.MAGIC_AMPLIFIER_POSITION_Y));


    private MagicAmplifier (String name, int numberOfGold, int numberOfBrick, int numberOfWood,
                            int numberOfBeer, int numberOfEssence, boolean isUnlocked,
                            Coordinate positionUpperLeft)
    {
        super (name, numberOfGold, numberOfBrick, numberOfWood, numberOfBeer, numberOfEssence, isUnlocked, positionUpperLeft);
    }


    @Override
    public void unlockBuilding ()
    {
        BuildingController.checkIfBuildingIsUnlock(getInstanceOfMagicamplifier());
    }


    public static void pushMagicSkillOfPlayer ()
    {
        System.out.printf("MagicSkill before: %d \n", Player.getInstance().getMagicSkill());
        Player.getInstance().setMagicSkill(Player.getInstance().getMagicSkill() + Constants_Player_Units.MAGIC_SKILL_BONUS);
        System.out.printf("MagicSkill after: %d" , Player.getInstance().getMagicSkill());
    }


    public static MagicAmplifier getInstanceOfMagicamplifier ()
    {
        return INSTANCE_OF_MAGICAMPLIFIER;
    }
}
