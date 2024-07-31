package model.buildings;


import control.BuildingController;
import model.Coordinate;
import resources.constants.scenes.Constants_Building;


public class FractionCampCat extends Building
{
    private static final FractionCampCat INSTANCE_OF_FRACTION_CAT_CAMP = new FractionCampCat(
            Constants_Building.NAME_FRACTIONCAMP_CAT, Constants_Building.FRACTIONCAMP_CAT_GOLD,
            Constants_Building.FRACTIONCAMP_CAT_BRICK, Constants_Building.FRACTIONCAMP_CAT_WOOD,
            Constants_Building.FRACTIONCAMP_CAT_BEER, Constants_Building.FRACTIONCAMP_CAT_ESSENCE, false,
            new Coordinate(Constants_Building.FRACTIONCAMP_CAT_POSITION_X, Constants_Building.FRACTIONCAMP_CAT_POSITION_Y));

    
    
    private FractionCampCat (String name, int numberOfGold, int numberOfBrick, int numberOfWood, int numberOfBeer,
                             int numberOfEssence, boolean isUnlocked, Coordinate positionUpperLeft)
    {
        super(name, numberOfGold, numberOfBrick, numberOfWood, numberOfBeer, numberOfEssence, isUnlocked, positionUpperLeft);
    }
    
    
    @Override
    public void unlockBuilding ()
    {
        BuildingController.checkIfBuildingIsUnlock(getInstanceOfFractionCatCamp());
    }

    
    
    public static FractionCampCat getInstanceOfFractionCatCamp ()
    {
        return INSTANCE_OF_FRACTION_CAT_CAMP;
    }
}
