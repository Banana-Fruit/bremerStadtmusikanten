package model.userInterface.showables;


import control.GameController;
import control.scenes.SceneController;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.VBox;
import model.userInterface.Game;
import model.userInterface.TransparentButton;
import resources.constants.Constants_ExceptionMessages;
import resources.constants.Constants_Popup;
import resources.constants.scenes.Constants_MainMenu;
import resources.constants.scenes.Constants_Showable;
import utility.ChatClient;
import utility.ChatServer;
import utility.popup.Popup;

import java.util.Optional;


/**
 * MainMenu class contains the scene of the MainMenu.
 *
 * @author Michael Markov
 */
public class MainMenu extends Showable
{
    private static volatile MainMenu instance;
    private static ChatServer chatServer;
    private static ChatClient chatClient;
    
    
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
        setBackground(Constants_MainMenu.PATH_BACKGROUND_IMAGE); // Sets background image
        addButtons(); // Adds buttons with functions to the main menu
    }
    
    
    /**
     * Method adds buttons (VBOX) to pane.
     *
     * @author Michael Markov
     */
    public void addButtons ()
    {
        // creates a Menu with six menuItems
        getPane().getChildren().add(createMenuInVBox(Constants_MainMenu.VBOX_ITEM_WIDTH, Constants_MainMenu.VBOX_ITEM_HEIGHT));
    }
    
    
    /**
     * Creates a vertical box consisting of 5 buttons.
     *
     * @param itemWidth
     * @param itemHeight
     * @return
     * @author Michael Markov, Jonas Helfer
     */
    public VBox createMenuInVBox (int itemWidth, int itemHeight)
    {
        VBox box = new VBox(Constants_MainMenu.VBOX_V,
                new TransparentButton(Constants_MainMenu.MENU_NEW_GAME, () ->
                {
                    newGame();
                }, itemWidth, itemHeight, Constants_MainMenu.LINEAR_GRADIENT_OPACITY, Constants_MainMenu.LINEAR_GRADIENT_OPACITY_W),
                new TransparentButton(Constants_MainMenu.MENU_CONTINUE_GAME, () ->
                {
                    continueGame();
                }, itemWidth, itemHeight, Constants_MainMenu.LINEAR_GRADIENT_OPACITY, Constants_MainMenu.LINEAR_GRADIENT_OPACITY_W),
                new TransparentButton(Constants_MainMenu.MENU_LOAD_GAME, () ->
                {
                    loadGame();
                }, itemWidth, itemHeight, Constants_MainMenu.LINEAR_GRADIENT_OPACITY, Constants_MainMenu.LINEAR_GRADIENT_OPACITY_W),
                new TransparentButton(Constants_MainMenu.MENU_SETTINGS, () ->
                {
                    settings();
                }, itemWidth, itemHeight, Constants_MainMenu.LINEAR_GRADIENT_OPACITY, Constants_MainMenu.LINEAR_GRADIENT_OPACITY_W),
                new TransparentButton(Constants_MainMenu.MENU_MULTIPLAYER, () ->
                {
                    openMultiplayerConnectionMenu();
                }, itemWidth, itemHeight, Constants_MainMenu.LINEAR_GRADIENT_OPACITY, Constants_MainMenu.LINEAR_GRADIENT_OPACITY_W),
                new TransparentButton(Constants_MainMenu.MENU_CLOSE_GAME, () ->
                {
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
    
    
    private void settings ()
    {
        SceneController.getInstance().switchShowable(Settings.getInstance());
    }
    
    
    private void openMultiplayerConnectionMenu ()
    {
        SceneController.getInstance().switchShowable(Combat.getInstance());
        String serverAddress;
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Netzwerkmodus");
        alert.setHeaderText("Möchten Sie als Server oder Client starten?");
        alert.setContentText("Wählen Sie den Modus:");
        
        ButtonType buttonTypeServer = new ButtonType("Server");
        ButtonType buttonTypeClient = new ButtonType("Client");
        
        alert.getButtonTypes().setAll(buttonTypeServer, buttonTypeClient);
        
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeServer)
        {
            
            Combat.getInstance().getPane().getChildren().add(ChatClient.createChatArea());
            ChatServer.initialize();
            chatServer = ChatServer.getInstance();
            new Thread(() -> chatServer.start(13579)).start();
            serverAddress = "192.168.0.90";
        } else
        {
            TextInputDialog dialog = new TextInputDialog("localhost");
            dialog.setTitle("Server Adresse");
            dialog.setHeaderText("Geben Sie die Server-Adresse ein");
            dialog.setContentText("Adresse:");
            
            Optional<String> address = dialog.showAndWait();
            serverAddress = address.orElseThrow();
        }
        chatClient = new ChatClient(13579, serverAddress);
    }
    
    
    /**
     * Method that runs if the correlating button is pressed.
     *
     * @author Michael Markov
     */
    private void continueGame ()
    {
        SceneController.getInstance().switchShowable(Map.getInstance());
    }
    
    
    /**
     * Method that runs if the correlating button is pressed.
     *
     * @author Michael Markov
     */
    private static void newGame ()
    {
        GameController.getInstance().newGame();
    }
    
    
    /**
     * Method that runs if the correlating button is pressed.
     *
     * @author Michael Markov
     */
    private void loadGame ()
    {
        SceneController.getInstance().switchShowable(LoadGame.getInstance());
    }
    
    
    /**
     * Method that runs if the correlating button is pressed.
     *
     * @author Michael Markov
     */
    private void closeGame ()
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
}
