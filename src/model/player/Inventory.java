package model.player;


import model.buildings.Building;


public class Inventory
{
    private static Inventory instanceOfInventory = new Inventory();
    
    
    // attributes
    private int inventoryGold;
    private int inventoryBrick;
    private int inventoryWood;
    private int inventoryBeer;
    private int inventoryEssence;
    
    
    // constructor
    private Inventory (int inventoryGold, int inventoryBrick, int inventoryWood, int inventoryBeer, int inventoryEssence)
    {
        this.inventoryGold = inventoryGold;
        this.inventoryBrick = inventoryBrick;
        this.inventoryWood = inventoryWood;
        this.inventoryBeer = inventoryBeer;
        this.inventoryEssence = inventoryEssence;
    }
    
    
    // default constructor for the Singleton
    private Inventory ()
    {
        ;
    }
    
    
    public static void getReward (int gold, int brick, int wood, int beer, int essence)
    {
        instanceOfInventory.setInventoryGold(instanceOfInventory.getInventoryGold() + gold);
        instanceOfInventory.setInventoryBrick(instanceOfInventory.getInventoryBeer() + brick);
        instanceOfInventory.setInventoryWood(instanceOfInventory.getInventoryBeer() + wood);
        instanceOfInventory.setInventoryBeer(instanceOfInventory.getInventoryBeer() + beer);
        instanceOfInventory.setInventoryEssence(instanceOfInventory.getInventoryEssence() + essence);
    }
    
    
    public static void payWithTheInventoryForABuilding (Building building)
    {
        instanceOfInventory.setInventoryGold(instanceOfInventory.getInventoryGold() - building.numberOfGold);
        instanceOfInventory.setInventoryBrick(instanceOfInventory.getInventoryBeer() - building.numberOfBrick);
        instanceOfInventory.setInventoryWood(instanceOfInventory.getInventoryBeer() - building.numberOfWood);
        instanceOfInventory.setInventoryBeer(instanceOfInventory.getInventoryBeer() - building.numberOfBeer);
        instanceOfInventory.setInventoryEssence(instanceOfInventory.getInventoryEssence() - building.numberOfEssence);
    }
    
    
    // Getter methods
    public static Inventory getInstanceOfInventory ()
    {
        return instanceOfInventory;
    }
    
    
    public int getInventoryGold ()
    {
        return inventoryGold;
    }
    
    
    public int getInventoryBrick ()
    {
        return inventoryBrick;
    }
    
    
    public int getInventoryWood ()
    {
        return inventoryWood;
    }
    
    
    public int getInventoryBeer ()
    {
        return inventoryBeer;
    }
    
    
    public int getInventoryEssence ()
    {
        return inventoryEssence;
    }
    
    
    // setter methods
    public void setInventoryGold (int inventoryGold)
    {
        this.inventoryGold = inventoryGold;
    }
    
    
    public void setInventoryBrick (int inventoryBrick)
    {
        this.inventoryBrick = inventoryBrick;
    }
    
    
    public void setInventoryWood (int inventoryWood)
    {
        this.inventoryWood = inventoryWood;
    }
    
    
    public void setInventoryBeer (int inventoryBeer)
    {
        this.inventoryBeer = inventoryBeer;
    }
    
    
    public void setInventoryEssence (int inventoryEssence)
    {
        this.inventoryEssence = inventoryEssence;
    }
}
