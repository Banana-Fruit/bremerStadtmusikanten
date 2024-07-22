package model.buildings;


import control.BuildingController;
import model.Coordinate;
import model.Unit;
import model.player.FractionChicken;
import resources.constants.scenes.Constants_Building;

import java.util.List;


public class FractionCampChicken extends Building
{
    private static final FractionCampChicken INSTANCE_OF_FRACTION_CHICKEN_CAMP = new FractionCampChicken(
            Constants_Building.NAME_FRACTIONCAMP_CHICKEN, Constants_Building.FRACTIONCAMP_CHICKEN_GOLD,
            Constants_Building.FRACTIONCAMP_CHICKEN_BRICK, Constants_Building.FRACTIONCAMP_CHICKEN_WOOD,
            Constants_Building.FRACTIONCAMP_CHICKEN_BEER, Constants_Building.FRACTIONCAMP_CHICKEN_ESSENCE, false,
            new Coordinate(Constants_Building.FRACTIONCAMP_CHICKEN_POSITION_X, Constants_Building.FRACTIONCAMP_CHICKEN_POSITION_Y));
    
    private List<Unit> listOfFractions;
    
    
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
    
    
    private static Unit recruitChickFromFractionChicken (FractionChicken fractionChicken)
    {
        return fractionChicken.getChick(); // TODO: später Einlesen
    }
    
    
    private static Unit recruitChefFromFractionChicken (FractionChicken fractionChicken)
    {
        return fractionChicken.getChef(); // TODO: später Einlesen
    }
    
    
    private static Unit recruitFightingChickenFromFractionChicken (FractionChicken fractionChicken)
    {
        return fractionChicken.getFightingChicken(); // TODO: später Einlesen
    }
    
    
    private static Unit recruitTurkeyFromFractionChicken (FractionChicken fractionChicken)
    {
        return fractionChicken.getTurkey(); // TODO: später Einlesen
    }
    
    
    private static Unit recruitChickenWithHatFromFractionChicken (FractionChicken fractionChicken)
    {
        return fractionChicken.getChickenWithHat(); // TODO: später Einlesen
    }
    
    
    public static void addUnitsToList (FractionChicken fractionChicken)
    {
        List<Unit> list = INSTANCE_OF_FRACTION_CHICKEN_CAMP.getListOfFractions();
        list.add(fractionChicken.getChick());
        list.add(fractionChicken.getFightingChicken());
        list.add(fractionChicken.getChef());
        list.add(fractionChicken.getTurkey());
        list.add(fractionChicken.getChickenWithHat());
    }
    
    
    public static FractionCampChicken getInstanceOfFractionChickenCamp ()
    {
        return INSTANCE_OF_FRACTION_CHICKEN_CAMP;
    }
    
    
    public List<Unit> getListOfFractions ()
    {
        return listOfFractions;
    }
}
