package control.scenes;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class GUIController
{
    private static final GUIController INSTANCE_OF_GUICONTROLLER = new GUIController();


    // default constructor
    private GUIController ()
    {
        ;
    }


    public static void showPriceOfBuilding (Building building)
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
        Label numberOfGold = new Label(Integer.toString(Inventory.getInstanceOfInventory().getInventoryGold()));
        Label numberOfBrick = new Label(Integer.toString(Inventory.getInstanceOfInventory().getInventoryBrick()));
        Label numberOfWood = new Label(Integer.toString(Inventory.getInstanceOfInventory().getInventoryWood()));
        Label numberOfBeer = new Label(Integer.toString(Inventory.getInstanceOfInventory().getInventoryBeer()));
        Label numberOfEssence = new Label(Integer.toString(Inventory.getInstanceOfInventory().getInventoryEssence()));

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



    //TODO: is a class Button with a constructor better?
    public static Button createAButton (String title, int size, int positionX, int positionY)
    {
        Button button = new Button(title);
        button.setMinSize(size, size);
        button.setLayoutX(positionX);
        button.setLayoutY(positionY);

        return button;
    }




    // getter method
    public GUIController getInstanceOfGuicontroller ()
    {
        return INSTANCE_OF_GUICONTROLLER;
    }
}
