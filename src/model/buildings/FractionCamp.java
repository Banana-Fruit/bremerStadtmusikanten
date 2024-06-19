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
            case "Hund":
                return new FractionCamp(
                        Constants_Building.NAME_FRACTIONCAMP,
                        Constants_Building.FRACTIONCAMP_GOLD,
                        Constants_Building.FRACTIONCAMP_BRICK,
                        Constants_Building.FRACTIONCAMP_WOOD,
                        Constants_Building.FRACTIONCAMP_BEER,
                        Constants_Building.FRACTIONCAMP_ESSENCE,
                        false,
                        new Coordinate(32.0, 2.0));
            case "Katze":
                return new FractionCamp(
                        "Type2Name",
                        200, // gold
                        150, // brick
                        100, // wood
                        50,  // beer
                        25,  // essence
                        false,
                        new Coordinate(39.0, 18.0));
            case "Huhn":
                return new FractionCamp(
                        "Type3Name",
                        300, // gold
                        200, // brick
                        150, // wood
                        75,  // beer
                        30,  // essence
                        false,
                        new Coordinate(14.0, 30.0));
            default:
                throw new IllegalArgumentException("Unknown FractionCamp type: " + type);
        }
    }

    // Getter methods for each type of FractionCamp
    public static FractionCamp getDogFractionCamp () {
        return createFractionCamp("Hund");
    }

    public static FractionCamp getCatFractionCamp () {
        return createFractionCamp("Katze");
    }

    public static FractionCamp getChickenFractionCamp () {
        return createFractionCamp("Huhn");
    }
}
