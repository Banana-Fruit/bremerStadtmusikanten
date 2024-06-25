package control;


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
import model.player.FractionDog;
import model.player.FractionDonkey;
import model.player.Inventory;
import model.userInterface.TransparentButton;
import model.userInterface.showables.Map;
import resources.constants.Constants_DefaultValues;
import resources.constants.Constants_ExceptionMessages;
import resources.constants.scenes.Constants_Building;
import resources.constants.scenes.Constants_City;
import resources.constants.scenes.Constants_Map;
import view.OutputImageView;

import java.awt.*;


public class BuildingController
{
    private static BuildingController instance = null;


    // default controller
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

            gridPane.add(gold, Constants_Building.GRIDPANE_COLUMN_ONE,Constants_Building.GRIDPANE_ROW_ONE);
            gridPane.add(brick, Constants_Building.GRIDPANE_COLUMN_ONE, Constants_Building.GRIDPANE_ROW_TWO);
            gridPane.add(wood, Constants_Building.GRIDPANE_COLUMN_ONE,Constants_Building.GRIDPANE_ROW_THREE);
            gridPane.add(beer, Constants_Building.GRIDPANE_COLUMN_ONE, Constants_Building.GRIDPANE_ROW_FOUR);
            gridPane.add(essence, Constants_Building.GRIDPANE_COLUMN_ONE, Constants_Building.GRIDPANE_ROW_FIVE);
            gridPane.add(questionUnlockBuilding, Constants_Building.GRIDPANE_COLUMN_ONE, Constants_Building.GRIDPANE_ROW_SIX);
            gridPane.add(yes, Constants_Building.GRIDPANE_COLUMN_ONE, Constants_Building.GRIDPANE_ROW_SEVEN);
            gridPane.add(no, Constants_Building.GRIDPANE_COLUMN_TWO, Constants_Building.GRIDPANE_ROW_SEVEN);


            Stage popUpStage = new Stage();
            Scene scene = new Scene(gridPane, Constants_Building.SCENE_WIDTH_UNLOCK_BUILDING, Constants_Building.SCENE_HEIGHT_UNLOCK_BUILDING);
            unlockBuildingWithButton(popUpStage, building, yes);
            disappearPopUpWindow(popUpStage, no);

