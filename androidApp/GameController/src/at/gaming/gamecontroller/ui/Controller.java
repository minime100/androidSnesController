package at.gaming.gamecontroller.ui;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import at.gaming.gamecontroller.R;
import at.gaming.gamecontroller.R.layout;
import at.gaming.gamecontroller.R.menu;
import at.gaming.gamecontroller.network.MessageBroadcaster;

public class Controller extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_controller);

		Button up = (Button) findViewById(R.id.btn_up);
		Button down = (Button) findViewById(R.id.btn_down);
		Button left = (Button) findViewById(R.id.btn_left);
		Button right = (Button) findViewById(R.id.btn_right);

		up.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				new MessageBroadcaster("up").start();
			}
		});
		down.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				new MessageBroadcaster("down").start();
			}
		});
		left.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				new MessageBroadcaster("left").start();
			}
		});
		right.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				new MessageBroadcaster("right").start();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.controller, menu);
		return true;
	}

}
