package com.ziggeo.androidapp;

import java.util.List;

import com.ziggeo.androidsdk.VideoPlayer;
import com.ziggeo.androidsdk.VideoRecorder;
import com.ziggeo.androidsdk.Ziggeo;
import com.ziggeo.androidsdk.helper.OnDismissVideoPlayerCallback;
import com.ziggeo.androidsdk.helper.OnUploadCompletedCallback;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
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

	private VideoRecorder videoRecorderFragment;
	
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
			String mode = params.get(0); // Either a recorder or a player
			String token = params.get(1); // "App token"
			
			// Set ziggeo token and start the recorder or player depending on the url
			Ziggeo.initialize(token);
			
			if (mode.equals("recorder"))
				setupRecorder();
			else if (mode.equals("player"))
				setupPlayer("", "");
			
		} 
		
		// Start the app in a different mode
		else {
			
		}

	}
	
	private void setupRecorder() {

		// Create a new recorder fragment
		videoRecorderFragment = VideoRecorder.newInstance(10);
		
		// Add the fragment to the activity
		getFragmentManager().beginTransaction().add(R.id.container, 
				videoRecorderFragment, "VideoRecorder").commit();
		
		// Subscribe to dismiss callback, in order to remove the recorder
		videoRecorderFragment.callback = new OnUploadCompletedCallback() {
			   public void OnUploadCompleted(String videoToken, String imagePath) {
//				   playerSetup(videoToken, imagePath);
//				   launchBroser();
			   }
			   
			   public void OnVideoRerecord() {
				   
			   }
		};
		
	}
	
	
	private void setupPlayer(String videoToken, String imagePath) {
				
		// Create a new player fragment
		VideoPlayer videoPlayerFragment = VideoPlayer.newInstance(videoToken, imagePath);
		
		// Add the fragment to the activity
		getFragmentManager().beginTransaction().add(R.id.container, 
				videoPlayerFragment, "VideoPlayer").commit();
	
		// Subscribe to dismiss callback, in order to remove the player
//		videoPlayerFragment.callback = new OnDismissVideoPlayerCallback() {
//			   public void OnDismissVideoPlayer() {
//			   }
//		};
		
	}
	
	private void launchBrowser() {
		String url = "http://www.google.com";
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setData(Uri.parse(url));
		startActivity(i);
	}


}
