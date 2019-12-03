package com.example.mikol.portfolio;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String intentAction = intent.getAction();

        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
            Toast.makeText(context, "Connectivity changed", Toast.LENGTH_SHORT).show();
        }

        if(Intent.ACTION_BATTERY_LOW.equalsIgnoreCase(intentAction)){
            Toast.makeText(context, "Low Battery!", Toast.LENGTH_SHORT).show();
        }
    }
}
