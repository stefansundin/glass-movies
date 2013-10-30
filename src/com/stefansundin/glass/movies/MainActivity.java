package com.stefansundin.glass.movies;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends Activity
		implements TextToSpeech.OnInitListener {

	public MainActivity mActivity;

	private String mMovieDirectory;
	private ListView mListView;
	private ArrayAdapter<String> mMovieList;
	private TextToSpeech mSpeech;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout);
		mActivity = this;

		mListView = (ListView) findViewById(R.id.listView);

		mMovieList = new ArrayAdapter<String>(this, android.R.layout.test_list_item);
		mListView.setAdapter(mMovieList);

		mMovieDirectory = Environment.getExternalStorageDirectory()+"/"+Environment.DIRECTORY_MOVIES;
		//Log.d("stefan", "Movie directory: "+mMovieDirectory);

		ArrayList<String> filenames = new ArrayList<String>();
		File dir = new File(mMovieDirectory);
		File[] files = dir.listFiles();
		for (File file : files) {
			filenames.add(file.getName());
		}
		java.util.Collections.sort(filenames);
		for (String filename : filenames) {
			mMovieList.add(filename);
		}

		mListView.setOnItemClickListener(new VideoLauncher());

		mSpeech = new TextToSpeech(this, this);
	}

	public void onInit(int status) {
		// Must be declared for TTS
	}

	protected void onDestroy() {
		super.onDestroy();
		mSpeech.shutdown();
	}

	public void launchVideo(String filename) {
		Log.d("stefan", "Launching: "+filename);

		String say = filename.substring(0, filename.lastIndexOf("."));
		mSpeech.speak(say, TextToSpeech.QUEUE_FLUSH, null);

		Intent i = new Intent();
		i.setAction("com.google.glass.action.VIDEOPLAYER");
		i.putExtra("video_url", mMovieDirectory+"/"+filename);
		startActivity(i);
	}

	private class VideoLauncher implements AdapterView.OnItemClickListener {
		public void onItemClick(AdapterView parent, View v, int position, long id) {
			mActivity.launchVideo(parent.getSelectedItem().toString());
		}
	}

}
