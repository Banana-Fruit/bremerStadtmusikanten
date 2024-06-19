package model.buildings;

import control.BuildingController;
import model.Coordinate;
import resources.constants.scenes.Constants_Building;

public class FractionCamp extends Building
{

    // constructor
    public FractionCamp (String name, int numberOfGold, int numberOfBrick, int numberOfWood,
                         int numberOfBeer, int numberOfEssence, boolean isUnlocked,
                         Coordinate positionUpperLeft)
    {
        super (name, numberOfGold, numberOfBrick, numberOfWood, numberOfBeer, numberOfEssence, isUnlocked, positionUpperLeft);
    }

    @Override
    public void unlockBuilding ()
    {
        BuildingController.checkIfBuildingIsUnlock(this);
    }

    // Factory method to create different types of FractionCamps
    public static FractionCamp createFractionCamp(String type)
    {
        switch(type) {
            case Constants_Building.NAME_FRACTIONCAMP_DOG:
                return new FractionCamp(
                        Constants_Building.NAME_FRACTIONCAMP_DOG,
                        Constants_Building.FRACTIONCAMP_DOG_GOLD,
                        Constants_Building.FRACTIONCAMP_DOG_BRICK,
                        Constants_Building.FRACTIONCAMP_DOG_WOOD,
                        Constants_Building.FRACTIONCAMP_DOG_BEER,
                        Constants_Building.FRACTIONCAMP_DOG_ESSENCE,
                        false,
                        new Coordinate(Constants_Building.FRACTIONCAMP_DOG_POSITION_X, Constants_Building.FRACTIONCAMP_DOG_POSITION_Y));
            case Constants_Building.NAME_FRACTIONCAMP_CAT:
                return new FractionCamp(
                        Constants_Building.NAME_FRACTIONCAMP_CAT,
                        Constants_Building.FRACTIONCAMP_CAT_GOLD,
                        Constants_Building.FRACTIONCAMP_CAT_BRICK,
                        Constants_Building.FRACTIONCAMP_CAT_WOOD,
                        Constants_Building.FRACTIONCAMP_CAT_BEER,
                        Constants_Building.FRACTIONCAMP_CAT_ESSENCE,
                        false,
                        new Coordinate(Constants_Building.FRACTIONCAMP_CAT_POSITION_X, Constants_Building.FRACTIONCAMP_CAT_POSITION_Y));
            case Constants_Building.NAME_FRACTIONCAMP_CHICKEN:
                return new FractionCamp(
                        Constants_Building.NAME_FRACTIONCAMP_CHICKEN,
                        Constants_Building.FRACTIONCAMP_CHICKEN_GOLD,
                        Constants_Building.FRACTIONCAMP_CHICKEN_BRICK,
                        Constants_Building.FRACTIONCAMP_CHICKEN_WOOD,
                        Constants_Building.FRACTIONCAMP_CHICKEN_BEER,
                        Constants_Building.FRACTIONCAMP_CHICKEN_ESSENCE,
                        false,
                        new Coordinate(Constants_Building.FRACTIONCAMP_CHICKEN_POSITION_X, Constants_Building.FRACTIONCAMP_CHICKEN_POSITION_Y));
            default:
                throw new IllegalArgumentException(Constants_Building.UNKNOWN_FRACTIONCAMP_TYPE + type);
        }
    }

    // Getter methods for each type of FractionCamp
    public static FractionCamp getDogFractionCamp ()
    {
        return createFractionCamp(Constants_Building.NAME_FRACTIONCAMP_DOG);
    }

    public static FractionCamp getCatFractionCamp () {
        return createFractionCamp(Constants_Building.NAME_FRACTIONCAMP_CAT);
    }

    public static FractionCamp getChickenFractionCamp () {
        return createFractionCamp(Constants_Building.NAME_FRACTIONCAMP_CHICKEN);
    }
}
