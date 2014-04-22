package com.stefansundin.glass.movies;

import android.widget.ListView;

import com.google.android.glass.touchpad.Gesture;
import com.google.android.glass.touchpad.GestureDetector;

public class Touchpad implements
		GestureDetector.BaseListener,
		GestureDetector.FingerListener,
		GestureDetector.ScrollListener {

	final float THRESHOLD = 50;
	float lastDisplacement = 0;
	ListView mListView;
	MainActivity mActivity;

	public Touchpad(ListView pListView, MainActivity pActivity) {
		mListView = pListView;
		mActivity = pActivity;
	}

	public boolean onGesture(Gesture gesture) {
		String filename = mListView.getSelectedItem().toString();
		if (gesture == Gesture.TAP) {
			mActivity.launchVideo(filename);
			return true;
		}
		else if (gesture == Gesture.TWO_TAP) {
			mActivity.say(filename);
			return true;
		}
		/*else if (gesture == Gesture.SWIPE_RIGHT) {
			Log.d("stefan", "Gesture.SWIPE_RIGHT");
			return true;
		}
		else if (gesture == Gesture.SWIPE_LEFT) {
			Log.d("stefan", "Gesture.SWIPE_LEFT");
			return true;
		}*/
		return false;
	}

	public void onFingerCountChanged(int previousCount, int currentCount) {
		lastDisplacement = 0;
	}

	public boolean onScroll(float displacement, float delta, float velocity) {
		//Log.d("stefan", "onScroll("+displacement+", "+delta+", "+velocity+")");
		if (Math.abs(displacement-lastDisplacement) > THRESHOLD) {
			if (displacement > lastDisplacement) {
				mListView.setSelection(mListView.getSelectedItemPosition()+1);
			}
			else {
				mListView.setSelection(mListView.getSelectedItemPosition()-1);
			}
			lastDisplacement = displacement;
			return true;
		}
		return false;
	}

}
