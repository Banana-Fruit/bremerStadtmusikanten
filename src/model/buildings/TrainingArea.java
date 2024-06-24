package model.buildings;


import control.BuildingController;
import model.Coordinate;
import model.player.*;
import resources.constants.Constants_Player_Units;
import resources.constants.scenes.Constants_Building;


public class TrainingArea extends Building
{
    private static final TrainingArea INSTANCE_OF_TRAININGAREA = new TrainingArea(Constants_Building.NAME_TRAININGSAREA,
            Constants_Building.TRAININGAREA_GOLD, Constants_Building.TRAININGAREA_BRICK,
            Constants_Building.TRAININGAREA_WOOD, Constants_Building.TRAININGAREA_BEER,
            Constants_Building.TRAININGAREA_ESSENCE, false, new Coordinate(Constants_Building.TRAINING_AREA_POSITION_X,Constants_Building.TRAINING_AREA_POSITION_Y));


    // constructor
    private TrainingArea (String name, int numberOfGold, int numberOfBrick, int numberOfWood,
                          int numberOfBeer, int numberOfEssence, boolean isUnlocked,
                          Coordinate positionUpperLeft)
    {
        super (name, numberOfGold, numberOfBrick, numberOfWood, numberOfBeer, numberOfEssence, isUnlocked, positionUpperLeft);
    }


    @Override
    public void unlockBuilding ()
    {
        BuildingController.checkIfBuildingIsUnlock(getInstanceOfTrainingarea());
    }


    public static void trainFractionDonkeyAttack ()
    {
        Unit rats = FractionDonkey.getInstanceOfFractiondonkey().getRats();
        Unit beetles = FractionDonkey.getInstanceOfFractiondonkey().getBeetle();
        Unit mosquitoes = FractionDonkey.getInstanceOfFractiondonkey().getMosquitoes();

        rats.setCloseCombat(rats.getCloseCombat() + Constants_Player_Units.TRAINING_BONUS);
        beetles.setCloseCombat(beetles.getCloseCombat() + Constants_Player_Units.TRAINING_BONUS);
        mosquitoes.setCloseCombat(mosquitoes.getCloseCombat() + Constants_Player_Units.TRAINING_BONUS);
    }



    public static void trainFractionDonkeyDefense ()
    {
        Unit rats = FractionDonkey.getInstanceOfFractiondonkey().getRats();
        Unit beetles = FractionDonkey.getInstanceOfFractiondonkey().getBeetle();
        Unit mosquitoes = FractionDonkey.getInstanceOfFractiondonkey().getMosquitoes();

        rats.setHealth(rats.getHealth() + Constants_Player_Units.TRAINING_BONUS);
        beetles.setHealth(beetles.getHealth() + Constants_Player_Units.TRAINING_BONUS);
        mosquitoes.setHealth(mosquitoes.getHealth() + Constants_Player_Units.TRAINING_BONUS);
    }



    public static void trainFractionDogAttack ()
    {
        Unit goldenRetriever = FractionDog.getInstanceOfFractionDog().getGoldenRetriever();
        Unit germanShepherd = FractionDog.getInstanceOfFractionDog().getGermanShepherd();
        Unit hunter = FractionDog.getInstanceOfFractionDog().getHunter();
        Unit bulldog = FractionDog.getInstanceOfFractionDog().getBulldog();
        Unit dogWithHat = FractionDog.getInstanceOfFractionDog().getHundini();

        goldenRetriever.setRangeOfMotion(goldenRetriever.getRangeOfMotion() + Constants_Player_Units.TRAINING_BONUS);
        germanShepherd.setRangeOfMotion(germanShepherd.getRangeOfMotion() + Constants_Player_Units.TRAINING_BONUS);
        hunter.setRangeOfMotion(hunter.getRangeOfMotion() + Constants_Player_Units.TRAINING_BONUS);
        bulldog.setRangeOfMotion(bulldog.getRangeOfMotion() + Constants_Player_Units.TRAINING_BONUS);
        dogWithHat.setRangeOfMotion(dogWithHat.getRangeOfMotion() + Constants_Player_Units.TRAINING_BONUS);
    }


    public static void trainFractionDogDefense ()
    {
        Unit goldenRetriever = FractionDog.getInstanceOfFractionDog().getGoldenRetriever();
        Unit germanShepherd = FractionDog.getInstanceOfFractionDog().getGermanShepherd();
        Unit hunter = FractionDog.getInstanceOfFractionDog().getHunter();
        Unit bulldog = FractionDog.getInstanceOfFractionDog().getBulldog();
        Unit dogWithHat = FractionDog.getInstanceOfFractionDog().getHundini();

        goldenRetriever.setShield(goldenRetriever.getShield() + Constants_Player_Units.TRAINING_BONUS);
        germanShepherd.setShield(germanShepherd.getShield() + Constants_Player_Units.TRAINING_BONUS);
        hunter.setShield(hunter.getShield() + Constants_Player_Units.TRAINING_BONUS);
        bulldog.setShield(bulldog.getShield() + Constants_Player_Units.TRAINING_BONUS);
        dogWithHat.setShield(dogWithHat.getShield() + Constants_Player_Units.TRAINING_BONUS);
    }


