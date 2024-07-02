package model.userInterface.showables;


import control.GameController;
import control.scenes.SceneController;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import model.userInterface.Game;
import model.userInterface.TransparentButton;
import resources.constants.Constants_ExceptionMessages;
import resources.constants.Constants_Popup;
import resources.constants.scenes.Constants_MainMenu;
import resources.constants.scenes.Constants_Showable;
import utility.popup.Popup;


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
                new TransparentButton(Constants_MainMenu.MENU_CONTINUE_GAME, () -> {
                    continueGame();
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
    
    
    private void continueGame ()
    {
        SceneController.getInstance().switchShowable(Map.getInstance());
    }
    
    
    private static void newGame ()
    {
        GameController.getInstance().newGame();
    }
    
    
    private void loadGame ()
    {
        SceneController.getInstance().switchShowable(LoadGame.getInstance());
    }
    
    
    private void closeGame ()
    {
        Popup.createPopupWithAction(Game.getInstance().getCurrentShowable().getPane(), Constants_Popup.MESSAGE_CLOSE_GAME,
                new Runnable()
                {
                    @Override
                    public void run ()
                    {
                        Platform.exit();
                    }
                }, new Runnable()
                {
                    @Override
                    public void run ()
                    {
                    
                    }
                }, Constants_Popup.YES, Constants_Popup.NO, Constants_Popup.TEXT_TO_BUTTONS_SPACING, Constants_Popup.POPUP_WIDTH, Constants_Popup.POPUP_HEIGHT,
                Constants_Popup.defaultBackgroundColor
        );
    }
}
