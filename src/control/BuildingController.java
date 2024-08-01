package control;


import control.game.InventoryController;
import control.game.PlayerController;
import control.scenes.DisplayController;
import control.scenes.PanelController;
import control.scenes.SceneController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Coordinate;
import model.Unit;
import model.buildings.*;
import model.player.*;
import model.userInterface.TransparentButton;
import model.userInterface.showables.Map;
import resources.constants.Constants_DefaultValues;
import resources.constants.Constants_ExceptionMessages;
import resources.constants.Constants_Player_Units;
import resources.constants.scenes.Constants_Building;
import resources.constants.scenes.Constants_City;
import resources.constants.scenes.Constants_Map;
import view.OutputImageView;


/**
 * This class contains all method to create scenes with all functions of the buildings.
 *
 * @author Jule Degener
 */
public class BuildingController
{
    /**
     * Instance of the BuildingController as attribute to create a singleton
     *
     * @author Jule Degener
     */
    private static BuildingController instance = null;


    /**
     * Default Controller to create a singleton
     *
     * @author Jule Degener
     * @precondition none
     * @postcondition An instance of the BuildingController is created without any parameters.
     */
    private BuildingController ()
    {
        ;
    }
    
    
    public static synchronized void initialize ()
    {
        if (instance == null)
        {
            instance = new BuildingController();
        } else
        {
            throw new IllegalStateException(Constants_ExceptionMessages.ALREADY_INITIALIZED);
        }
    }


    /**
     * Method to show the price of a building.
     *
     * @author Jule Degener
     * @param building Building whose price is to be shown
     * @precondition none
     * @postcondition The price of a building is shown in a gridPane.
     */
    public static void showPriceOfBuilding (Building building)
    {
        if (!building.isUnlocked)
        {
            GridPane gridPane = new GridPane();
            Label gold = new Label(Constants_Building.GOLD + building.getNumberOfGold());
            Label brick = new Label(Constants_Building.BRICK + building.getNumberOfBrick());
            Label wood = new Label(Constants_Building.WOOD + building.getNumberOfWood());
            Label beer = new Label(Constants_Building.BEER + building.getNumberOfBeer());
            Label essence = new Label(Constants_Building.ESSENCE + building.getNumberOfEssence());
            Label questionUnlockBuilding = new Label(Constants_Building.QUESTION_UNLOCK_BUILDING);
            Button yes = new Button(Constants_Building.ANSWER_YES);
            Button no = new Button(Constants_Building.ANSWER_NO);
            
            gridPane.add(gold, Constants_Building.GRIDPANE_COLUMN_ONE, Constants_Building.GRIDPANE_ROW_ONE);
            gridPane.add(brick, Constants_Building.GRIDPANE_COLUMN_ONE, Constants_Building.GRIDPANE_ROW_TWO);
            gridPane.add(wood, Constants_Building.GRIDPANE_COLUMN_ONE, Constants_Building.GRIDPANE_ROW_THREE);
            gridPane.add(beer, Constants_Building.GRIDPANE_COLUMN_ONE, Constants_Building.GRIDPANE_ROW_FOUR);
            gridPane.add(essence, Constants_Building.GRIDPANE_COLUMN_ONE, Constants_Building.GRIDPANE_ROW_FIVE);
            gridPane.add(questionUnlockBuilding, Constants_Building.GRIDPANE_COLUMN_ONE,
                    Constants_Building.GRIDPANE_ROW_SIX);
            gridPane.add(yes, Constants_Building.GRIDPANE_COLUMN_ONE, Constants_Building.GRIDPANE_ROW_SEVEN);
            gridPane.add(no, Constants_Building.GRIDPANE_COLUMN_TWO, Constants_Building.GRIDPANE_ROW_SEVEN);
            
            
            Stage popUpStage = new Stage();
            Scene scene = new Scene(gridPane, Constants_Building.SCENE_WIDTH_UNLOCK_BUILDING,
                    Constants_Building.SCENE_HEIGHT_UNLOCK_BUILDING);
            unlockBuildingWithButton(popUpStage, building, yes);
            disappearPopUpWindow(popUpStage, no);
            
            popUpStage.setScene(scene);
            popUpStage.show();
        } else
        {
            System.out.println(Constants_Building.IS_UNLOCKED);
            SceneController.buildSceneBuildingInside(building);
        }
    }


