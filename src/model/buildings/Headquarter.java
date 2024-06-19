package model.buildings;


import control.BuildingController;
import model.Coordinate;
import resources.constants.scenes.Constants_Building;


public class Headquarter extends Building
{
    private static final Headquarter INSTANCE_OF_HEADQUARTER = new Headquarter(Constants_Building.NAME_HEADQUARTER,
            Constants_Building.HEADQUARTER_GOLD, Constants_Building.HEADQUARTER_BRICK, Constants_Building.HEADQUARTER_WOOD,
            Constants_Building.HEADQUARTER_BEER, Constants_Building.HEADQUARTER_ESSENCE, true, new Coordinate(4.0,34.0));


    private Headquarter (String name, int numberOfGold, int numberOfBrick, int numberOfWood,
                         int numberOfBeer, int numberOfEssence, boolean isUnlocked,
                         Coordinate positionUpperLeft)
    {
        super (name, numberOfGold, numberOfBrick, numberOfWood, numberOfBeer, numberOfEssence, isUnlocked, positionUpperLeft);
    }


    @Override
    public void unlockBuilding ()
    {
        BuildingController.checkIfBuildingIsUnlock(getInstanceOfHeadquarter());
    }


    public static Headquarter getInstanceOfHeadquarter ()
    {
        return INSTANCE_OF_HEADQUARTER;
    }
}
