package at.game.server.ui.view;

import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import at.game.server.ui.model.ModelDataChangedListener;
import at.game.server.ui.model.ServerSettingsModel;

/**
 * the main page of the server configuration
 */
public class ServerSettings extends JFrame {
	private static final long serialVersionUID = 0L;
	private JLabel serverStatusTxt;
	private JLabel serverStatus;
	private JLabel serverResponse;
	private JButton startStopServer;
	private ServerSettingsModel model = new ServerSettingsModel();

	public ServerSettings(ServerSettingsModel model) {
		this.model = model;
		model.addModelDataChangedListener(new ModelDataChangedListener() {
			@Override
			public void dataChanged() {
				ServerSettings.this.updateContent();
			}
		});

		initComponents();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(200, 200);
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	private void initComponents() {
		serverStatusTxt = new JLabel();
		serverStatus = new JLabel();
		serverResponse = new JLabel();
		startStopServer = new JButton();
		setupLayout();
		updateContent();
	}

	private void setupLayout() {
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addComponent(startStopServer).addComponent(serverStatusTxt)
				.addComponent(serverStatus).addComponent(serverResponse));
		layout.setVerticalGroup(layout.createParallelGroup()
				.addComponent(startStopServer).addComponent(serverStatusTxt)
				.addComponent(serverStatus).addComponent(serverResponse));

		pack();
	}

	public void setStartStopBtnActionListener(ActionListener listener) {
		startStopServer.addActionListener(listener);
	}

	private void updateContent() {
		startStopServer.setText(model.getTxtStartStopButton());
		serverStatusTxt.setText(model.getLblServerStatus());
		serverStatus.setText(model.getTxtServerStatus());
		serverResponse.setText(model.getServerResponse());
	}
}
