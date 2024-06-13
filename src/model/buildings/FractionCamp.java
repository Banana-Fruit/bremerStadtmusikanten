package model.buildings;


import control.BuildingController;
import model.Coordinate;
import resources.constants.scenes.Constants_Building;


public class FractionCamp extends Building
{
    private static final FractionCamp INSTANCE_OF_FRACTIONCAMP = new FractionCamp(Constants_Building.NAME_FRACTIONCAMP,
            Constants_Building.FRACTIONCAMP_GOLD, Constants_Building.FRACTIONCAMP_BRICK,
            Constants_Building.FRACTIONCAMP_WOOD, Constants_Building.FRACTIONCAMP_BEER,
            Constants_Building.FRACTIONCAMP_ESSENCE, false, new Coordinate(29.0,19.0));


    // constructor
    private FractionCamp (String name, int numberOfGold, int numberOfBrick, int numberOfWood,
                          int numberOfBeer, int numberOfEssence, boolean isUnlocked,
                          Coordinate positionUpperLeft)
    {
        super (name, numberOfGold, numberOfBrick, numberOfWood, numberOfBeer, numberOfEssence, isUnlocked, positionUpperLeft);
    }




    @Override
    public void unlockBuilding ()
    {
        BuildingController.checkIfBuildingIsUnlock(getInstanceOfFractioncamp());
    }


    public static FractionCamp getInstanceOfFractioncamp ()
    {
        return INSTANCE_OF_FRACTIONCAMP;
    }
}
