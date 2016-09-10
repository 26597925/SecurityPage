package com.example.login;

import java.lang.reflect.Method;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import dalvik.system.PathClassLoader;

public class MainActivity extends Activity {
EditText Text1;
EditText Text2;
Button button;
Class dynamic;  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Toast.makeText(getApplicationContext(), "null", 0).show();
		Text1=(EditText) findViewById(R.id.account);
	    Text2=(EditText) findViewById(R.id.password);
	    button=(Button) findViewById(R.id.btn);
	    Log.i("tangsilain","btnclick");
	    button.setOnClickListener(new OnClickListener() {
	    	
			//getaccount and password
			String account=Text1.getText().toString();
			String passsword=Text2.getText().toString();

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.i("tangsilian","btnclick");
			
					if (uzi(account, passsword)) {
						Toast.makeText(getApplicationContext(), "right", 0).show();
					
				}else {
					Toast.makeText(getApplicationContext(), "null", 0).show();
				}
			}

		
		});
	}

	private boolean uzi(String account2, String passsword2) {
		if (account2=="uzi"&&passsword2=="uzi") {
			return true;
		}else {
			
			return false;
		}
	}

	
}
