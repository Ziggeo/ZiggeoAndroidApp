/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.android.media;

import com.ziggeo.androidsdk.*;
import com.ziggeo.androidsdk.helper.*;

import android.app.*;
import android.os.Bundle;
import android.util.Log;
import android.view.*;

public class VideoViewDemo extends Activity {

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);
		
		final VideoRecorder videoRecorderFragment = VideoRecorder.newInstance(30);
/*		FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
		fragmentTransaction.add(R.id.main_layout, videoRecorderFragment, "VideoRecorderFragment").commit();
*/
		getFragmentManager().
			beginTransaction().
			add(R.id.main_layout,
					//VideoPlayer.newInstance("YOUR_VIDEO_TOKEN"),
					videoRecorderFragment,
					"VideoTest"
			).commit();
		/*
		videoRecorderFragment.callback = new OnUploadCompletedCallback() {
		   public void OnUploadCompleted(String videoToken, String imagePath) {
				RerecordVideoPlayer videoPlayerFragment = RerecordVideoPlayer.newInstance(videoToken);
				FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
				fragmentTransaction.add(R.id.main_layout, videoPlayerFragment, "VideoPlayerFragment").commit();
				videoPlayerFragment.callback = new OnDismissVideoPlayerCallback() {
					   public void OnDismissVideoPlayer() {
						    // Remove the VideoPlayer fragment using its tag
							FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
							fragmentTransaction.remove(getFragmentManager().findFragmentByTag("VideoPlayerFragment")).commit();
							videoRecorderFragment.resetStream();
					   }
				};
		   }
		};*/
	}
	
}
