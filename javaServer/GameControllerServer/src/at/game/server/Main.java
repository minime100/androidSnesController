package at.game.server;

import at.game.server.ui.ServerSettings;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new ServerSettings().setVisible(true);
			}
		});
	}

}
