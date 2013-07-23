package at.game.server;

import org.apache.log4j.Logger;

import at.game.server.ui.controller.ServerSettingsController;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Logger.getLogger("Main").trace(System.getProperty("os.name"));
		Logger.getLogger("Main").trace(System.getProperty("java.vendor"));
		Logger.getLogger("Main").trace(System.getProperty("java.vm.name"));
		Logger.getLogger("Main").trace(System.getProperty("java.version"));
		
		ServerSettingsController controller = new ServerSettingsController();
		controller.init();
	}

}
