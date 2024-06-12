package model.buildings;


import control.BuildingController;
import resources.constants.scenes.Constants_Building;


public class FractionCamp extends Building
{
    private static final FractionCamp INSTANCE_OF_FRACTIONCAMP = new FractionCamp(Constants_Building.NAME_FRACTIONCAMP,
            Constants_Building.FRACTIONCAMP_GOLD, Constants_Building.FRACTIONCAMP_BRICK,
            Constants_Building.FRACTIONCAMP_WOOD, Constants_Building.FRACTIONCAMP_BEER,
            Constants_Building.FRACTIONCAMP_ESSENCE, false);


    // constructor
    private FractionCamp (String name, int numberOfGold, int numberOfBrick, int numberOfWood, int numberOfBeer,
                          int numberOfEssence, boolean isUnlocked)
    {
        super (name, numberOfGold, numberOfBrick, numberOfWood, numberOfBeer, numberOfEssence, isUnlocked);
    }




    @Override
    public void unlockBuilding ()
    {
        BuildingController.checkIfBuildingIsUnlock(getInstanceOfFractioncamp());
    }


    public FractionCamp getInstanceOfFractioncamp ()
    {
        return INSTANCE_OF_FRACTIONCAMP;
    }
}
