package model.buildings;


import control.BuildingController;
import model.Coordinate;
import resources.constants.scenes.Constants_Building;


public class BaseCamp extends Building
{
    private static final BaseCamp INSTANCE_OF_BASECAMP = new BaseCamp(Constants_Building.NAME_BASECAMP_NAME,
            Constants_Building.BASECAMP_GOLD, Constants_Building.BASECAMP_BRICK, Constants_Building.BASECAMP_WOOD,
            Constants_Building.BASECAMP_BEER, Constants_Building.BASECAMP_ESSENCE, false, new Coordinate(Constants_Building.BASECAMP_POSITION_X,Constants_Building.BASECAMP_POSITION_Y));


    // constructor
    private BaseCamp (String name, int numberOfGold, int numberOfBrick, int numberOfWood,
                      int numberOfBeer, int numberOfEssence, boolean isUnlocked,
                      Coordinate positionUpperLeft)
    {
        super (name, numberOfGold, numberOfBrick, numberOfWood, numberOfBeer, numberOfEssence, isUnlocked, positionUpperLeft);
    }



    @Override
    public void unlockBuilding ()
    {
        BuildingController.checkIfBuildingIsUnlock(getInstanceOfBasecamp());
    }


    public static BaseCamp getInstanceOfBasecamp ()
    {
        return INSTANCE_OF_BASECAMP;
    }
}
