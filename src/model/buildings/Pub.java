package model.buildings;


import control.BuildingController;
import model.Coordinate;
import resources.constants.scenes.Constants_Building;


public class Pub extends Building
{
    private static final Pub INSTANCE_OF_PUB = new Pub(Constants_Building.NAME_PUB, Constants_Building.PUB_GOLD,
            Constants_Building.PUB_BRICK, Constants_Building.PUB_WOOD, Constants_Building.PUB_BEER,
            Constants_Building.PUB_ESSENCE, false, new Coordinate(10.0,8.0));


    // constructor
    private Pub (String name, int numberOfGold, int numberOfBrick, int numberOfWood,
                 int numberOfBeer, int numberOfEssence, boolean isUnlocked,
                 Coordinate positionUpperLeft)
    {
        super (name, numberOfGold, numberOfBrick, numberOfWood, numberOfBeer, numberOfEssence, isUnlocked, positionUpperLeft);
    }



    @Override
    public void unlockBuilding ()
    {
        BuildingController.checkIfBuildingIsUnlock(getInstanceOfPub());
    }


    public static Pub getInstanceOfPub ()
    {
        return INSTANCE_OF_PUB;
    }
}
