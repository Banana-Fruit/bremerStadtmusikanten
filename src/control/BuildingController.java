package control;


public class BuildingController
{
    private static final BuildingController INSTANCE_OF_BUILDING_CONTROLLER = new BuildingController();


    // default controller
    private BuildingController ()
    {
        ;
    }


    public static boolean checkIfBuildingIsUnlock (Building building)
    {
        Inventory inventory = Inventory.getInstanceOfInventory();

        if ((inventory.getInventoryGold()>= building.numberOfGold) &&
                (inventory.getInventoryBrick() >= building.numberOfBrick) &&
                (inventory.getInventoryWood() >= building.numberOfWood) &&
                (inventory.getInventoryBeer() >= building.numberOfBeer) &&
                (inventory.getInventoryEssence() >= building.numberOfEssence))
        {
            System.out.println(Constants_Building.BUILDING_UNLOCKED);
            Inventory.payWithTheInventoryForABuilding(building);
            return true;
        }
        else
        {
            System.out.println(Constants_Building.BUILDING_LOCKED);
            return false;
        }
    }


    // getter method
    public BuildingController getInstanceOfBuildingController ()
    {
        return INSTANCE_OF_BUILDING_CONTROLLER;
    }

}
