package com.bank.axisbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.Telephony;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class testActivity extends AppCompatActivity {
    private static final int READ_SMS_PERMISSION_CODE =1;
    String contentBody ,body ;
    String messageBody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        TextView textView = findViewById(R.id.mess);
        Button read = findViewById(R.id.read);
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_SMS},READ_SMS_PERMISSION_CODE);

        }else {
            readSMS(messageBody);

        }
        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                readSMS(messageBody);
                Toast.makeText(getApplicationContext(), body, Toast.LENGTH_SHORT).show();
            }
        });
        BroadcastReceiver smsReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if ("com.bank.axisbank.NEW_SMS_RECEIVED".equals(intent.getAction())) {
                    messageBody = intent.getStringExtra("messageBody");
                    // Process the received SMS
                    readSMS(messageBody);
                }
            }
        };

        IntentFilter filter = new IntentFilter("com.bank.axisbank.NEW_SMS_RECEIVED");
        registerReceiver(smsReceiver, filter);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==READ_SMS_PERMISSION_CODE){
            if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                readSMS(messageBody);
            }
        }
    }
    public void readSMS (String messgebody){
        Cursor ContactCursor =null;
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            ContactCursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null);
            assert ContactCursor != null;
            ContactCursor.moveToFirst();
            while (ContactCursor.moveToNext()){
                try {
                    String contaceName = ContactCursor.getString(ContactCursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    String number = ContactCursor.getString(ContactCursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    String ContantId = String.valueOf(ContactCursor.getInt(ContactCursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.CONTACT_ID)));
                    contentBody = contaceName+number+ContantId;
                }catch (android.database.CursorIndexOutOfBoundsException f){
                    continue;
                }
            }
        }
        Log.d("content",messgebody);
        assert ContactCursor != null;
        ContactCursor.close();
        Cursor cursor =getContentResolver().query(Telephony.Sms.CONTENT_URI,null,null,null,"date ASC");
        assert cursor != null;
        cursor.moveToFirst();
        while (cursor != null && cursor.moveToNext()){
            try {
                int messegeId =cursor.getInt(cursor.getColumnIndexOrThrow("_id"));
                String contact_sender = cursor.getString(cursor.getColumnIndexOrThrow("address"));
                body = String.valueOf(messegeId)+contact_sender+ "\t\t"+cursor.getString(cursor.getColumnIndexOrThrow("body"));
            }catch(Exception e){
                Log.d("Error",e.toString());
            }
        }
        cursor.close();
        Log.d("Body",body);



    }
}