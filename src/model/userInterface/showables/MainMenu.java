package model.userInterface.showables;


import control.GameController;
import control.scenes.SceneController;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.VBox;
import model.userInterface.Game;
import model.userInterface.TransparentButton;
import resources.constants.Constants_ExceptionMessages;
import resources.constants.Constants_Multiplayer;
import resources.constants.Constants_Popup;
import resources.constants.scenes.Constants_MainMenu;
import resources.constants.scenes.Constants_Showable;
import utility.ChatClient;
import utility.ChatServer;
import utility.popup.Popup;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Optional;


/**
 * MainMenu class contains the scene of the MainMenu.
 *
 * @author Michael Markov
 */
public class MainMenu extends Showable
{
    /**
     * The single instance of the MainMenu class.
     * Volatile keyword ensures visibility across threads.
     */
    private static volatile MainMenu instance;

    /**
     * The ChatServer instance for handling in-game chat functionality.
     */
    private static ChatServer chatServer;

    /**
     * The address of the chat server.
     */
    private static String serverAddress;



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
     * Creates a VBox containing the main menu buttons.
     * @author Michael Markov, Jonas Helfer
     * @param itemWidth The width of each menu button
     * @param itemHeight The height of each menu button
     * @return VBox containing the main menu buttons
     * @precondition The Constants classes are properly initialized with all necessary values
     * @postcondition A VBox with centered, styled menu buttons is created and returned
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

    /**
     * Opens the multiplayer connection menu and sets up the chat interface.
     * This method initializes either a server or client based on user selection.
     * @author Jonas Helfer
     * @precondition The game is in a state where multiplayer can be initiated
     * @postcondition The chat area is added to the combat scene and a server or client connection is established
     */
    private void openMultiplayerConnectionMenu()
    {
        try
        {
            serverAddress = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e)
        {
            throw new RuntimeException(e);
        }
        SceneController.getInstance().switchShowable(Combat.getInstance());
        Combat.getInstance().getPane().getChildren().add(ChatClient.createChatArea());
        Popup.createPopupWithAction(Game.getInstance().getCurrentShowable().getPane(), Constants_Multiplayer.POPUP_TITLE_MULT_IPLAYER_CONNECTION,
                new Runnable()
				{
                    @Override
                    public void run()
					{
                        ChatServer.initialize();
                        chatServer = ChatServer.getInstance();
                        new Thread(() -> chatServer.start(Constants_Multiplayer.PORT)).start();
                        ChatClient chatClient = new ChatClient(Constants_Multiplayer.PORT, serverAddress);
                    }
                }, new Runnable()
				{
                    @Override
                    public void run()
					{
                        TextInputDialog dialog = new TextInputDialog();
                        dialog.setTitle(Constants_Multiplayer.HOST_ADDRESS);
                        dialog.setHeaderText(Constants_Multiplayer.ENTER_HOST_ADDRESS);
                        dialog.setContentText(Constants_Multiplayer.ADDRESS);
                        dialog.setGraphic(null);

                        Optional<String> address = dialog.showAndWait();
                        serverAddress = address.orElseThrow();
                        ChatClient chatClient = new ChatClient(Constants_Multiplayer.PORT, serverAddress);
                    }
                }, Constants_Multiplayer.HOST, Constants_Multiplayer.GUEST, Constants_Popup.TEXT_TO_BUTTONS_SPACING,
                Constants_Popup.POPUP_WIDTH, Constants_Popup.POPUP_HEIGHT, Constants_Popup.defaultBackgroundColor
        );
    }
    
    
    private void settings()
    {
        SceneController.getInstance().switchShowable(Settings.getInstance());
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
