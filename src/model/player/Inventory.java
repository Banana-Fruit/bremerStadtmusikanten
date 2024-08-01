package model.player;


import model.buildings.Building;


/**
 * The class Inventory contains alle method to create an instance of an inventory for the player.
 * Tt contains all resources from the game.
 *
 * @author Jule Degener
 */
public class Inventory
{
    /**
     * Instance of Inventory as attribute of the class to create a singleton
     *
     * @author Jule Degener
     */
    private static Inventory instanceOfInventory = new Inventory();
    
    
    // attributes
    private int inventoryGold;
    private int inventoryBrick;
    private int inventoryWood;
    private int inventoryBeer;
    private int inventoryEssence;



    /**
     * Default constructor
     *
     * @author Jule Degener
     * @precondition none
     * @postcondition Instance of Inventory is created without any parameters.
     */
    private Inventory ()
    {
        ;
    }



    /**
     * Getter-method to access of the instance of Inventory.
     *
     * @author Jule Degener
     * @return The instance of Inventory is returned.
     * @precondition none
     * @postcondition Access of the instance of Inventory.
     */
    public static Inventory getInstanceOfInventory ()
    {
        return instanceOfInventory;
    }


    /**
     * Getter-method to access of the attribute gold.
     *
     * @author Jule Degener
     * @return The attribute gold is returned.
     * @precondition none
     * @postcondition Access of the attribute gold.
     */
    public int getInventoryGold ()
    {
        return inventoryGold;
    }


    /**
     * Getter-method to access of the attribute brick.
     *
     * @author Jule Degener
     * @return The attribute brick is returned.
     * @precondition none
     * @postcondition Access of the attribute brick.
     */
    public int getInventoryBrick ()
    {
        return inventoryBrick;
    }


    /**
     * Getter-method to access of the attribute wood.
     *
     * @author Jule Degener
     * @return The attribute wood is returned.
     * @precondition none
     * @postcondition Access of the attribute wood.
     */
    public int getInventoryWood ()
    {
        return inventoryWood;
    }


    /**
     * Getter-method to access of the attribute beer.
     *
     * @author Jule Degener
     * @return The attribute beer is returned.
     * @precondition none
     * @postcondition Access of the attribute beer.
     */
    public int getInventoryBeer ()
    {
        return inventoryBeer;
    }


    /**
     * Getter-method to access of the attribute essence.
     *
     * @author Jule Degener
     * @return The attribute essence is returned.
     * @precondition none
     * @postcondition Access of the attribute essence.
     */
    public int getInventoryEssence ()
    {
        return inventoryEssence;
    }


    /**
     * Setter-method to set the value of the attribute gold.
     *
     * @author Jule Degener
     * @param inventoryGold new Integer value of the attribute gold.
     * @precondition none
     * @postcondition Parameter bonusHealth is the current value of the attribute gold.
     */
    public void setInventoryGold (int inventoryGold)
    {
        this.inventoryGold = inventoryGold;
    }


    /**
     * Setter-method to set the value of the attribute brick.
     *
     * @author Jule Degener
     * @param inventoryBrick new Integer value of the attribute brick.
     * @precondition none
     * @postcondition Parameter bonusHealth is the current value of the attribute brick.
     */
    public void setInventoryBrick (int inventoryBrick)
    {
        this.inventoryBrick = inventoryBrick;
    }


    /**
     * Setter-method to set the value of the attribute wood.
     *
     * @author Jule Degener
     * @param inventoryWood new Integer value of the attribute wood.
     * @precondition none
     * @postcondition Parameter bonusHealth is the current value of the attribute wood.
     */
    public void setInventoryWood (int inventoryWood)
    {
        this.inventoryWood = inventoryWood;
    }


    /**
     * Setter-method to set the value of the attribute beer.
     *
     * @author Jule Degener
     * @param inventoryBeer new Integer value of the attribute beer.
     * @precondition none
     * @postcondition Parameter bonusHealth is the current value of the attribute beer.
     */
    public void setInventoryBeer (int inventoryBeer)
    {
        this.inventoryBeer = inventoryBeer;
    }


    /**
     * Setter-method to set the value of the attribute essence.
     *
     * @author Jule Degener
     * @param inventoryEssence new Integer value of the attribute essence.
     * @precondition none
     * @postcondition Parameter bonusHealth is the current value of the attribute essence.
     */
    public void setInventoryEssence (int inventoryEssence)
    {
        this.inventoryEssence = inventoryEssence;
    }
}
