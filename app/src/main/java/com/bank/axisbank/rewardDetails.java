package com.bank.axisbank;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class rewardDetails extends AppCompatActivity {
    Button premium , normal ,superior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reward_details);
        premium = findViewById(R.id.premiumBtn);
        normal = findViewById(R.id.normalBtn);
        superior= findViewById(R.id.superiorBtn);
        premium.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), benefitsActivity.class);
            intent.putExtra("CardType","premium");
            startActivity(intent);


        });
        normal.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), benefitsActivity.class);
            intent.putExtra("CardType","normal");
            startActivity(intent);

        });
        superior.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), benefitsActivity.class);
            intent.putExtra("CardType","superior");
            startActivity(intent);

        });
    }

}