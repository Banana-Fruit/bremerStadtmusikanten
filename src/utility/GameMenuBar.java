package utility;

import control.scenes.SceneController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import model.userInterface.showables.LoadGame;
import resources.constants.Constants_Popup;


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
        closeTheGame(finishItem);
        // load the game
        loadGame(loadItem);


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
                CloseGame closeGame = new CloseGame(Constants_Popup.MESSAGE_CLOSE_GAME, Constants_Popup.TEXT_TO_BUTTONS_SPACING,
                        Constants_Popup.POPUP_WIDTH, Constants_Popup.POPUP_HEIGHT, Constants_Popup.defaultBackgroundColor);
            }
        });
    }


    /**
     * Methode zum Abfragen und Beenden des Spiels
     *
     * @author Jonas Helfer
     */
    /*static void closeGame ()
    {
        // Todo: Dialog nicht doppelt anzeigen ohne dieses boolean?
        final boolean[] dialogShown = {false};
        if (dialogShown[0])
        {
            return;
        }
        Stage dialogStage = new Stage();
        VBox dialogVbox = new VBox();
        dialogVbox.setSpacing(Constants_MainMenu.VBOX_SPACE_BETWEEN_CHOICE_AND_TEXT);
        HBox buttonBox = new HBox();
        Scene dialogScene = new Scene(dialogVbox, Constants_MainMenu.DIALOG_SCENE_WIDTH,
                Constants_MainMenu.DIALOG_SCENE_HEIGHT);
        // set a dialog window with a text message
        setDialogWindow(dialogStage, dialogVbox);

        // Text message whether to close the game
        Text message = new Text(Constants_MainMenu.MESSAGE_CLOSE_GAME);
        message.setFill(Color.WHITE);

        // Yes-Button to commit the text message
        TransparentButton yesButton = new TransparentButton(Constants_MainMenu.YES_BUTTON, () -> {
            Platform.exit();
            dialogStage.close();
        },Constants_MainMenu.RC_WIDTH, Constants_MainMenu.RC_HEIGHT, Constants_MainMenu.LINEAR_GRADIENT_OPACITY, Constants_MainMenu.LINEAR_GRADIENT_OPACITY_W);

        // No-Button to reject the text message
        TransparentButton noButton = new TransparentButton(Constants_MainMenu.NO_BUTTON, () -> {
            dialogStage.close();
            dialogShown[0] = false;
        },Constants_MainMenu.RC_WIDTH, Constants_MainMenu.RC_HEIGHT, Constants_MainMenu.LINEAR_GRADIENT_OPACITY, Constants_MainMenu.LINEAR_GRADIENT_OPACITY_W);

        // HBox with yes and no button
        arrangeTwoButtonsHorizontal(buttonBox, yesButton, noButton, Constants_MainMenu.SPACE_BETWEEN_YES_NO_BOXES);

        // Add message and buttons to the VBox
        dialogVbox.getChildren().addAll(message, buttonBox);



        // Set the dialog Background black
        Background background = new Background(new BackgroundFill(Color.rgb(Constants_MainMenu.RGB_SCHWARZ, Constants_MainMenu.RGB_SCHWARZ, Constants_MainMenu.RGB_SCHWARZ, Constants_MainMenu.LINEAR_GRADIENT_OPACITY), CornerRadii.EMPTY, Insets.EMPTY));
        // Set the dialog background with an image
        //Background background = createBackground(Constants_MenuSetting.PATH_BACKGROUND_IMAGE, dialogStage.getWidth(), dialogStage.getHeight());
        dialogVbox.setBackground(background);

        showSceneOnStage(dialogScene, dialogStage);
        dialogShown[0] = true;
        CloseGame closeGame = new CloseGame(Constants_Popup.MESSAGE_CLOSE_GAME, Constants_Popup.TEXT_TO_BUTTONS_SPACING,
                Constants_Popup.POPUP_WIDTH, Constants_Popup.POPUP_HEIGHT, Constants_Popup.defaultBackgroundColor);
    }*/


    /*private static void setDialogWindow(Stage dialogStage, VBox dialogVbox)
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
    }*/


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
}
