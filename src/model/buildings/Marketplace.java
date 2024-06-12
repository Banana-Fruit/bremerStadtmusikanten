package model.buildings;


import java.util.Objects;


public class Marketplace extends Building
{
    private static final Marketplace INSTANCE_OF_MARKETPLACE = new Marketplace(Constants_Building.NAME_MARKETPLACE,
            Constants_Building.MARKET_GOLD, Constants_Building.MARKET_BRICK, Constants_Building.MARKET_WOOD,
            Constants_Building.MARKET_BEER, Constants_Building.MARKET_ESSENCE, false);


    // constructor
    private Marketplace (String name, int numberOfGold, int numberOfBrick, int numberOfWood, int numberOfBeer,
                         int numberOfEssence, boolean isUnlocked)
    {
        super (name, numberOfGold, numberOfBrick, numberOfWood, numberOfBeer, numberOfEssence, isUnlocked);
    }


    // Exchange course:
    // 1 wood = 2 beers
    // 1 brick = 2 woods
    // 1 gold = 2 bricks
    // 1 essence = 2 gold


    public static void exchangeBeer (int numberOfBeer, String resource)
    {
        int numberOfResourceForBeer;

        if (Objects.equals(resource, "wood"))
        {
            numberOfResourceForBeer = numberOfBeer / Constants_Building.TWO;
            System.out.printf("For %d beers you get %d woods.", numberOfBeer, numberOfResourceForBeer);
            Inventory.getInstanceOfInventory().setInventoryBeer(Inventory.getInstanceOfInventory().getInventoryBeer() - numberOfBeer);
            Inventory.getInstanceOfInventory().setInventoryWood(Inventory.getInstanceOfInventory().getInventoryWood() + numberOfResourceForBeer);
        }
        else if (Objects.equals(resource, "brick"))
        {
            numberOfResourceForBeer = numberOfBeer / Constants_Building.FOUR;
            System.out.printf("For %d beers you get %d bricks.", numberOfBeer, numberOfResourceForBeer);
            Inventory.getInstanceOfInventory().setInventoryBeer(Inventory.getInstanceOfInventory().getInventoryBeer() - numberOfBeer);
            Inventory.getInstanceOfInventory().setInventoryBrick(Inventory.getInstanceOfInventory().getInventoryBrick() + numberOfResourceForBeer);
        }
        else if (Objects.equals(resource, "gold"))
        {
            numberOfResourceForBeer = numberOfBeer / Constants_Building.EIGHT;
            System.out.printf("For %d beers you get %d golds.", numberOfBeer, numberOfResourceForBeer);
            Inventory.getInstanceOfInventory().setInventoryBeer(Inventory.getInstanceOfInventory().getInventoryBeer() - numberOfBeer);
            Inventory.getInstanceOfInventory().setInventoryGold(Inventory.getInstanceOfInventory().getInventoryGold() + numberOfResourceForBeer);
        }
        else if (Objects.equals(resource, "essence"))
        {
            numberOfResourceForBeer = numberOfBeer / Constants_Building.SIXTEEN;
            System.out.printf("For %d beers you get %d essence.", numberOfBeer, numberOfResourceForBeer);
            Inventory.getInstanceOfInventory().setInventoryBeer(Inventory.getInstanceOfInventory().getInventoryBeer() - numberOfBeer);
            Inventory.getInstanceOfInventory().setInventoryEssence(Inventory.getInstanceOfInventory().getInventoryEssence() + numberOfResourceForBeer);
        }
        else
        {
            System.out.println("Invalid input.");
        }
    }


