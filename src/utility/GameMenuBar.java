package utility;


import control.GameController;
import control.scenes.SceneController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import model.userInterface.showables.LoadGame;
import model.userInterface.showables.MainMenu;
import resources.constants.Constants_MenuBar;
import resources.constants.Constants_Popup;


public interface GameMenuBar
{
    static MenuBar createMenuBar ()
    {
        MenuBar menuBar = new MenuBar();
        
        // Add the menus to the menuBar
        menuBar.getMenus().addAll(getGameTab(), getMultiplayerTab(), getMainMenuTab());
        
        return menuBar;
    }
    
    
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
    
    
    static Menu getGameTab ()
    {
        Menu menuGame = new Menu(Constants_MenuBar.MENUBAR_GAME);
        MenuItem closeItem = new MenuItem(Constants_MenuBar.MENUBAR_CLOSE);
        MenuItem loadItem = new MenuItem(Constants_MenuBar.MENUBAR_LOAD);
        MenuItem newItem = new MenuItem(Constants_MenuBar.MENUBAR_NEWGAME);
        
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
                CloseGame closeGame = new CloseGame(Constants_Popup.MESSAGE_CLOSE_GAME, Constants_Popup.TEXT_TO_BUTTONS_SPACING,
                        Constants_Popup.POPUP_WIDTH, Constants_Popup.POPUP_HEIGHT, Constants_Popup.defaultBackgroundColor);
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
        
        menuGame.getItems().addAll(newItem, loadItem, closeItem);
        return menuGame;
    }
    
    
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
