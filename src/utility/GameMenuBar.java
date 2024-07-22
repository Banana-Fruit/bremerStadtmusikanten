package utility;


import control.GameController;
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
     * @return
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
     * @return
     * @author Michael Markov
     */
    static Menu getMultiplayerTab ()
    {
        Menu menuMultiplayer = new Menu(Constants_MenuBar.MENUBAR_MULTIPLAYER);
        MenuItem multiplayerItem = new MenuItem(Constants_MenuBar.MENUBAR_MULTIPLAYER);
        
        multiplayerItem.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle (ActionEvent actionEvent)
            {
                SceneController.getInstance().switchShowable(MainMenu.getInstance());
            }
        });
        
        menuMultiplayer.getItems().add(multiplayerItem);
        return menuMultiplayer;
    }
    
    
    /**
     * The game tab contains items that are related to the game functionalities.
     *
     * @return
     * @author Michael Markov
     */
    static Menu getGameTab ()
    {
        Menu menuGame = new Menu(Constants_MenuBar.MENUBAR_GAME);
        MenuItem closeItem = new MenuItem(Constants_MenuBar.MENUBAR_CLOSE);
        MenuItem loadItem = new MenuItem(Constants_MenuBar.MENUBAR_LOAD);
        MenuItem newItem = new MenuItem(Constants_MenuBar.MENUBAR_NEWGAME);
        MenuItem continueItem = new MenuItem(Constants_MenuBar.MENUBAR_CONTINUE);
        
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
        
        menuGame.getItems().addAll(newItem, continueItem, loadItem, closeItem);
        return menuGame;
    }
    
    /**
     * The main menu tab contains items that are related to the main menu functionalities.
     *
     * @return
     * @author Michael Markov
     */
    static Menu getMainMenuTab ()
    {
        Menu menuMainMenu = new Menu(Constants_MenuBar.MENUBAR_MAINMENU);
        MenuItem mainMenuItem = new MenuItem(Constants_MenuBar.MENUBAR_MAINMENU);
        
        mainMenuItem.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle (ActionEvent actionEvent)
            {
                SceneController.getInstance().switchShowable(MainMenu.getInstance());
            }
        });
        
        menuMainMenu.getItems().add(mainMenuItem);
        return menuMainMenu;
    }
}
