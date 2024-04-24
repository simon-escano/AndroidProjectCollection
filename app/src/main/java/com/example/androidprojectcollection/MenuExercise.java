package com.example.androidprojectcollection;

import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MenuExercise extends AppCompatActivity {

    Button btnChanger;
    int colors[];
    int bgColor = 0;
    int txtColor = 3;
    String fonts[];
    int font = 0;
    float rotation = 0;
    float scalex = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_exercise);

        btnChanger = findViewById(R.id.btnTransformingButton);
        colors = new int[4];
        colors[0] = Color.RED;
        colors[1] = Color.YELLOW;
        colors[2] = Color.GREEN;
        colors[3] = Color.BLUE;

        fonts = new String[3];
        fonts[0] = "casual";
        fonts[1] = "cursive";
        fonts[2] = "sans-serif";
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.choice_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mItemChange:
                Toast.makeText(this, "Edit Object Item is clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.mItemReset:
                Toast.makeText(this, "Reset Object Item is clicked", Toast.LENGTH_SHORT).show();
                reset();
                break;
            case R.id.mItemExit:
                finish();
                break;
            case R.id.mItemChangeBGColor:
                changeBGColor(colors[bgColor]);
                bgColor++;
                if (bgColor < 0) {
                    bgColor = 3;
                }
                break;
            case R.id.mItemChangeTextColor:
                changeTextColor(colors[txtColor]);
                txtColor--;
                if (txtColor < 0) {
                    txtColor = 0;
                }
                break;
            case R.id.mItemChangeFont:
                changeFont(fonts[font]);
                font++;
                if (font >= 3) {
                    font = 0;
                }
                break;
            case R.id.mItemRotate:
                rotate(rotation, rotation += 35F);
                if (rotation > 360) {
                    rotate(rotation, rotation = 0);
                }
                break;
            case R.id.mItemMakeFat:
                makeFat(scalex, scalex += 1F);
                if (scalex > 5) {
                    makeFat(scalex, scalex = 1F);
                }
                break;
        }
        return true;
    }

    public void reset() {
        changeBGColor(getResources().getColor(R.color.black));
        changeTextColor(getResources().getColor(R.color.white));
        changeFont(fonts[2]);
        rotate(rotation, 0);
        makeFat(scalex, 1);
        bgColor = 0;
        txtColor = 4;
        font = 0;
        rotation = 0;
        scalex = 1;
    }

    public void changeBGColor(int color) {
        ObjectAnimator animator = ObjectAnimator.ofInt(btnChanger.getBackground(), "alpha", 0, 255);
        animator.setDuration(500);
        animator.addUpdateListener(animation -> {
            ((ColorDrawable) btnChanger.getBackground()).setColor(color);
        });
        animator.start();
    }

    public void changeTextColor(int color) {
        ObjectAnimator animator = ObjectAnimator.ofArgb(btnChanger, "textColor", ((ColorDrawable) btnChanger.getBackground()).getColor(), color);
        animator.setDuration(500);
        animator.start();
    }

    public void changeFont(String font) {
        btnChanger.setTypeface(Typeface.create(font, Typeface.NORMAL));
    }

    public void rotate(float start, float end) {
        float clockwiseDistance = (end - start + 360) % 360;
        float counterClockwiseDistance = (start - end + 360) % 360;
        float finalEnd = (clockwiseDistance < counterClockwiseDistance) ? end : start - counterClockwiseDistance;

        ObjectAnimator animator = ObjectAnimator.ofFloat(btnChanger, "rotationY", start, finalEnd);
        animator.setDuration(500);
        animator.start();
    }


    public void makeFat(float start, float end) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(btnChanger, "scaleX", start, end);
        animator.setDuration(500);
        animator.start();
    }
}
