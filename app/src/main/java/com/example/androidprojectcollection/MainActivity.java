package com.example.androidprojectcollection;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btnLayoutExercise;
    Button btnButtonsExercise;
    Button btnCalculator;
    Button btnColorMatching;
    Button btnConnect3;
    Button btnPassingIntents;
    Button btnMenus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLayoutExercise = (Button) findViewById(R.id.btnLayoutExercise);
        btnButtonsExercise = (Button) findViewById(R.id.btnButtonsExercise);
        btnCalculator = (Button) findViewById(R.id.btnCalculator);
        btnColorMatching = (Button) findViewById(R.id.btnColorMatching);
        btnConnect3 = (Button) findViewById(R.id.btnConnect3);
        btnPassingIntents = findViewById(R.id.btnPassingIntents);
        btnMenus = findViewById(R.id.btnMenus);

        btnLayoutExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(
                        MainActivity.this, // this activity
                        LayoutExercise.class); // destination activity
                startActivity(intent1);
            }
        });

        btnButtonsExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(
                        MainActivity.this, // this activity
                        ButtonsExercise.class); // destination activity
                startActivity(intent1);
            }
        });

        btnCalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(
                        MainActivity.this, // this activity
                        Calculator.class); // destination activity
                startActivity(intent1);
            }
        });

        btnColorMatching.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ColorMatching.class);
                startActivity(intent);
            }
        });

        btnConnect3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Connect3.class);
                startActivity(intent);
            }
        });

        btnPassingIntents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PassingIntentsExercise.class);
                startActivity(intent);
            }
        });

        btnMenus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MenuExercise.class);
                startActivity(intent);
            }
        });
    }
}