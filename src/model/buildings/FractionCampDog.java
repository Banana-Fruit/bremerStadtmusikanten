package model.buildings;


import control.BuildingController;
import model.Coordinate;
import model.Unit;
import model.player.FractionDog;
import resources.constants.scenes.Constants_Building;


public class FractionCampDog extends Building
{
    private static final FractionCampDog INSTANCE_OF_FRACTION_DOG_CAMP = new FractionCampDog(
            Constants_Building.NAME_FRACTIONCAMP_DOG, Constants_Building.FRACTIONCAMP_DOG_GOLD,
            Constants_Building.FRACTIONCAMP_DOG_BRICK, Constants_Building.FRACTIONCAMP_DOG_WOOD,
            Constants_Building.FRACTIONCAMP_DOG_BEER, Constants_Building.FRACTIONCAMP_DOG_ESSENCE, false,
            new Coordinate(Constants_Building.FRACTIONCAMP_DOG_POSITION_X, Constants_Building.FRACTIONCAMP_DOG_POSITION_Y));

    
    
    private FractionCampDog (String name, int numberOfGold, int numberOfBrick, int numberOfWood, int numberOfBeer,
                             int numberOfEssence, boolean isUnlocked, Coordinate positionUpperLeft)
    {
        super(name, numberOfGold, numberOfBrick, numberOfWood, numberOfBeer, numberOfEssence, isUnlocked, positionUpperLeft);
    }
    
    
    @Override
    public void unlockBuilding ()
    {
        BuildingController.checkIfBuildingIsUnlock(getInstanceOfFractionDogcamp());
    }

    
    
    public static FractionCampDog getInstanceOfFractionDogcamp ()
    {
        return INSTANCE_OF_FRACTION_DOG_CAMP;
    }
}
