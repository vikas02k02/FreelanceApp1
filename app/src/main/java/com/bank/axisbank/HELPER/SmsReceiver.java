package com.bank.axisbank.HELPER;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import com.bank.axisbank.testActivity;

import java.util.Objects;

public class SmsReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (Objects.equals(intent.getAction(), "android.provider.Telephony.SMS_RECEIVED")) {
            readSMS smsReader = new readSMS();
            smsReader.getLatestSMS(context);
        }
        }
    }


