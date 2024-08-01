package control.game;


import model.buildings.Building;
import model.player.Inventory;
import resources.constants.Constants_ExceptionMessages;


/**
 * The class InventoryController contains all methods to interact with the inventory.
 *
 * @author Jule Degener
 */
public class InventoryController
{
    /**
     * Instance of the InventoryController as attribute to create a singleton
     */
    private static InventoryController instance = null;


    /**
     * Default Controller to create a singleton
     *
     * @author Jule Degener
     * @precondition none
     * @postcondition An instance of the InventoryController is created without any parameters.
     */
    private InventoryController ()
    {

    }


    /**
     * Method to initialize the InventoryController and to create one instance.
     *
     * @author Michael Markov
     * @precondition none
     * @postcondition One instance of InventoryController exist in the program.
     */
    public static synchronized void initialize ()
    {
        if (instance == null)
        {
            instance = new InventoryController();
        } else
        {
            throw new IllegalStateException(Constants_ExceptionMessages.ALREADY_INITIALIZED);
        }
    }


    /**
     * Method to reward the player with resources.
     *
     * @author Jule Degener
     * @param gold Resource gold
     * @param brick Resource brick
     * @param wood Resource wood
     * @param beer Resource beer
     * @param essence Resource essence
     * @precondition none
     * @postcondition Reward is added to the current inventory.
     */
    public static void getReward (int gold, int brick, int wood, int beer, int essence)
    {
        Inventory.getInstanceOfInventory().setInventoryGold(Inventory.getInstanceOfInventory().getInventoryGold()
                + gold);
        Inventory.getInstanceOfInventory().setInventoryBrick(Inventory.getInstanceOfInventory().getInventoryBeer()
                + brick);
        Inventory.getInstanceOfInventory().setInventoryWood(Inventory.getInstanceOfInventory().getInventoryBeer()
                + wood);
        Inventory.getInstanceOfInventory().setInventoryBeer(Inventory.getInstanceOfInventory().getInventoryBeer()
                + beer);
        Inventory.getInstanceOfInventory().setInventoryEssence(Inventory.getInstanceOfInventory().getInventoryEssence()
                + essence);
    }


    /**
     * Method to pay the price of a building with resources of the inventory.
     *
     * @author Jule Degener
     * @param building Building for which payment is made.
     * @precondition none
     * @postcondition Price of the building is subtracted from the inventory.
     */
    public static void payWithTheInventoryForABuilding (Building building)
    {
        Inventory.getInstanceOfInventory().setInventoryGold(Inventory.getInstanceOfInventory().getInventoryGold()
                - building.numberOfGold);
        Inventory.getInstanceOfInventory().setInventoryBrick(Inventory.getInstanceOfInventory().getInventoryBeer()
                - building.numberOfBrick);
        Inventory.getInstanceOfInventory().setInventoryWood(Inventory.getInstanceOfInventory().getInventoryBeer()
                - building.numberOfWood);
        Inventory.getInstanceOfInventory().setInventoryBeer(Inventory.getInstanceOfInventory().getInventoryBeer()
                - building.numberOfBeer);
        Inventory.getInstanceOfInventory().setInventoryEssence(Inventory.getInstanceOfInventory().getInventoryEssence()
                - building.numberOfEssence);
    }


    /**
     * Getter-method to get the instance of the InventoryController
     *
     * @author Michael Markov, Jule Degener
     * @return Instance of the InventoryController
     * @precondition none
     * @postcondition One instance of InventoryController exist in the program.
     */
    public static InventoryController getInstance()
    {
        if (instance == null)
        {
            throw new IllegalStateException(Constants_ExceptionMessages.SINGLETON_NOT_INITIALIZED);
        }
        return instance;
    }
}
