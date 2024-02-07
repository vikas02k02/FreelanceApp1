package com.bank.axisbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EligibilityActivity extends AppCompatActivity {
    Button eligibility;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eligibility);
        eligibility = findViewById(R.id.eligibilityBtn);
        eligibility.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), rewardDetails.class);
            startActivity(intent);
        });
    }
}