package at.gaming.gamecontroller.ui.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import at.gaming.gamecontroller.R;
import at.gaming.gamecontroller.network.MessageBroadcaster;
import at.gaming.gamecontroller.ui.settings.Settings;

public class Controller extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_controller);

		Button up = (Button) findViewById(R.id.btn_up);
		Button down = (Button) findViewById(R.id.btn_down);
		Button left = (Button) findViewById(R.id.btn_left);
		Button right = (Button) findViewById(R.id.btn_right);

		
		up.setOnTouchListener(new ControllerButtonsListener("up"));
		down.setOnTouchListener(new ControllerButtonsListener("down"));
		left.setOnTouchListener(new ControllerButtonsListener("left"));
		right.setOnTouchListener(new ControllerButtonsListener("right"));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.controller, menu);
		return true;
	}
	
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		if(item.getItemId() == R.id.action_settings) {
			Intent intent = new Intent(this, Settings.class);
			startActivity(intent);
		}
		return super.onMenuItemSelected(featureId, item);
	}

}
