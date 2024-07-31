package model.buildings;


import control.BuildingController;
import model.Coordinate;
import model.player.Inventory;
import resources.constants.scenes.Constants_Building;


/**
 * The class Marketplace contains all functional methods from the building Marketplace.
 * The class extends from the abstract class Building.
 *
 * @author Jule Degener
 */
public class Marketplace extends Building
{
    /**
     * Instance of Marketplace as attribute of the class to create a singleton
     *
     * @author Jule Degener
     */
    private static final Marketplace INSTANCE_OF_MARKETPLACE = new Marketplace(Constants_Building.NAME_MARKETPLACE,
            Constants_Building.MARKET_GOLD, Constants_Building.MARKET_BRICK, Constants_Building.MARKET_WOOD,
            Constants_Building.MARKET_BEER, Constants_Building.MARKET_ESSENCE, false,
            new Coordinate(Constants_Building.MARKETPLACE_POSITION_X, Constants_Building.MARKETPLACE_POSITION_Y));


    /**
     * Constructor to create an instance of Marketplace.
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
     * @postcondition An instance of the Marketplace is created.
     */
    private Marketplace (String name, int numberOfGold, int numberOfBrick, int numberOfWood,
                         int numberOfBeer, int numberOfEssence, boolean isUnlocked,
                         Coordinate positionUpperLeft)
    {
        super(name, numberOfGold, numberOfBrick, numberOfWood, numberOfBeer, numberOfEssence, isUnlocked,
                positionUpperLeft);
    }


    /**
     * Method to exchange gold to get beer. The inventory is updated.
     *
     * @author Jule Degener
     * @precondition none
     * @postcondition Resource gold was exchanged.
     */
    public void buyBeerFromGold ()
    {
        int numberOfGold = countCurrentGoldInInventory();

        int numberOfBeerForGold = numberOfGold * Constants_Building.EIGHT;
        System.out.printf(Constants_Building.EXCHANGE_GOLD_FOR, numberOfGold, numberOfBeerForGold,
                Constants_Building.EXCHANGE_BEER);
        updateInventoryGoldAfterBuy(numberOfGold);
        Inventory.getInstanceOfInventory().setInventoryBeer(Inventory.getInstanceOfInventory().getInventoryBeer()
                + numberOfBeerForGold);
    }


    /**
     * Method to exchange gold to get wood. The inventory is updated.
     *
     * @author Jule Degener
     * @precondition none
     * @postcondition Resource gold was exchanged.
     */
    public void buyWoodFromGold ()
    {
        int numberOfGold = countCurrentGoldInInventory();

        int numberOfWoodForGold = numberOfGold * Constants_Building.FOUR;
        System.out.printf(Constants_Building.EXCHANGE_GOLD_FOR, numberOfGold, numberOfWoodForGold,
                Constants_Building.EXCHANGE_WOOD);
        updateInventoryGoldAfterBuy(numberOfGold);
        Inventory.getInstanceOfInventory().setInventoryWood(Inventory.getInstanceOfInventory().getInventoryWood()
                + numberOfWoodForGold);
    }


    /**
     * Method to exchange gold to get brick. The inventory is updated.
     *
     * @author Jule Degener
     * @precondition none
     * @postcondition Resource gold was exchanged.
     */
    public void buyBrickFromGold ()
    {
        int numberOfGold = countCurrentGoldInInventory();

        int numberOfBrickForGold = numberOfGold * Constants_Building.TWO;
        System.out.printf(Constants_Building.EXCHANGE_GOLD_FOR, numberOfGold, numberOfBrickForGold,
                Constants_Building.EXCHANGE_BRICK);
        updateInventoryGoldAfterBuy(numberOfGold);
        Inventory.getInstanceOfInventory().setInventoryBrick(Inventory.getInstanceOfInventory().getInventoryBrick()
                + numberOfBrickForGold);
    }


    /**
     * Method to exchange gold to get esence. The inventory is updated.
     *
     * @author Jule Degener
     * @precondition none
     * @postcondition Resource gold was exchanged.
     */
    public void buyEssenceFromGold ()
    {
        int numberOfGold = countCurrentGoldInInventory();
        int numberOfEssenceForGold = numberOfGold / Constants_Building.TWO;
        System.out.printf(Constants_Building.EXCHANGE_GOLD_FOR, numberOfGold, numberOfEssenceForGold,
                Constants_Building.EXCHANGE_ESSENCE);
        updateInventoryGoldAfterBuy(numberOfGold);
        Inventory.getInstanceOfInventory().setInventoryEssence(Inventory.getInstanceOfInventory().getInventoryEssence()
                + numberOfEssenceForGold);
    }


    /**
     * Method to count the number of golf of the current inventory
     *
     * @author Jule Degener
     * @return The number of Gold of the inventory as Integer is returned.
     * @precondition none
     * @postcondition The number of gold in the inventory is known.
     */
    private static int countCurrentGoldInInventory ()
    {
        return Inventory.getInstanceOfInventory().getInventoryGold();
    }


    /**
     * Method to update the number of gold of the inventory after a buy in the Marketplace.
     *
     * @author Jule Degener
     * @param numberOfGold Number of Gold in the inventory
     * @precondition An exchange has been made.
     * @postcondition Correct number of gold is saved in the inventory.
     */
    private static void updateInventoryGoldAfterBuy (int numberOfGold)
    {
        Inventory.getInstanceOfInventory().setInventoryGold(Inventory.getInstanceOfInventory().getInventoryGold() -
                numberOfGold);
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
        BuildingController.getInstance().checkIfBuildingIsUnlock(getInstanceOfMarketplace());
    }


    /**
     * Getter-method to have access of the instance of the Marketplace
     *
     * @author Jule Degener
     * @return The instance of the Marketplace is returned.
     * @precondition none
     * @postcondition Access of the instance of the Marketplace
     */
    public static Marketplace getInstanceOfMarketplace ()
    {
        return INSTANCE_OF_MARKETPLACE;
    }
}
