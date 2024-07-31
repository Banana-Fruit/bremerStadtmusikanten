package model.buildings;


import control.BuildingController;
import model.Coordinate;
import model.player.Artifact;
import model.player.Player;
import resources.constants.scenes.Constants_Building;

import java.util.ArrayList;


public class Forge extends Building
{
    private static final Forge INSTANCE_OF_FORGE = new Forge(Constants_Building.NAME_FORGE, Constants_Building.FORGE_GOLD,
            Constants_Building.FORGE_BRICK, Constants_Building.FORGE_WOOD, Constants_Building.FORGE_BEER,
            Constants_Building.FORGE_ESSENCE, false, new Coordinate(Constants_Building.FORGE_POSITION_X, Constants_Building.FORGE_POSITION_Y));
    
    
    // constructor
    private Forge (String name, int numberOfGold, int numberOfBrick, int numberOfWood,
                   int numberOfBeer, int numberOfEssence, boolean isUnlocked,
                   Coordinate positionUpperLeft)
    {
        super(name, numberOfGold, numberOfBrick, numberOfWood, numberOfBeer, numberOfEssence, isUnlocked, positionUpperLeft);
    }
    
    
    @Override
    public void unlockBuilding ()
    {
        BuildingController.checkIfBuildingIsUnlock(getInstanceOfForge());
    }
    
    
    public static void forgeHotMilkWithHoney ()
    {
        Artifact hotMilkWithHoney = new Artifact(Constants_Building.BONUS_TWO, Constants_Building.BONUS_ZERO,
                Constants_Building.BONUS_ZERO, Constants_Building.BONUS_ZERO);

        addArtifactToPlayer(hotMilkWithHoney);
    }
    
    
    public static void forgeChickenSoup ()
    {
        Artifact chickenSoup = new Artifact(Constants_Building.BONUS_ZERO, Constants_Building.BONUS_TWO,
                Constants_Building.BONUS_ZERO, Constants_Building.BONUS_ZERO);

        addArtifactToPlayer(chickenSoup);
    }
    
    
    public static void forgePowderKeg ()
    {
        Artifact powderKeg = new Artifact(Constants_Building.BONUS_ZERO, Constants_Building.BONUS_ZERO,
                Constants_Building.BONUS_ZERO, Constants_Building.BONUS_TWO);

        addArtifactToPlayer(powderKeg);
    }
    
    
    public static void forgePocketKnife ()
    {
        Artifact pocketKnife = new Artifact(Constants_Building.BONUS_ZERO, Constants_Building.BONUS_ZERO,
                Constants_Building.BONUS_TWO, Constants_Building.BONUS_ZERO);

        addArtifactToPlayer(pocketKnife);
    }


    private static void addArtifactToPlayer (Artifact artifact)
    {
        ArrayList<Artifact> listOfArtifacts = Player.getInstance().getListOfArtifacts();

        if (listOfArtifacts.size() <= Constants_Building.MAX_NUMBER_ARTIFACTS)
        {
            listOfArtifacts.add(artifact);
        }
        else
        {
            System.out.println(Constants_Building.ERROR_NUMBER_OF_ARTIFACTS);
        }
    }


    public static void deleteListOfArtifacts ()
    {
        ArrayList<Artifact> listOfArtifacts = Player.getInstance().getListOfArtifacts();

        listOfArtifacts.clear();
    }
    
    
    public static void upgradeArtifact (Artifact artifact)
    {
        artifact.setBonusHealth(artifact.getBonusHealth() * Constants_Building.BONUS_FACTOR_TWO);
        artifact.setBonusShield(artifact.getBonusShield() * Constants_Building.BONUS_FACTOR_TWO);
        artifact.setBonusCloseCombat(artifact.getBonusCloseCombat() * Constants_Building.BONUS_FACTOR_TWO);
        artifact.setBonusRangeCombat(artifact.getBonusRangeCombat() * Constants_Building.BONUS_FACTOR_TWO);
    }
    
    
    public static Forge getInstanceOfForge ()
    {
        return INSTANCE_OF_FORGE;
    }
}
