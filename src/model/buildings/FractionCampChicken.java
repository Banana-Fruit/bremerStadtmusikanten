package model.buildings;


import control.BuildingController;
import model.Coordinate;
import resources.constants.scenes.Constants_Building;


/**
 * The class FractionCampChicken contains all functional methods from the building FractionCampChicken.
 * The class extends from the abstract class Building.
 *
 * @author Jule Degener
 */
public class FractionCampChicken extends Building
{
    /**
     * Instance of FractionCampChicken as attribute of the class to create a singleton
     *
     * @author Jule Degener
     */
    private static final FractionCampChicken INSTANCE_OF_FRACTION_CHICKEN_CAMP = new FractionCampChicken(
            Constants_Building.NAME_FRACTIONCAMP_CHICKEN, Constants_Building.FRACTIONCAMP_CHICKEN_GOLD,
            Constants_Building.FRACTIONCAMP_CHICKEN_BRICK, Constants_Building.FRACTIONCAMP_CHICKEN_WOOD,
            Constants_Building.FRACTIONCAMP_CHICKEN_BEER, Constants_Building.FRACTIONCAMP_CHICKEN_ESSENCE,
            false, new Coordinate(Constants_Building.FRACTIONCAMP_CHICKEN_POSITION_X,
            Constants_Building.FRACTIONCAMP_CHICKEN_POSITION_Y));


    /**
     * Constructor to create an instance of FractionCampChicken.
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
     * @postcondition An instance of the FractionCampChicken is created.
     */
    private FractionCampChicken (String name, int numberOfGold, int numberOfBrick, int numberOfWood, int numberOfBeer,
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
        BuildingController.getInstance().checkIfBuildingIsUnlock(getInstanceOfFractionChickenCamp());
    }


    /**
     * Getter-method to have access of the instance of the FractionCampChicken
     *
     * @author Jule Degener
     * @return The instance of the FractionCampChicken is returned.
     * @precondition none
     * @postcondition Access of the instance of the FractionCampChicken
     */
    public static FractionCampChicken getInstanceOfFractionChickenCamp ()
    {
        return INSTANCE_OF_FRACTION_CHICKEN_CAMP;
    }
}
