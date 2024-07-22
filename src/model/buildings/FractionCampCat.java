package model.buildings;


import control.BuildingController;
import model.Coordinate;
import model.Unit;
import model.player.FractionCat;
import resources.constants.scenes.Constants_Building;

import java.util.List;


public class FractionCampCat extends Building
{
    private static final FractionCampCat INSTANCE_OF_FRACTION_CAT_CAMP = new FractionCampCat(
            Constants_Building.NAME_FRACTIONCAMP_CAT, Constants_Building.FRACTIONCAMP_CAT_GOLD,
            Constants_Building.FRACTIONCAMP_CAT_BRICK, Constants_Building.FRACTIONCAMP_CAT_WOOD,
            Constants_Building.FRACTIONCAMP_CAT_BEER, Constants_Building.FRACTIONCAMP_CAT_ESSENCE, false,
            new Coordinate(Constants_Building.FRACTIONCAMP_CAT_POSITION_X, Constants_Building.FRACTIONCAMP_CAT_POSITION_Y));
    
    private List<Unit> listOfFractions;
    
    
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
    
    
    private static Unit recruitCatsFromFractionCat (FractionCat fractionCat)
    {
        return fractionCat.getCat(); // TODO: später Einlesen
    }
    
    
    private static Unit recruitTigersFromFractionCat (FractionCat fractionCat)
    {
        return fractionCat.getTiger(); // TODO: später Einlesen
    }
    
    
    private static Unit recruitHousekeepersFromFractionCat (FractionCat fractionCat)
    {
        return fractionCat.getHousekeeper(); // TODO: später Einlesen
    }
    
    
    private static Unit recruitJaguarsFromFractionCat (FractionCat fractionCat)
    {
        return fractionCat.getJaguar(); // TODO: später Einlesen
    }
    
    
    private static Unit recruitCatsWithHatFromFractionCat (FractionCat fractionCat)
    {
        return fractionCat.getBingus(); // TODO: später Einlesen
    }
    
    
    public static void addUnitsToList (FractionCat fractionCat)
    {
        List<Unit> list = INSTANCE_OF_FRACTION_CAT_CAMP.getListOfFractions();
        list.add(fractionCat.getCat());
        list.add(fractionCat.getJaguar());
        list.add(fractionCat.getHousekeeper());
        list.add(fractionCat.getTiger());
        list.add(fractionCat.getBingus());
    }
    
    
    public static FractionCampCat getInstanceOfFractionCatCamp ()
    {
        return INSTANCE_OF_FRACTION_CAT_CAMP;
    }
    
    
    public List<Unit> getListOfFractions ()
    {
        return listOfFractions;
    }
}
