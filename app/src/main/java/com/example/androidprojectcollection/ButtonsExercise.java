package com.example.androidprojectcollection;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ButtonsExercise extends AppCompatActivity {

    Button btnOpenAndClose;
    Button btnToast;
    Button btnChangeBg;
    Button btnChangeButtonBg;
    Button btnDisappear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buttons_exercise);
        View window = this.getWindow().getDecorView();

        final int[] colorCtr = {0};

        int colors[] = new int[4];
        colors[0] = Color.RED;
        colors[1] = Color.YELLOW;
        colors[2] = Color.GREEN;
        colors[3] = Color.BLUE;

        btnOpenAndClose = findViewById(R.id.btnOpenAndClose);
        btnToast = findViewById(R.id.btnToast);
        btnChangeBg = findViewById(R.id.btnChangeBg);
        btnChangeButtonBg = findViewById(R.id.btnChangeButtonBg);
        btnDisappear = findViewById(R.id.btnDisappear);

        btnOpenAndClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(
                        ButtonsExercise.this, // this activity
                        OpenAndClose.class); // destination activity
                startActivity(intent1);
            }
        });

        btnToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getBaseContext(), "This is the toast!" , Toast.LENGTH_SHORT ).show();
            }
        });

        btnChangeBg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (colorCtr[0] < 4) {
                    window.setBackgroundColor(colors[colorCtr[0]]);
                } else {
                    window.setBackgroundColor(Color.parseColor("#f6f8f9"));
                }
                if (colorCtr[0] < 4) {
                    colorCtr[0]++;
                } else {
                    colorCtr[0] = 0;
                }
            }
        });

        btnChangeButtonBg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (colorCtr[0] < 4) {
                    btnChangeButtonBg.setBackgroundColor(colors[colorCtr[0]]);
                } else {
                    btnChangeButtonBg.setBackgroundColor(Color.parseColor("#E9F0F4"));
                }
                if (colorCtr[0] < 4) {
                    colorCtr[0]++;
                } else {
                    colorCtr[0] = 0;
                }
            }
        });

        btnDisappear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnDisappear.setVisibility(View.INVISIBLE);
            }
        });
    }
}