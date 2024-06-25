package control.scenes;


import control.BuildingController;
import control.game.PlayerController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Unit;
import model.buildings.Building;
import model.buildings.MagicAmplifier;
import model.buildings.Pub;
import model.buildings.TrainingArea;
import model.player.FractionDog;
import model.player.FractionDonkey;
import model.player.Inventory;
import resources.constants.Constants_ExceptionMessages;
import resources.constants.scenes.Constants_Building;


public class GUIController
{
    private static volatile GUIController instance = null;

    private static Label numberOfGold;
    private static Label numberOfBrick;
    private static Label numberOfWood;
    private static Label numberOfBeer;
    private static Label numberOfEssence;


    // default constructor
    private GUIController ()
    {
        ;
    }

    public static synchronized void initialize ()
    {
        if (instance == null)
        {
            instance = new GUIController();
        } else
        {
            throw new IllegalStateException(Constants_ExceptionMessages.ALREADY_INITIALIZED);
        }
    }


    public static GUIController getInstance ()
    {
        if (instance == null)
        {
            throw new IllegalStateException(Constants_ExceptionMessages.SINGLETON_NOT_INITIALIZED);
        }
        return instance;
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
            System.out.println("already unlocked");
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
                // TODO: Markierung (ist später weg, muss mit Threads arbeiten, da noch keine Abfrage der Zustände)
                SceneController.buildSceneBuildingInside(stage,building);
                //SceneController.switchToSceneBuildingInside(SceneController.buildSceneBuildingInside(stage, building),
                        //SceneController.buildSceneBuildingInside(stage, building));
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
                    SceneController.buildSceneBuildingInside(stage, building);
                }
            });
        }

    }


    public static GridPane createInventory()
    {
        GridPane gridpane = new GridPane();

        // create labels
        Label inventory = new Label(Constants_Building.RESOURCES_INVENTORY);
        Label gold = new Label(Constants_Building.GOLD);
        Label brick = new Label(Constants_Building.BRICK);
        Label wood = new Label(Constants_Building.WOOD);
        Label beer = new Label(Constants_Building.BEER);
        Label essence = new Label(Constants_Building.ESSENCE);
        numberOfGold = new Label(Integer.toString(Inventory.getInstanceOfInventory().getInventoryGold()));
        numberOfBrick = new Label(Integer.toString(Inventory.getInstanceOfInventory().getInventoryBrick()));
        numberOfWood = new Label(Integer.toString(Inventory.getInstanceOfInventory().getInventoryWood()));
        numberOfBeer = new Label(Integer.toString(Inventory.getInstanceOfInventory().getInventoryBeer()));
        numberOfEssence = new Label(Integer.toString(Inventory.getInstanceOfInventory().getInventoryEssence()));

        // add labels to the gridPane
        gridpane.add(inventory, Constants_Building.GRIDPANE_COLUMN_ZERO, Constants_Building.GRIDPANE_ROW_ZERO,
                Constants_Building.GRIDPANE_SIZE_TWO,Constants_Building.GRIDPANE_SIZE_TWO);
        gridpane.add(gold, Constants_Building.GRIDPANE_COLUMN_ZERO, Constants_Building.GRIDPANE_ROW_THREE,
                Constants_Building.GRIDPANE_SIZE_ONE, Constants_Building.GRIDPANE_SIZE_ONE);
        gridpane.add(brick, Constants_Building.GRIDPANE_COLUMN_ZERO, Constants_Building.GRIDPANE_ROW_FOUR,
                Constants_Building.GRIDPANE_SIZE_ONE, Constants_Building.GRIDPANE_SIZE_ONE);
        gridpane.add(wood, Constants_Building.GRIDPANE_COLUMN_ZERO, Constants_Building.GRIDPANE_ROW_FIVE,
                Constants_Building.GRIDPANE_SIZE_ONE, Constants_Building.GRIDPANE_SIZE_ONE);
        gridpane.add(beer, Constants_Building.GRIDPANE_COLUMN_ZERO, Constants_Building.GRIDPANE_ROW_SIX,
                Constants_Building.GRIDPANE_SIZE_ONE, Constants_Building.GRIDPANE_SIZE_ONE);
        gridpane.add(essence, Constants_Building.GRIDPANE_COLUMN_ZERO, Constants_Building.GRIDPANE_ROW_SEVEN,
                Constants_Building.GRIDPANE_SIZE_ONE, Constants_Building.GRIDPANE_SIZE_ONE);
        gridpane.add(numberOfGold, Constants_Building.GRIDPANE_COLUMN_ONE,Constants_Building.GRIDPANE_ROW_THREE,
                Constants_Building.GRIDPANE_SIZE_ONE,Constants_Building.GRIDPANE_SIZE_ONE);
        gridpane.add(numberOfBrick, Constants_Building.GRIDPANE_COLUMN_ONE,Constants_Building.GRIDPANE_ROW_FOUR,
                Constants_Building.GRIDPANE_SIZE_ONE,Constants_Building.GRIDPANE_SIZE_ONE);
        gridpane.add(numberOfWood, Constants_Building.GRIDPANE_COLUMN_ONE,Constants_Building.GRIDPANE_ROW_FIVE,
                Constants_Building.GRIDPANE_SIZE_ONE,Constants_Building.GRIDPANE_SIZE_ONE);
        gridpane.add(numberOfBeer, Constants_Building.GRIDPANE_COLUMN_ONE,Constants_Building.GRIDPANE_ROW_SIX,
                Constants_Building.GRIDPANE_SIZE_ONE,Constants_Building.GRIDPANE_SIZE_ONE);
        gridpane.add(numberOfEssence, Constants_Building.GRIDPANE_COLUMN_ONE,Constants_Building.GRIDPANE_ROW_SEVEN,
                Constants_Building.GRIDPANE_SIZE_ONE,Constants_Building.GRIDPANE_SIZE_ONE);

        return gridpane;
    }
    public void updateInventory()
    {
        // Update the labels with current inventory values
        numberOfGold.setText(Integer.toString(Inventory.getInstanceOfInventory().getInventoryGold()));
        numberOfBrick.setText(Integer.toString(Inventory.getInstanceOfInventory().getInventoryBrick()));
        numberOfWood.setText(Integer.toString(Inventory.getInstanceOfInventory().getInventoryWood()));
        numberOfBeer.setText(Integer.toString(Inventory.getInstanceOfInventory().getInventoryBeer()));
        numberOfEssence.setText(Integer.toString(Inventory.getInstanceOfInventory().getInventoryEssence()));
    }


    private static HBox informAboutUnit (Unit unit)
    {
        VBox informationAboutUnit = new VBox();
        VBox attributesOfUnit = new VBox();
        HBox structureInformation = new HBox();

        Label name = new Label ("UNIT NAME");
        Label shield = new Label ("Shield: ");
        Label health = new Label ("Health: ");
        Label mana = new Label ("Mana: ");
        Label meele = new Label ("Meele: ");
        Label ranged = new Label ("Ranged: ");
        Label ammo = new Label ("Ammo: ");
        Label dodge = new Label ("Dodeg: ");
        Label magicResist = new Label("Magic resist: ");
        Label rangeOfMotion = new Label("Range of Motion: ");
        Label initiative = new Label ("Initiative: ");
        Label magicDamage = new Label ("Magic damage: ");
        Label myAttack = new Label ("My attack: ");

        attributesOfUnit.getChildren().addAll(name, shield, health, mana, meele, ranged, ammo, dodge, magicResist,
                rangeOfMotion, initiative, magicDamage, myAttack);


        Label infoName = new Label("");
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
        structureInformation.setSpacing(5);

        return structureInformation;
    }


    //---------------------GUI BaseCamp-------------------------------------
    public static void getInsideBaseCamp (GridPane gridpane)
    {
        Button recruitRats = new Button("recruit rats");
        Button recruitBeetles = new Button("recruit beetles");
        Button recruitMosquitoes = new Button("recruit mosquitoes");

        HBox infoRats = informAboutUnit(FractionDonkey.getInstanceOfFractiondonkey().getRats());
        HBox infoBeetle = informAboutUnit(FractionDonkey.getInstanceOfFractiondonkey().getBeetle());
        HBox infoMosquitoes = informAboutUnit(FractionDonkey.getInstanceOfFractiondonkey().getMosquitoes());

        gridpane.add(infoRats, 1, 2);
        gridpane.add(infoBeetle, 5, 2);
        gridpane.add(infoMosquitoes, 9, 2);
        gridpane.add(recruitRats, 1, 3);
        gridpane.add(recruitBeetles, 5, 3);
        gridpane.add(recruitMosquitoes, 9, 3);
        gridpane.setVgap(10);

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
        Button pushMagic = new Button("push magic skill");

        gridpane.add(pushMagic, 1, 3);

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
        Button mission1 = new Button("Mission 1");
        Button mission2 = new Button("Mission 2");
        Button mission3 = new Button ("Mission 3");
        Button mission4 = new Button ("Mission 4");

        gridPane.add(mission1, 1, 3);
        gridPane.add(mission2, 2, 3);
        gridPane.add(mission3, 1, 4);
        gridPane.add(mission4, 2, 4);

        showWayToMissionOne(mission1);
    }


    private static void showWayToMissionOne (Button button)
    {
        button.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                ;
            }
        });
    }


    // ---------------------------- Trainingsarea ------------------------------------

    public static void getInsideTrainingsArea (GridPane gridPane)
    {
        Button DogsAttackTraining = new Button("Fraction Dog Attack");
        Button DogsDefenseTraining = new Button("Fraction Dog Defense");
        Button CatsAttackTraining = new Button ("Fraction Cat Attack");
        Button CatsDefenseTraining = new Button ("Fraction Cat Defense");
        Button ChickenAttackTraining = new Button("Fraction Chicken Attack");
        Button ChickenDefenseTraining = new Button ("Fraction Chicken Defense");

        gridPane.add(DogsAttackTraining, 1, 3);
        gridPane.add(DogsDefenseTraining, 2, 3);
        gridPane.add(CatsAttackTraining, 1,4);
        gridPane.add(CatsDefenseTraining, 2,4);
        gridPane.add(ChickenAttackTraining, 1, 5);
        gridPane.add(ChickenDefenseTraining, 2,5);

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
        Button findMercenary = new Button("recruit a mercenary");
        Label info = new Label("To recruit a mercenary spend one beer.");

        gridPane.add(findMercenary, 3, 2);
        gridPane.add(info, 2, 2);

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

    //----------------------GUI FractionCamp Dog --------------------------------

    public static void getInsideFractionCampDog (GridPane gridpane)
    {
        Button recruitGoldenRetriever = new Button("recruit golden Retriever");
        Button recruitGermanShepherd = new Button ("recruit german Shepherd");
        Button recruitHunter = new Button("recruit hunter");
        Button recruitBulldog = new Button("recruit bulldog");
        Button recruitDogWithHat = new Button ("recruit Hundini");

        HBox infoGoldenRetriever = informAboutUnit(FractionDog.getInstanceOfFractionDog().getGoldenRetriever());
        HBox infoGermanShepherd = informAboutUnit(FractionDog.getInstanceOfFractionDog().getGermanShepherd());
        HBox infoHunter = informAboutUnit(FractionDog.getInstanceOfFractionDog().getHunter());
        HBox infoBulldog = informAboutUnit(FractionDog.getInstanceOfFractionDog().getBulldog());
        HBox infoDogWithHat = informAboutUnit(FractionDog.getInstanceOfFractionDog().getHundini());

        gridpane.add(infoGoldenRetriever, 1, 2);
        gridpane.add(infoGermanShepherd, 5, 2);
        gridpane.add(infoHunter, 9, 2);
        gridpane.add(infoBulldog, 13, 2);
        gridpane.add(infoDogWithHat, 17, 2);
        gridpane.add(recruitGoldenRetriever, 1, 3);
        gridpane.add(recruitGermanShepherd, 5, 3);
        gridpane.add(recruitHunter, 9, 3);
        gridpane.add(recruitBulldog, 13, 3);
        gridpane.add(recruitDogWithHat, 17, 3);
        gridpane.setVgap(10);

        recruitGoldenRetriever.setOnAction(e -> PlayerController.addUnitToTheTeam(FractionDog.getInstanceOfFractionDog().getGoldenRetriever()));
        recruitGermanShepherd.setOnAction(e -> PlayerController.addUnitToTheTeam(FractionDog.getInstanceOfFractionDog().getGermanShepherd()));
        recruitHunter.setOnAction(e -> PlayerController.addUnitToTheTeam(FractionDog.getInstanceOfFractionDog().getHunter()));
        recruitBulldog.setOnAction(e -> PlayerController.addUnitToTheTeam(FractionDog.getInstanceOfFractionDog().getBulldog()));
        recruitDogWithHat.setOnAction(e -> PlayerController.addUnitToTheTeam(FractionDog.getInstanceOfFractionDog().getHundini()));
    }

}
