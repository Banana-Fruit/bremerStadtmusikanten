package model.buildings;


import control.BuildingController;
import model.Coordinate;
import resources.constants.scenes.Constants_Building;


/**
 * The class Headquarter contains all functional methods from the building Headquarter.
 * The class extends from the abstract class Building.
 *
 * @author Jule Degener
 */
public class Headquarter extends Building
{
    /**
     * Instance of Headquarter as attribute of the class to create a singleton
     *
     * @author Jule Degener
     */
    private static final Headquarter INSTANCE_OF_HEADQUARTER = new Headquarter(Constants_Building.NAME_HEADQUARTER,
            Constants_Building.HEADQUARTER_GOLD, Constants_Building.HEADQUARTER_BRICK,
            Constants_Building.HEADQUARTER_WOOD, Constants_Building.HEADQUARTER_BEER,
            Constants_Building.HEADQUARTER_ESSENCE, true,
            new Coordinate(Constants_Building.HEADQUARTER_POSITION_X, Constants_Building.HEADQUARTER_POSITION_Y));


    /**
     * Constructor to create an instance of Headquarter.
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
     * @postcondition An instance of the Headquarter is created.
     */
    private Headquarter (String name, int numberOfGold, int numberOfBrick, int numberOfWood,
                         int numberOfBeer, int numberOfEssence, boolean isUnlocked,
                         Coordinate positionUpperLeft)
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
        BuildingController.getInstance().checkIfBuildingIsUnlock(getInstanceOfHeadquarter());
    }



    /**
     * Getter-method to have access of the instance of the Headquarter
     *
     * @author Jule Degener
     * @return The instance of the Headquarter is returned.
     * @precondition none
     * @postcondition Access of the instance of the Headquarter
     */
    public static Headquarter getInstanceOfHeadquarter ()
    {
        return INSTANCE_OF_HEADQUARTER;
    }
}
