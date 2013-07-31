package at.game.server.ui.serverSettings;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import at.game.server.network.ServerDispatcher;
import at.game.server.network.ServerStateChangedListener;

public class ServerSettingsController {
	private ServerSettingsView settings;
	private ServerSettingsModel model;
	private ServerDispatcher server;
	
	public void init() {
		setUpModel();
		setUpServer();
		setUpUi();
	}

	private void setUpServer() {
		server = new ServerDispatcher(50101);
		server.addServerResponseListener(new SnesServerResponseListener(model));
		server.addServerStateChangeListener(new ServerStateChangedListener() {
			@Override
			public void serverStateChanged(boolean serverIsRunning) {
				model.setTxtServerStatus(serverIsRunning ? "running"
						: "stopped");
				model.setTxtStartStopButton(serverIsRunning ? "stop" : "start");
				model.setCanPortBeModified(!serverIsRunning);
				model.dataHasBeenUpdated();
			}
		});
	}

	private void setUpModel() {
		model = new ServerSettingsModel();
		model.setClientMessage("");
		model.setLblServerStatus("server status:");
		model.setPortTxt("server started at port:");
		model.setPort(50101);
		model.setTxtServerStatus("stopped");
		model.setTxtStartStopButton("start");
		model.setCanPortBeModified(true);
	}

	private void setUpUi() {
		settings = new ServerSettingsView(model);
		settings.setStartStopBtnActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (server.isRunning()) {
					server.stopServer();
				} else {
					server.changePortAndRestart(model.getPort());
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
