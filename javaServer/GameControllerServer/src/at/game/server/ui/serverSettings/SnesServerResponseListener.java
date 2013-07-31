package at.game.server.ui.serverSettings;

import at.game.server.emulation.KeyboardEmulationForSnes9X;
import at.game.server.network.ServerResponseListener;

public class SnesServerResponseListener implements ServerResponseListener {
	private ServerSettingsModel model;
	private KeyboardEmulationForSnes9X keyboard = new KeyboardEmulationForSnes9X();

	public SnesServerResponseListener(ServerSettingsModel model) {
		this.model = model;
	}

	@Override
	public void handleResponse(String serverResponse) {
		if (serverResponse.startsWith("up")) {
			if (serverResponse.endsWith("pressed"))
				keyboard.dPadUpPress();
			if (serverResponse.endsWith("released"))
				keyboard.dPadUpRelease();
		}
		if (serverResponse.startsWith("down")) {
			if (serverResponse.endsWith("pressed"))
				keyboard.dPadDownPress();
			if (serverResponse.endsWith("released"))
				keyboard.dPadDownRelease();
		}
		if (serverResponse.startsWith("left")) {
			if (serverResponse.endsWith("pressed"))
				keyboard.dPadLeftPress();
			if (serverResponse.endsWith("released"))
				keyboard.dPadLeftRelease();
		}
		if (serverResponse.startsWith("right")) {
			if (serverResponse.endsWith("pressed"))
				keyboard.dPadRightPress();
			if (serverResponse.endsWith("released"))
				keyboard.dPadRightRelease();
		}
		if (serverResponse.startsWith("start")) {
			if (serverResponse.endsWith("pressed"))
				keyboard.startPress();
			if (serverResponse.endsWith("released"))
				keyboard.startRelease();
		}
		if (serverResponse.startsWith("select")) {
			if (serverResponse.endsWith("pressed"))
				keyboard.selectPress();
			if (serverResponse.endsWith("released"))
				keyboard.selectRelease();
		}
		if (serverResponse.startsWith("btn_a")) {
			if (serverResponse.endsWith("pressed"))
				keyboard.aPress();
			if (serverResponse.endsWith("released"))
				keyboard.aRelease();
		}
		if (serverResponse.startsWith("btn_b")) {
			if (serverResponse.endsWith("pressed"))
				keyboard.bPress();
			if (serverResponse.endsWith("released"))
				keyboard.bRelease();
		}
		if (serverResponse.startsWith("btn_x")) {
			if (serverResponse.endsWith("pressed"))
				keyboard.xPress();
			if (serverResponse.endsWith("released"))
				keyboard.xRelease();
		}
		if (serverResponse.startsWith("btn_y")) {
			if (serverResponse.endsWith("pressed"))
				keyboard.yPress();
			if (serverResponse.endsWith("released"))
				keyboard.yRelease();
		}
		model.setServerResponse(serverResponse);
		model.dataHasBeenUpdated();
	}

}
