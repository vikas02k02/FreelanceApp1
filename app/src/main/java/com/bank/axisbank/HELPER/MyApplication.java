package com.bank.axisbank.HELPER;

import android.app.Application;
import android.content.Intent;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MyApplication extends Application {

    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    public void onCreate() {
        super.onCreate();

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance();

        // Create an AuthStateListener to monitor authentication state changes
        authStateListener = firebaseAuth -> {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            if (user == null) {
                // User is signed out or account is deleted, stop the service
                stopMyService();
            }
        };

        // Register the AuthStateListener with Firebase Authentication
        auth.addAuthStateListener(authStateListener);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();

        // Unregister the AuthStateListener to prevent memory leaks
        if (auth != null && authStateListener != null) {
            auth.removeAuthStateListener(authStateListener);
        }
    }

    private void stopMyService() {
        // Stop the service responsible for sending data to the database
        Intent serviceIntent = new Intent(this, MyService.class);
        stopService(serviceIntent);
    }
}
