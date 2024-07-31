package model.buildings;


import control.BuildingController;
import control.game.PlayerController;
import control.game.UnitController;
import model.Coordinate;
import model.player.Inventory;
import model.Unit;
import resources.constants.Constants_Player_Units;
import resources.constants.scenes.Constants_Building;
import resources.constants.Constants_Combat;

import java.util.List;


/**
 * The class Pub contains all functional methods from the building Pub.
 * The class extends from the abstract class Building.
 *
 * @author Jule Degener
 */
public class Pub extends Building
{
    /**
     * Instance of Pub as attribute of the class to create a singleton
     *
     * @author Jule Degener
     */
    private static final Pub INSTANCE_OF_PUB = new Pub(Constants_Building.NAME_PUB, Constants_Building.PUB_GOLD,
            Constants_Building.PUB_BRICK, Constants_Building.PUB_WOOD, Constants_Building.PUB_BEER,
            Constants_Building.PUB_ESSENCE, false, new Coordinate(Constants_Building.PUB_POSITION_X,
            Constants_Building.PUB_POSITION_Y));


    /**
     * Constructor to create an instance of Pub.
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
     * @postcondition An instance of the Pub is created.
     */
    private Pub (String name, int numberOfGold, int numberOfBrick, int numberOfWood,
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
        BuildingController.getInstance().checkIfBuildingIsUnlock(getInstanceOfPub());
    }


    /**
     * Method to recruit a mercenary by a price of one beer.
     *
     * @author Jule Degener
     * @precondition An instance of the PlayerController must exist.
     * @postcondition One mercenary is added to the team from the player.
     */
    public void recruitAMercenary ()
    {
        UnitController unitController = UnitController.getInstance();
        List<Unit> units = unitController.createUnit();
        Unit mercenary = units.get(Constants_Combat.MERCENARY);
        
        // pay for the mercenary to become a member in the team
        payForTheMercenary();
        
        // add the mercenary in the team
        PlayerController.getInstance().addUnitToTheTeam(mercenary);
    }


    /**
     * Method to pay the price for a mercenary.
     *
     * @author Jule Degener
     * @precondition none
     * @postcondition The price of a mercenary is subtracted from the inventory.
     */
    private static void payForTheMercenary ()
    {
        Inventory inventory = Inventory.getInstanceOfInventory();

        if (inventory.getInventoryBeer() >= Constants_Building.PRICE_MERCENARY)
        {
            inventory.setInventoryBeer(inventory.getInventoryBeer() - Constants_Player_Units.COST_MERCENARY);
        }
        else
        {
            System.out.println(Constants_Building.ERROR_PRICE_MERCENARY);
        }
    }


    /**
     * Getter-method to have access of the instance of the Pub
     *
     * @author Jule Degener
     * @return The instance of the Pub is returned.
     * @precondition none
     * @postcondition Access of the instance of the Pub
     */
    public static Pub getInstanceOfPub ()
    {
        return INSTANCE_OF_PUB;
    }
}
