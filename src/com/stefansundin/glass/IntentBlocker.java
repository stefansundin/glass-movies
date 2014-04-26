package com.stefansundin.glass;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class IntentBlocker extends BroadcastReceiver {

	public void onReceive(Context context, Intent intent) {
		abortBroadcast();
		Log.d("stefan", "Blocking intent.");
	}

}