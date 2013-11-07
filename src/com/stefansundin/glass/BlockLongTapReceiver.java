package com.stefansundin.glass;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BlockLongTapReceiver extends BroadcastReceiver {

	public void onReceive(Context context, Intent intent) {
		if (intent.getAction().equals("com.google.glass.action.LONG_PRESS")) {
			abortBroadcast();
			Log.d("stefan", "Blocking long press.");
		}
	}

}