    public static void exchangeWood (int numberOfWood, String resource)
    {
        int numberOfResourceForWood;

        if (Objects.equals(resource, "beer"))
        {
            numberOfResourceForWood = numberOfWood * Constants_Building.TWO;
            System.out.printf("For %d woods you get %d beers.", numberOfWood, numberOfResourceForWood);
            Inventory.getInstanceOfInventory().setInventoryWood(Inventory.getInstanceOfInventory().getInventoryWood() - numberOfWood);
            Inventory.getInstanceOfInventory().setInventoryBeer(Inventory.getInstanceOfInventory().getInventoryBeer() + numberOfResourceForWood);
        }
        else if (Objects.equals(resource, "brick"))
        {
            numberOfResourceForWood = numberOfWood / Constants_Building.TWO;
            System.out.printf("For %d woods you get %d bricks.", numberOfWood, numberOfResourceForWood);
            Inventory.getInstanceOfInventory().setInventoryWood(Inventory.getInstanceOfInventory().getInventoryWood() - numberOfWood);
            Inventory.getInstanceOfInventory().setInventoryBrick(Inventory.getInstanceOfInventory().getInventoryBrick() + numberOfResourceForWood);
        }
        else if (Objects.equals(resource, "gold"))
        {
            numberOfResourceForWood = numberOfWood / Constants_Building.FOUR;
            System.out.printf("For %d woods you get %d golds.", numberOfWood, numberOfResourceForWood);
            Inventory.getInstanceOfInventory().setInventoryWood(Inventory.getInstanceOfInventory().getInventoryWood() - numberOfWood);
            Inventory.getInstanceOfInventory().setInventoryGold(Inventory.getInstanceOfInventory().getInventoryGold() + numberOfResourceForWood);
        }
        else if (Objects.equals(resource, "essence"))
        {
            numberOfResourceForWood = numberOfWood / Constants_Building.EIGHT;
            System.out.printf("For %d woods you get %d essence.", numberOfWood, numberOfResourceForWood);
            Inventory.getInstanceOfInventory().setInventoryWood(Inventory.getInstanceOfInventory().getInventoryWood() - numberOfWood);
            Inventory.getInstanceOfInventory().setInventoryEssence(Inventory.getInstanceOfInventory().getInventoryEssence() + numberOfResourceForWood);
        }
        else
        {
            System.out.println("Invalid input.");
        }
    }



    public static void exchangeBrick (int numberOfBrick, String resource)
    {
        int numberOfResourceForBrick;

        if (Objects.equals(resource, "beer"))
        {
            numberOfResourceForBrick = numberOfBrick * Constants_Building.FOUR;
            System.out.printf("For %d bricks you get %d beers.", numberOfBrick, numberOfResourceForBrick);
            Inventory.getInstanceOfInventory().setInventoryBrick(Inventory.getInstanceOfInventory().getInventoryBrick() - numberOfBrick);
            Inventory.getInstanceOfInventory().setInventoryBeer(Inventory.getInstanceOfInventory().getInventoryBeer() + numberOfResourceForBrick);
        }
        else if (Objects.equals(resource, "wood"))
        {
            numberOfResourceForBrick = numberOfBrick * Constants_Building.TWO;
            System.out.printf("For %d bricks you get %d woods.", numberOfBrick, numberOfResourceForBrick);
            Inventory.getInstanceOfInventory().setInventoryBrick(Inventory.getInstanceOfInventory().getInventoryBrick() - numberOfBrick);
            Inventory.getInstanceOfInventory().setInventoryWood(Inventory.getInstanceOfInventory().getInventoryWood() + numberOfResourceForBrick);
        }
        else if (Objects.equals(resource, "gold"))
        {
            numberOfResourceForBrick = numberOfBrick / Constants_Building.TWO;
            System.out.printf("For %d bricks you get %d golds.", numberOfBrick, numberOfResourceForBrick);
            Inventory.getInstanceOfInventory().setInventoryBrick(Inventory.getInstanceOfInventory().getInventoryBrick() - numberOfBrick);
            Inventory.getInstanceOfInventory().setInventoryGold(Inventory.getInstanceOfInventory().getInventoryGold() + numberOfResourceForBrick);
        }
        else if (Objects.equals(resource, "essence"))
        {
            numberOfResourceForBrick = numberOfBrick / Constants_Building.FOUR;
            System.out.printf("For %d bricks you get %d essence.", numberOfBrick, numberOfResourceForBrick);
            Inventory.getInstanceOfInventory().setInventoryBrick(Inventory.getInstanceOfInventory().getInventoryBrick() - numberOfBrick);
            Inventory.getInstanceOfInventory().setInventoryEssence(Inventory.getInstanceOfInventory().getInventoryEssence() + numberOfResourceForBrick);
        }
        else
        {
            System.out.println("Invalid input.");
        }
    }


