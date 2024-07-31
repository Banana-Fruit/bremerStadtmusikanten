package control;


import control.scenes.SceneController;
import javafx.scene.control.TextInputDialog;
import model.userInterface.Game;
import model.userInterface.showables.Combat;
import resources.constants.Constants_ExceptionMessages;
import resources.constants.Constants_Multiplayer;
import resources.constants.Constants_Popup;
import utility.ChatClient;
import utility.ChatServer;
import utility.popup.Popup;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Optional;


public class MultiplayerController
{
    private static volatile MultiplayerController instance = null;
    /**
     * The ChatServer instance for handling in-game chat functionality.
     */
    private static ChatServer chatServer;
    /**
     * The address of the chat server.
     */
    private static String serverAddress;
    
    
    private MultiplayerController ()
    {
    }
    
    
    public static synchronized void initialize ()
    {
        if (instance == null)
        {
            instance = new MultiplayerController();
        } else
        {
            throw new IllegalStateException(Constants_ExceptionMessages.ALREADY_INITIALIZED);
        }
    }
    
    
    public void openConnectionMenu ()
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
        Popup.createPopupWithAction(Game.getInstance().getCurrentShowable().getPane(),
                Constants_Multiplayer.POPUP_TITLE_MULT_IPLAYER_CONNECTION,
                new Runnable()
                {
                    @Override
                    public void run ()
                    {
                        ChatServer.initialize();
                        chatServer = ChatServer.getInstance();
                        new Thread(() -> chatServer.start(Constants_Multiplayer.PORT)).start();
                        ChatClient chatClient = new ChatClient(Constants_Multiplayer.PORT, serverAddress);
                    }
                }, new Runnable()
                {
                    @Override
                    public void run ()
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
    
    
    public static MultiplayerController getInstance ()
    {
        if (instance == null)
        {
            throw new IllegalStateException(Constants_ExceptionMessages.SINGLETON_NOT_INITIALIZED);
        }
        return instance;
    }
}
