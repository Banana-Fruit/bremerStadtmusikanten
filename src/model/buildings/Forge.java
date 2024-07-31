package model.buildings;


import control.BuildingController;
import model.Coordinate;
import model.player.Artifact;
import model.player.Player;
import resources.constants.scenes.Constants_Building;

import java.util.ArrayList;

/**
 * The class Forge contains all functional methods from the building Forge.
 * The class extends from the abstract class Building.
 *
 * @author Jule Degener
 */
public class Forge extends Building
{
    /**
     * Instance of Forge as attribute of the class to create a singleton
     *
     * @author Jule Degener
     */
    private static final Forge INSTANCE_OF_FORGE = new Forge(Constants_Building.NAME_FORGE,
            Constants_Building.FORGE_GOLD, Constants_Building.FORGE_BRICK, Constants_Building.FORGE_WOOD,
            Constants_Building.FORGE_BEER, Constants_Building.FORGE_ESSENCE, false,
            new Coordinate(Constants_Building.FORGE_POSITION_X, Constants_Building.FORGE_POSITION_Y));


    /**
     * Constructor to create an instance of Forge.
     *
     * @author Jule Degener, Jonas Helfer
     * @param name Name of the building
     * @param numberOfGold gold-price of the building
     * @param numberOfBrick brick-price of the building
     * @param numberOfWood wood-price of the building
     * @param numberOfBeer beer-price of the building
     * @param numberOfEssence essence-price of the building
     * @param isUnlocked boolean-value to see if a building is unlocked
     * @param positionUpperLeft Coordinate from the building
     * @precondition none
     * @postcondition An instance of the Forge is created.
     */
    private Forge (String name, int numberOfGold, int numberOfBrick, int numberOfWood,
                   int numberOfBeer, int numberOfEssence, boolean isUnlocked,
                   Coordinate positionUpperLeft)
    {
        super(name, numberOfGold, numberOfBrick, numberOfWood, numberOfBeer, numberOfEssence, isUnlocked,
                positionUpperLeft);
    }


    /**
     * Override method from the abstract class Building to unlock a building
     *
     * @author Jule Degener
     * @precondition An instance of BuildingController must exist.
     * @postcondition Status is known if a building is unlocked.
     */
    @Override
    public void unlockBuilding ()
    {
        BuildingController.getInstance().checkIfBuildingIsUnlock(getInstanceOfForge());
    }


    /**
     * Method to create an instance of the artifact HotMilkWithHoney an add to the player.
     *
     * @author Jule Degener
     * @precondition none
     * @postcondition Instance is created and added to the player.
     */
    public static void forgeHotMilkWithHoney ()
    {
        Artifact hotMilkWithHoney = new Artifact(Constants_Building.BONUS_TWO, Constants_Building.BONUS_ZERO,
                Constants_Building.BONUS_ZERO, Constants_Building.BONUS_ZERO,
                Constants_Building.DEFAULT_LEVEL_ARTIFACT);

        addArtifactToPlayer(hotMilkWithHoney);
    }


    /**
     * Method to create an instance of the artifact ChickenSoup an add to the player.
     *
     * @author Jule Degener
     * @precondition none
     * @postcondition Instance is created and added to the player.
     */
    public static void forgeChickenSoup ()
    {
        Artifact chickenSoup = new Artifact(Constants_Building.BONUS_ZERO, Constants_Building.BONUS_TWO,
                Constants_Building.BONUS_ZERO, Constants_Building.BONUS_ZERO,
                Constants_Building.DEFAULT_LEVEL_ARTIFACT);

        addArtifactToPlayer(chickenSoup);
    }


    /**
     * Method to create an instance of the artifact PowderKeg an add to the player.
     *
     * @author Jule Degener
     * @precondition none
     * @postcondition Instance is created and added to the player.
     */
    public static void forgePowderKeg ()
    {
        Artifact powderKeg = new Artifact(Constants_Building.BONUS_ZERO, Constants_Building.BONUS_ZERO,
                Constants_Building.BONUS_ZERO, Constants_Building.BONUS_TWO, Constants_Building.DEFAULT_LEVEL_ARTIFACT);

        addArtifactToPlayer(powderKeg);
    }


    /**
     * Method to create an instance of the artifact PocketKnife an add to the player.
     *
     * @author Jule Degener
     * @precondition none
     * @postcondition Instance is created and added to the player.
     */
    public static void forgePocketKnife ()
    {
        Artifact pocketKnife = new Artifact(Constants_Building.BONUS_ZERO, Constants_Building.BONUS_ZERO,
                Constants_Building.BONUS_TWO, Constants_Building.BONUS_ZERO, Constants_Building.DEFAULT_LEVEL_ARTIFACT);

        addArtifactToPlayer(pocketKnife);
    }


    /**
     * Method to add an instance of an artifact to the list of the player.
     *
     * @author Jule Degener
     * @param artifact Artifact to be added
     * @precondition An instance of Player must exist.
     * @postcondition The artifact is added to the list.
     */
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


    /**
     * Method to delete all artifacts in the list of th player.
     *
     * @author Jule Degener
     * @precondition none
     * @postcondition The list of artifacts is empty.
     */
    public static void deleteListOfArtifacts ()
    {
        ArrayList<Artifact> listOfArtifacts = Player.getInstance().getListOfArtifacts();

        listOfArtifacts.clear();
    }


    /**
     * Method to upgrade an artifact.
     *
     * @author Jule Degener
     * @param artifact Artifact to be improved.
     * @precondition none
     * @postcondition Artifact is upgraded.
     */
    public static void upgradeArtifact (Artifact artifact)
    {
        artifact.setBonusHealth(artifact.getBonusHealth() * Constants_Building.BONUS_FACTOR_TWO);
        artifact.setBonusShield(artifact.getBonusShield() * Constants_Building.BONUS_FACTOR_TWO);
        artifact.setBonusCloseCombat(artifact.getBonusCloseCombat() * Constants_Building.BONUS_FACTOR_TWO);
        artifact.setBonusRangeCombat(artifact.getBonusRangeCombat() * Constants_Building.BONUS_FACTOR_TWO);

        artifact.setLevelOfUpgrade(artifact.getLevelOfUpgrade() + Constants_Building.UPGRADE_LEVEL_ARTIFACT);
    }


    /**
     * Getter-method to have access of the instance of the Forge
     *
     * @author Jule Degener
     * @return The instance of the Forge is returned.
     * @precondition none
     * @postcondition Access of the instance of the Forge
     */
    public static Forge getInstanceOfForge ()
    {
        return INSTANCE_OF_FORGE;
    }
}
