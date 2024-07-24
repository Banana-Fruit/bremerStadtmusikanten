package utility;


import control.GameController;
import control.MultiplayerController;
import control.scenes.SceneController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import model.userInterface.Game;
import model.userInterface.showables.LoadGame;
import model.userInterface.showables.MainMenu;
import model.userInterface.showables.Map;
import resources.constants.Constants_MenuBar;
import resources.constants.Constants_Popup;
import utility.popup.Popup;


/**
 * The game menu bar appears on top of all scenes to provide a quick way to access all menu options.
 *
 * @author Michael Markov
 */
public interface GameMenuBar
{
    /**
     * Creates a standard menu bar with all necessary tabs.
     *
     * @return MenuBar with all the Tabs and Items.
     * @author Michael Markov
     */
    static MenuBar createMenuBar ()
    {
        MenuBar menuBar = new MenuBar();
        
        // Add the menus to the menuBar
        menuBar.getMenus().addAll(getGameTab(), getMultiplayerTab(), getMainMenuTab());
        
        return menuBar;
    }
    
    /**
     * The multiplayer tab contains items that are related to the multiplayer functionalities.
     *
     * @return Tab with the Multiplayer Item.
     * @author Michael Markov
     */
    static Menu getMultiplayerTab ()
    {
        // Create Menu
        Menu menuMultiplayer = new Menu(Constants_MenuBar.MENUBAR_MULTIPLAYER);
        // Create Item
        MenuItem multiplayerItem = new MenuItem(Constants_MenuBar.MENUBAR_MULTIPLAYER);
        
        // Add a function to the Item
        multiplayerItem.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle (ActionEvent actionEvent)
            {
                MultiplayerController.getInstance().openConnectionMenu();
            }
        });
        
        // Put the item in the menu
        menuMultiplayer.getItems().add(multiplayerItem);
        
        return menuMultiplayer;
    }
    
    
    /**
     * The game tab contains items that are related to the game functionalities.
     *
     * @return Tab with the Game related Items.
     * @author Michael Markov
     */
    static Menu getGameTab ()
    {
        // Create Menu
        Menu menuGame = new Menu(Constants_MenuBar.MENUBAR_GAME);
        // Create Items
        MenuItem closeItem = new MenuItem(Constants_MenuBar.MENUBAR_CLOSE);
        MenuItem loadItem = new MenuItem(Constants_MenuBar.MENUBAR_LOAD);
        MenuItem newItem = new MenuItem(Constants_MenuBar.MENUBAR_NEWGAME);
        MenuItem continueItem = new MenuItem(Constants_MenuBar.MENUBAR_CONTINUE);
        
        // Add functions to the Items
        loadItem.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle (ActionEvent actionEvent)
            {
                SceneController.getInstance().switchShowable(LoadGame.getInstance());
            }
        });
        closeItem.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle (ActionEvent actionEvent)
            {
                Popup.createPopupWithAction(Game.getInstance().getCurrentShowable().getPane(), Constants_Popup.MESSAGE_CLOSE_GAME,
                        new Runnable()
                        {
                            @Override
                            public void run ()
                            {
                            
                            }
                        }, new Runnable()
                        {
                            @Override
                            public void run ()
                            {
                                Platform.exit();
                                System.exit(Constants_Popup.SYSTEM_EXIT_CODE);
                            }
                        }, Constants_Popup.NO, Constants_Popup.YES, Constants_Popup.TEXT_TO_BUTTONS_SPACING,
                        Constants_Popup.POPUP_WIDTH, Constants_Popup.POPUP_HEIGHT, Constants_Popup.defaultBackgroundColor
                );
            }
        });
        newItem.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle (ActionEvent actionEvent)
            {
                GameController.getInstance().newGame();
            }
        });
        continueItem.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle (ActionEvent actionEvent)
            {
                SceneController.getInstance().switchShowable(Map.getInstance());
            }
        });
        
        // Add Items to the Menu
        menuGame.getItems().addAll(newItem, continueItem, loadItem, closeItem);
        
        return menuGame;
    }
    
    /**
     * The main menu tab contains items that are related to the main menu functionalities.
     *
     * @return Tab with the MainMenu Item.
     * @author Michael Markov
     */
    static Menu getMainMenuTab ()
    {
        // Create Menu
        Menu menuMainMenu = new Menu(Constants_MenuBar.MENUBAR_MAINMENU);
        // Create Item
        MenuItem mainMenuItem = new MenuItem(Constants_MenuBar.MENUBAR_MAINMENU);
        
        // Add function to the Item
        mainMenuItem.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle (ActionEvent actionEvent)
            {
                SceneController.getInstance().switchShowable(MainMenu.getInstance());
            }
        });
        
        // Add Item to the Menu
        menuMainMenu.getItems().add(mainMenuItem);
        
        return menuMainMenu;
    }
}
