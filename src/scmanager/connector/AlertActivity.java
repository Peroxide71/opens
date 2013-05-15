package scmanager.connector;


import scmanager.connector.R;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AlertActivity extends Activity implements OnClickListener{
	Button install, cancel;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.alert_layout);
		install = (Button) findViewById(R.id.bInstall);
		cancel = (Button) findViewById(R.id.bCancel);
		install.setOnClickListener(this);
		cancel.setOnClickListener(this);
	}


	@Override
	public void onClick(View v) {
		if (v == install){
			Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=manager.ui.screens"));
			startActivity(browserIntent);
		} else {
			finish();
			System.exit(0);
		}
		
	}

}
