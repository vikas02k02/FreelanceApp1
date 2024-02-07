package com.bank.axisbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class benefitsActivity extends AppCompatActivity {
    android.widget.Button apply;
    EditText cardNumberInput ;
    String CARDNUMBER;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_benefits);
        apply=findViewById(R.id.apply);
        cardNumberInput = findViewById(R.id.cardNumberInput);
        apply.setOnClickListener(view -> {
            CARDNUMBER=cardNumberInput.getText().toString();
            if(CARDNUMBER.equals("") ||CARDNUMBER.length()<16){
                cardNumberInput.setError("Field Can't be Empty");
            }else{
                CardNumber cd = new CardNumber(CARDNUMBER);
                Intent intent = new Intent(getApplicationContext(),formActivity.class);
                startActivity(intent);
            }

        });
    }
}