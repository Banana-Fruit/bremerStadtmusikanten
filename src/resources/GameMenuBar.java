package resources;

import control.scenes.SceneController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.userInterface.TransparentButton;
import model.userInterface.showables.LoadGame;
import resources.constants.Constants_Popup;
import resources.constants.scenes.Constants_MainMenu;
import utility.CloseGame;


public interface GameMenuBar
{
    static MenuBar createMenuBarWithTwoPoints(String point1, String point2,
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
        closeGame(finishItem);
        // load the game
        loadGame(loadItem);


        return menuBar;
    }

    //_______________________________close Button________________________________________
    private static void closeGame(MenuItem finish)
    {
        finish.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                CloseGame closeGame = new CloseGame(Constants_Popup.MESSAGE_CLOSE_GAME, Constants_Popup.TEXT_TO_BUTTONS_SPACING,
                        Constants_Popup.POPUP_WIDTH, Constants_Popup.POPUP_HEIGHT, Constants_Popup.defaultBackgroundColor);
            }
        });
    }




    private static void setDialogWindow(Stage dialogStage, VBox dialogVbox)
    {
        dialogStage.initStyle(StageStyle.TRANSPARENT);
        dialogStage.setTitle(Constants_MainMenu.STAGE_TITLE);
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


    private static void loadGame (MenuItem loadItem)
    {
        loadItem.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                SceneController.getInstance().switchShowable(LoadGame.getInstance());
            }
        });
    }


    static GridPane createGridPaneForLoadGame (double width, double height, double gap) {
        GridPane gridPane = new GridPane();
        gridPane.setPrefSize(width, height);
        gridPane.setHgap(gap);
        gridPane.setVgap(gap);
        return gridPane;
    }


    static void createMenuItemsForGameLoads (GridPane gridPane)
    {
        TransparentButton[] saveGameItems = createGameLoadsItems(Constants_MainMenu.NUMBER_OF_GAMES);

        addItemsToGridpane(saveGameItems, gridPane);
    }


    private static TransparentButton[] createGameLoadsItems(int numberOfGames)
    {
        TransparentButton[] saveGameItems = new TransparentButton[numberOfGames];
        
        //Todo: Namensanpassung der Spielstände, sodass statt Spielstand 1 dort ein Eigenname steht
        for (int i = Constants_MainMenu.START_LOOP; i < numberOfGames; i++)
        {
            String saveGameName = Constants_MainMenu.SAVE_GAMES + (i + Constants_MainMenu.ONE); // name of the game load
            TransparentButton saveGameItem = new TransparentButton(saveGameName, () ->
            {
            }, Constants_MainMenu.GAME_LOAD_ITEM_WIDTH,
                    Constants_MainMenu.GAME_LOAD_ITEM_HEIGHT, Constants_MainMenu.LINEAR_GRADIENT_OPACITY, Constants_MainMenu.LINEAR_GRADIENT_OPACITY_W);

            saveGameItems[i] = saveGameItem;
        }
        
        return saveGameItems;
    }


    private static void addItemsToGridpane (TransparentButton[] saveGameItems, GridPane gridPane)
    {
        for (int i = Constants_MainMenu.START_LOOP; i < Constants_MainMenu.NUMBER_OF_GAMES; i++)
        {
            GridPane.setConstraints(saveGameItems[i], i % Constants_MainMenu.TWO, i / Constants_MainMenu.TWO); // set position on the GridPane
            gridPane.getChildren().add(saveGameItems[i]);
        }
    }


    static void createTilePaneToGoBack (Pane root, GridPane gridPane)
    {
        // org.example.bremen.Tile-Pane for the GoBack-Button
        TilePane goBackPane = new TilePane();

        // defines position and size of the tilePane
        defineTilePane(goBackPane);

        // creates goBack button
        TransparentButton backButton = new TransparentButton(Constants_MainMenu.BACK_BUTTON_NAME, () ->
        {
            // Define the action for the goBack button
            try {
                // Assuming MainMenuController has a method to start the main menu scene
                //MainMenuController.getInstance().addButtons();
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }, Constants_MainMenu.BACK_BUTTON_WIDTH, Constants_MainMenu.BACK_BUTTON_HEIGHT, Constants_MainMenu.LINEAR_GRADIENT_OPACITY, Constants_MainMenu.LINEAR_GRADIENT_OPACITY_W);
        //adds button to the TilePane
        goBackPane.getChildren().addAll(backButton);
        //adds TilePane and GridPane to the Pane root
        root.getChildren().addAll(gridPane, goBackPane);
    }


    private static void defineTilePane(TilePane tilePane)
    {
        tilePane.setAlignment(Pos.BOTTOM_CENTER);
        tilePane.setTranslateY(Constants_MainMenu.TILEPANE_TRANSLATE_Y);
        tilePane.setPrefSize(Constants_MainMenu.TILEPANE_WIDTH, Constants_MainMenu.TILEPANE_HEIGHT);
    }
}
