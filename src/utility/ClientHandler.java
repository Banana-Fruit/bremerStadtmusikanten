package utility;


import resources.constants.Constants_Multiplayer;
import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable
{
	private ChatServer chatServer;
	private Socket connectionToClient;
	private String name;
	private BufferedReader fromClientReader;
	private PrintWriter toClientWriter;


	public ClientHandler(ChatServer chatServer, Socket connectionToClient)
	{
		this.chatServer = chatServer;
		this.connectionToClient = connectionToClient;
		this.name = connectionToClient.getInetAddress().getHostAddress();
		new Thread(this).start();
	}

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

	public void sendMessage(String message)
	{
		if (toClientWriter != null)
		{
			toClientWriter.println(message);
		}
	}

	public String getName()
	{
		return name;
	}
}
