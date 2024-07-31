package model.buildings;


import control.BuildingController;
import model.Coordinate;
import model.Unit;
import model.player.*;
import resources.constants.Constants_Player_Units;
import resources.constants.scenes.Constants_Building;


public class TrainingArea extends Building
{
    private static final TrainingArea INSTANCE_OF_TRAININGAREA = new TrainingArea(Constants_Building.NAME_TRAININGSAREA,
            Constants_Building.TRAININGAREA_GOLD, Constants_Building.TRAININGAREA_BRICK,
            Constants_Building.TRAININGAREA_WOOD, Constants_Building.TRAININGAREA_BEER,
            Constants_Building.TRAININGAREA_ESSENCE, false, new Coordinate(Constants_Building.TRAINING_AREA_POSITION_X, Constants_Building.TRAINING_AREA_POSITION_Y));
    
    
    // constructor
    private TrainingArea (String name, int numberOfGold, int numberOfBrick, int numberOfWood,
                          int numberOfBeer, int numberOfEssence, boolean isUnlocked,
                          Coordinate positionUpperLeft)
    {
        super(name, numberOfGold, numberOfBrick, numberOfWood, numberOfBeer, numberOfEssence, isUnlocked, positionUpperLeft);
    }
    
    
    @Override
    public void unlockBuilding ()
    {
        BuildingController.checkIfBuildingIsUnlock(getInstanceOfTrainingarea());
    }
    
    
    public void trainFractionDonkeyAttack ()
    {
        Unit rats = FractionDonkey.getInstanceOfFractiondonkey().getRats();
        Unit beetles = FractionDonkey.getInstanceOfFractiondonkey().getBeetle();
        Unit mosquitoes = FractionDonkey.getInstanceOfFractiondonkey().getMosquitoes();
        
        rats.setMeele(rats.getMeele() + Constants_Player_Units.TRAINING_BONUS);
        beetles.setMeele(beetles.getMeele() + Constants_Player_Units.TRAINING_BONUS);
        mosquitoes.setMeele(mosquitoes.getMeele() + Constants_Player_Units.TRAINING_BONUS);
    }
    
    
    public void trainFractionDonkeyDefense ()
    {
        Unit rats = FractionDonkey.getInstanceOfFractiondonkey().getRats();
        Unit beetles = FractionDonkey.getInstanceOfFractiondonkey().getBeetle();
        Unit mosquitoes = FractionDonkey.getInstanceOfFractiondonkey().getMosquitoes();
        
        rats.setHealth(rats.getHealth() + Constants_Player_Units.TRAINING_BONUS);
        beetles.setHealth(beetles.getHealth() + Constants_Player_Units.TRAINING_BONUS);
        mosquitoes.setHealth(mosquitoes.getHealth() + Constants_Player_Units.TRAINING_BONUS);
    }
    
    
    public void trainFractionDogAttack ()
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
    
    
    public void trainFractionDogDefense ()
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
    
    
    public void trainFractionCatAttack ()
    {
        Unit cat = FractionCat.getInstanceOfFractionCat().getCat();
        Unit tiger = FractionCat.getInstanceOfFractionCat().getTiger();
        Unit housekeeper = FractionCat.getInstanceOfFractionCat().getHousekeeper();
        Unit jaguar = FractionCat.getInstanceOfFractionCat().getJaguar();
        Unit catWithHat = FractionCat.getInstanceOfFractionCat().getBingus();
        
        cat.setInitiative(cat.getInitiative() + Constants_Player_Units.TRAINING_BONUS);
        tiger.setInitiative(tiger.getInitiative() + Constants_Player_Units.TRAINING_BONUS);
        housekeeper.setInitiative(housekeeper.getInitiative() + Constants_Player_Units.TRAINING_BONUS);
        jaguar.setInitiative(jaguar.getInitiative() + Constants_Player_Units.TRAINING_BONUS);
        catWithHat.setInitiative(catWithHat.getInitiative() + Constants_Player_Units.TRAINING_BONUS);
    }
    
    
    public void trainFractionCatDefense ()
    {
        Unit cat = FractionCat.getInstanceOfFractionCat().getCat();
        Unit tiger = FractionCat.getInstanceOfFractionCat().getTiger();
        Unit housekeeper = FractionCat.getInstanceOfFractionCat().getHousekeeper();
        Unit jaguar = FractionCat.getInstanceOfFractionCat().getJaguar();
        Unit catWithHat = FractionCat.getInstanceOfFractionCat().getBingus();
        
        cat.setDodge(cat.getDodge() + Constants_Player_Units.TRAINING_BONUS);
        tiger.setDodge(tiger.getDodge() + Constants_Player_Units.TRAINING_BONUS);
        housekeeper.setDodge(housekeeper.getDodge() + Constants_Player_Units.TRAINING_BONUS);
        jaguar.setDodge(jaguar.getDodge() + Constants_Player_Units.TRAINING_BONUS);
        catWithHat.setDodge(catWithHat.getDodge() + Constants_Player_Units.TRAINING_BONUS);
    }
    
    
    public void trainFractionChickenAttack ()
    {
        Unit chick = FractionChicken.getInstanceOfFractionChicken().getChick();
        Unit fightingChicken = FractionChicken.getInstanceOfFractionChicken().getFightingChicken();
        Unit chef = FractionChicken.getInstanceOfFractionChicken().getChef();
        Unit turkey = FractionChicken.getInstanceOfFractionChicken().getTurkey();
        Unit chickenWithHat = FractionChicken.getInstanceOfFractionChicken().getChickenWithHat();
        
        chick.setMagicDamage(chick.getMagicDamage() + Constants_Player_Units.TRAINING_BONUS);
        fightingChicken.setMagicDamage(fightingChicken.getMagicDamage() + Constants_Player_Units.TRAINING_BONUS);
        chef.setMagicDamage(chef.getMagicDamage() + Constants_Player_Units.TRAINING_BONUS);
        turkey.setMagicDamage(turkey.getMagicDamage() + Constants_Player_Units.TRAINING_BONUS);
        chickenWithHat.setMagicDamage(chickenWithHat.getMagicDamage() + Constants_Player_Units.TRAINING_BONUS);
    }
    
    
    public void trainFractionChickenDefense ()
    {
        Unit chick = FractionChicken.getInstanceOfFractionChicken().getChick();
        Unit fightingChicken = FractionChicken.getInstanceOfFractionChicken().getFightingChicken();
        Unit chef = FractionChicken.getInstanceOfFractionChicken().getChef();
        Unit turkey = FractionChicken.getInstanceOfFractionChicken().getTurkey();
        Unit chickenWithHat = FractionChicken.getInstanceOfFractionChicken().getChickenWithHat();
        
        chick.setMagicresist(chick.getMagicresist() + Constants_Player_Units.TRAINING_BONUS);
        fightingChicken.setMagicresist(fightingChicken.getMagicresist() + Constants_Player_Units.TRAINING_BONUS);
        chef.setMagicresist(chef.getMagicresist() + Constants_Player_Units.TRAINING_BONUS);
        turkey.setMagicresist(turkey.getMagicresist() + Constants_Player_Units.TRAINING_BONUS);
        chickenWithHat.setMagicresist(chickenWithHat.getMagicresist() + Constants_Player_Units.TRAINING_BONUS);
    }
    
    
    public static TrainingArea getInstanceOfTrainingarea ()
    {
        return INSTANCE_OF_TRAININGAREA;
    }
}
