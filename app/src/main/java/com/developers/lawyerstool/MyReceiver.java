package com.developers.lawyerstool;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyReceiver extends BroadcastReceiver {
    public MyReceiver() {
    }
    private static final String TAG=MyService.class.getSimpleName();
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Log.d(TAG,"RECIEVINGGGGGGGGGGGGG,..........");
        Intent intent1=new Intent(context,MyService.class);
        context.startService(intent1);
    }
}
