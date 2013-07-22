package at.gaming.gamecontroller.network;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

import org.apache.commons.io.IOUtils;

import android.util.Log;

public class MessageBroadcaster extends Thread {
	private String message;
	InetSocketAddress serverAddress = new InetSocketAddress("192.168.1.101", 50101);

	public MessageBroadcaster(String message) {
		this.message = message;
	}
	
	@Override
	public void run() {
		Socket outSocket = new Socket();
		try {
			Log.d("serv", "connecting...");
			outSocket.connect(serverAddress);
			Log.d("serv", "connected!");
			OutputStream out = outSocket.getOutputStream();
			IOUtils.write(message, out, "UTF-8");
			outSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.run();
	}
}
