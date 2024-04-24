package com.example.androidprojectcollection;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PassingIntentsExercise extends AppCompatActivity {
    EditText etxtFName;
    EditText etxtLName;

    RadioButton radMale;
    RadioButton radFemale;
    RadioButton radOther;

    EditText etxtBirthdate;
    EditText etxtPhoneNumber;
    EditText etxtEmailAddress;
    EditText etxtAddress;
    EditText etxtHobbies;
    Spinner spnMaritalStatus;
    Spinner spnYearLevel;
    Spinner spnProgram;

    Button btnClear;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passing_intents_exercise);

        btnClear = findViewById(R.id.btnPIClear);
        btnSubmit = findViewById(R.id.btnPISubmit);
        etxtFName = findViewById(R.id.etxtFName);
        etxtLName = findViewById(R.id.etxtLName);
        radMale = findViewById(R.id.radMale);
        radFemale = findViewById(R.id.radFemale);
        radOther = findViewById(R.id.radOther);
        etxtBirthdate = findViewById(R.id.etxtBirthdate);
        etxtPhoneNumber = findViewById(R.id.etxtPhoneNumber);
        etxtEmailAddress = findViewById(R.id.etxtEmailAddress);
        etxtAddress = findViewById(R.id.etxtAddress);
        etxtHobbies = findViewById(R.id.etxtHobbies);
        spnMaritalStatus = findViewById(R.id.spnMaritalStatus);
        spnYearLevel = findViewById(R.id.spnYearLevel);
        spnProgram = findViewById(R.id.spnProgram);

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etxtFName.setText("");
                etxtLName.setText("");
                radMale.setChecked(false);
                radFemale.setChecked(false);
                radOther.setChecked(false);
                etxtBirthdate.setText("");
                etxtPhoneNumber.setText("");
                etxtEmailAddress.setText("");
                etxtAddress.setText("");
                etxtHobbies.setText("");
                spnMaritalStatus.setSelection(0);
                spnYearLevel.setSelection(0);
                spnProgram.setSelection(0);
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fName = etxtFName.getText().toString();
                String lName = etxtLName.getText().toString();

                String gender;
                if (radMale.isChecked()) {
                    gender = "Male";
                } else if (radFemale.isChecked()) {
                    gender = "Female";
                } else if (radOther.isChecked()) {
                    gender = "Other";
                } else {
                    gender = "Unknown";
                }

                String bDate = etxtBirthdate.getText().toString();
                String pNum = etxtPhoneNumber.getText().toString();
                String email = etxtEmailAddress.getText().toString();
                String address = etxtAddress.getText().toString();
                String hobbies = etxtHobbies.getText().toString();
                String mStatus = spnMaritalStatus.getSelectedItem().toString();
                String yLevel = spnYearLevel.getSelectedItem().toString();
                String program = spnProgram.getSelectedItem().toString();

                if (fName.isEmpty() || lName.isEmpty() || bDate.isEmpty() || pNum.isEmpty() || email.isEmpty() || address.isEmpty() || hobbies.isEmpty()) {
                    Toast.makeText(PassingIntentsExercise.this, "Please fill all the fields.", Toast.LENGTH_SHORT).show();
                    return;
                }
                
                if (!isValidBirthDate(bDate)) {
                    Toast.makeText(PassingIntentsExercise.this, "Please enter a valid birth date.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!isValidPhoneNumber(pNum)) {
                    Toast.makeText(PassingIntentsExercise.this, "Please enter a valid phone number.", Toast.LENGTH_SHORT).show();
                    return;
                }
                
                if (!isValidEmail(email)) {
                    Toast.makeText(PassingIntentsExercise.this, "Please enter a valid email address.", Toast.LENGTH_SHORT).show();
                    return;
                }

                switch (yLevel) {
                    case "1":
                        yLevel = "First Year";
                        break;
                    case "2":
                        yLevel = "Second Year";
                        break;
                    case "3":
                        yLevel = "Third Year";
                        break;
                    case "4":
                        yLevel = "Fourth Year";
                        break;
                }

                

                Intent intent = new Intent(PassingIntentsExercise.this, PassingIntentsExercise2.class);
                intent.putExtra("fname_key", fName);
                intent.putExtra("lname_key", lName);
                intent.putExtra("gender_key", gender);
                intent.putExtra("bdate_key", bDate);
                intent.putExtra("pnum_key", pNum);
                intent.putExtra("email_key", email);
                intent.putExtra("address_key", address);
                intent.putExtra("hobbies_key", hobbies);
                intent.putExtra("mstatus_key", mStatus);
                intent.putExtra("ylevel_key", yLevel);
                intent.putExtra("program_key", program);

                startActivity(intent);
            }
        });
    }

    public static boolean isValidBirthDate(String birthDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");
        dateFormat.setLenient(false);

        try {
            Date parsedDate = dateFormat.parse(birthDate);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        String phoneNumberRegex = "^(09|\\+639)\\d{9}$";
        Pattern pattern = Pattern.compile(phoneNumberRegex);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }

    public static boolean isValidEmail(String email) {
        String emailRegex =
                "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" +
                        "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
