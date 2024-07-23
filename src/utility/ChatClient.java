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
    private String address;
    private int port;
    private Socket connectionToServer;
    private BufferedReader fromServerReader;
    private static PrintWriter toServerWriter;
    private static TextArea chatArea;
    private static TextField inputField;
    private Thread receiveThread;

    public ChatClient (int port, String address)
    {
        this.port = port;
        this.address = address;
        connectToServer();
    }

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

    private static void sendMessage (String message)
    {
        if (toServerWriter != null)
        {
            toServerWriter.println(message);
        }
    }

    public static void appendChatMessage (String message)
    {
        if (chatArea != null)
        {
            Platform.runLater(() -> chatArea.appendText(message + "\n"));
        }
    }

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

    public void shutdown ()
    {
        closeResources();
    }
}
