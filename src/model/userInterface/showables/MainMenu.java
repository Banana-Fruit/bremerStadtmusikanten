package model.userInterface.showables;


import control.BuildingController;
import control.events.KeyboardController;
import control.game.PlayerController;
import control.scenes.DisplayController;
import control.scenes.MapController;
import control.scenes.PanelController;
import control.scenes.SceneController;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import model.player.Player;
import model.userInterface.TransparentButton;
import resources.constants.Constants_ExceptionMessages;
import resources.constants.Constants_Popup;
import resources.constants.scenes.Constants_MainMenu;
import resources.constants.scenes.Constants_Map;
import resources.constants.scenes.Constants_Showable;
import utility.CloseGame;


public class MainMenu extends Showable
{
    private static volatile MainMenu instance;
    
    
    private MainMenu (Scene scene)
    {
        super(scene);
        init();
    }
    
    
    public static synchronized void initialize (Scene scene)
    {
        if (instance == null)
        {
            instance = new MainMenu(scene);
        } else
        {
            throw new IllegalStateException(Constants_ExceptionMessages.ALREADY_INITIALIZED);
        }
    }
    
    
    public static MainMenu getInstance ()
    {
        if (instance == null)
        {
            throw new IllegalStateException(Constants_ExceptionMessages.SINGLETON_NOT_INITIALIZED);
        }
        return instance;
    }
    
    
    private void init ()
    {
        setBackground(Constants_MainMenu.PATH_BACKGROUND_IMAGE);
        addButtons();
    }
    
    public void addButtons ()
    {
        // creates a Menu with six menuItems
        getPane().getChildren().add(createMenuInVBox(Constants_MainMenu.VBOX_ITEM_WIDTH, Constants_MainMenu.VBOX_ITEM_HEIGHT));
    }
    
    
    public VBox createMenuInVBox(int itemWidth, int itemHeight) {
        VBox box = new VBox(Constants_MainMenu.VBOX_V,
                new TransparentButton(Constants_MainMenu.MENU_NEW_GAME, () -> {
                    newGame();
                }, itemWidth, itemHeight, Constants_MainMenu.LINEAR_GRADIENT_OPACITY, Constants_MainMenu.LINEAR_GRADIENT_OPACITY_W),
                new TransparentButton(Constants_MainMenu.MENU_LOAD_GAME, () -> {
                    loadGame();
                }, itemWidth, itemHeight, Constants_MainMenu.LINEAR_GRADIENT_OPACITY, Constants_MainMenu.LINEAR_GRADIENT_OPACITY_W),
                new TransparentButton(Constants_MainMenu.MENU_MULTIPLAYER, () -> {
                    // TODO: Multiplayer
                }, itemWidth, itemHeight, Constants_MainMenu.LINEAR_GRADIENT_OPACITY, Constants_MainMenu.LINEAR_GRADIENT_OPACITY_W),
                new TransparentButton(Constants_MainMenu.MENU_CLOSE_GAME, () -> {
                    closeGame();
                }, itemWidth, itemHeight, Constants_MainMenu.LINEAR_GRADIENT_OPACITY, Constants_MainMenu.LINEAR_GRADIENT_OPACITY_W));
        
        // Set alignment for VBox
        box.setAlignment(Pos.CENTER);
        
        // Center the StackPane itself
        box.layoutXProperty().bind(getScene().widthProperty().subtract(
                box.widthProperty()).divide(Constants_Showable.CENTER_VAR));
        box.layoutYProperty().bind(getScene().heightProperty().subtract(
                box.heightProperty()).divide(Constants_Showable.CENTER_VAR));
        
        return box;
    }
    
    
    private static void newGame ()
    {
        SceneController.getInstance().switchShowable(Map.getInstance());
        Map.getInstance().setCurrentMapName(Constants_Map.MAP_NAME_CITY);
        MapController.getInstance().setNewMap(Constants_Map.MAP_NAME_CITY);
        Player.initialize();
        BuildingController.getInstance().addButtons();
        PlayerController.getInstance().addPlayer(PanelController.getInstance().getCoordinateFromPanelTile(
                Map.getInstance().getPanel(), Constants_Map.STARTPOSITION_X, Constants_Map.STARTPOSITION_Y));
        PlayerController.getInstance().setPlayerInventory();
        Map.getInstance().getPane().getChildren().add(DisplayController.createInventory());
        
        new Thread(KeyboardController.getInstance()).start();
        new Thread(PlayerController.getInstance()).start();
    }
    
    
    private void loadGame ()
    {
        SceneController.getInstance().switchShowable(LoadGame.getInstance());
    }
    
    
    private void closeGame ()
    {
        CloseGame closeGame = new CloseGame(Constants_Popup.MESSAGE_CLOSE_GAME, Constants_Popup.TEXT_TO_BUTTONS_SPACING,
                Constants_Popup.POPUP_WIDTH, Constants_Popup.POPUP_HEIGHT, Constants_Popup.defaultBackgroundColor);
    }
}
