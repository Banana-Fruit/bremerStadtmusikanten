package utility;


import resources.constants.Constants_ExceptionMessages;
import resources.constants.Constants_Multiplayer;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ChatServer
{
    // ServerSocket for accepting incoming client connections
    private ServerSocket serverSocket;

    // Thread-safe list of connected client handlers
    private List<ClientHandler> clients;

    // Singleton instance of the ChatServer
    private static volatile ChatServer instance = null;

    // Thread pool for managing client connections
    private ExecutorService executorService;

    // Flag to control the server's main loop
    private volatile boolean running = true;

    /**
     * Private constructor for the ChatServer class.
     * This method initializes a new ChatServer instance with a thread-safe list of clients
     * and a cached thread pool for handling client connections.
     * @author Jonas Helfer
     * @precondition None
     * @postcondition A new ChatServer instance is created with initialized client list and executor service
     */
    private ChatServer ()
    {
        clients = new CopyOnWriteArrayList<>();
        executorService = Executors.newCachedThreadPool();
    }


    /**
     * Starts the ChatServer on the specified port.
     * This method creates a ServerSocket and continuously accepts new client connections,
     * creating a new ClientHandler for each connection.
     * @author Jonas Helfer
     * @param port The port number on which the server will listen
     * @precondition The server is not already running
     * @postcondition The server is running and accepting client connections
     */
    public void start (int port)
    {
        try
        {
            serverSocket = new ServerSocket(port, Constants_Multiplayer.BACKLOG, InetAddress.getByName(Constants_Multiplayer.LISTEN_FOR_ADDRESS));
            while (running)
            {
                Socket connectionToClient = serverSocket.accept();
                ClientHandler client = new ClientHandler(this, connectionToClient);
                clients.add(client);
                executorService.submit(client);
                broadcastMessage(client.getName() + Constants_Multiplayer.CONNECTED);
            }
        } catch (IOException e)
        {
            if (running)
            {
                throw new RuntimeException(e);
            }
        } finally
        {
            closeServerSocket();
        }
    }


    /**
     * Closes the ServerSocket.
     * This method is called when the server is shutting down to release resources.
     * @author Jonas Helfer
     * @precondition The ServerSocket has been initialized
     * @postcondition The ServerSocket is closed
     */
    private void closeServerSocket ()
    {
        try
        {
            if (serverSocket != null)
            {
                serverSocket.close();
            }
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }


    /**
     * Initializes the ChatServer singleton instance.
     * This method ensures that only one instance of ChatServer is created.
     * @author Jonas Helfer
     * @precondition The ChatServer instance has not been initialized
     * @postcondition The ChatServer instance is initialized
     * @throws IllegalStateException if the ChatServer has already been initialized
     */
    public static synchronized void initialize ()
    {
        if (instance == null)
        {
            instance = new ChatServer();
        } else
        {
            throw new IllegalStateException(Constants_ExceptionMessages.ALREADY_INITIALIZED);
        }
    }


    /**
     * Broadcasts a message to all connected clients.
     * This method iterates through all client handlers and sends the message to each.
     * @author Jonas Helfer
     * @param message The message to be broadcast
     * @precondition The server is running and has active clients
     * @postcondition The message is sent to all connected clients
     */
    public void broadcastMessage (String message)
    {
        for (ClientHandler client : clients)
        {
            client.sendMessage(message);
        }
    }


    /**
     * Removes a client from the server and notifies other clients.
     * This method is called when a client disconnects.
     * @author Jonas Helfer
     * @param client The ClientHandler to be removed
     * @precondition The client is in the list of connected clients
     * @postcondition The client is removed and other clients are notified of the disconnection
     */
    public void removeClient (ClientHandler client)
    {
        clients.remove(client);
        broadcastMessage(client.getName() + Constants_Multiplayer.DISCONNECTED);
    }


    /**
     * Returns the singleton instance of the ChatServer.
     * This method ensures that only one instance of ChatServer is used throughout the application.
     * @author Jonas Helfer
     * @return The singleton instance of ChatServer
     * @precondition The ChatServer has been initialized
     * @postcondition The singleton instance of ChatServer is returned
     * @throws IllegalStateException if the ChatServer has not been initialized
     */
    public static ChatServer getInstance ()
    {
        if (instance == null)
        {
            throw new IllegalStateException(Constants_ExceptionMessages.SINGLETON_NOT_INITIALIZED);
        }
        return instance;
    }
}