package at.game.server.ui.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import at.game.server.network.Server;
import at.game.server.network.ServerResponseListener;
import at.game.server.network.ServerStateChangedListener;
import at.game.server.ui.model.ServerSettingsModel;
import at.game.server.ui.view.ServerSettings;

public class ServerSettingsController {
	ServerSettings settings;
	ServerSettingsModel model;
	private Server server;

	public void init() {
		model = new ServerSettingsModel();
		model.setClientMessage("");
		model.setLblServerStatus("server status:");
		model.setPort(50101);
		model.setTxtServerStatus("stopped");
		model.setTxtStartStopButton("start");

		settings = new ServerSettings(model);
		settings.setStartStopBtnActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if ((server == null) ? false : server.isRunning()) {
					server.stopServer();
					model.setTxtStartStopButton("start");
				} else {
					server = new Server(50101);
					server.start();
					model.setTxtStartStopButton("stop");
					model.setTxtServerStatus("running");
					server.addServerResponseListener(new ServerResponseListener() {
						@Override
						public void handleResponse(String serverResponse) {
							model.setServerResponse(serverResponse);
							model.dataHasBeenUpdated();
						}
					});
					server.addServerStateChangeListener(new ServerStateChangedListener() {
						@Override
						public void serverStateChanged() {
							model.setTxtServerStatus(server.isRunning() ? "running"
									: "stopped");
							model.dataHasBeenUpdated();
						}
					});
				}
				model.dataHasBeenUpdated();
			}
		});

		updateIsRunningText();

		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				settings.setVisible(true);
			}
		});
	}

	private void updateIsRunningText() {
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
			}
		};

		Timer timer = new Timer();
		timer.schedule(task, 100, 100);
	}
}
