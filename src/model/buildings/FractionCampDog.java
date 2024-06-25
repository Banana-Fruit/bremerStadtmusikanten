package model.buildings;


import control.BuildingController;
import model.Coordinate;
import model.Unit;
import model.player.FractionDog;
import model.player.FractionDonkey;
import resources.constants.scenes.Constants_Building;

import java.util.List;


public class FractionCampDog extends Building
{
    private static final FractionCampDog INSTANCE_OF_FRACTION_DOG_CAMP = new FractionCampDog(
            Constants_Building.NAME_FRACTIONCAMP_DOG, Constants_Building.FRACTIONCAMP_DOG_GOLD,
            Constants_Building.FRACTIONCAMP_DOG_BRICK, Constants_Building.FRACTIONCAMP_DOG_WOOD,
            Constants_Building.FRACTIONCAMP_DOG_BEER, Constants_Building.FRACTIONCAMP_DOG_ESSENCE, false,
            new Coordinate(Constants_Building.FRACTIONCAMP_DOG_POSITION_X,Constants_Building.FRACTIONCAMP_DOG_POSITION_Y));

    private List<Unit> listOfFractions;

    private FractionCampDog (String name, int numberOfGold, int numberOfBrick, int numberOfWood, int numberOfBeer,
                             int numberOfEssence, boolean isUnlocked, Coordinate positionUpperLeft)
    {
        super (name, numberOfGold, numberOfBrick, numberOfWood, numberOfBeer, numberOfEssence, isUnlocked, positionUpperLeft);
    }


    @Override
    public void unlockBuilding ()
    {
        BuildingController.checkIfBuildingIsUnlock(getInstanceOfFractionDogcamp());
    }


    private static Unit recruitGoldenRetrieverFromFractionDog (FractionDog fractionDog)
    {
        return fractionDog.getGoldenRetriever(); // TODO: später Einlesen
    }


    private static Unit recruitGermanShepherdFromFractionDog (FractionDog fractionDog)
    {
        return fractionDog.getGermanShepherd(); // TODO: später Einlesen
    }


    private static Unit recruitHunterFromFractionDog (FractionDog fractionDog)
    {
        return fractionDog.getHunter(); // TODO: später Einlesen
    }


    private static Unit recruitBulldogFromFractionDog (FractionDog fractionDog)
    {
        return fractionDog.getBulldog(); // TODO: später Einlesen
    }


    private static Unit recruitDogWithHatFromFractionDog (FractionDog fractionDog)
    {
        return fractionDog.getHundini(); // TODO: später Einlesen
    }


    public static void addUnitsToList (FractionDonkey fractionDonkey)
    {
        List<Unit> list = INSTANCE_OF_FRACTION_DOG_CAMP.getListOfFractions();
        list.add(fractionDonkey.getRats());
        list.add(fractionDonkey.getBeetle());
        list.add(fractionDonkey.getMosquitoes());
    }


    public static FractionCampDog getInstanceOfFractionDogcamp ()
    {
        return INSTANCE_OF_FRACTION_DOG_CAMP;
    }


    public List<Unit> getListOfFractions ()
    {
        return listOfFractions;
    }
}
