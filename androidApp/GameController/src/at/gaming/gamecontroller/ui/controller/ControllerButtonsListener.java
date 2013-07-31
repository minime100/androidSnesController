package at.gaming.gamecontroller.ui.controller;

import android.view.MotionEvent;
import android.view.SoundEffectConstants;
import android.view.View;
import at.gaming.gamecontroller.network.MessageBroadcaster;
public class ControllerButtonsListener implements View.OnTouchListener {
	private String buttonBaseMessage;

	public ControllerButtonsListener(String buttonBaseMessage) {
		this.buttonBaseMessage = buttonBaseMessage;
	}
	
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		switch(event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			v.playSoundEffect(SoundEffectConstants.CLICK);
			new MessageBroadcaster(buttonBaseMessage + "_pressed").start();
			break;
		case MotionEvent.ACTION_UP:
			new MessageBroadcaster(buttonBaseMessage + "_released").start();
			break;
		}
		return false;
	}
}