    public static void trainFractionCatAttack ()
    {
        Unit cat = FractionCat.getInstanceOfFractionCat().getCat();
        Unit tiger = FractionCat.getInstanceOfFractionCat().getTiger();
        Unit housekeeper = FractionCat.getInstanceOfFractionCat().getHousekeeper();
        Unit jaguar = FractionCat.getInstanceOfFractionCat().getJaguar();
        Unit catWithHat = FractionCat.getInstanceOfFractionCat().getBingus();

        cat.setProactive(cat.getProactive() + Constants_Player_Units.TRAINING_BONUS);
        tiger.setProactive(tiger.getProactive() + Constants_Player_Units.TRAINING_BONUS);
        housekeeper.setProactive(housekeeper.getProactive() + Constants_Player_Units.TRAINING_BONUS);
        jaguar.setProactive(jaguar.getProactive() + Constants_Player_Units.TRAINING_BONUS);
        catWithHat.setProactive(catWithHat.getProactive() + Constants_Player_Units.TRAINING_BONUS);
    }


    public static void trainFractionCatDefense ()
    {
        Unit cat = FractionCat.getInstanceOfFractionCat().getCat();
        Unit tiger = FractionCat.getInstanceOfFractionCat().getTiger();
        Unit housekeeper = FractionCat.getInstanceOfFractionCat().getHousekeeper();
        Unit jaguar = FractionCat.getInstanceOfFractionCat().getJaguar();
        Unit catWithHat = FractionCat.getInstanceOfFractionCat().getBingus();

        cat.setEvade(cat.getEvade() + Constants_Player_Units.TRAINING_BONUS);
        tiger.setEvade(tiger.getEvade() + Constants_Player_Units.TRAINING_BONUS);
        housekeeper.setEvade(housekeeper.getEvade() + Constants_Player_Units.TRAINING_BONUS);
        jaguar.setEvade(jaguar.getEvade() + Constants_Player_Units.TRAINING_BONUS);
        catWithHat.setEvade(catWithHat.getEvade() + Constants_Player_Units.TRAINING_BONUS);
    }


    public static void trainFractionChickenAttack ()
    {
        Unit chick = FractionChicken.getInstanceOfFractionChicken().getChick();
        Unit fightingChicken = FractionChicken.getInstanceOfFractionChicken().getFightingChicken();
        Unit chef = FractionChicken.getInstanceOfFractionChicken().getChef();
        Unit turkey = FractionChicken.getInstanceOfFractionChicken().getTurkey();
        Unit chickenWithHat = FractionChicken.getInstanceOfFractionChicken().getChickenWithHat();

        chick.setMagic(chick.getMagic() + Constants_Player_Units.TRAINING_BONUS);
        fightingChicken.setMagic(fightingChicken.getMagic() + Constants_Player_Units.TRAINING_BONUS);
        chef.setMagic(chef.getMagic() + Constants_Player_Units.TRAINING_BONUS);
        turkey.setMagic(turkey.getMagic() + Constants_Player_Units.TRAINING_BONUS);
        chickenWithHat.setMagic(chickenWithHat.getMagic() + Constants_Player_Units.TRAINING_BONUS);
    }


    public static void trainFractionChickenDefense ()
    {
        Unit chick = FractionChicken.getInstanceOfFractionChicken().getChick();
        Unit fightingChicken = FractionChicken.getInstanceOfFractionChicken().getFightingChicken();
        Unit chef = FractionChicken.getInstanceOfFractionChicken().getChef();
        Unit turkey = FractionChicken.getInstanceOfFractionChicken().getTurkey();
        Unit chickenWithHat = FractionChicken.getInstanceOfFractionChicken().getChickenWithHat();

        chick.setMagicResidence(chick.getMagicResidence() + Constants_Player_Units.TRAINING_BONUS);
        fightingChicken.setMagicResidence(fightingChicken.getMagicResidence() + Constants_Player_Units.TRAINING_BONUS);
        chef.setMagicResidence(chef.getMagicResidence() + Constants_Player_Units.TRAINING_BONUS);
        turkey.setMagicResidence(turkey.getMagicResidence() + Constants_Player_Units.TRAINING_BONUS);
        chickenWithHat.setMagicResidence(chickenWithHat.getMagicResidence() + Constants_Player_Units.TRAINING_BONUS);
    }


    public static TrainingArea getInstanceOfTrainingarea ()
    {
        return INSTANCE_OF_TRAININGAREA;
    }
}
