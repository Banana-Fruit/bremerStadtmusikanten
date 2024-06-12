package model.buildings;


import control.BuildingController;
import resources.constants.scenes.Constants_Building;


public class Pub extends Building
{
    private static final Pub INSTANCE_OF_PUB = new Pub(Constants_Building.NAME_PUB, Constants_Building.PUB_GOLD,
            Constants_Building.PUB_BRICK, Constants_Building.PUB_WOOD, Constants_Building.PUB_BEER,
            Constants_Building.PUB_ESSENCE, false);


    // constructor
    private Pub (String name, int numberOfGold, int numberOfBrick, int numberOfWood, int numberOfBeer, int numberOfEssence,
                 boolean isUnlocked)
    {
        super (name, numberOfGold, numberOfBrick, numberOfWood, numberOfBeer, numberOfEssence, isUnlocked);
    }



    @Override
    public void unlockBuilding ()
    {
        BuildingController.checkIfBuildingIsUnlock(getInstanceOfPub());
    }


    public Pub getInstanceOfPub ()
    {
        return INSTANCE_OF_PUB;
    }
}
