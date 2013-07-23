package at.game.server.ui.model;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

public class ServerSettingsModel {
	private Logger logger = Logger.getLogger(getClass());
	private String lblServerStatus;
	private String txtServerStatus;
	private String clientMessage;
	private String txtStartStopButton;
	private int port;
	public Set<ModelDataChangedListener> dataChangedListeners = new HashSet<ModelDataChangedListener>();
	
	public String getLblServerStatus() {
		return lblServerStatus;
	}
	public void setLblServerStatus(String lblServerStatus) {
		this.lblServerStatus = lblServerStatus;
	}
	public String getTxtServerStatus() {
		return txtServerStatus;
	}
	public void setTxtServerStatus(String txtServerStatus) {
		this.txtServerStatus = txtServerStatus;
	}
	public String getClientMessage() {
		return clientMessage;
	}
	public void setClientMessage(String clientMessage) {
		this.clientMessage = clientMessage;
	}
	public String getTxtStartStopButton() {
		return txtStartStopButton;
	}
	public void setTxtStartStopButton(String txtStartStopButton) {
		this.txtStartStopButton = txtStartStopButton;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	
	public void addModelDataChangedListener(ModelDataChangedListener listener) {
		dataChangedListeners.add(listener);
	}
	
	public void dataHasBeenUpdated() {
		logger.debug("model data updated, firing data changed listeners");
		for(ModelDataChangedListener listener : dataChangedListeners) {
			listener.dataChanged();
		}
	}
}
