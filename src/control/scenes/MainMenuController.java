package control.scenes;


import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.showables.MainMenu;
import model.userInterface.TransparentButton;
import resources.constants.Constants_ExceptionMessages;
import resources.constants.Constants_MenuSetting;
import resources.GameMenuBar;


public class MainMenuController implements GameMenuBar
{
    private static volatile MainMenuController instance = null;



    private MainMenuController () {}
    
    
    public static synchronized void initialize ()
    {
        if (instance == null)
        {
            instance = new MainMenuController();
        } else
        {
            throw new IllegalStateException(Constants_ExceptionMessages.ALREADY_INITIALIZED);
        }
    }


    public static MainMenuController getInstance ()
    {
        if (instance == null)
        {
            throw new IllegalStateException(Constants_ExceptionMessages.SINGLETON_NOT_INITIALIZED);
        }
        return instance;
    }


    public void startMainMenu (Stage stage)
    {
        MainMenu.getInstance().setBackground(Constants_MenuSetting.PATH_BACKGROUND_IMAGE);
        
        // creates a Menu bar with two points (game and settings) and add two menuItems to the point game
        MenuBar menuBar = GameMenuBar.createMenuBarWithTwoPoints(stage, Constants_MenuSetting.MENUBAR_GAME,
                Constants_MenuSetting.MENUBAR_SETTING, Constants_MenuSetting.MENUBAR_CLOSE,
                Constants_MenuSetting.MENUBAR_LOAD);

        // creates a Menu with six menuItems
        VBox box = createMenuInVBox(stage, MainMenu.getInstance().getPane(), Constants_MenuSetting.VBOX_ITEM_WIDTH,
                Constants_MenuSetting.VBOX_ITEM_HEIGHT, Constants_MenuSetting.VBOX_XPOSITION,
                Constants_MenuSetting.VBOX_YPOSITION);

        // add menu bar and vertical menu to the pane
        MainMenu.getInstance().getPane().getChildren().addAll(menuBar, box);
    }




    /**
     * Methode zum Öffnen der Einstellungen des Spiels
     *
     * @param stage Die Bühne, auf der die Szene angezeigt werden soll
     * @author Jonas Helfer
     */

    private static void openSettings(Stage stage, Pane root)
    {
        Pane newRoot = new Pane();
        Scene scene = new Scene(newRoot, Constants_MenuSetting.SCENE_WIDTH, Constants_MenuSetting.SCENE_HEIGHT);

        // Set background
        Background background = new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY));
        newRoot.setBackground(background);

        stage.setScene(scene);
        stage.show();
    }

    private static void loadGame(Stage stage, Pane root)
    {
        Scene scene = new Scene(root, Constants_MenuSetting.SCENE_WIDTH, Constants_MenuSetting.SCENE_HEIGHT);
        Background background = new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY));
        root.setBackground(background);

        GridPane gridPane = GameMenuBar.createGridPaneForLoadGame(Constants_MenuSetting.GRIDPANE_WIDTH, Constants_MenuSetting.GRIDPANE_HEIGHT,
                Constants_MenuSetting.GRIDPANE_TRANSLATE_Y, Constants_MenuSetting.GRIDPANE_GAP);
        
        GameMenuBar.createMenuItemsForGameLoads(gridPane);
        GameMenuBar.createTilePaneToGoBack(stage, root, gridPane);
        stage.setScene(scene);
        stage.show();
    }



    public static VBox createMenuInVBox(Stage stage, Pane root, int itemWidth, int itemHeight, int xPositionVBox, int yPositionVBox)
    {
        VBox box = new VBox(Constants_MenuSetting.VBOX_V,
                new TransparentButton(Constants_MenuSetting.MENU_CONTINUE_GAME, () ->
                {
                    getInstance().continueGame();
                }, itemWidth, itemHeight),
                new TransparentButton(Constants_MenuSetting.MENU_NEW_GAME, () ->
                {

                }, itemWidth, itemHeight),
                new TransparentButton(Constants_MenuSetting.MENU_LOAD_GAME, () ->
                {
                    GameMenuBar.loadGame(stage);
                }, itemWidth, itemHeight),
                new TransparentButton(Constants_MenuSetting.MENU_MULTIPLAYER, () ->
                {

                }, itemWidth, itemHeight),
                new TransparentButton(Constants_MenuSetting.MENU_SETTINGS, () ->
                {
                    openSettings(stage, root);
                }, itemWidth, itemHeight),
                new TransparentButton(Constants_MenuSetting.MENU_CLOSE_GAME, () ->
                {
                    GameMenuBar.closeGame();
                }, itemWidth, itemHeight));
        // X-Position of the vertical field
        box.setTranslateX(xPositionVBox);
        // Y-Position of the vertical field
        box.setTranslateY(yPositionVBox);
        return box;
    }

    //TODO: Program only likes GamePanel in the start() method (needs runnable)
    private void continueGame()
    {
        //Implementation Game
    }
}
