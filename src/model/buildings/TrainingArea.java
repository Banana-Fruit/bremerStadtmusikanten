package model.buildings;


import control.BuildingController;
import resources.constants.scenes.Constants_Building;


public class TrainingArea extends Building
{
    private static final TrainingArea INSTANCE_OF_TRAININGAREA = new TrainingArea(Constants_Building.NAME_TRAININGSAREA,
            Constants_Building.TRAININGAREA_GOLD, Constants_Building.TRAININGAREA_BRICK,
            Constants_Building.TRAININGAREA_WOOD, Constants_Building.TRAININGAREA_BEER,
            Constants_Building.TRAININGAREA_ESSENCE, false);


    // constructor
    private TrainingArea (String name, int numberOfGold, int numberOfBrick, int numberOfWood, int numberOfBeer, int numberOfEssence,
                          boolean isUnlocked)
    {
        super (name, numberOfGold, numberOfBrick, numberOfWood, numberOfBeer, numberOfEssence, isUnlocked);
    }


    @Override
    public void unlockBuilding ()
    {
        BuildingController.checkIfBuildingIsUnlock(getInstanceOfTrainingarea());
    }


    public TrainingArea getInstanceOfTrainingarea ()
    {
        return INSTANCE_OF_TRAININGAREA;
    }
}
