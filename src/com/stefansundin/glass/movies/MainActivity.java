package com.stefansundin.glass.movies;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.glass.touchpad.GestureDetector;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends Activity
		implements TextToSpeech.OnInitListener {

	private String mMovieDirectory;
	private GestureDetector mGestureDetector;
	private TextToSpeech mSpeech;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout);

		ArrayAdapter<String> movieList = new ArrayAdapter<String>(this, android.R.layout.test_list_item);
		ListView listView = (ListView) findViewById(R.id.listView);
		listView.setAdapter(movieList);

		mMovieDirectory = Environment.getExternalStorageDirectory()+"/"+Environment.DIRECTORY_MOVIES;
		Log.d("stefan", "Movie directory: "+mMovieDirectory);

		ArrayList<String> filenames = new ArrayList<String>();
		File dir = new File(mMovieDirectory);
		File[] files = dir.listFiles();
		for (File file : files) {
			filenames.add(file.getName());
		}
		java.util.Collections.sort(filenames);
		for (String filename : filenames) {
			movieList.add(filename);
		}

		mSpeech = new TextToSpeech(this, this);

		Touchpad touchpad = new Touchpad(listView, this);
		mGestureDetector = new GestureDetector(this);
		mGestureDetector.setBaseListener(touchpad);
		mGestureDetector.setFingerListener(touchpad);
		mGestureDetector.setScrollListener(touchpad);
	}

	public boolean onGenericMotionEvent(MotionEvent event) {
		if (mGestureDetector != null) {
			return mGestureDetector.onMotionEvent(event);
		}
		return false;
	}

	public void say(String filename) {
		String text = filename.substring(0, filename.lastIndexOf("."));
		mSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null);
		Log.d("stefan", "Saying: "+text);
	}

	public void launchVideo(String filename) {
		say(filename);
		Intent i = new Intent();
		i.setAction("com.google.glass.action.VIDEOPLAYER");
		i.putExtra("video_url", mMovieDirectory+"/"+filename);
		startActivity(i);
	}

	public void onInit(int status) {
		// Must be declared for TTS
	}

	protected void onDestroy() {
		super.onDestroy();
		mSpeech.shutdown();
	}

}
