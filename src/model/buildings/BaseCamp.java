package model.buildings;


import control.BuildingController;
import model.Coordinate;
import resources.constants.scenes.Constants_Building;


/**
 * The class BaseCamp contains all functional methods from the building BaseCamp.
 * The class extends from the abstract class Building.
 *
 * @author Jule Degener
 */
public class BaseCamp extends Building
{
    /**
     * Instance of BaseCamp as attribute of the class to create a singleton
     *
     * @author Jule Degener
     */
    private static final BaseCamp INSTANCE_OF_BASECAMP = new BaseCamp(Constants_Building.NAME_BASECAMP_NAME,
            Constants_Building.BASECAMP_GOLD, Constants_Building.BASECAMP_BRICK, Constants_Building.BASECAMP_WOOD,
            Constants_Building.BASECAMP_BEER, Constants_Building.BASECAMP_ESSENCE, false,
            new Coordinate(Constants_Building.BASECAMP_POSITION_X, Constants_Building.BASECAMP_POSITION_Y));


    /**
     * Constructor to create an instance of BaseCamp.
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
     * @postcondition An instance of the BaseCamp is created.
     */
    private BaseCamp (String name, int numberOfGold, int numberOfBrick, int numberOfWood,
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
        BuildingController.getInstance().checkIfBuildingIsUnlock(getInstanceOfBasecamp());
    }


    /**
     * Getter-method to have access of the instance of the BasisCamp
     *
     * @author Jule Degener
     * @return The instance of the BasisCamp is returned.
     * @precondition none
     * @postcondition Access of the instance of the BasisCamp
     */
    public static BaseCamp getInstanceOfBasecamp ()
    {
        return INSTANCE_OF_BASECAMP;
    }
}
