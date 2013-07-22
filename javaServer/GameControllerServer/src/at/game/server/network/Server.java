package at.game.server.network;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

import at.game.server.parsing.InputStreamParser;

public class Server extends Thread {
	private ServerSocket mainSocket;
	private int port = 50101;
	private boolean running = false;
	private Set<ServerResponseListener> responseListeners = new HashSet<ServerResponseListener>();

	public Server() {
		super("Server" + System.currentTimeMillis());
	}

	@Override
	public void run() {

		running = true;
		try {
			mainSocket = new ServerSocket(port);

			while (running) {
				Socket connectedSocket = mainSocket.accept();
				System.out.println("Connection accepted");

				InputStream is = connectedSocket.getInputStream();
				InputStreamParser parser = new InputStreamParser();
				String clientMessage = parser.parseFromInputStream(is);
				for (ServerResponseListener listener : responseListeners) {
					synchronized (listener) {
						listener.handleResponse(clientMessage);
					}
				}
			}
		} catch (IOException e) {
			if (running) {
				e.printStackTrace();
				running = false;
			}
		}
	}

	public synchronized void stopServer() {
		running = false;

		if (!mainSocket.isClosed()) {
			try {
				mainSocket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public boolean isRunning() {
		return running;
	}

	public void addServerResponseListener(ServerResponseListener listener) {
		responseListeners.add(listener);
	}
}
