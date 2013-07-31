package at.gaming.gamecontroller.ui.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import at.gaming.gamecontroller.R;
import at.gaming.gamecontroller.ui.settings.Settings;

public class Controller extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_controller);

		Button up = (Button) findViewById(R.id.btn_up);
		up.setOnTouchListener(new ControllerButtonsListener("up"));
		Button down = (Button) findViewById(R.id.btn_down);
		down.setOnTouchListener(new ControllerButtonsListener("down"));
		Button left = (Button) findViewById(R.id.btn_left);
		left.setOnTouchListener(new ControllerButtonsListener("left"));
		Button right = (Button) findViewById(R.id.btn_right);
		right.setOnTouchListener(new ControllerButtonsListener("right"));

		Button start = (Button) findViewById(R.id.btn_start);
		start.setOnTouchListener(new ControllerButtonsListener("start"));
		Button select = (Button) findViewById(R.id.btn_select);
		select.setOnTouchListener(new ControllerButtonsListener("select"));
		
		Button a = (Button) findViewById(R.id.btn_a);
		a.setOnTouchListener(new ControllerButtonsListener("btn_a"));
		Button b = (Button) findViewById(R.id.btn_b);
		b.setOnTouchListener(new ControllerButtonsListener("btn_b"));
		Button x = (Button) findViewById(R.id.btn_x);
		x.setOnTouchListener(new ControllerButtonsListener("btn_x"));
		Button y = (Button) findViewById(R.id.btn_y);
		y.setOnTouchListener(new ControllerButtonsListener("btn_y"));
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
