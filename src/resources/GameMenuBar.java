package resources;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.userInterface.TransparentButton;
import resources.constants.Constants_MenuSetting;


public interface GameMenuBar
{

    static MenuBar createMenuBarWithTwoPoints(Stage stage, String point1, String point2,
                                              String menuItem1, String menuItem2)
    {
        MenuBar menuBar = new MenuBar();
        // define points of the menu bar
        Menu menuGame = new Menu(point1);
        Menu menuSettings = new Menu(point2);
        // add the points to the menu bar
        menuBar.getMenus().addAll(menuGame, menuSettings);

        // define menuItems for the point menuGame and add them
        MenuItem finishItem = new MenuItem(menuItem1);
        MenuItem loadItem = new MenuItem(menuItem2);
        menuGame.getItems().addAll(finishItem, loadItem);

        // close the game
        closeTheGame(finishItem);
        // load the game
        loadTheGame(stage, loadItem);


        return menuBar;
    }

    //_______________________________close Button________________________________________
    private static void closeTheGame(MenuItem finish)
    {
        finish.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                closeGame();
            }
        });
    }


    /**
     * Methode zum Abfragen und Beenden des Spiels
     *
     * @author Jonas Helfer
     */
    static void closeGame()
    {
        // Todo: Dialog nicht doppelt anzeigen ohne dieses boolean?
        final boolean[] dialogShown = {false};
        if (dialogShown[0])
        {
            return;
        }
        Stage dialogStage = new Stage();
        VBox dialogVbox = new VBox();
        dialogVbox.setSpacing(35);
        HBox buttonBox = new HBox();
        Scene dialogScene = new Scene(dialogVbox, Constants_MenuSetting.DIALOG_SCENE_WIDTH,
                Constants_MenuSetting.DIALOG_SCENE_HEIGHT);
        // set a dialog window with a text message
        setDialogWindow(dialogStage, dialogVbox);

        // Text message whether to close the game
        Text message = new Text(Constants_MenuSetting.MESSAGE_CLOSE_GAME);
        message.setFill(Color.WHITE);

        // Yes-Button to commit the text message
        TransparentButton yesButton = new TransparentButton(Constants_MenuSetting.YES_BUTTON, () -> {
            Platform.exit();
            dialogStage.close();
        },
                Constants_MenuSetting.RC_WIDTH, Constants_MenuSetting.RC_HEIGHT);

        // No-Button to reject the text message
        TransparentButton noButton = new TransparentButton(Constants_MenuSetting.NO_BUTTON, () -> {
            dialogStage.close();
            dialogShown[0] = false;
        },
                Constants_MenuSetting.RC_WIDTH, Constants_MenuSetting.RC_HEIGHT);

        // HBox with yes and no button
        arrangeTwoButtonsHorizontal(buttonBox, yesButton, noButton, 35);

        // Add message and buttons to the VBox
        dialogVbox.getChildren().addAll(message, buttonBox);



        // Set the dialog Background black
        Background background = new Background(new BackgroundFill(Color.rgb(0, 0, 0, 0.7), CornerRadii.EMPTY, Insets.EMPTY));
        // Set the dialog background with an image
        //Background background = createBackground(Constants_MenuSetting.PATH_BACKGROUND_IMAGE, dialogStage.getWidth(), dialogStage.getHeight());
        dialogVbox.setBackground(background);

        showSceneOnStage(dialogScene, dialogStage);
        dialogShown[0] = true;
    }
    private static Background createBackground (String path, double sceneWidth, double sceneHeight) throws IllegalArgumentException
    {
        Image imgBackground = new Image(path);
        BackgroundSize backgroundSize = new BackgroundSize(sceneWidth, sceneHeight, false, false, true, false);
        return new Background(new BackgroundImage(
                imgBackground,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                backgroundSize));
    }




    private static void setDialogWindow(Stage dialogStage, VBox dialogVbox)
    {
        dialogStage.initStyle(StageStyle.TRANSPARENT);
        dialogStage.setTitle(Constants_MenuSetting.STAGE_TITLE);
        //set position of the vbox
        dialogVbox.setAlignment(Pos.CENTER);
    }


    private static void arrangeTwoButtonsHorizontal(HBox buttonBox, TransparentButton button1, TransparentButton button2, int space)
    {
        //Position of the HBox
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setSpacing(space);
        //add the MenuItems
        buttonBox.getChildren().addAll(button1, button2);
    }

    private static void showSceneOnStage(Scene dialogScene, Stage dialogStage)
    {
        dialogScene.setFill(Color.TRANSPARENT);
        dialogStage.setScene(dialogScene);
        dialogStage.show();
    }


    //_________________________________load Button___________________________________________


    private static void loadTheGame(Stage stage, MenuItem loadItem)
    {
        loadItem.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                loadGame(stage);
            }
        });
    }

    static void loadGame(Stage stage)
    {
        Pane root = new Pane();
        Scene scene = new Scene(root, Constants_MenuSetting.SCENE_WIDTH, Constants_MenuSetting.SCENE_HEIGHT);


        Background background = createBackground(Constants_MenuSetting.PATH_BACKGROUND_IMAGE, stage.getWidth(), stage.getHeight());
        root.setBackground(background);

        // creates a gridpane
        GridPane gridPane = createGridPaneForLoadGame(Constants_MenuSetting.GRIDPANE_WIDTH, Constants_MenuSetting.GRIDPANE_HEIGHT,
                Constants_MenuSetting.GRIDPANE_TRANSLATE_Y, Constants_MenuSetting.GRIDPANE_GAP);

        // creates four items in the gridpane for the different game loads
        createMenuItemsForGameLoads(gridPane);

        // creates a TilePane with the option to go back
        createTilePaneToGoBack(stage, root, gridPane);


        stage.setScene(scene);
        stage.show();
    }


    public static GridPane createGridPaneForLoadGame (int width, int height, int translateY, int gap)
    {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPrefSize(width, height);
        gridPane.setTranslateY(translateY);
        gridPane.setHgap(gap);
        gridPane.setVgap(gap);
        return gridPane;
    }


    static void createMenuItemsForGameLoads (GridPane gridPane)
    {
        TransparentButton[] saveGameItems = new TransparentButton[Constants_MenuSetting.NUMBER_OF_GAMES];

        createGameLoadsItems(saveGameItems);

        addItemsToGridPane(saveGameItems, gridPane);
    }


    private static void createGameLoadsItems(TransparentButton[] saveGameItems)
    {
        //Todo: Namensanpassung der Spielstände, sodass statt Spielstand 1 dort ein Eigenname steht
        for (int i = Constants_MenuSetting.START_LOOP; i < Constants_MenuSetting.NUMBER_OF_GAMES; i++)
        {
            String saveGameName = Constants_MenuSetting.SAVE_GAMES + (i + Constants_MenuSetting.ONE); // name of the game load
            TransparentButton saveGameItem = new TransparentButton(saveGameName, () ->
            {
            }, Constants_MenuSetting.GAME_LOAD_ITEM_WIDTH,
                    Constants_MenuSetting.GAME_LOAD_ITEM_HEIGHT);

            saveGameItems[i] = saveGameItem;
        }
    }


    private static void addItemsToGridPane(TransparentButton[] saveGameItems, GridPane gridPane)
    {
        for (int i = Constants_MenuSetting.START_LOOP; i < Constants_MenuSetting.NUMBER_OF_GAMES; i++)
        {
            GridPane.setConstraints(saveGameItems[i], i % Constants_MenuSetting.TWO, i / Constants_MenuSetting.TWO); // set position on the GridPane
            gridPane.getChildren().add(saveGameItems[i]);
        }
    }


    static void createTilePaneToGoBack (Stage stage, Pane root, GridPane gridPane)
    {
        // org.example.bremen.Tile-Pane for the GoBack-Button
        TilePane goBackPane = new TilePane();

        // defines position and size of the tilePane
        defineTilePane(goBackPane);

        // creates goBack button
        TransparentButton backButton = new TransparentButton(Constants_MenuSetting.BACK_BUTTON_NAME, () ->
        {
            // Define the action for the goBack button
            try {
                // Assuming MainMenuController has a method to start the main menu scene
                MainMenuController mainMenuController = MainMenuController.getInstance();
                mainMenuController.start(stage);
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }, Constants_MenuSetting.BACK_BUTTON_WIDTH, Constants_MenuSetting.BACK_BUTTON_HEIGHT);
        //adds button to the TilePane
        goBackPane.getChildren().addAll(backButton);
        //adds TilePane and GridPane to the Pane root
        root.getChildren().addAll(gridPane, goBackPane);
    }


    private static void defineTilePane(TilePane tilePane)
    {
        tilePane.setAlignment(Pos.BOTTOM_CENTER);
        tilePane.setTranslateY(Constants_MenuSetting.TILEPANE_TRANSLATE_Y);
        tilePane.setPrefSize(Constants_MenuSetting.TILEPANE_WIDTH, Constants_MenuSetting.TILEPANE_HEIGHT);
    }
}
