package control.scenes;


import control.events.KeyboardController;
import control.game.PlayerController;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import model.player.Player;
import model.userInterface.Game;
import model.userInterface.showables.*;
import model.userInterface.TransparentButton;
import resources.constants.Constants_ExceptionMessages;
import resources.constants.Constants_MainMenu;
import resources.GameMenuBar;
import resources.constants.scenes.Constants_Map;


public class MainMenuController implements GameMenuBar
{
    private static volatile MainMenuController instance = null;
    
    
    private MainMenuController ()
    {
    }
    
    
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
    
    
    public void addButtons ()
    {
        // creates a Menu with six menuItems
        MainMenu.getInstance().getPane().getChildren().add(createMenuInVBox(Constants_MainMenu.VBOX_ITEM_WIDTH,
                Constants_MainMenu.VBOX_ITEM_HEIGHT, Constants_MainMenu.VBOX_XPOSITION,
                Constants_MainMenu.VBOX_YPOSITION));
    }
    
    
    public static VBox createMenuInVBox (int itemWidth, int itemHeight, int xPositionVBox, int yPositionVBox)
    {
        VBox box = new VBox(Constants_MainMenu.VBOX_V,
                new TransparentButton(Constants_MainMenu.MENU_NEW_GAME, () ->
                {
                    newGame();
                }, itemWidth, itemHeight),
                new TransparentButton(Constants_MainMenu.MENU_LOAD_GAME, () ->
                {
                    loadGame();
                }, itemWidth, itemHeight),
                new TransparentButton(Constants_MainMenu.MENU_MULTIPLAYER, () ->
                {
                    // TODO: Multiplayer
                }, itemWidth, itemHeight),
                new TransparentButton(Constants_MainMenu.MENU_SETTINGS, () ->
                {
                    openSettings();
                }, itemWidth, itemHeight),
                new TransparentButton(Constants_MainMenu.MENU_CLOSE_GAME, () ->
                {
                    GameMenuBar.closeGame();
                }, itemWidth, itemHeight));
        
        // X-Position of the vertical field
        box.setTranslateX(xPositionVBox);
        // Y-Position of the vertical field
        box.setTranslateY(yPositionVBox);
        return box;
    }
    
    
    private static void newGame ()
    {
        Map.initialize(new Scene(SceneController.getInstance().getBasePane()));
        SceneController.getInstance().switchShowable(Map.getInstance());
        MapController.getInstance().setNewMap("main.dat");
        Player.initialize();
        PlayerController.getInstance().addPlayer(PanelController.getInstance().getCoordinateFromPanelTile(
                Map.getInstance().getPanel(), Constants_Map.STARTPOSITION_X, Constants_Map.STARTPOSITION_Y));
        
        new Thread(KeyboardController.getInstance()).start();
        new Thread(PlayerController.getInstance()).start();
    }
    
    
    private static void openSettings ()
    {
        SceneController.getInstance().switchShowable(Settings.getInstance());
    }
    
    
    private static void loadGame ()
    {
        GridPane gridPane = GameMenuBar.createGridPaneForLoadGame(Constants_MainMenu.GRIDPANE_WIDTH, Constants_MainMenu.GRIDPANE_HEIGHT,
                Constants_MainMenu.GRIDPANE_TRANSLATE_Y, Constants_MainMenu.GRIDPANE_GAP);
        
        GameMenuBar.createMenuItemsForGameLoads(gridPane);
        GameMenuBar.createTilePaneToGoBack(MainMenu.getInstance().getPane(), gridPane);
        
        LoadGame.getInstance().getPane().getChildren().add(gridPane);
        
        SceneController.getInstance().switchShowable(LoadGame.getInstance());
    }
}
