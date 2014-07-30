package com.example.favtube.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.favtube.R;
import com.example.favtube.model.Mp3;
import com.example.favtube.util.DeveloperKey;
import com.example.favtube.util.Log;
import com.example.favtube.util.VideoUtilities;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

/**
 * A simple YouTube Android API demo application which shows how to create a
 * simple application that displays a YouTube Video in a
 * {@link YouTubePlayerView}.
 * <p>
 * Note, to use a {@link YouTubePlayerView}, your activity must extend
 * {@link YouTubeBaseActivity}.
 */
public class PlayerActivity extends YouTubeFailureRecoveryActivity {
	String videoId = "" ;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_player);
		   
		YouTubePlayerView youTubeView = (YouTubePlayerView) findViewById(R.id.youtube_view);
		youTubeView.initialize(DeveloperKey.DEVELOPER_KEY, this);
		
		Bundle b = getIntent().getExtras();
		videoId = b.getString("video_id");
		Log.d("videoId="+videoId);
		String title = b.getString("title");
		String description = b.getString("description");
		
		TextView titleTextView = (TextView)findViewById(R.id.videoTitle) ;
		titleTextView.setText(title);
		TextView descTextView = (TextView)findViewById(R.id.videoDescription) ;
		descTextView.setText(description);
		
		Button convertButton = (Button) findViewById(R.id.ButtonConvert) ; 
		convertButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Mp3 mp3 = VideoUtilities.getConvertFile(videoId);
				if(mp3.getStatus() != 1){
					Toast toast = Toast.makeText(getApplicationContext(), "Can not download this video, please check your source video not over 20 minutes.", 50000);
					toast.show();
				}
			}
			
		});
		
	}

	@Override
	public void onInitializationSuccess(YouTubePlayer.Provider provider,
			YouTubePlayer player, boolean wasRestored) {
		if (!wasRestored) {
			Bundle b = getIntent().getExtras();
			Log.d("bundle="+b);
			String videoId = b.getString("video_id") ;
			Log.d("video_id="+videoId);

			player.loadVideo(videoId);
		}
	}

	@Override
	protected YouTubePlayer.Provider getYouTubePlayerProvider() {
		return (YouTubePlayerView) findViewById(R.id.youtube_view);
	}

}