    public static void exchangeGold (int numberOfGold, String resource)
    {
        int numberOfResourceForGold;

        if (Objects.equals(resource, "beer"))

        {
            numberOfResourceForGold = numberOfGold * Constants_Building.EIGHT;
            System.out.printf("For %d golds you get %d beers.", numberOfGold, numberOfResourceForGold);
            Inventory.getInstanceOfInventory().setInventoryGold(Inventory.getInstanceOfInventory().getInventoryGold() - numberOfGold);
            Inventory.getInstanceOfInventory().setInventoryBeer(Inventory.getInstanceOfInventory().getInventoryBeer() + numberOfResourceForGold);
        }
        else if (Objects.equals(resource, "wood"))
        {
            numberOfResourceForGold = numberOfGold * Constants_Building.FOUR;
            System.out.printf("For %d golds you get %d woods.", numberOfGold, numberOfResourceForGold);
            Inventory.getInstanceOfInventory().setInventoryGold(Inventory.getInstanceOfInventory().getInventoryGold() - numberOfGold);
            Inventory.getInstanceOfInventory().setInventoryWood(Inventory.getInstanceOfInventory().getInventoryWood() + numberOfResourceForGold);
        }
        else if (Objects.equals(resource, "brick"))
        {
            numberOfResourceForGold = numberOfGold * Constants_Building.TWO;
            System.out.printf("For %d golds you get %d bricks.", numberOfGold, numberOfResourceForGold);
            Inventory.getInstanceOfInventory().setInventoryGold(Inventory.getInstanceOfInventory().getInventoryGold() - numberOfGold);
            Inventory.getInstanceOfInventory().setInventoryBrick(Inventory.getInstanceOfInventory().getInventoryBrick() + numberOfResourceForGold);
        }
        else if (Objects.equals(resource, "essence"))
        {
            numberOfResourceForGold = numberOfGold / Constants_Building.TWO;
            System.out.printf("For %d golds you get %d essence.", numberOfGold, numberOfResourceForGold);
            Inventory.getInstanceOfInventory().setInventoryGold(Inventory.getInstanceOfInventory().getInventoryGold() - numberOfGold);
            Inventory.getInstanceOfInventory().setInventoryEssence(Inventory.getInstanceOfInventory().getInventoryEssence() + numberOfResourceForGold);
        }
        else
        {
            System.out.println("Invalid input.");
        }
    }


    public static void exchangeEssence (int numberOfEssence, String resource)
    {
        int numberOfResourceForEssence;

        if (Objects.equals(resource, "beer"))

        {
            numberOfResourceForEssence = numberOfEssence * Constants_Building.SIXTEEN;
            System.out.printf("For %d golds you get %d beers.", numberOfEssence, numberOfResourceForEssence);
            Inventory.getInstanceOfInventory().setInventoryEssence(Inventory.getInstanceOfInventory().getInventoryEssence() - numberOfEssence);
            Inventory.getInstanceOfInventory().setInventoryBeer(Inventory.getInstanceOfInventory().getInventoryBeer() + numberOfResourceForEssence);
        }
        else if (Objects.equals(resource, "wood"))
        {
            numberOfResourceForEssence = numberOfEssence * Constants_Building.EIGHT;
            System.out.printf("For %d golds you get %d woods.", numberOfEssence, numberOfResourceForEssence);
            Inventory.getInstanceOfInventory().setInventoryEssence(Inventory.getInstanceOfInventory().getInventoryEssence() - numberOfEssence);
            Inventory.getInstanceOfInventory().setInventoryWood(Inventory.getInstanceOfInventory().getInventoryWood() + numberOfResourceForEssence);
        }
        else if (Objects.equals(resource, "brick"))
        {
            numberOfResourceForEssence = numberOfEssence * Constants_Building.FOUR;
            System.out.printf("For %d golds you get %d bricks.", numberOfEssence, numberOfResourceForEssence);
            Inventory.getInstanceOfInventory().setInventoryEssence(Inventory.getInstanceOfInventory().getInventoryEssence() - numberOfEssence);
            Inventory.getInstanceOfInventory().setInventoryBrick(Inventory.getInstanceOfInventory().getInventoryBrick() + numberOfResourceForEssence);
        }
        else if (Objects.equals(resource, "gold"))
        {
            numberOfResourceForEssence = numberOfEssence * Constants_Building.TWO;
            System.out.printf("For %d golds you get %d golds.", numberOfEssence, numberOfResourceForEssence);
            Inventory.getInstanceOfInventory().setInventoryEssence(Inventory.getInstanceOfInventory().getInventoryEssence() - numberOfEssence);
            Inventory.getInstanceOfInventory().setInventoryGold(Inventory.getInstanceOfInventory().getInventoryGold() + numberOfResourceForEssence);
        }
        else
        {
            System.out.println("Invalid input.");
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
