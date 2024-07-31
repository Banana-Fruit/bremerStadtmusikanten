package model.buildings;


import control.BuildingController;
import model.Coordinate;
import model.Unit;
import model.player.FractionChicken;
import resources.constants.scenes.Constants_Building;


public class FractionCampChicken extends Building
{
    private static final FractionCampChicken INSTANCE_OF_FRACTION_CHICKEN_CAMP = new FractionCampChicken(
            Constants_Building.NAME_FRACTIONCAMP_CHICKEN, Constants_Building.FRACTIONCAMP_CHICKEN_GOLD,
            Constants_Building.FRACTIONCAMP_CHICKEN_BRICK, Constants_Building.FRACTIONCAMP_CHICKEN_WOOD,
            Constants_Building.FRACTIONCAMP_CHICKEN_BEER, Constants_Building.FRACTIONCAMP_CHICKEN_ESSENCE, false,
            new Coordinate(Constants_Building.FRACTIONCAMP_CHICKEN_POSITION_X, Constants_Building.FRACTIONCAMP_CHICKEN_POSITION_Y));
    
    
    private FractionCampChicken (String name, int numberOfGold, int numberOfBrick, int numberOfWood, int numberOfBeer,
                                 int numberOfEssence, boolean isUnlocked, Coordinate positionUpperLeft)
    {
        super(name, numberOfGold, numberOfBrick, numberOfWood, numberOfBeer, numberOfEssence, isUnlocked, positionUpperLeft);
    }
    
    
    @Override
    public void unlockBuilding ()
    {
        BuildingController.checkIfBuildingIsUnlock(getInstanceOfFractionChickenCamp());
    }

    
    
    public static FractionCampChicken getInstanceOfFractionChickenCamp ()
    {
        return INSTANCE_OF_FRACTION_CHICKEN_CAMP;
    }
}
