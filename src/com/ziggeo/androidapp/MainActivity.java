package com.ziggeo.androidapp;

import java.util.List;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// Check if the app was opened by an external app, i.e. the browser
		if (getIntent().getData() != null) {
			
			// Get the url that triggered the app
			Uri data = getIntent().getData();
			
			// Get its parameters, delimited by '/' 
			List<String> params = data.getPathSegments();
			
			// Get the App token, whose position depends on the url type
			String token = params.get(1); // "App token"

		}

	}


}
