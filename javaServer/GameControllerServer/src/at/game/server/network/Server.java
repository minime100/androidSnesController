package at.game.server.network;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import at.game.server.network.parsing.InputStreamParser;

/**
 * waiting for client tcp requests to the specified tcp port. Add handling for
 * client requests by adding {@link ServerResponseListener} to
 * {@link Server.addServerResponseListener}
 */
class Server extends Thread {
	private Logger logger = Logger.getLogger(getClass());
	ServerSocket mainSocket;
	private int port;
	private boolean running = false;
	private Set<ServerResponseListener> responseListeners = new HashSet<ServerResponseListener>();
	private Set<ServerStateChangedListener> stateChangedListeners = new HashSet<ServerStateChangedListener>();
	
	public Server(int port) {
		super("server" + System.currentTimeMillis());
		this.port = port;
	}

	@Override
	public void run() {
		logger.debug("starting server on port " + port);
		running = true;
		try {
			mainSocket = new ServerSocket(port);
			fireServerStateChangedListeners(true);

			while (running) {
				Socket connectedSocket = mainSocket.accept();
				logger.debug("connection accepted from "
						+ connectedSocket.getInetAddress().toString());

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
				logger.error("exception thrown while waiting for/retrieving a message: "
						+ e.getClass().getName() + " " + e.getMessage());
				running = false;
				fireServerStateChangedListeners(false);
			} else {
				logger.trace("exception was thrown, but server has been stopped anyway: "
						+ e.getClass().getName() + " " + e.getMessage());
			}
		}
	}

	public synchronized void stopServer() {
		logger.trace("stopping server...");
		running = false;

		if (!mainSocket.isClosed()) {
			try {
				mainSocket.close();
			} catch (IOException e) {
				logger.error("tried to close the server socket, but failed. cause: "
						+ e.getClass().getName() + " " + e.getMessage());
			}
		}
		
		fireServerStateChangedListeners(false);
	}

	public boolean isRunning() {
		return running;
	}

	public void addServerResponseListener(ServerResponseListener listener) {
		responseListeners.add(listener);
	}
	
	public void addServerStateChangeListener(ServerStateChangedListener listener) {
		stateChangedListeners.add(listener);
	}
	
	private void fireServerStateChangedListeners(boolean newState) {
		for(ServerStateChangedListener listener : stateChangedListeners) {
			listener.serverStateChanged(newState);
		}
	}
}
