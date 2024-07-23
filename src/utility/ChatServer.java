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
        broadcastMessage(client.getName() + Constants_Multiplayer.DISCONNECTED);
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