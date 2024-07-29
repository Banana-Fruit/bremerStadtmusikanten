package utility;

import resources.constants.Constants_Multiplayer;
import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable
{
	// Attributes

	// Reference to the parent ChatServer
	private ChatServer chatServer;

	// Socket connection to the client
	private Socket connectionToClient;

	// Name or identifier of the client
	private String name;

	// Reader for incoming messages from the client
	private BufferedReader fromClientReader;

	// Writer for outgoing messages to the client
	private PrintWriter toClientWriter;

	/**
	 * * Constructor for the ClientHandler class.
	 *  * This method initializes a new ClientHandler instance and starts a new thread for it.
	 *  * It sets up the connection to the client and assigns a name based on the client's IP address.
	 * @author Jonas Helfer
	 * @param chatServer The parent ChatServer
	 * @param connectionToClient The socket connection to the client
	 * @precondition chatServer is not null and connectionToClient is a valid, open socket connection
	 * @postcondition A new ClientHandler thread is started
	 */
	public ClientHandler(ChatServer chatServer, Socket connectionToClient)
	{
		this.chatServer = chatServer;
		this.connectionToClient = connectionToClient;
		this.name = connectionToClient.getInetAddress().getHostAddress();
		new Thread(this).start();
	}

	/**
	 * * The main method that runs when the ClientHandler thread starts.
	 *  * It sets up input and output streams for communication with the client,
	 *  * continuously reads messages from the client, and broadcasts them to all clients.
	 *  * If an exception occurs or the client disconnects, it cleans up resources and removes the client from the server.
	 * @author Jonas Helfer
	 * @precondition The ClientHandler has been properly initialized
	 * @postcondition The client is removed from the ChatServer and all resources are closed
	 */
	@Override
	public void run()
	{
		try
		{
			fromClientReader = new BufferedReader(new InputStreamReader(connectionToClient.getInputStream()));
			toClientWriter = new PrintWriter(new OutputStreamWriter(connectionToClient.getOutputStream()), true);

			String message;
			while ((message = fromClientReader.readLine()) != null)
			{
				chatServer.broadcastMessage(name + Constants_Multiplayer.KOLON + message);
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		} finally
		{
			chatServer.removeClient(this);
			closeResources();
		}
	}

	/**
	 * * A private helper method to close all open resources.
	 *  * This method is called when the client connection is terminating.
	 *  * It ensures that the input reader, output writer, and socket connection are all properly closed.
	 * @author Jonas Helfer
	 * @precondition This method is called when the connection is terminating
	 * @postcondition All resources (reader, writer, socket) are closed
	 */
	private void closeResources()
	{
		try
		{
			if (fromClientReader != null)
			{
				fromClientReader.close();
			}
			if (toClientWriter != null)
			{
				toClientWriter.close();
			}
			if (connectionToClient != null)
			{
				connectionToClient.close();
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * * A public method to send a message to the client.
	 *  * This method is likely called by the ChatServer when it needs to send a message to this specific client.
	 *  * It checks if the output writer is available before attempting to send the message.
	 * @author Jonas Helfer
	 * @param message The message to be sent
	 * @precondition toClientWriter is initialized and not null
	 * @postcondition The message is sent to the client
	 */
	public void sendMessage(String message)
	{
		if (toClientWriter != null)
		{
			toClientWriter.println(message);
		}
	}

	/**
	 * * A simple getter method that returns the name (identifier) of the client.
	 *  * This name is typically set to the client's IP address when the ClientHandler is created.
	 * @author Jonas Helfer
	 * @return The name or identifier of the client
	 */
	public String getName()
	{
		return name;
	}
}