            popUpStage.setScene(scene);
            popUpStage.show();
        }
        else
        {
            System.out.println(Constants_Building.IS_UNLOCKED);
            SceneController.buildSceneBuildingInside(building);
        }

    }


    private static void unlockBuildingWithButton (Stage stage, Building building, Button button)
    {
        button.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                building.unlockBuilding();
                stage.close();
                SceneController.buildSceneBuildingInside(building);
            }
        });
    }


    private static void disappearPopUpWindow (Stage stage, Button button)
    {
        button.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                stage.close();
            }
        });
    }


    public static void getInformABoutBuilding (Building building, Button button, Stage stage)
    {
        if (building.getIsUnlocked() == false)
        {
            button.setOnAction(new EventHandler<ActionEvent>()
            {
                @Override
                public void handle (ActionEvent actionEvent)
                {
                    showPriceOfBuilding(building);
                }
            });
        }
        if (building.getIsUnlocked() == true)
        {
            button.setOnAction(new EventHandler<ActionEvent>()
            {
                @Override
                public void handle (ActionEvent actionEvent)
                {
                    SceneController.buildSceneBuildingInside(building);
                }
            });
        }

    }




    public static boolean checkIfBuildingIsUnlock (Building building)
    {
        Inventory inventory = Inventory.getInstanceOfInventory();

        if ((inventory.getInventoryGold()>= building.numberOfGold) &&
                (inventory.getInventoryBrick() >= building.numberOfBrick) &&
                (inventory.getInventoryWood() >= building.numberOfWood) &&
                (inventory.getInventoryBeer() >= building.numberOfBeer) &&
                (inventory.getInventoryEssence() >= building.numberOfEssence))
        {
            System.out.println(Constants_Building.BUILDING_UNLOCKED);
            Inventory.payWithTheInventoryForABuilding(building);
            DisplayController.getInstance().updateInventory();
            building.setUnlocked(true);
            return true;
        }
        else
        {
            System.out.println(Constants_Building.BUILDING_LOCKED);
            return false;
        }
    }

    public void addButtons()
    {
        Map.getInstance().getPane().getChildren().add(createBuildingButton(Constants_City.CITY_LABEL_BASECAMP, () -> showPriceOfBuilding(BaseCamp.getInstanceOfBasecamp()),Constants_Building.BUTTON_WIDTH_BASECAMP,Constants_Building.BUTTON_HEIGHT_BASECAMP, BaseCamp.getInstanceOfBasecamp().getPositionUpperLeft()));
        Map.getInstance().getPane().getChildren().add(createBuildingButton(Constants_City.CITY_LABEL_FORGE, () -> showPriceOfBuilding(Forge.getInstanceOfForge()),Constants_Building.BUTTON_WIDTH_FORGE,Constants_Building.BUTTON_HEIGHT_FORGE, Forge.getInstanceOfForge().getPositionUpperLeft()));
        Map.getInstance().getPane().getChildren().add(createBuildingButton(Constants_City.CITY_LABEL_FRACTION_CAMP_DOG, () -> showPriceOfBuilding(FractionCampDog.getInstanceOfFractionDogcamp()),Constants_Building.BUTTON_WIDTH_FRACTIONCAMP_DOG,Constants_Building.BUTTON_HEIGHT_FRACTIONCAMP_DOG, FractionCampDog.getInstanceOfFractionDogcamp().getPositionUpperLeft()));
        Map.getInstance().getPane().getChildren().add(createBuildingButton(Constants_City.CITY_LABEL_FRACTION_CAMP_CAT, () -> showPriceOfBuilding(FractionCampCat.getInstanceOfFractionCatCamp()),Constants_Building.BUTTON_WIDTH_FRACTIONCAMP_CAT,Constants_Building.BUTTON_HEIGHT_FRACTIONCAMP_CAT, FractionCampCat.getInstanceOfFractionCatCamp().getPositionUpperLeft()));
        Map.getInstance().getPane().getChildren().add(createBuildingButton(Constants_City.CITY_LABEL_FRACTION_CAMP_CHICKEN, () -> showPriceOfBuilding(FractionCampChicken.getInstanceOfFractionChickenCamp()),Constants_Building.BUTTON_WIDTH_FRACTIONCAMP_CHICKEN,Constants_Building.BUTTON_HEIGHT_FRACTIONCAMP_CHICKEN, FractionCampChicken.getInstanceOfFractionChickenCamp().getPositionUpperLeft()));
        Map.getInstance().getPane().getChildren().add(createBuildingButton(Constants_City.CITY_LABEL_HEADQUARTER, () -> showPriceOfBuilding(Headquarter.getInstanceOfHeadquarter()),Constants_Building.BUTTON_WIDTH_HEADQUARTER,Constants_Building.BUTTON_HEIGHT_HEADQUARTER, Headquarter.getInstanceOfHeadquarter().getPositionUpperLeft()));
        Map.getInstance().getPane().getChildren().add(createBuildingButton(Constants_City.CITY_LABEL_MAGIC_AMPLIFIER, () -> showPriceOfBuilding(MagicAmplifier.getInstanceOfMagicamplifier()),Constants_Building.BUTTON_WIDTH_MAGICAMPLIFIER,Constants_Building.BUTTON_HEIGHT_MAGICAMPLIFIER, MagicAmplifier.getInstanceOfMagicamplifier().getPositionUpperLeft()));
        Map.getInstance().getPane().getChildren().add(createBuildingButton(Constants_City.CITY_LABEL_MARKETPLACE, () -> showPriceOfBuilding(Marketplace.getInstanceOfMarketplace()),Constants_Building.BUTTON_WIDTH_MARKETPLACE, Constants_Building.BUTTON_HEIGHT_MARKETPLACE, Marketplace.getInstanceOfMarketplace().getPositionUpperLeft()));
        Map.getInstance().getPane().getChildren().add(createBuildingButton(Constants_City.CITY_LABEL_PUB, () -> showPriceOfBuilding(Pub.getInstanceOfPub()),Constants_Building.BUTTON_WIDTH_PUB,Constants_Building.BUTTON_HEIGHT_PUB, Pub.getInstanceOfPub().getPositionUpperLeft()));
        Map.getInstance().getPane().getChildren().add(createBuildingButton(Constants_City.CITY_LABEL_TRAININGS_AREA, () -> showPriceOfBuilding(TrainingArea.getInstanceOfTrainingarea()),Constants_Building.BUTTON_WIDTH_TRAINING_AREA,Constants_Building.BUTTON_HEIGHT_TRAINING_AREA, TrainingArea.getInstanceOfTrainingarea().getPositionUpperLeft()));
        ;
    }

    public TransparentButton createBuildingButton (String name, Runnable action, int itemWidth, int itemHeight, Coordinate coordinate)
    {
        Coordinate buildingCoordinate = PanelController.getInstance().getCoordinateFromPanelTile(Map.getInstance().getPanel(),
                (int) coordinate.getPositionX(),(int) coordinate.getPositionY() - Constants_DefaultValues.ONE);
        TransparentButton button = new TransparentButton(name, () -> { action.run();},
                itemWidth, itemHeight, Constants_Building.BUILDING_BUTTON_OPACITY_RELEASED, Constants_Building.BUILDING_BUTTON_OPACITY_PRESSED);
        button.setTranslateX(buildingCoordinate.getPositionX());
        button.setTranslateY(buildingCoordinate.getPositionY());
        return button;
    }



    private static HBox informAboutUnit (Unit unit)
    {
        VBox informationAboutUnit = new VBox();
        VBox attributesOfUnit = new VBox();
        HBox structureInformation = new HBox();

        Label name = new Label (unit.getName());
        Label shield = new Label (Constants_City.INFO_SHIELD);
        Label health = new Label (Constants_City.INFO_HEALTH);
        Label mana = new Label (Constants_City.INFO_MANA);
        Label meele = new Label (Constants_City.INFO_MEELE);
        Label ranged = new Label (Constants_City.INFO_RANGED);
        Label ammo = new Label (Constants_City.INFO_AMMO);
        Label dodge = new Label (Constants_City.INFO_DODGE);
        Label magicResist = new Label(Constants_City.INFO_MAGIC_RESISTENCE);
        Label rangeOfMotion = new Label(Constants_City.INFO_RANGE_OF_MOTION);
        Label initiative = new Label (Constants_City.INFO_INITIATIVE);
        Label magicDamage = new Label (Constants_City.INFO_MAGIC_DAMAGE);
        Label myAttack = new Label (Constants_City.INFO_MY_ATTACK);

        attributesOfUnit.getChildren().addAll(name, shield, health, mana, meele, ranged, ammo, dodge, magicResist,
                rangeOfMotion, initiative, magicDamage, myAttack);


        Label infoName = new Label(Constants_DefaultValues.EMPTY);
        Label numberOfShield = new Label(Integer.toString(unit.getShield()));
        Label numberOfHealth = new Label(Integer.toString(unit.getHealth()));
        Label numberOfMana = new Label (Integer.toString(unit.getMana()));
        Label numberOfMeele = new Label (Integer.toString(unit.getMeele()));
        Label numberOfRanged = new Label(Integer.toString(unit.getRanged()));
        Label numberOfAmmo = new Label (Integer.toString(unit.getAmmo()));
        Label numberOfDodge = new Label (Integer.toString(unit.getDodge()));
        Label numberOfMagicResist = new Label(Integer.toString(unit.getMagicresist()));
        Label numberOfRangeOfMotion = new Label(Integer.toString(unit.getRangeOfMotion()));
        Label numberOfInitiative = new Label(Integer.toString(unit.getInitiative()));
        Label numberOfMagicDamage = new Label (Integer.toString(unit.getMagicDamage()));

        informationAboutUnit.getChildren().addAll(infoName, numberOfShield, numberOfHealth, numberOfMana, numberOfMeele,
                numberOfRanged, numberOfAmmo, numberOfDodge, numberOfMagicResist, numberOfRangeOfMotion,
                numberOfInitiative, numberOfMagicDamage);


        structureInformation.getChildren().addAll(attributesOfUnit, informationAboutUnit);
        structureInformation.setSpacing(Constants_City.SPACING_HBOX);

        return structureInformation;
    }


    //---------------------GUI BaseCamp-------------------------------------
    public static void getInsideBaseCamp (GridPane gridpane)
    {
        Button recruitRats = new Button(Constants_City.BASECAMP_BUTTON_RATS);
        Button recruitBeetles = new Button(Constants_City.BASECAMP_BUTTON_BEETLES);
        Button recruitMosquitoes = new Button(Constants_City.BASECAMP_BUTTON_MOSQUITOES);

        HBox infoRats = informAboutUnit(FractionDonkey.getInstanceOfFractiondonkey().getRats());
        HBox infoBeetle = informAboutUnit(FractionDonkey.getInstanceOfFractiondonkey().getBeetle());
        HBox infoMosquitoes = informAboutUnit(FractionDonkey.getInstanceOfFractiondonkey().getMosquitoes());

        gridpane.add(infoRats, Constants_City.GRIDPANE_ROW_ONE, Constants_City.GRIDPANE_COLUMN_TWO);
        gridpane.add(infoBeetle, Constants_City.GRIDPANE_ROW_FIVE, Constants_City.GRIDPANE_COLUMN_TWO);
        gridpane.add(infoMosquitoes, Constants_City.GRIDPANE_ROW_NINE, Constants_City.GRIDPANE_COLUMN_TWO);
        gridpane.add(recruitRats, Constants_City.GRIDPANE_ROW_ONE, Constants_City.GRIDPANE_COLUMN_THREE);
        gridpane.add(recruitBeetles, Constants_City.GRIDPANE_ROW_FIVE, Constants_City.GRIDPANE_COLUMN_THREE);
        gridpane.add(recruitMosquitoes, Constants_City.GRIDPANE_ROW_NINE, Constants_City.GRIDPANE_COLUMN_THREE);
        gridpane.setVgap(Constants_City.GRIDPANE_VGAP);

        recruitARat(recruitRats);
        recruitABeetle(recruitBeetles);
        recruitAMosquitoe(recruitMosquitoes);
    }


    private static void recruitARat (Button button)
    {
        button.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                PlayerController.addUnitToTheTeam(FractionDonkey.getInstanceOfFractiondonkey().getRats());
            }
        });
    }


    private static void recruitABeetle (Button button)
    {
        button.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                PlayerController.addUnitToTheTeam(FractionDonkey.getInstanceOfFractiondonkey().getBeetle());
            }
        });
    }


    private static void recruitAMosquitoe (Button button)
    {
        button.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                PlayerController.addUnitToTheTeam(FractionDonkey.getInstanceOfFractiondonkey().getMosquitoes());
            }
        });
    }

    //---------------------------------GUI Forge ----------------------------------------

    public static void getInsideMagicAmplifier (GridPane gridpane)
    {
        Button pushMagic = new Button(Constants_City.MAGIC_AMPLIFIER_BUTTON);

        gridpane.add(pushMagic, Constants_City.GRIDPANE_ROW_ONE, Constants_City.GRIDPANE_COLUMN_THREE);

        pushMagicSkillsOfPlayer(pushMagic);
    }


    private static void pushMagicSkillsOfPlayer (Button button)
    {
        button.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                MagicAmplifier.pushMagicSkillOfPlayer();
            }
        });
    }


    // -------------------------GUI Headquarter ---------------------------
    public static void getInsideHeadquarter (GridPane gridPane)
    {
        Button mission1 = new Button(Constants_City.HEADQUARTER_BUTTON_M1);
        Button mission2 = new Button(Constants_City.HEADQUARTER_BUTTON_M2);
        Button mission3 = new Button (Constants_City.HEADQUARTER_BUTTON_M3);
        Button mission4 = new Button (Constants_City.HEADQUARTER_BUTTON_M4);

        gridPane.add(mission1, Constants_City.GRIDPANE_ROW_ONE, Constants_City.GRIDPANE_COLUMN_THREE);
        gridPane.add(mission2, Constants_City.GRIDPANE_ROW_TWO, Constants_City.GRIDPANE_COLUMN_THREE);
        gridPane.add(mission3, Constants_City.GRIDPANE_ROW_ONE, Constants_City.GRIDPANE_COLUMN_FOUR);
        gridPane.add(mission4, Constants_City.GRIDPANE_ROW_TWO, Constants_City.GRIDPANE_COLUMN_FOUR);

        showWayToMissionOne(mission1);
    }





    private static void showWayToMissionOne (Button button)
    {
        button.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                OutputImageView arrowView = new OutputImageView(new Image(Constants_Map.ARROW_LEFT), Constants_Map.PLAYER_SIZE);
                Map.getInstance().getPane().getChildren().add(arrowView);


                // Koordinaten für den Pfeil setzen
                arrowView.setCoordinates(PanelController.getInstance().getCoordinateFromPanelTile(Map.getInstance().getPanel(), 25,25));

                PlayerController.getInstance().updatePlayer();
            }
        });
    }



    // ---------------------------- Trainingsarea ------------------------------------

    public static void getInsideTrainingsArea (GridPane gridPane)
    {
        Button DogsAttackTraining = new Button(Constants_City.TRAINING_AREA_BUTTON_DOG_ATTACK);
        Button DogsDefenseTraining = new Button(Constants_City.TRAINING_AREA_BUTTON_DOG_DEFENSE);
        Button CatsAttackTraining = new Button (Constants_City.TRAINING_AREA_BUTTON_CAT_ATTACK);
        Button CatsDefenseTraining = new Button (Constants_City.TRAINING_AREA_BUTTON_CAT_DEFENSE);
        Button ChickenAttackTraining = new Button(Constants_City.TRAINING_AREA_BUTTON_CHICKEN_ATTACK);
        Button ChickenDefenseTraining = new Button (Constants_City.TRAINING_AREA_BUTTON_CHICKEN_DEFENSE);

        gridPane.add(DogsAttackTraining, Constants_City.GRIDPANE_ROW_ONE, Constants_City.GRIDPANE_COLUMN_THREE);
        gridPane.add(DogsDefenseTraining, Constants_City.GRIDPANE_ROW_TWO, Constants_City.GRIDPANE_COLUMN_THREE);
        gridPane.add(CatsAttackTraining, Constants_City.GRIDPANE_ROW_ONE,Constants_City.GRIDPANE_COLUMN_FOUR);
        gridPane.add(CatsDefenseTraining, Constants_City.GRIDPANE_ROW_TWO,Constants_City.GRIDPANE_COLUMN_FOUR);
        gridPane.add(ChickenAttackTraining, Constants_City.GRIDPANE_ROW_ONE, Constants_City.GRIDPANE_COLUMN_FIVE);
        gridPane.add(ChickenDefenseTraining, Constants_City.GRIDPANE_ROW_TWO,Constants_City.GRIDPANE_COLUMN_FIVE);

        trainDogAttack(DogsAttackTraining);
        trainDogDefense(DogsDefenseTraining);
        trainCatAttack(CatsAttackTraining);
        trainCatDefense(CatsDefenseTraining);
        trainChickenAttack(ChickenAttackTraining);
        trainChickenDefense(ChickenDefenseTraining);
    }

    private static void trainDogAttack (Button button)
    {
        button.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                TrainingArea.trainFractionDogAttack();
            }
        });
    }


    private static void trainDogDefense (Button button)
    {
        button.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                TrainingArea.trainFractionDogDefense();
            }
        });
    }


    private static void trainCatAttack (Button button)
    {
        button.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                TrainingArea.trainFractionCatAttack();
            }
        });
    }



    private static void trainCatDefense (Button button)
    {
        button.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                TrainingArea.trainFractionCatDefense();
            }
        });
    }



    private static void trainChickenAttack (Button button)
    {
        button.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                TrainingArea.trainFractionChickenAttack();
            }
        });
    }



    private static void trainChickenDefense (Button button)
    {
        button.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                TrainingArea.trainFractionChickenDefense();
            }
        });
    }


    // ------------------------GUI Pub --------------------------

    public static void getInsidePub (GridPane gridPane)
    {
        Button findMercenary = new Button(Constants_City.PUB_BUTTON);
        Label info = new Label(Constants_City.PUB_LABEL);

        gridPane.add(findMercenary, Constants_City.GRIDPANE_ROW_THREE, Constants_City.GRIDPANE_COLUMN_TWO);
        gridPane.add(info, Constants_City.GRIDPANE_ROW_TWO, Constants_City.GRIDPANE_COLUMN_TWO);

        findAMercenary(findMercenary);
    }


    private static void findAMercenary (Button button)
    {
        button.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                Pub.recruitAMercenary();
            }
        });
    }

    //---------------------- GUI FractionCamp Dog --------------------------------

    public static void getInsideFractionCampDog (GridPane gridpane)
    {
        Button recruitGoldenRetriever = new Button(Constants_City.FRACTION_DOG_BUTTON_GOLDEN_RETRIEVER);
        Button recruitGermanShepherd = new Button (Constants_City.FRACTION_DOG_BUTTON_GERMAN_SHEPHERD);
        Button recruitHunter = new Button(Constants_City.FRACTION_DOG_BUTTON_HUNTER);
        Button recruitBulldog = new Button(Constants_City.FRACTION_DOG_BUTTON_BULLDOG);
        Button recruitDogWithHat = new Button (Constants_City.FRACTION_DOG_BUTTON_HUNDINI);

        HBox infoGoldenRetriever = informAboutUnit(FractionDog.getInstanceOfFractionDog().getGoldenRetriever());
        HBox infoGermanShepherd = informAboutUnit(FractionDog.getInstanceOfFractionDog().getGermanShepherd());
        HBox infoHunter = informAboutUnit(FractionDog.getInstanceOfFractionDog().getHunter());
        HBox infoBulldog = informAboutUnit(FractionDog.getInstanceOfFractionDog().getBulldog());
        HBox infoDogWithHat = informAboutUnit(FractionDog.getInstanceOfFractionDog().getHundini());

        gridpane.add(infoGoldenRetriever, Constants_City.GRIDPANE_ROW_ONE, Constants_City.GRIDPANE_COLUMN_TWO);
        gridpane.add(infoGermanShepherd, Constants_City.GRIDPANE_ROW_FIVE, Constants_City.GRIDPANE_COLUMN_TWO);
        gridpane.add(infoHunter, Constants_City.GRIDPANE_ROW_NINE, Constants_City.GRIDPANE_COLUMN_TWO);
        gridpane.add(infoBulldog, Constants_City.GRIDPANE_ROW_THIRTEEN, Constants_City.GRIDPANE_COLUMN_TWO);
        gridpane.add(infoDogWithHat, Constants_City.GRIDPANE_ROW_SEVENTEEN, Constants_City.GRIDPANE_COLUMN_TWO);
        gridpane.add(recruitGoldenRetriever, Constants_City.GRIDPANE_ROW_ONE, Constants_City.GRIDPANE_COLUMN_THREE);
        gridpane.add(recruitGermanShepherd, Constants_City.GRIDPANE_ROW_FIVE, Constants_City.GRIDPANE_COLUMN_THREE);
        gridpane.add(recruitHunter, Constants_City.GRIDPANE_ROW_NINE, Constants_City.GRIDPANE_COLUMN_THREE);
        gridpane.add(recruitBulldog, Constants_City.GRIDPANE_ROW_THIRTEEN, Constants_City.GRIDPANE_COLUMN_THREE);
        gridpane.add(recruitDogWithHat, Constants_City.GRIDPANE_ROW_SEVENTEEN, Constants_City.GRIDPANE_COLUMN_THREE);
        gridpane.setVgap(Constants_City.GRIDPANE_VGAP);

        recruitGoldenRetriever.setOnAction(e -> PlayerController.addUnitToTheTeam(FractionDog.getInstanceOfFractionDog().getGoldenRetriever()));
        recruitGermanShepherd.setOnAction(e -> PlayerController.addUnitToTheTeam(FractionDog.getInstanceOfFractionDog().getGermanShepherd()));
        recruitHunter.setOnAction(e -> PlayerController.addUnitToTheTeam(FractionDog.getInstanceOfFractionDog().getHunter()));
        recruitBulldog.setOnAction(e -> PlayerController.addUnitToTheTeam(FractionDog.getInstanceOfFractionDog().getBulldog()));
        recruitDogWithHat.setOnAction(e -> PlayerController.addUnitToTheTeam(FractionDog.getInstanceOfFractionDog().getHundini()));
    }



    // Method to retrieve the Singleton instance without parameters
    public static BuildingController getInstance ()
    {
        if (instance == null)
        {
            throw new IllegalStateException(Constants_ExceptionMessages.SINGLETON_NOT_INITIALIZED);
        }
        return instance;
    }


}
