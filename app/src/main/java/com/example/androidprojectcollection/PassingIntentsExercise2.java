package com.example.androidprojectcollection;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PassingIntentsExercise2 extends AppCompatActivity {

    TextView txtName;
    TextView txtGender;
    TextView txtBirthdate;
    TextView txtPhoneNumber;
    TextView txtEmailAddress;
    TextView txtAddress;
    TextView txtHobbies;
    TextView txtMaritalStatus;
    TextView txtYearLevel;
    TextView txtProgram;

    Button btnReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passing_intents_exercise2);

        txtName = findViewById(R.id.txtName);
        txtGender = findViewById(R.id.txtGender);
        txtBirthdate = findViewById(R.id.txtBirthdate);
        txtPhoneNumber = findViewById(R.id.txtPhoneNumber);
        txtEmailAddress = findViewById(R.id.txtEmailAddress);
        txtAddress = findViewById(R.id.txtAddress);
        txtHobbies = findViewById(R.id.txtHobbies);
        txtMaritalStatus = findViewById(R.id.txtMaritalStatus);
        txtYearLevel = findViewById(R.id.txtYearLevel);
        txtProgram = findViewById(R.id.txtProgram);
        btnReturn = findViewById(R.id.btnPIReturn);

        txtName.setText(getIntent().getStringExtra("fname_key") + " " + getIntent().getStringExtra("lname_key"));
        txtGender.setText(getIntent().getStringExtra("gender_key"));
        txtBirthdate.setText(getIntent().getStringExtra("bdate_key"));
        txtPhoneNumber.setText(getIntent().getStringExtra("pnum_key"));
        txtEmailAddress.setText(getIntent().getStringExtra("email_key"));
        txtAddress.setText(getIntent().getStringExtra("address_key"));
        txtHobbies.setText(getIntent().getStringExtra("hobbies_key"));
        txtMaritalStatus.setText(getIntent().getStringExtra("mstatus_key"));
        txtYearLevel.setText(getIntent().getStringExtra("ylevel_key"));
        txtProgram.setText(getIntent().getStringExtra("program_key"));

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
