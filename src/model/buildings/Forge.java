package model.buildings;


import control.BuildingController;
import javafx.scene.control.Button;
import model.Coordinate;
import model.player.Artifact;
import resources.constants.scenes.Constants_Building;


public class Forge extends Building
{
    private static final Forge instanceOfForge = new Forge(Constants_Building.NAME_FORGE, Constants_Building.FORGE_GOLD,
            Constants_Building.FORGE_BRICK, Constants_Building.FORGE_WOOD, Constants_Building.FORGE_BEER,
            Constants_Building.FORGE_ESSENCE, false, new Coordinate(29.0,16.0));


    // constructor
    private Forge (String name, int numberOfGold, int numberOfBrick, int numberOfWood,
                   int numberOfBeer, int numberOfEssence, boolean isUnlocked,
                   Coordinate positionUpperLeft)
    {
        super (name, numberOfGold, numberOfBrick, numberOfWood, numberOfBeer, numberOfEssence, isUnlocked, positionUpperLeft);
    }



    @Override
    public void unlockBuilding ()
    {
        BuildingController.checkIfBuildingIsUnlock(getInstanceOfForge());
    }


    public static void showForge ()
    {
        System.out.printf(Constants_Building.RESOURCES_FORGE + Constants_Building.CURRENT_NUMBER_OF_RESOURCES,
                instanceOfForge.getNumberOfGold(), instanceOfForge.getNumberOfBrick(), instanceOfForge.getNumberOfWood(),
                instanceOfForge.getNumberOfBeer(), instanceOfForge.getNumberOfEssence());
    }



    public static void chooseArtifactToForgeIt ()
    {

    }



    public static void forgeHotMilkWithHoney () // parameter: Player player
    {
        Artifact hotMilkWithHoney = new Artifact(Constants_Building.BONUS_TWO, Constants_Building.BONUS_ZERO,
                Constants_Building.BONUS_ZERO, Constants_Building.BONUS_ZERO);
        // TODO: connect Artifact with player/ Units of the player
    }


    public static void forgeChickenSoup () // parameter: Player player
    {
        Artifact chickenSoup = new Artifact(Constants_Building.BONUS_ZERO, Constants_Building.BONUS_TWO,
                Constants_Building.BONUS_ZERO, Constants_Building.BONUS_ZERO);
        // TODO: connect Artifact with player/ Units of the player
    }


    public static void forgePowderKeg () // parameter: Player player
    {
        Artifact powderKeg = new Artifact(Constants_Building.BONUS_ZERO, Constants_Building.BONUS_ZERO,
                Constants_Building.BONUS_ZERO, Constants_Building.BONUS_TWO);
        // TODO: connect Artifact with player/ Units of the player
    }


    public static void forgePocketKnife () // parameter: Player player
    {
        Artifact pocketKnife = new Artifact(Constants_Building.BONUS_ZERO, Constants_Building.BONUS_ZERO,
                Constants_Building.BONUS_TWO, Constants_Building.BONUS_ZERO);
        // TODO: connect Artifact with player/ Units of the player
    }


    public static void upgradeArtifact (Artifact artifact)
    {
            artifact.setBonusHealth(artifact.getBonusHealth() * Constants_Building.BONUS_FACTOR_TWO);
            artifact.setBonusShield(artifact.getBonusShield() * Constants_Building.BONUS_FACTOR_TWO);
            artifact.setBonusCloseCombat(artifact.getBonusCloseCombat() * Constants_Building.BONUS_FACTOR_TWO);
            artifact.setBonusRangeCombat(artifact.getBonusRangeCombat() * Constants_Building.BONUS_FACTOR_TWO);
    }





    // getter methods

    /*
    public static synchronized Forge getInstanceOfForge(int gold, int brick, int wood, int beer, int essence)
    {
        if (instanceOfForge == null)
        {
            instanceOfForge = new Forge(gold, brick, wood, beer, essence);
        }
        return instanceOfForge;
    }

     */



    public static Forge getInstanceOfForge ()
    {
        return instanceOfForge;
    }



/*
    public boolean getIsObstacle ()
    {
        return isObstacle;
    }


    public void setIsObstacle (boolean isObstacle)
    {
        this.isObstacle = isObstacle;
    }

 */


}
