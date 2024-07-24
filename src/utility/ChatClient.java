package utility;


import javafx.application.Platform;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import resources.constants.Constants_Multiplayer;

import java.io.*;
import java.net.Socket;

public class ChatClient
{
    // Server address
    private String address;

    // Server port
    private int port;

    // Socket connection to the server
    private Socket connectionToServer;

    // Reader for incoming messages from the server
    private BufferedReader fromServerReader;

    // Writer for outgoing messages to the server
    private static PrintWriter toServerWriter;

    // TextArea for displaying chat messages
    private static TextArea chatArea;

    // TextField for user input
    private static TextField inputField;

    // Thread for receiving messages
    private Thread receiveThread;


    /**
     * Constructor for the ChatClient.
     * Initializes the client with server address and port, then connects to the server.
     * @author Jonas Helfer
     * @param port The server port
     * @param address The server address
     * @precondition port is a valid port number and address is a valid server address
     * @postcondition A new ChatClient instance is created and connected to the server
     */
    public ChatClient (int port, String address)
    {
        this.port = port;
        this.address = address;
        connectToServer();
    }


    /**
     * Establishes a connection to the server and initializes I/O streams.
     * Also starts a thread for receiving messages.
     * @author Jonas Helfer
     * @precondition The server is running and accessible
     * @postcondition The client is connected to the server and ready to send/receive messages
     */
    private void connectToServer ()
    {
        try
        {
            connectionToServer = new Socket(address, port);
            fromServerReader = new BufferedReader(new InputStreamReader(connectionToServer.getInputStream()));
            toServerWriter = new PrintWriter(new OutputStreamWriter(connectionToServer.getOutputStream()), true);

            receiveThread = new Thread(this::receiveMessages);
            receiveThread.start();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }


    /**
     * Continuously receives messages from the server and appends them to the chat area.
     * @author Jonas Helfer
     * @precondition The client is connected to the server
     * @postcondition Messages from the server are displayed in the chat area
     */
    private void receiveMessages ()
    {
        try
        {
            String message;
            while ((message = fromServerReader.readLine()) != null)
            {
                final String finalMessage = message;
                Platform.runLater(() -> appendChatMessage(finalMessage));
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            closeResources();
        }
    }


    /**
     * Creates and returns a VBox containing the chat area and input field.
     * @author Jonas Helfer
     * @return VBox containing chat UI elements
     * @precondition JavaFX is properly initialized
     * @postcondition A VBox with chat UI elements is created and returned
     */
    public static VBox createChatArea ()
    {
        chatArea = new TextArea();
        chatArea.setEditable(false);
        inputField = new TextField();
        inputField.setOnKeyPressed(event ->
        {
            if (event.getCode() == KeyCode.ENTER)
            {
                sendMessage(inputField.getText());
                inputField.clear();
            }
        });

        VBox chatBox = new VBox(chatArea, inputField);
        chatBox.setLayoutX(Constants_Multiplayer.LAYOUT_X_CHAT);
        chatBox.setLayoutY(Constants_Multiplayer.LAYOUT_Y_CHAT);
        return chatBox;
    }


    /**
     * Sends a message to the server.
     * @author Jonas Helfer
     * @param message The message to send
     * @precondition The client is connected to the server and toServerWriter is initialized
     * @postcondition The message is sent to the server
     */
    private static void sendMessage (String message)
    {
        if (toServerWriter != null)
        {
            toServerWriter.println(message);
        }
    }


    /**
     * Appends a message to the chat area.
     * @author Jonas Helfer
     * @param message The message to append
     * @precondition chatArea is initialized
     * @postcondition The message is appended to the chat area
     */
    public static void appendChatMessage (String message)
    {
        if (chatArea != null)
        {
            Platform.runLater(() -> chatArea.appendText(message + "\n"));
        }
    }


    /**
     * Closes all resources used by the client.
     * @author Jonas Helfer
     * @precondition The client has been initialized and connected
     * @postcondition All resources (sockets, streams, threads) are closed
     */
    private void closeResources ()
    {
        try
        {
            if (connectionToServer != null)
            {
                connectionToServer.close();
            }
            if (fromServerReader != null)
            {
                fromServerReader.close();
            }
            if (toServerWriter != null)
            {
                toServerWriter.close();
            }
            if (receiveThread != null)
            {
                receiveThread.interrupt();
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
