package model.buildings;


import control.BuildingController;
import control.game.UnitController;
import model.Coordinate;
import model.player.Inventory;
import model.player.Player;
import model.Unit;
import resources.constants.Constants_Player_Units;
import resources.constants.scenes.Constants_Building;
import resources.constants.Constants_Combat;
import java.util.List;


public class Pub extends Building
{
    private static final Pub INSTANCE_OF_PUB = new Pub(Constants_Building.NAME_PUB, Constants_Building.PUB_GOLD,
            Constants_Building.PUB_BRICK, Constants_Building.PUB_WOOD, Constants_Building.PUB_BEER,
            Constants_Building.PUB_ESSENCE, false, new Coordinate(Constants_Building.PUB_POSITION_X,Constants_Building.PUB_POSITION_Y));


    // constructor
    private Pub (String name, int numberOfGold, int numberOfBrick, int numberOfWood,
                 int numberOfBeer, int numberOfEssence, boolean isUnlocked,
                 Coordinate positionUpperLeft)
    {
        super (name, numberOfGold, numberOfBrick, numberOfWood, numberOfBeer, numberOfEssence, isUnlocked, positionUpperLeft);
    }



    @Override
    public void unlockBuilding ()
    {
        BuildingController.checkIfBuildingIsUnlock(getInstanceOfPub());
    }


    public static void recruitAMercenary ()
    {
        UnitController unitController = UnitController.getInstance();
        List<Unit> units = unitController.UnitCreator();
        Unit mercenary = units.get(Constants_Combat.MERCENARY);

        // pay for the mercenary to become a member in the team
        payForTheMercenary();

        // add the mercenary in the team
        addMercenaryToTheTeam(mercenary);
    }


    private static void payForTheMercenary ()
    {
        Inventory inventory = Inventory.getInstanceOfInventory();
        inventory.setInventoryBeer(inventory.getInventoryBeer() - Constants_Player_Units.COST_MERCENARY);
    }


    private static void addMercenaryToTheTeam (Unit mercenary)
    {
        Unit[] team = Player.getInstance().getTeammembers();

        int i;
        for (i = Constants_Player_Units.ZERO; i < team.length; i++)
        {
            if (team[i] == null)
            {
                team[i] = mercenary;
            }
            else
            {
                if(!(team[Constants_Player_Units.LAST_INDEX_NUMBER_OF_TEAM] == null))
                {
                    System.out.println("The team is full.");
                }
                else
                {
                    i++;
                }
            }
        }
    }


    public static Pub getInstanceOfPub ()
    {
        return INSTANCE_OF_PUB;
    }
}
