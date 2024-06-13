package model.buildings;


import control.BuildingController;
import model.Coordinate;
import resources.constants.scenes.Constants_Building;


public class MagicAmplifier extends Building
{
    private static final MagicAmplifier INSTANCE_OF_MAGICAMPLIFIER = new MagicAmplifier
            (Constants_Building.NAME_MAGICAMPLIFIER, Constants_Building.MAGIC_AMPLIFIER_GOLD,
            Constants_Building.MAGIC_AMPLIFIER_BRICK, Constants_Building.MAGIC_AMPLIFIER_WOOD,
            Constants_Building.MAGIC_AMPLIFIER_BEER, Constants_Building.MAGIC_AMPLIFIER_ESSENCE, false, new Coordinate(1.0,1.0));


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


    public MagicAmplifier getInstanceOfMagicamplifier ()
    {
        return INSTANCE_OF_MAGICAMPLIFIER;
    }
}
