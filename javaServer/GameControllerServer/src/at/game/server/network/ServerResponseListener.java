package at.game.server.network;

/**
 * used to handle server responses
 */
public interface ServerResponseListener {
	public void handleResponse(String serverResponse);
}
