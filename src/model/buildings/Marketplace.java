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
    
    
    public static void exchangeBeer (int numberOfBeer, String resource)
    {
        int numberOfResourceForBeer;
        
        if (Objects.equals(resource, Constants_Building.EXCHANGE_WOOD))
        {
            numberOfResourceForBeer = numberOfBeer / Constants_Building.TWO;
            System.out.printf(Constants_Building.EXCHANGE_BEER_FOR, numberOfBeer, numberOfResourceForBeer, Constants_Building.EXCHANGE_WOOD);
            Inventory.getInstanceOfInventory().setInventoryBeer(Inventory.getInstanceOfInventory().getInventoryBeer() - numberOfBeer);
            Inventory.getInstanceOfInventory().setInventoryWood(Inventory.getInstanceOfInventory().getInventoryWood() + numberOfResourceForBeer);
        } else if (Objects.equals(resource, Constants_Building.EXCHANGE_BRICK))
        {
            numberOfResourceForBeer = numberOfBeer / Constants_Building.FOUR;
            System.out.printf(Constants_Building.EXCHANGE_BEER_FOR, numberOfBeer, numberOfResourceForBeer, Constants_Building.EXCHANGE_BRICK);
            Inventory.getInstanceOfInventory().setInventoryBeer(Inventory.getInstanceOfInventory().getInventoryBeer() - numberOfBeer);
            Inventory.getInstanceOfInventory().setInventoryBrick(Inventory.getInstanceOfInventory().getInventoryBrick() + numberOfResourceForBeer);
        } else if (Objects.equals(resource, Constants_Building.EXCHANGE_GOLD))
        {
            numberOfResourceForBeer = numberOfBeer / Constants_Building.EIGHT;
            System.out.printf(Constants_Building.EXCHANGE_BEER_FOR, numberOfBeer, numberOfResourceForBeer, Constants_Building.EXCHANGE_GOLD);
            Inventory.getInstanceOfInventory().setInventoryBeer(Inventory.getInstanceOfInventory().getInventoryBeer() - numberOfBeer);
            Inventory.getInstanceOfInventory().setInventoryGold(Inventory.getInstanceOfInventory().getInventoryGold() + numberOfResourceForBeer);
        } else if (Objects.equals(resource, Constants_Building.EXCHANGE_ESSENCE))
        {
            numberOfResourceForBeer = numberOfBeer / Constants_Building.SIXTEEN;
            System.out.printf(Constants_Building.EXCHANGE_BEER_FOR, numberOfBeer, numberOfResourceForBeer, Constants_Building.EXCHANGE_ESSENCE);
            Inventory.getInstanceOfInventory().setInventoryBeer(Inventory.getInstanceOfInventory().getInventoryBeer() - numberOfBeer);
            Inventory.getInstanceOfInventory().setInventoryEssence(Inventory.getInstanceOfInventory().getInventoryEssence() + numberOfResourceForBeer);
        } else
        {
            System.out.println(Constants_Building.INVALID_INPUT);
        }
    }
    
    
    public static void exchangeWood (int numberOfWood, String resource)
    {
        int numberOfResourceForWood;
        
        if (Objects.equals(resource, Constants_Building.EXCHANGE_BEER))
        {
            numberOfResourceForWood = numberOfWood * Constants_Building.TWO;
            System.out.printf(Constants_Building.EXCHANGE_WOOD_FOR, numberOfWood, numberOfResourceForWood, Constants_Building.EXCHANGE_BEER);
            Inventory.getInstanceOfInventory().setInventoryWood(Inventory.getInstanceOfInventory().getInventoryWood() - numberOfWood);
            Inventory.getInstanceOfInventory().setInventoryBeer(Inventory.getInstanceOfInventory().getInventoryBeer() + numberOfResourceForWood);
        } else if (Objects.equals(resource, Constants_Building.EXCHANGE_BRICK))
        {
            numberOfResourceForWood = numberOfWood / Constants_Building.TWO;
            System.out.printf(Constants_Building.EXCHANGE_WOOD_FOR, numberOfWood, numberOfResourceForWood, Constants_Building.EXCHANGE_BRICK);
            Inventory.getInstanceOfInventory().setInventoryWood(Inventory.getInstanceOfInventory().getInventoryWood() - numberOfWood);
            Inventory.getInstanceOfInventory().setInventoryBrick(Inventory.getInstanceOfInventory().getInventoryBrick() + numberOfResourceForWood);
        } else if (Objects.equals(resource, Constants_Building.EXCHANGE_GOLD))
        {
            numberOfResourceForWood = numberOfWood / Constants_Building.FOUR;
            System.out.printf(Constants_Building.EXCHANGE_WOOD_FOR, numberOfWood, numberOfResourceForWood, Constants_Building.EXCHANGE_GOLD);
            Inventory.getInstanceOfInventory().setInventoryWood(Inventory.getInstanceOfInventory().getInventoryWood() - numberOfWood);
            Inventory.getInstanceOfInventory().setInventoryGold(Inventory.getInstanceOfInventory().getInventoryGold() + numberOfResourceForWood);
        } else if (Objects.equals(resource, Constants_Building.EXCHANGE_ESSENCE))
        {
            numberOfResourceForWood = numberOfWood / Constants_Building.EIGHT;
            System.out.printf(Constants_Building.EXCHANGE_WOOD_FOR, numberOfWood, numberOfResourceForWood, Constants_Building.EXCHANGE_ESSENCE);
            Inventory.getInstanceOfInventory().setInventoryWood(Inventory.getInstanceOfInventory().getInventoryWood() - numberOfWood);
            Inventory.getInstanceOfInventory().setInventoryEssence(Inventory.getInstanceOfInventory().getInventoryEssence() + numberOfResourceForWood);
        } else
        {
            System.out.println(Constants_Building.INVALID_INPUT);
        }
    }
    
    
    public static void exchangeBrick (int numberOfBrick, String resource)
    {
        int numberOfResourceForBrick;
        
        if (Objects.equals(resource, Constants_Building.EXCHANGE_BEER))
        {
            numberOfResourceForBrick = numberOfBrick * Constants_Building.FOUR;
            System.out.printf(Constants_Building.EXCHANGE_BRICK_FOR, numberOfBrick, numberOfResourceForBrick, Constants_Building.EXCHANGE_BEER);
            Inventory.getInstanceOfInventory().setInventoryBrick(Inventory.getInstanceOfInventory().getInventoryBrick() - numberOfBrick);
            Inventory.getInstanceOfInventory().setInventoryBeer(Inventory.getInstanceOfInventory().getInventoryBeer() + numberOfResourceForBrick);
        } else if (Objects.equals(resource, Constants_Building.EXCHANGE_WOOD))
        {
            numberOfResourceForBrick = numberOfBrick * Constants_Building.TWO;
            System.out.printf(Constants_Building.EXCHANGE_BRICK_FOR, numberOfBrick, numberOfResourceForBrick, Constants_Building.EXCHANGE_WOOD);
            Inventory.getInstanceOfInventory().setInventoryBrick(Inventory.getInstanceOfInventory().getInventoryBrick() - numberOfBrick);
            Inventory.getInstanceOfInventory().setInventoryWood(Inventory.getInstanceOfInventory().getInventoryWood() + numberOfResourceForBrick);
        } else if (Objects.equals(resource, Constants_Building.EXCHANGE_GOLD))
        {
            numberOfResourceForBrick = numberOfBrick / Constants_Building.TWO;
            System.out.printf(Constants_Building.EXCHANGE_BRICK_FOR, numberOfBrick, numberOfResourceForBrick, Constants_Building.EXCHANGE_GOLD);
            Inventory.getInstanceOfInventory().setInventoryBrick(Inventory.getInstanceOfInventory().getInventoryBrick() - numberOfBrick);
            Inventory.getInstanceOfInventory().setInventoryGold(Inventory.getInstanceOfInventory().getInventoryGold() + numberOfResourceForBrick);
        } else if (Objects.equals(resource, Constants_Building.EXCHANGE_ESSENCE))
        {
            numberOfResourceForBrick = numberOfBrick / Constants_Building.FOUR;
            System.out.printf(Constants_Building.EXCHANGE_BRICK_FOR, numberOfBrick, numberOfResourceForBrick, Constants_Building.EXCHANGE_ESSENCE);
            Inventory.getInstanceOfInventory().setInventoryBrick(Inventory.getInstanceOfInventory().getInventoryBrick() - numberOfBrick);
            Inventory.getInstanceOfInventory().setInventoryEssence(Inventory.getInstanceOfInventory().getInventoryEssence() + numberOfResourceForBrick);
        } else
        {
            System.out.println(Constants_Building.INVALID_INPUT);
        }
    }
    
    
    public static void exchangeGold (int numberOfGold, String resource)
    {
        int numberOfResourceForGold;
        
        if (Objects.equals(resource, Constants_Building.EXCHANGE_BEER))
        
        {
            numberOfResourceForGold = numberOfGold * Constants_Building.EIGHT;
            System.out.printf(Constants_Building.EXCHANGE_GOLD_FOR, numberOfGold, numberOfResourceForGold, Constants_Building.EXCHANGE_BEER);
            Inventory.getInstanceOfInventory().setInventoryGold(Inventory.getInstanceOfInventory().getInventoryGold() - numberOfGold);
            Inventory.getInstanceOfInventory().setInventoryBeer(Inventory.getInstanceOfInventory().getInventoryBeer() + numberOfResourceForGold);
        } else if (Objects.equals(resource, Constants_Building.EXCHANGE_WOOD))
        {
            numberOfResourceForGold = numberOfGold * Constants_Building.FOUR;
            System.out.printf(Constants_Building.EXCHANGE_GOLD_FOR, numberOfGold, numberOfResourceForGold, Constants_Building.EXCHANGE_WOOD);
            Inventory.getInstanceOfInventory().setInventoryGold(Inventory.getInstanceOfInventory().getInventoryGold() - numberOfGold);
            Inventory.getInstanceOfInventory().setInventoryWood(Inventory.getInstanceOfInventory().getInventoryWood() + numberOfResourceForGold);
        } else if (Objects.equals(resource, Constants_Building.EXCHANGE_BRICK))
        {
            numberOfResourceForGold = numberOfGold * Constants_Building.TWO;
            System.out.printf(Constants_Building.EXCHANGE_GOLD_FOR, numberOfGold, numberOfResourceForGold, Constants_Building.EXCHANGE_BRICK);
            Inventory.getInstanceOfInventory().setInventoryGold(Inventory.getInstanceOfInventory().getInventoryGold() - numberOfGold);
            Inventory.getInstanceOfInventory().setInventoryBrick(Inventory.getInstanceOfInventory().getInventoryBrick() + numberOfResourceForGold);
        } else if (Objects.equals(resource, Constants_Building.EXCHANGE_ESSENCE))
        {
            numberOfResourceForGold = numberOfGold / Constants_Building.TWO;
            System.out.printf(Constants_Building.EXCHANGE_GOLD_FOR, numberOfGold, numberOfResourceForGold, Constants_Building.EXCHANGE_ESSENCE);
            Inventory.getInstanceOfInventory().setInventoryGold(Inventory.getInstanceOfInventory().getInventoryGold() - numberOfGold);
            Inventory.getInstanceOfInventory().setInventoryEssence(Inventory.getInstanceOfInventory().getInventoryEssence() + numberOfResourceForGold);
        } else
        {
            System.out.println(Constants_Building.INVALID_INPUT);
        }
    }
    
    
    public static void exchangeEssence (int numberOfEssence, String resource)
    {
        int numberOfResourceForEssence;
        
        if (Objects.equals(resource, Constants_Building.EXCHANGE_BEER))
        
        {
            numberOfResourceForEssence = numberOfEssence * Constants_Building.SIXTEEN;
            System.out.printf(Constants_Building.EXCHANGE_ESSENCE_FOR, numberOfEssence, numberOfResourceForEssence, Constants_Building.EXCHANGE_BEER);
            Inventory.getInstanceOfInventory().setInventoryEssence(Inventory.getInstanceOfInventory().getInventoryEssence() - numberOfEssence);
            Inventory.getInstanceOfInventory().setInventoryBeer(Inventory.getInstanceOfInventory().getInventoryBeer() + numberOfResourceForEssence);
        } else if (Objects.equals(resource, Constants_Building.EXCHANGE_WOOD))
        {
            numberOfResourceForEssence = numberOfEssence * Constants_Building.EIGHT;
            System.out.printf(Constants_Building.EXCHANGE_ESSENCE_FOR, numberOfEssence, numberOfResourceForEssence, Constants_Building.EXCHANGE_WOOD);
            Inventory.getInstanceOfInventory().setInventoryEssence(Inventory.getInstanceOfInventory().getInventoryEssence() - numberOfEssence);
            Inventory.getInstanceOfInventory().setInventoryWood(Inventory.getInstanceOfInventory().getInventoryWood() + numberOfResourceForEssence);
        } else if (Objects.equals(resource, Constants_Building.EXCHANGE_BRICK))
        {
            numberOfResourceForEssence = numberOfEssence * Constants_Building.FOUR;
            System.out.printf(Constants_Building.EXCHANGE_ESSENCE_FOR, numberOfEssence, numberOfResourceForEssence, Constants_Building.EXCHANGE_BRICK);
            Inventory.getInstanceOfInventory().setInventoryEssence(Inventory.getInstanceOfInventory().getInventoryEssence() - numberOfEssence);
            Inventory.getInstanceOfInventory().setInventoryBrick(Inventory.getInstanceOfInventory().getInventoryBrick() + numberOfResourceForEssence);
        } else if (Objects.equals(resource, Constants_Building.EXCHANGE_GOLD))
        {
            numberOfResourceForEssence = numberOfEssence * Constants_Building.TWO;
            System.out.printf(Constants_Building.EXCHANGE_ESSENCE_FOR, numberOfEssence, numberOfResourceForEssence, Constants_Building.EXCHANGE_GOLD);
            Inventory.getInstanceOfInventory().setInventoryEssence(Inventory.getInstanceOfInventory().getInventoryEssence() - numberOfEssence);
            Inventory.getInstanceOfInventory().setInventoryGold(Inventory.getInstanceOfInventory().getInventoryGold() + numberOfResourceForEssence);
        } else
        {
            System.out.println(Constants_Building.INVALID_INPUT);
        }
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
