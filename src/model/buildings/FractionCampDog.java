package model.buildings;


import control.BuildingController;
import model.Coordinate;
import resources.constants.scenes.Constants_Building;


/**
 * The class FractionCampDog contains all functional methods from the building FractionCampDog.
 * The class extends from the abstract class Building.
 *
 * @author Jule Degener
 */
public class FractionCampDog extends Building
{
    /**
     * Instance of FractionCampCDog as attribute of the class to create a singleton
     *
     * @author Jule Degener
     */
    private static final FractionCampDog INSTANCE_OF_FRACTION_DOG_CAMP = new FractionCampDog(
            Constants_Building.NAME_FRACTIONCAMP_DOG, Constants_Building.FRACTIONCAMP_DOG_GOLD,
            Constants_Building.FRACTIONCAMP_DOG_BRICK, Constants_Building.FRACTIONCAMP_DOG_WOOD,
            Constants_Building.FRACTIONCAMP_DOG_BEER, Constants_Building.FRACTIONCAMP_DOG_ESSENCE, false,
            new Coordinate(Constants_Building.FRACTIONCAMP_DOG_POSITION_X,
                    Constants_Building.FRACTIONCAMP_DOG_POSITION_Y));


    /**
     * Constructor to create an instance of FractionCampDog.
     *
     * @author Jule Degener, Jonas Helfer
     * @param name Name of the building
     * @param numberOfGold gold-price of the building
     * @param numberOfBrick brick-price of the building
     * @param numberOfWood wood-price of the building
     * @param numberOfBeer beer-price of the building
     * @param numberOfEssence essence-price of the building
     * @param isUnlocked boolean-value to see if a building is unlocked
     * @param positionUpperLeft Coordinate from the building
     * @precondition none
     * @postcondition An instance of the FractionCampDog is created.
     */
    private FractionCampDog (String name, int numberOfGold, int numberOfBrick, int numberOfWood, int numberOfBeer,
                             int numberOfEssence, boolean isUnlocked, Coordinate positionUpperLeft)
    {
        super(name, numberOfGold, numberOfBrick, numberOfWood, numberOfBeer, numberOfEssence, isUnlocked,
                positionUpperLeft);
    }


    /**
     * Override method from the abstract class Building to unlock a building
     *
     * @author Jule Degener
     * @precondition An instance of BuildingController must exist.
     * @postcondition Status is known if a building is unlocked.
     */
    @Override
    public void unlockBuilding ()
    {
        BuildingController.getInstance().checkIfBuildingIsUnlock(getInstanceOfFractionDogCamp());
    }


    /**
     * Getter-method to have access of the instance of the FractionCampDog
     *
     * @author Jule Degener
     * @return The instance of the FractionCampDog is returned.
     * @precondition none
     * @postcondition Access of the instance of the FractionCampDog
     */
    public static FractionCampDog getInstanceOfFractionDogCamp ()
    {
        return INSTANCE_OF_FRACTION_DOG_CAMP;
    }
}
