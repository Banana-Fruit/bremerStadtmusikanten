package utility;


import resources.constants.Constants_ExceptionMessages;
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
    private ServerSocket serverSocket;
    private List<ClientHandler> clients;
    private static volatile ChatServer instance = null;
    private ExecutorService executorService;
    private volatile boolean running = true;


    private ChatServer ()
    {
        clients = new CopyOnWriteArrayList<>();
        executorService = Executors.newCachedThreadPool();
    }

    public void start (int port)
    {
        try
        {
            serverSocket = new ServerSocket(port, 50, InetAddress.getByName("0.0.0.0"));
            System.out.println("Started chat server on port " + port);


            while (running)
            {
                System.out.println("Waiting for new Client...");
                Socket connectionToClient = serverSocket.accept();
                ClientHandler client = new ClientHandler(this, connectionToClient);
                clients.add(client);
                executorService.submit(client);
                broadcastMessage(client.getName() + " connected.");
                System.out.println("Accepted new client");
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

    public void broadcastMessage (String message)
    {
        System.out.println(message);
        for (ClientHandler client : clients)
        {
            client.sendMessage(message);
        }
    }

    public void removeClient (ClientHandler client)
    {
        clients.remove(client);
        broadcastMessage(client.getName() + " disconnected.");
    }



    public static ChatServer getInstance ()
    {
        if (instance == null)
        {
            throw new IllegalStateException(Constants_ExceptionMessages.SINGLETON_NOT_INITIALIZED);
        }
        return instance;
    }
}