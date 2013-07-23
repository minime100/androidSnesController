package at.game.server.ui.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import at.game.server.network.ServerDispatcher;
import at.game.server.network.ServerResponseListener;
import at.game.server.network.ServerStateChangedListener;
import at.game.server.ui.model.ServerSettingsModel;
import at.game.server.ui.view.ServerSettings;

public class ServerSettingsController {
	ServerSettings settings;
	ServerSettingsModel model;
	private ServerDispatcher server;

	public void init() {
		setUpServer();
		setUpModel();
		setUpUi();
	}
	
	private void setUpServer() {
		server = new ServerDispatcher(50101);
		server.addServerResponseListener(new ServerResponseListener() {
			@Override
			public void handleResponse(String serverResponse) {
				model.setServerResponse(serverResponse);
				model.dataHasBeenUpdated();
			}
		});
		server.addServerStateChangeListener(new ServerStateChangedListener() {
			@Override
			public void serverStateChanged(boolean serverIsRunning) {
				model.setTxtServerStatus(serverIsRunning ? "running"
						: "stopped");
				model.setTxtStartStopButton(serverIsRunning ? "stop" : "start");
				model.dataHasBeenUpdated();
			}
		});
	}
	
	private void setUpModel() {
		model = new ServerSettingsModel();
		model.setClientMessage("");
		model.setLblServerStatus("server status:");
		model.setPort(50101);
		model.setTxtServerStatus("stopped");
		model.setTxtStartStopButton("start");
	}
	
	private void setUpUi() {
		settings = new ServerSettings(model);
		settings.setStartStopBtnActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (server.isRunning()) {
					server.stopServer();
				} else {
					server.startServer();
				}
				model.dataHasBeenUpdated();
			}
		});

		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				settings.setVisible(true);
			}
		});
	}
}
