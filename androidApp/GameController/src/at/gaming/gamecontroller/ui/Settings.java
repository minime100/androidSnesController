package at.gaming.gamecontroller.ui;

import java.net.InetSocketAddress;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import at.gaming.gamecontroller.R;
import at.gaming.gamecontroller.network.MessageBroadcaster;

public class Settings extends Activity {
	Button portOk;
	EditText port;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		
		port = (EditText) findViewById(R.id.port_text);
		port.setText(MessageBroadcaster.serverAddress.toString().substring(1));
		portOk = (Button) findViewById(R.id.port_ok);
		portOk.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				parsePort(port.getText());
			}
		});
	}

	private void parsePort(Editable text) {
		String[] splitText = text.toString().split(":");
		MessageBroadcaster.serverAddress = new InetSocketAddress(splitText[0], Integer.parseInt(splitText[1]));
	}
}
