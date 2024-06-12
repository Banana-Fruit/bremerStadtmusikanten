package model.buildings;


public class Headquarter extends Building
{
    private static final Headquarter INSTANCE_OF_HEADQUARTER = new Headquarter(Constants_Building.NAME_HEADQUARTER,
            Constants_Building.HEADQUARTER_GOLD, Constants_Building.HEADQUARTER_BRICK, Constants_Building.HEADQUARTER_WOOD,
            Constants_Building.HEADQUARTER_BEER, Constants_Building.HEADQUARTER_ESSENCE, true);


    private Headquarter (String name, int numberOfGold, int numberOfBrick, int numberOfWood, int numberOfBeer,
                         int numberOfEssence, boolean isUnlocked)
    {
        super (name, numberOfGold, numberOfBrick, numberOfWood, numberOfBeer, numberOfEssence, isUnlocked);
    }


    @Override
    public void unlockBuilding ()
    {
        BuildingController.checkIfBuildingIsUnlock(getInstanceOfHeadquarter());
    }


    public Headquarter getInstanceOfHeadquarter ()
    {
        return INSTANCE_OF_HEADQUARTER;
    }
}
