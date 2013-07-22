package at.game.server;

import org.apache.log4j.Logger;

import at.game.server.ui.view.ServerSettings;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Logger.getLogger("Main").trace(System.getProperty("os.name"));
		Logger.getLogger("Main").trace(System.getProperty("java.vendor"));
		Logger.getLogger("Main").trace(System.getProperty("java.vm.name"));
		Logger.getLogger("Main").trace(System.getProperty("java.version"));
		
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new ServerSettings().setVisible(true);
			}
		});
	}

}
