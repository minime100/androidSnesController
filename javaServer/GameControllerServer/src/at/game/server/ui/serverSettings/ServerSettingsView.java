package at.game.server.ui.serverSettings;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import at.game.server.ui.ModelDataChangedListener;

/**
 * the main page of the server configuration
 */
public class ServerSettingsView extends JFrame {
	private static final long serialVersionUID = 0L;
	private JLabel serverStatusTxt;
	private JLabel serverStatus;
	private JLabel serverResponse;
	private JLabel portTxt;
	private JTextField port;
	private JButton startStopServer;
	private ServerSettingsModel model = new ServerSettingsModel();

	public ServerSettingsView(ServerSettingsModel model) {
		this.model = model;
		model.addModelDataChangedListener(new ModelDataChangedListener() {
			@Override
			public void dataChanged() {
				ServerSettingsView.this.updateContent();
			}
		});

		initComponents();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(200, 120);
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
		portTxt = new JLabel();
		port = new JTextField();

		setupLayout();
		updateContent();
	}

	private void setupLayout() {
		GridLayout layout = new GridLayout(0, 2);
		getContentPane().setLayout(layout);
		getContentPane().add(serverStatusTxt);
		getContentPane().add(serverStatus);
		getContentPane().add(portTxt);
		getContentPane().add(port);
		getContentPane().add(startStopServer);
		getContentPane().add(serverResponse);
	}

	public void setStartStopBtnActionListener(ActionListener listener) {
		startStopServer.addActionListener(new StartStopListenerDecorator(
				listener));
	}

	private void updateContent() {
		startStopServer.setText(model.getTxtStartStopButton());
		serverStatusTxt.setText(model.getLblServerStatus());
		serverStatus.setText(model.getTxtServerStatus());
		serverResponse.setText(model.getServerResponse());
		portTxt.setText(model.getPortTxt());
		port.setText(String.valueOf(model.getPort()));
		port.setEditable(model.isCanPortBeModified());
	}

	private class StartStopListenerDecorator implements ActionListener {
		private ActionListener decorated;

		public StartStopListenerDecorator(ActionListener decorated) {
			this.decorated = decorated;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (validatePort(port.getText())) {
				model.setPort(Integer.parseInt(port.getText()));
				decorated.actionPerformed(e);
			} else
				JOptionPane.showMessageDialog(null,
						"port must be in range of 1-65535");
		}
	}

	private boolean validatePort(String text) {
		try {
			int port = Integer.parseInt(text);
			return port > 0 && port <= 65535;
		} catch (NumberFormatException pe) {
			return false;
		}
	}
}
