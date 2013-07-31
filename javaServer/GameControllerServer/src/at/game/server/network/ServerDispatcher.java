package at.game.server.network;

import java.util.HashSet;
import java.util.Set;

public class ServerDispatcher {
	private Server server;
	private Set<ServerResponseListener> responseListeners = new HashSet<>();
	private Set<ServerStateChangedListener> stateChangedListeners = new HashSet<ServerStateChangedListener>();
	private int port;
	
	public ServerDispatcher(int port) {
		this.port = port;
	}
	
	public void startServer() {
		server = new Server(port);
		for(ServerResponseListener listener : responseListeners) {
			server.addServerResponseListener(listener);
		}
		for(ServerStateChangedListener listener : stateChangedListeners) {
			server.addServerStateChangeListener(listener);
		}
		server.start();
	}

	public void changePortAndRestart(int port) {
		this.port = port;
		if(isRunning()) {
			restartServer();
		} else
			startServer();
	}
	
	public void stopServer() {
		server.stopServer();
	}
	
	public void restartServer() {
		stopServer();
		startServer();
	}
	
	public void addServerResponseListener(ServerResponseListener listener) {
		responseListeners.add(listener);
	}
	
	public void addServerStateChangeListener(ServerStateChangedListener listener) {
		stateChangedListeners.add(listener);
	}
	
	public void modifyPort(int port) {
		this.port = port;
		restartServer();
	}
	
	public boolean isRunning() {
		return (server == null) ? false : server.isRunning();
	}
}