    /**
     * Method for unlocking a building when clicking on the button.
     *
     * @author Jule Degener
     * @param stage Stage on which the popup-window is shown
     * @param building Building to be visited.
     * @param button Button on which the event is to be triggered.
     * @precondition none
     * @postcondition The popup-window is closed.
     */
    private static void unlockBuildingWithButton (Stage stage, Building building, Button button)
    {
        button.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle (ActionEvent actionEvent)
            {
                building.unlockBuilding();
                stage.close();
                SceneController.buildSceneBuildingInside(building);
            }
        });
    }


    /**
     * Method to close the popup-window.
     *
     * @author Jule Degener
     * @param stage Stage on which the popup-window is shown
     * @param button Button on which the event is to be triggered.
     * @precondition none
     * @postcondition Popup-window is closed.
     */
    private static void disappearPopUpWindow (Stage stage, Button button)
    {
        button.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle (ActionEvent actionEvent)
            {
                stage.close();
            }
        });
    }


    /**
     * Method to check if a building is already unlocked.
     *
     * @author Jule Degener
     * @param building Building being inspected.
     * @return Boolean-value that show the unlock-status.
     * @precondition An instance of inventory exist.
     * @postcondition Status of the blocking of a building is known.
     */
    public boolean checkIfBuildingIsUnlock (Building building)
    {
        Inventory inventory = Inventory.getInstanceOfInventory();
        
        if ((inventory.getInventoryGold() >= building.numberOfGold) &&
                (inventory.getInventoryBrick() >= building.numberOfBrick) &&
                (inventory.getInventoryWood() >= building.numberOfWood) &&
                (inventory.getInventoryBeer() >= building.numberOfBeer) &&
                (inventory.getInventoryEssence() >= building.numberOfEssence))
        {
            System.out.println(Constants_Building.BUILDING_UNLOCKED);
            InventoryController.payWithTheInventoryForABuilding(building);
            DisplayController.getInstance().updateInventory();
            building.setUnlocked(true);
            return true;
        } else
        {
            System.out.println(Constants_Building.BUILDING_LOCKED);
            return false;
        }
    }
    
    
    public void addButtons ()
    {
        Map.getInstance().getPane().getChildren().add(createBuildingButton(Constants_City.CITY_LABEL_BASECAMP,
                () -> showPriceOfBuilding(BaseCamp.getInstanceOfBasecamp()), Constants_Building.BUTTON_WIDTH_BASECAMP,
                Constants_Building.BUTTON_HEIGHT_BASECAMP, BaseCamp.getInstanceOfBasecamp().getPositionUpperLeft()));
        Map.getInstance().getPane().getChildren().add(createBuildingButton(Constants_City.CITY_LABEL_FORGE,
                () -> showPriceOfBuilding(Forge.getInstanceOfForge()), Constants_Building.BUTTON_WIDTH_FORGE,
                Constants_Building.BUTTON_HEIGHT_FORGE, Forge.getInstanceOfForge().getPositionUpperLeft()));
        Map.getInstance().getPane().getChildren().add(createBuildingButton(Constants_City.CITY_LABEL_FRACTION_CAMP_DOG,
                () -> showPriceOfBuilding(FractionCampDog.getInstanceOfFractionDogCamp()),
                Constants_Building.BUTTON_WIDTH_FRACTIONCAMP_DOG, Constants_Building.BUTTON_HEIGHT_FRACTIONCAMP_DOG,
                FractionCampDog.getInstanceOfFractionDogCamp().getPositionUpperLeft()));
        Map.getInstance().getPane().getChildren().add(createBuildingButton(Constants_City.CITY_LABEL_FRACTION_CAMP_CAT,
                () -> showPriceOfBuilding(FractionCampCat.getInstanceOfFractionCatCamp()),
                Constants_Building.BUTTON_WIDTH_FRACTIONCAMP_CAT, Constants_Building.BUTTON_HEIGHT_FRACTIONCAMP_CAT,
                FractionCampCat.getInstanceOfFractionCatCamp().getPositionUpperLeft()));
        Map.getInstance().getPane().getChildren().add(createBuildingButton(Constants_City
                .CITY_LABEL_FRACTION_CAMP_CHICKEN, () ->
                showPriceOfBuilding(FractionCampChicken.getInstanceOfFractionChickenCamp()),
                Constants_Building.BUTTON_WIDTH_FRACTIONCAMP_CHICKEN,
                Constants_Building.BUTTON_HEIGHT_FRACTIONCAMP_CHICKEN,
                FractionCampChicken.getInstanceOfFractionChickenCamp().getPositionUpperLeft()));
        Map.getInstance().getPane().getChildren().add(createBuildingButton(Constants_City.CITY_LABEL_HEADQUARTER,
                () -> showPriceOfBuilding(Headquarter.getInstanceOfHeadquarter()),
                Constants_Building.BUTTON_WIDTH_HEADQUARTER, Constants_Building.BUTTON_HEIGHT_HEADQUARTER,
                Headquarter.getInstanceOfHeadquarter().getPositionUpperLeft()));
        Map.getInstance().getPane().getChildren().add(createBuildingButton(Constants_City.CITY_LABEL_MAGIC_AMPLIFIER,
                () -> showPriceOfBuilding(MagicAmplifier.getInstanceOfMagicamplifier()),
                Constants_Building.BUTTON_WIDTH_MAGICAMPLIFIER, Constants_Building.BUTTON_HEIGHT_MAGICAMPLIFIER,
                MagicAmplifier.getInstanceOfMagicamplifier().getPositionUpperLeft()));
        Map.getInstance().getPane().getChildren().add(createBuildingButton(Constants_City.CITY_LABEL_MARKETPLACE,
                () -> showPriceOfBuilding(Marketplace.getInstanceOfMarketplace()),
                Constants_Building.BUTTON_WIDTH_MARKETPLACE, Constants_Building.BUTTON_HEIGHT_MARKETPLACE,
                Marketplace.getInstanceOfMarketplace().getPositionUpperLeft()));
        Map.getInstance().getPane().getChildren().add(createBuildingButton(Constants_City.CITY_LABEL_PUB,
                () -> showPriceOfBuilding(Pub.getInstanceOfPub()),
                Constants_Building.BUTTON_WIDTH_PUB, Constants_Building.BUTTON_HEIGHT_PUB,
                Pub.getInstanceOfPub().getPositionUpperLeft()));
        Map.getInstance().getPane().getChildren().add(createBuildingButton(Constants_City.CITY_LABEL_TRAININGS_AREA,
                () -> showPriceOfBuilding(TrainingArea.getInstanceOfTrainingarea()),
                Constants_Building.BUTTON_WIDTH_TRAINING_AREA, Constants_Building.BUTTON_HEIGHT_TRAINING_AREA,
                TrainingArea.getInstanceOfTrainingarea().getPositionUpperLeft()));
    }


    /**
     * Creates a TransparentButton for a building at a specified coordinate on the map.
     * @author Jonas Helfer
     * @param name The name of the building, which will be used as the button text.
     * @param action The Runnable to be executed when the button is clicked.
     * @param itemWidth The width of the button.
     * @param itemHeight The height of the button.
     * @param coordinate The coordinate where the building (and thus the button) should be placed.
     * @precondition The PanelController and Map instances are properly initialized and accessible.
     *               The Constants_DefaultValues and Constants_Building classes are properly defined.
     *               The TransparentButton class is implemented and available.
     *               The coordinate is within the bounds of the game map.
     * @postcondition A new TransparentButton is created with the specified properties and positioned on the map.
     *                The button's action is set to execute the provided Runnable when clicked.
     * @return TransparentButton A new TransparentButton instance for the building.
     */
    public TransparentButton createBuildingButton (String name, Runnable action, int itemWidth, int itemHeight,
                                                   Coordinate coordinate)
    {
        Coordinate buildingCoordinate = PanelController.getInstance().getCoordinateFromPanelTile(Map.getInstance()
                        .getPanel(), (int) coordinate.getPositionX(), (int) coordinate.getPositionY() -
                Constants_DefaultValues.ONE);
        TransparentButton button = new TransparentButton(name, () ->
        {
            action.run();
        },
                itemWidth, itemHeight, Constants_Building.BUILDING_BUTTON_OPACITY_RELEASED,
                Constants_Building.BUILDING_BUTTON_OPACITY_PRESSED);
        button.setTranslateX(buildingCoordinate.getPositionX());
        button.setTranslateY(buildingCoordinate.getPositionY());
        return button;
    }


    /**
     * Method to structure the Output of the information about a unit.
     *
     * @author Jule Degener
     * @param unit Unit about which information is provided.
     * @return A HBox with Labels of the attributes of a unit and their values is returned.
     * @precondition none
     * @postcondition All information about a unit can be shown.
     */
    private static HBox informAboutUnit (Unit unit)
    {
        VBox informationAboutUnit = showValuesOfAttributesOfUnit(unit);
        VBox attributesOfUnit = showAllAttributesOfUnit(unit);
        HBox structureInformation = new HBox();

        structureInformation.getChildren().addAll(attributesOfUnit, informationAboutUnit);
        structureInformation.setSpacing(Constants_City.SPACING_HBOX);
        
        return structureInformation;
    }


    /**
     * This method show all attributes of a unit in labels.
     *
     * @author Jule Degener
     * @param unit Unit about which information is provided.
     * @return A VBox with Labels of the attributes of a unit is returned.
     * @precondition none
     * @postcondition All attributes of a unit can be shown.
     */
    private static VBox showAllAttributesOfUnit (Unit unit)
    {
        VBox attributesOfUnit = new VBox();

        Label name = new Label(unit.getName());
        Label shield = new Label(Constants_City.INFO_SHIELD);
        Label health = new Label(Constants_City.INFO_HEALTH);
        Label mana = new Label(Constants_City.INFO_MANA);
        Label meele = new Label(Constants_City.INFO_MEELE);
        Label ranged = new Label(Constants_City.INFO_RANGED);
        Label ammo = new Label(Constants_City.INFO_AMMO);
        Label dodge = new Label(Constants_City.INFO_DODGE);
        Label magicResist = new Label(Constants_City.INFO_MAGIC_RESISTENCE);
        Label rangeOfMotion = new Label(Constants_City.INFO_RANGE_OF_MOTION);
        Label initiative = new Label(Constants_City.INFO_INITIATIVE);
        Label magicDamage = new Label(Constants_City.INFO_MAGIC_DAMAGE);
        Label myAttack = new Label(Constants_City.INFO_MY_ATTACK);

        attributesOfUnit.getChildren().addAll(name, shield, health, mana, meele, ranged, ammo, dodge, magicResist,
                rangeOfMotion, initiative, magicDamage, myAttack);

        return attributesOfUnit;
    }


    /**
     * This method show the value of all attributes of a unit in labels.
     *
     * @author Jule Degener
     * @param unit Unit about which information is provided.
     * @return A VBox with Labels of the value of all attributes of a unit is returned.
     * @precondition none
     * @postcondition Values of all attributes of a unit can be shown.
     */
    private static VBox showValuesOfAttributesOfUnit (Unit unit)
    {
        VBox informationAboutUnit = new VBox();

        Label infoName = new Label(Constants_DefaultValues.EMPTY);
        Label numberOfShield = new Label(Integer.toString(unit.getShield()));
        Label numberOfHealth = new Label(Integer.toString(unit.getHealth()));
        Label numberOfMana = new Label(Integer.toString(unit.getMana()));
        Label numberOfMeele = new Label(Integer.toString(unit.getMeele()));
        Label numberOfRanged = new Label(Integer.toString(unit.getRanged()));
        Label numberOfAmmo = new Label(Integer.toString(unit.getAmmo()));
        Label numberOfDodge = new Label(Integer.toString(unit.getDodge()));
        Label numberOfMagicResist = new Label(Integer.toString(unit.getMagicresist()));
        Label numberOfRangeOfMotion = new Label(Integer.toString(unit.getRangeOfMotion()));
        Label numberOfInitiative = new Label(Integer.toString(unit.getInitiative()));
        Label numberOfMagicDamage = new Label(Integer.toString(unit.getMagicDamage()));

        informationAboutUnit.getChildren().addAll(infoName, numberOfShield, numberOfHealth, numberOfMana, numberOfMeele,
                numberOfRanged, numberOfAmmo, numberOfDodge, numberOfMagicResist, numberOfRangeOfMotion,
                numberOfInitiative, numberOfMagicDamage);

        return informationAboutUnit;
    }
    
    
    //---------------------GUI BaseCamp-------------------------------------


    /**
     * Method to represent the inside of the BaseCamp with all functions of it.
     *
     * @author Jule Degener
     * @param gridpane GridPane which structures all elements of this scene.
     * @precondition One instance of PlayerController exist.
     * @postcondition All elements for the scene to show the inside of the BaseCamp is created.
     */
    public static void getInsideBaseCamp (GridPane gridpane)
    {
        Button recruitRats = new Button(Constants_City.BASECAMP_BUTTON_RATS);
        Button recruitBeetles = new Button(Constants_City.BASECAMP_BUTTON_BEETLES);
        Button recruitMosquitoes = new Button(Constants_City.BASECAMP_BUTTON_MOSQUITOES);
        
        HBox infoRats = informAboutUnit(FractionDonkey.getInstanceOfFractiondonkey().getRats());
        HBox infoBeetle = informAboutUnit(FractionDonkey.getInstanceOfFractiondonkey().getBeetle());
        HBox infoMosquitoes = informAboutUnit(FractionDonkey.getInstanceOfFractiondonkey().getMosquitoes());
        
        informAboutUnitsFromFractionDonkey(gridpane, infoRats, infoBeetle, infoMosquitoes, recruitRats, recruitBeetles,
                recruitMosquitoes);

        recruitRats.setOnAction(event ->
                PlayerController.getInstance().
                        addUnitToTheTeam(FractionDonkey.getInstanceOfFractiondonkey().getRats()));
        
        recruitBeetles.setOnAction(event ->
                PlayerController.getInstance().
                        addUnitToTheTeam(FractionDonkey.getInstanceOfFractiondonkey().getBeetle()));

        recruitMosquitoes.setOnAction(event ->
                PlayerController.getInstance().
                        addUnitToTheTeam(FractionDonkey.getInstanceOfFractiondonkey().getMosquitoes()));
    }


    /**
     * Method to sort three HBoxes and three buttons on one GridPane.
     *
     * @author Jule Degener
     * @param gridpane Basis gridPane
     * @param info1 First HBox
     * @param info2 Second HBox
     * @param info3 Third HBox
     * @param button1 First button
     * @param button2 Second button
     * @param button3 Third button
     * @precondition none
     * @postcondition A filled gridPane with HBoxes and buttons is created.
     */
    private static void informAboutUnitsFromFractionDonkey (GridPane gridpane, HBox info1, HBox info2, HBox info3,
                                                            Button button1, Button button2, Button button3)
    {
        gridpane.add(info1, Constants_City.GRIDPANE_ROW_ONE, Constants_City.GRIDPANE_COLUMN_TWO);
        gridpane.add(info2, Constants_City.GRIDPANE_ROW_FIVE, Constants_City.GRIDPANE_COLUMN_TWO);
        gridpane.add(info3, Constants_City.GRIDPANE_ROW_NINE, Constants_City.GRIDPANE_COLUMN_TWO);
        gridpane.add(button1, Constants_City.GRIDPANE_ROW_ONE, Constants_City.GRIDPANE_COLUMN_THREE);
        gridpane.add(button2, Constants_City.GRIDPANE_ROW_FIVE, Constants_City.GRIDPANE_COLUMN_THREE);
        gridpane.add(button3, Constants_City.GRIDPANE_ROW_NINE, Constants_City.GRIDPANE_COLUMN_THREE);
        gridpane.setVgap(Constants_City.GRIDPANE_VGAP);
    }

    
    //---------------------------------GUI MagicAmplifier ----------------------------------------


    /**
     * Method to represent the inside of the MagicAmplifier with all functions of it.
     *
     * @author Jule Degener
     * @param gridpane GridPane which structures all elements of this scene.
     * @precondition none
     * @postcondition All elements for the scene to show the inside of the MagicAmplifier is created.
     */
    public static void getInsideMagicAmplifier (GridPane gridpane)
    {
        Button pushMagic = new Button(Constants_City.MAGIC_AMPLIFIER_BUTTON);
        
        gridpane.add(pushMagic, Constants_City.GRIDPANE_ROW_ONE, Constants_City.GRIDPANE_COLUMN_THREE);

        pushMagic.setOnAction(event -> MagicAmplifier.getInstanceOfMagicamplifier().pushMagicSkillOfPlayer());
    }

    
    // -------------------------GUI Headquarter ---------------------------


    /**
     * Method to represent the inside of the MagicAmplifier with all functions of it.
     *
     * @author Jule Degener
     * @param gridPane GridPane which structures all elements of this scene.
     * @precondition none
     * @postcondition All elements for the scene to show the inside of the Headquarter is created.
     */
    public static void getInsideHeadquarter (GridPane gridPane)
    {
        Button mission1 = new Button(Constants_City.HEADQUARTER_BUTTON_M1);
        Button mission2 = new Button(Constants_City.HEADQUARTER_BUTTON_M2);
        Button mission3 = new Button(Constants_City.HEADQUARTER_BUTTON_M3);
        Button mission4 = new Button(Constants_City.HEADQUARTER_BUTTON_M4);
        
        gridPane.add(mission1, Constants_City.GRIDPANE_ROW_ONE, Constants_City.GRIDPANE_COLUMN_THREE);
        gridPane.add(mission2, Constants_City.GRIDPANE_ROW_TWO, Constants_City.GRIDPANE_COLUMN_THREE);
        gridPane.add(mission3, Constants_City.GRIDPANE_ROW_ONE, Constants_City.GRIDPANE_COLUMN_FOUR);
        gridPane.add(mission4, Constants_City.GRIDPANE_ROW_TWO, Constants_City.GRIDPANE_COLUMN_FOUR);
        
        showWayToMissionOne(mission1);
    }


    /**
     * Sets up a button to show the way to Mission One when clicked.
     * This method adds an arrow pointing to the mission's location on the map.
     * @author Jule Degener, Jonas Helfer
     * @param button The Button to which the action will be attached.
     * @precondition The Constants_Map class is properly defined with ARROW_LEFT and PLAYER_SIZE constants.
     *               The Map, PanelController,Controller instances are properly initialized and accessible.
     *               The OutputImageView class is implemented and available.
     * @postcondition When the button is clicked, an arrow image is added to the map pane,
     *                pointing towards the location of Mission One.
     *                The player's position is updated after adding the arrow.
     */
    private static void showWayToMissionOne (Button button)
    {
        button.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle (ActionEvent actionEvent)
            {
                OutputImageView arrowView = new OutputImageView(new Image(Constants_Map.ARROW_LEFT),
                        Constants_Map.PLAYER_SIZE);
                Map.getInstance().getPane().getChildren().add(arrowView);

                arrowView.setCoordinates(PanelController.getInstance().getCoordinateFromPanelTile(Map.getInstance().
                        getPanel(), Constants_City.ROW_INDEX, Constants_City.COLUMN_INDEX));
                
                PlayerController.getInstance().updatePlayer();
            }
        });
    }
    
    
    // ---------------------------- TrainingsArea ------------------------------------


    /**
     * Method to represent the inside of the TrainingArea with all functions of it.
     *
     * @author Jule Degener
     * @param gridPane GridPane which structures all elements of this scene.
     * @precondition none
     * @postcondition All elements for the scene to show the inside of the TrainingArea is created.
     */
    public static void getInsideTrainingsArea (GridPane gridPane)
    {
        Button DonkeyAttackTraining = new Button(Constants_City.TRAINING_AREA_BUTTON_DONKEY_ATTACK);
        Button DonkeyDefenseTraining = new Button(Constants_City.TRAINING_AREA_BUTTON_DONKEY_DEFENSE);
        Button DogsAttackTraining = new Button(Constants_City.TRAINING_AREA_BUTTON_DOG_ATTACK);
        Button DogsDefenseTraining = new Button(Constants_City.TRAINING_AREA_BUTTON_DOG_DEFENSE);
        Button CatsAttackTraining = new Button(Constants_City.TRAINING_AREA_BUTTON_CAT_ATTACK);
        Button CatsDefenseTraining = new Button(Constants_City.TRAINING_AREA_BUTTON_CAT_DEFENSE);
        Button ChickenAttackTraining = new Button(Constants_City.TRAINING_AREA_BUTTON_CHICKEN_ATTACK);
        Button ChickenDefenseTraining = new Button(Constants_City.TRAINING_AREA_BUTTON_CHICKEN_DEFENSE);
        
        gridPane.add(DogsAttackTraining, Constants_City.GRIDPANE_ROW_ONE, Constants_City.GRIDPANE_COLUMN_THREE);
        gridPane.add(DogsDefenseTraining, Constants_City.GRIDPANE_ROW_TWO, Constants_City.GRIDPANE_COLUMN_THREE);
        gridPane.add(CatsAttackTraining, Constants_City.GRIDPANE_ROW_ONE, Constants_City.GRIDPANE_COLUMN_FOUR);
        gridPane.add(CatsDefenseTraining, Constants_City.GRIDPANE_ROW_TWO, Constants_City.GRIDPANE_COLUMN_FOUR);
        gridPane.add(ChickenAttackTraining, Constants_City.GRIDPANE_ROW_ONE, Constants_City.GRIDPANE_COLUMN_FIVE);
        gridPane.add(ChickenDefenseTraining, Constants_City.GRIDPANE_ROW_TWO, Constants_City.GRIDPANE_COLUMN_FIVE);
        gridPane.add(DonkeyAttackTraining, Constants_City.GRIDPANE_ROW_ONE, Constants_City.GRIDPANE_COLUMN_SIX);
        gridPane.add(DonkeyDefenseTraining, Constants_City.GRIDPANE_ROW_TWO, Constants_City.GRIDPANE_COLUMN_SIX);

        setAllButtonsOnAction(DogsAttackTraining, DogsDefenseTraining, CatsAttackTraining, CatsDefenseTraining,
                ChickenAttackTraining, ChickenDefenseTraining, DonkeyAttackTraining, DonkeyDefenseTraining);
    }


    /**
     * Method to set eight buttons on action with the methods from the building TrainingArea.
     *
     * @author Jule Degener
     * @param button1 Button for the attack of dogs
     * @param button2 Button for the defense of dogs
     * @param button3 Button for the attack of cats
     * @param button4 Button for the defense of cats
     * @param button5 Button for the attack of chicken
     * @param button6 Button for the defense of chicken
     * @param button7 Button for the attack of donkeys
     * @param button8 Button for the defense of donkeys
     * @precondition none
     * @postcondition All eight buttons has been assigned to one event.
     */
    private static void setAllButtonsOnAction (Button button1, Button button2, Button button3, Button button4,
                                               Button button5, Button button6, Button button7, Button button8)
    {
        button1.setOnAction(e -> TrainingArea.getInstanceOfTrainingarea().trainFractionDogAttack());
        button2.setOnAction(e -> TrainingArea.getInstanceOfTrainingarea().trainFractionDogDefense());
        button3.setOnAction(e -> TrainingArea.getInstanceOfTrainingarea().trainFractionCatAttack());
        button4.setOnAction(e -> TrainingArea.getInstanceOfTrainingarea().trainFractionCatDefense());
        button5.setOnAction(e -> TrainingArea.getInstanceOfTrainingarea().trainFractionChickenAttack());
        button6.setOnAction(e -> TrainingArea.getInstanceOfTrainingarea().trainFractionChickenDefense());
        button7.setOnAction(e -> TrainingArea.getInstanceOfTrainingarea().trainFractionDonkeyAttack());
        button8.setOnAction(e -> TrainingArea.getInstanceOfTrainingarea().trainFractionDonkeyDefense());
    }

    
    // ------------------------GUI Pub --------------------------


    /**
     * Method to represent the inside of the Pub with all functions of it.
     *
     * @author Jule Degener
     * @param gridPane GridPane which structures all elements of this scene.
     * @precondition none
     * @postcondition All elements for the scene to show the inside of the Pub is created.
     */
    public static void getInsidePub (GridPane gridPane)
    {
        Button findMercenary = new Button(Constants_City.PUB_BUTTON);
        Label info = new Label(Constants_City.PUB_LABEL);
        
        gridPane.add(findMercenary, Constants_City.GRIDPANE_ROW_THREE, Constants_City.GRIDPANE_COLUMN_TWO);
        gridPane.add(info, Constants_City.GRIDPANE_ROW_TWO, Constants_City.GRIDPANE_COLUMN_TWO);
        
        findMercenary.setOnAction(event -> Pub.getInstanceOfPub().recruitAMercenary());
    }


    //-----------------------------GUI Forge---------------------------------


    /**
     * Method to represent the inside of the Forge with all functions of it.
     *
     * @author Jule Degener
     * @param gridPane GridPane which structures all elements of this scene.
     * @precondition none
     * @postcondition All elements for the scene to show the inside of the Forge is created.
     */
    public static void getInsideForge (GridPane gridPane)
    {
        Button chooseHotMilkWithHoney = new Button(Constants_City.FORGE_HOT_MILK_WITH_HONEY);
        Button chooseChickenSoup = new Button(Constants_City.FORGE_CHICKEN_SOUP);
        Button choosePowderKeg = new Button(Constants_City.FORGE_POWDER_KEG);
        Button choosePocketKnife = new Button(Constants_City.FORGE_POCKED_KNIFE);
        Button updateFirstArtifact = new Button(Constants_City.FORGE_ARTIFACT_1_UPDATE);
        Button updateSecondArtifact = new Button(Constants_City.FORGE_ARTIFACT_2_UPDATE);
        Button deleteArtifacts = new Button(Constants_City.FORGE_DELETE_ARTIFACTS);

        gridPane.add(chooseHotMilkWithHoney, Constants_City.GRIDPANE_ROW_ONE, Constants_City.GRIDPANE_COLUMN_ONE);
        gridPane.add(chooseChickenSoup, Constants_City.GRIDPANE_ROW_ONE, Constants_City.GRIDPANE_COLUMN_TWO);
        gridPane.add(choosePowderKeg, Constants_City.GRIDPANE_ROW_ONE, Constants_City.GRIDPANE_COLUMN_THREE);
        gridPane.add(choosePocketKnife, Constants_City.GRIDPANE_ROW_ONE, Constants_City.GRIDPANE_COLUMN_FOUR);
        gridPane.add(updateFirstArtifact, Constants_City.GRIDPANE_ROW_TWO, Constants_City.GRIDPANE_COLUMN_ONE);
        gridPane.add(updateSecondArtifact, Constants_City.GRIDPANE_ROW_TWO, Constants_City.GRIDPANE_COLUMN_TWO);
        gridPane.add(deleteArtifacts, Constants_City.GRIDPANE_ROW_TWO, Constants_City.GRIDPANE_COLUMN_THREE);

        chooseHotMilkWithHoney.setOnAction(event -> Forge.forgeHotMilkWithHoney());
        chooseChickenSoup.setOnAction(event -> Forge.forgeChickenSoup());
        choosePocketKnife.setOnAction(event -> Forge.forgePocketKnife());
        choosePowderKeg.setOnAction(event -> Forge.forgePowderKeg());
        updateFirstArtifact.setOnAction(event ->
                {Forge.upgradeArtifact(Player.getInstance().getListOfArtifacts().getFirst());
                    updateFirstArtifact.setDisable(true); updateSecondArtifact.setDisable(true);});
        updateSecondArtifact.setOnAction(event ->
                {Forge.upgradeArtifact(Player.getInstance().getListOfArtifacts()
                        .get(Constants_Player_Units.PLAYER_SECOND_ARTIFACT_INDEX));
                    updateFirstArtifact.setDisable(true); updateSecondArtifact.setDisable(true);});
        deleteArtifacts.setOnAction(event -> Forge.deleteListOfArtifacts());
    }

    //-----------------------Marketplace-------------------------------------------


    /**
     * Method to represent the inside of the Forge with all functions of it.
     *
     * @author Jule Degener
     * @param gridPane GridPane which structures all elements of this scene.
     * @precondition none
     * @postcondition All elements for the scene to show the inside of the Marketplace is created.
     */
    public static void getInsideMarketplace (GridPane gridPane)
    {
        Button buyBeer = new Button();
        Button buyWood = new Button();
        Button buyBrick = new Button();
        Button buyEssence = new Button();

        gridPane.add(buyBeer, Constants_City.GRIDPANE_ROW_ONE, Constants_City.GRIDPANE_COLUMN_ONE);
        gridPane.add(buyWood, Constants_City.GRIDPANE_ROW_ONE, Constants_City.GRIDPANE_COLUMN_TWO);
        gridPane.add(buyBrick, Constants_City.GRIDPANE_ROW_ONE, Constants_City.GRIDPANE_COLUMN_THREE);
        gridPane.add(buyEssence, Constants_City.GRIDPANE_ROW_ONE, Constants_City.GRIDPANE_COLUMN_FOUR);

        buyBeer.setOnAction(event -> Marketplace.getInstanceOfMarketplace().buyBeerFromGold());
        buyWood.setOnAction(event -> Marketplace.getInstanceOfMarketplace().buyWoodFromGold());
        buyBrick.setOnAction(event -> Marketplace.getInstanceOfMarketplace().buyBrickFromGold());
        buyEssence.setOnAction(event -> Marketplace.getInstanceOfMarketplace().buyEssenceFromGold());
    }

    
    //---------------------- GUI FractionCamps --------------------------------


    /**
     * Method to represent the inside of the FractionCamp Dog with all functions of it.
     *
     * @author Jule Degener
     * @param gridpane GridPane which structures all elements of this scene.
     * @precondition An instance of the PlayerController must exist.
     * @postcondition All elements for the scene to show the inside of the FractionCamp Dog is created.
     */
    public static void getInsideFractionCampDog (GridPane gridpane)
    {
        Button recruitGoldenRetriever = new Button(Constants_City.FRACTION_DOG_BUTTON_GOLDEN_RETRIEVER);
        Button recruitGermanShepherd = new Button(Constants_City.FRACTION_DOG_BUTTON_GERMAN_SHEPHERD);
        Button recruitHunter = new Button(Constants_City.FRACTION_DOG_BUTTON_HUNTER);
        Button recruitBulldog = new Button(Constants_City.FRACTION_DOG_BUTTON_BULLDOG);
        Button recruitHundini = new Button(Constants_City.FRACTION_DOG_BUTTON_HUNDINI);
        
        HBox infoGoldenRetriever = informAboutUnit(FractionDog.getInstanceOfFractionDog().getGoldenRetriever());
        HBox infoGermanShepherd = informAboutUnit(FractionDog.getInstanceOfFractionDog().getGermanShepherd());
        HBox infoHunter = informAboutUnit(FractionDog.getInstanceOfFractionDog().getHunter());
        HBox infoBulldog = informAboutUnit(FractionDog.getInstanceOfFractionDog().getBulldog());
        HBox infoHundini = informAboutUnit(FractionDog.getInstanceOfFractionDog().getHundini());
        
        informAboutTheUnitsOfOneFraction(gridpane, infoGoldenRetriever, infoGermanShepherd, infoHunter, infoBulldog,
                infoHundini, recruitGoldenRetriever, recruitGermanShepherd, recruitHunter, recruitBulldog,
                recruitHundini);
        
        recruitGoldenRetriever.setOnAction(e -> PlayerController.getInstance().
                addUnitToTheTeam(FractionDog.getInstanceOfFractionDog().getGoldenRetriever()));
        recruitGermanShepherd.setOnAction(e -> PlayerController.getInstance().
                addUnitToTheTeam(FractionDog.getInstanceOfFractionDog().getGermanShepherd()));
        recruitHunter.setOnAction(e -> PlayerController.getInstance().
                addUnitToTheTeam(FractionDog.getInstanceOfFractionDog().getHunter()));
        recruitBulldog.setOnAction(e -> PlayerController.getInstance().
                addUnitToTheTeam(FractionDog.getInstanceOfFractionDog().getBulldog()));
        recruitHundini.setOnAction(e -> PlayerController.getInstance().
                addUnitToTheTeam(FractionDog.getInstanceOfFractionDog().getHundini()));
    }


    /**
     * Method to represent the inside of the FractionCamp Cat with all functions of it.
     *
     * @author Jule Degener
     * @param gridpane GridPane which structures all elements of this scene.
     * @precondition An instance of the PlayerController must exist.
     * @postcondition All elements for the scene to show the inside of the FractionCamp Cat is created.
     */
    public static void getInsideFractionCampCat (GridPane gridpane)
    {
        Button recruitCat = new Button(Constants_City.FRACTION_CAT_BUTTON_CAT);
        Button recruitJaguar = new Button(Constants_City.FRACTION_CAT_BUTTON_JAGUAR);
        Button recruitHousekeeper = new Button(Constants_City.FRACTION_CAT_BUTTON_HOUSEKEEPER);
        Button recruitTiger = new Button(Constants_City.FRACTION_CAT_BUTTON_TIGER);
        Button recruitBingus = new Button(Constants_City.FRACTION_CAT_BUTTON_BINGUS);
        
        HBox infoCat = informAboutUnit(FractionCat.getInstanceOfFractionCat().getCat());
        HBox infoJaguar = informAboutUnit(FractionCat.getInstanceOfFractionCat().getJaguar());
        HBox infoHousekeeper = informAboutUnit(FractionCat.getInstanceOfFractionCat().getHousekeeper());
        HBox infoTiger = informAboutUnit(FractionCat.getInstanceOfFractionCat().getTiger());
        HBox infoBingus = informAboutUnit(FractionCat.getInstanceOfFractionCat().getBingus());
        
        informAboutTheUnitsOfOneFraction(gridpane, infoCat, infoJaguar, infoHousekeeper, infoTiger, infoBingus,
                recruitCat, recruitJaguar, recruitHousekeeper, recruitTiger, recruitBingus);
        
        recruitCat.setOnAction(e -> PlayerController.getInstance().
                addUnitToTheTeam(FractionCat.getInstanceOfFractionCat().getCat()));
        recruitJaguar.setOnAction(e -> PlayerController.getInstance().
                addUnitToTheTeam(FractionCat.getInstanceOfFractionCat().getJaguar()));
        recruitHousekeeper.setOnAction(e -> PlayerController.getInstance().
                addUnitToTheTeam(FractionCat.getInstanceOfFractionCat().getHousekeeper()));
        recruitTiger.setOnAction(e -> PlayerController.getInstance().
                addUnitToTheTeam(FractionCat.getInstanceOfFractionCat().getTiger()));
        recruitBingus.setOnAction(e -> PlayerController.getInstance().
                addUnitToTheTeam(FractionCat.getInstanceOfFractionCat().getBingus()));
    }


    /**
     * Method to represent the inside of the FractionCamp Chicken with all functions of it.
     *
     * @author Jule Degener
     * @param gridpane GridPane which structures all elements of this scene.
     * @precondition An instance of the PlayerController must exist.
     * @postcondition All elements for the scene to show the inside of the FractionCamp Chicken is created.
     */
    public static void getInsideFractionCampChicken (GridPane gridpane)
    {
        Button recruitChick = new Button(Constants_City.FRACTION_CHICKEN_BUTTON_CHICK);
        Button recruitFightingChicken = new Button(Constants_City.FRACTION_CHICKEN_BUTTON_FIGHTING_CHICKEN);
        Button recruitChef = new Button(Constants_City.FRACTION_CHICKEN_BUTTON_CHEF);
        Button recruitTurkey = new Button(Constants_City.FRACTION_CHICKEN_BUTTON_TURKEY);
        Button recruitChickenWithHat = new Button(Constants_City.FRACTION_CHICKEN_BUTTON_CHICKEN_WITH_HAT);
        
        HBox infoChick = informAboutUnit(FractionChicken.getInstanceOfFractionChicken().getChick());
        HBox infoFightingChicken = informAboutUnit(FractionChicken.getInstanceOfFractionChicken().getFightingChicken());
        HBox infoChef = informAboutUnit(FractionChicken.getInstanceOfFractionChicken().getChef());
        HBox infoTurkey = informAboutUnit(FractionChicken.getInstanceOfFractionChicken().getTurkey());
        HBox infoChickenWithHat = informAboutUnit(FractionChicken.getInstanceOfFractionChicken().getChickenWithHat());
        
        informAboutTheUnitsOfOneFraction(gridpane, infoChick, infoFightingChicken, infoChef, infoTurkey,
                infoChickenWithHat, recruitChick, recruitFightingChicken, recruitChef, recruitTurkey,
                recruitChickenWithHat);
        
        recruitChick.setOnAction(e -> PlayerController.getInstance().
                addUnitToTheTeam(FractionChicken.getInstanceOfFractionChicken().getChick()));
        recruitFightingChicken.setOnAction(e -> PlayerController.getInstance().
                addUnitToTheTeam(FractionChicken.getInstanceOfFractionChicken().getFightingChicken()));
        recruitChef.setOnAction(e -> PlayerController.getInstance().
                addUnitToTheTeam(FractionChicken.getInstanceOfFractionChicken().getChef()));
        recruitTurkey.setOnAction(e -> PlayerController.getInstance().
                addUnitToTheTeam(FractionChicken.getInstanceOfFractionChicken().getTurkey()));
        recruitChickenWithHat.setOnAction(e -> PlayerController.getInstance().
                addUnitToTheTeam(FractionChicken.getInstanceOfFractionChicken().getChickenWithHat()));
    }


    /**
     * Method to show all information of a Unit from one fraction.
     *
     * @author Jule Degener
     * @param gridpane GridPane which structures all elements
     * @param info1 First HBox
     * @param info2 Second HBox
     * @param info3 Third HBox
     * @param info4 Fourth HBox
     * @param info5 Fifth HBox
     * @param button1 First button
     * @param button2 Second button
     * @param button3 Third button
     * @param button4 Fourth button
     * @param button5 Fifth button
     * @precondition none
     * @postcondition All information about a unit of one fraction is structures in one gridPane.
     */
    private static void informAboutTheUnitsOfOneFraction (GridPane gridpane, HBox info1, HBox info2, HBox info3,
                                                          HBox info4, HBox info5, Button button1, Button button2,
                                                          Button button3, Button button4, Button button5)
    {
        gridpane.add(info1, Constants_City.GRIDPANE_ROW_ONE, Constants_City.GRIDPANE_COLUMN_TWO);
        gridpane.add(info2, Constants_City.GRIDPANE_ROW_FIVE, Constants_City.GRIDPANE_COLUMN_TWO);
        gridpane.add(info3, Constants_City.GRIDPANE_ROW_NINE, Constants_City.GRIDPANE_COLUMN_TWO);
        gridpane.add(info4, Constants_City.GRIDPANE_ROW_THIRTEEN, Constants_City.GRIDPANE_COLUMN_TWO);
        gridpane.add(info5, Constants_City.GRIDPANE_ROW_SEVENTEEN, Constants_City.GRIDPANE_COLUMN_TWO);
        gridpane.add(button1, Constants_City.GRIDPANE_ROW_ONE, Constants_City.GRIDPANE_COLUMN_THREE);
        gridpane.add(button2, Constants_City.GRIDPANE_ROW_FIVE, Constants_City.GRIDPANE_COLUMN_THREE);
        gridpane.add(button3, Constants_City.GRIDPANE_ROW_NINE, Constants_City.GRIDPANE_COLUMN_THREE);
        gridpane.add(button4, Constants_City.GRIDPANE_ROW_THIRTEEN, Constants_City.GRIDPANE_COLUMN_THREE);
        gridpane.add(button5, Constants_City.GRIDPANE_ROW_SEVENTEEN, Constants_City.GRIDPANE_COLUMN_THREE);
        gridpane.setVgap(Constants_City.GRIDPANE_VGAP);
    }


    /**
     * Getter-method to get the instance of the BuildingController
     *
     * @author Michael Markov, Jule Degener
     * @return Instance of the BuildingController
     * @precondition none
     * @postcondition One instance of BuildingController exist in the program.
     */
    public static BuildingController getInstance ()
    {
        if (instance == null)
        {
            throw new IllegalStateException(Constants_ExceptionMessages.SINGLETON_NOT_INITIALIZED);
        }
        return instance;
    }
}
