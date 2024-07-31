package model.buildings;


import control.BuildingController;
import model.Coordinate;
import model.player.Inventory;
import resources.constants.scenes.Constants_Building;

import java.util.Objects;


public class Marketplace extends Building
{
    private static final Marketplace INSTANCE_OF_MARKETPLACE = new Marketplace(Constants_Building.NAME_MARKETPLACE,
            Constants_Building.MARKET_GOLD, Constants_Building.MARKET_BRICK, Constants_Building.MARKET_WOOD,
            Constants_Building.MARKET_BEER, Constants_Building.MARKET_ESSENCE, false, new Coordinate(Constants_Building.MARKETPLACE_POSITION_X, Constants_Building.MARKETPLACE_POSITION_Y));
    
    
    // constructor
    private Marketplace (String name, int numberOfGold, int numberOfBrick, int numberOfWood,
                         int numberOfBeer, int numberOfEssence, boolean isUnlocked,
                         Coordinate positionUpperLeft)
    {
        super(name, numberOfGold, numberOfBrick, numberOfWood, numberOfBeer, numberOfEssence, isUnlocked, positionUpperLeft);
    }
    
    
    // Exchange course:
    // 1 wood = 2 beers
    // 1 brick = 2 woods
    // 1 gold = 2 bricks
    // 1 essence = 2 gold


    public void buyBeerFromGold ()
    {
        int numberOfGold = countCurrentGoldInInventory();

        int numberOfBeerForGold = numberOfGold * Constants_Building.EIGHT;
        System.out.printf(Constants_Building.EXCHANGE_GOLD_FOR, numberOfGold, numberOfBeerForGold, Constants_Building.EXCHANGE_BEER);
        updateInventoryGoldAfterBuy(numberOfGold);
        Inventory.getInstanceOfInventory().setInventoryBeer(Inventory.getInstanceOfInventory().getInventoryBeer() + numberOfBeerForGold);
    }


    public void buyWoodFromGold ()
    {
        int numberOfGold = countCurrentGoldInInventory();

        int numberOfWoodForGold = numberOfGold * Constants_Building.FOUR;
        System.out.printf(Constants_Building.EXCHANGE_GOLD_FOR, numberOfGold, numberOfWoodForGold, Constants_Building.EXCHANGE_WOOD);
        updateInventoryGoldAfterBuy(numberOfGold);
        Inventory.getInstanceOfInventory().setInventoryWood(Inventory.getInstanceOfInventory().getInventoryWood() + numberOfWoodForGold);
    }


    public void buyBrickFromGold ()
    {
        int numberOfGold = countCurrentGoldInInventory();

        int numberOfBrickForGold = numberOfGold * Constants_Building.TWO;
        System.out.printf(Constants_Building.EXCHANGE_GOLD_FOR, numberOfGold, numberOfBrickForGold, Constants_Building.EXCHANGE_BRICK);
        updateInventoryGoldAfterBuy(numberOfGold);
        Inventory.getInstanceOfInventory().setInventoryBrick(Inventory.getInstanceOfInventory().getInventoryBrick() + numberOfBrickForGold);
    }


    public void buyEssenceFromGold ()
    {
        int numberOfGold = countCurrentGoldInInventory();
        int numberOfEssenceForGold = numberOfGold / Constants_Building.TWO;
        System.out.printf(Constants_Building.EXCHANGE_GOLD_FOR, numberOfGold, numberOfEssenceForGold, Constants_Building.EXCHANGE_ESSENCE);
        updateInventoryGoldAfterBuy(numberOfGold);
        Inventory.getInstanceOfInventory().setInventoryEssence(Inventory.getInstanceOfInventory().getInventoryEssence() + numberOfEssenceForGold);
    }


    private static int countCurrentGoldInInventory ()
    {
        return Inventory.getInstanceOfInventory().getInventoryGold();
    }


    private static void updateInventoryGoldAfterBuy (int numberOfGold)
    {
        Inventory.getInstanceOfInventory().setInventoryGold(Inventory.getInstanceOfInventory().getInventoryGold() - numberOfGold);
    }
    
    
    @Override
    public void unlockBuilding ()
    {
        BuildingController.checkIfBuildingIsUnlock(getInstanceOfMarketplace());
    }
    
    
    public static Marketplace getInstanceOfMarketplace ()
    {
        return INSTANCE_OF_MARKETPLACE;
    }
}
