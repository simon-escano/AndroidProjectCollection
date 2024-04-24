package com.example.androidprojectcollection;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import java.util.Random;

public class ColorMatching extends AppCompatActivity {

    GridLayout grid;
    Button btnReturn;
    Button btnAutoWin;
    int size;
    Button[][] buttons;
    int[][] colors;
    int[] colorPalette;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colormatching);

        grid = findViewById(R.id.gridColorMatching);
        btnReturn = findViewById(R.id.btnReturn);
        btnAutoWin = findViewById(R.id.btnAutoWin);
        size = grid.getRowCount();
        buttons = new Button[size][size];
        colors = new int[size][size];
        colorPalette = new int[4];
        colorPalette[0] = Color.RED;
        colorPalette[1] = Color.YELLOW;
        colorPalette[2] = Color.GREEN;
        colorPalette[3] = Color.BLUE;

        Toast.makeText(this, "Simon Lyster P. Escaño | ColorMatching", Toast.LENGTH_SHORT).show();
        resetColors();

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetColors();
            }
        });

        btnAutoWin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                autoWin();
            }
        });

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int row = i;
                int col = j;
                buttons[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (checkWin()) {
                            Toast.makeText(ColorMatching.this, "You already won!", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        changeColor(row - 1, col);
                        changeColor(row + 1, col);
                        changeColor(row, col - 1);
                        changeColor(row, col + 1);

                        if (checkWin()) {
                            Toast.makeText(ColorMatching.this, "Nice, you win!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }
    }

    public int getNextColor(int color) {
        int colorIndex = -1;
        for (int i = 0; i < 4; i++) {
            if (color == colorPalette[i]) {
                colorIndex = i;
                break;
            }
        }
        return colorPalette[(colorIndex + 1) % 4];
    }

    public void resetColors() {
        Random random = new Random();
        int k = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                buttons[i][j] = (Button) grid.getChildAt(k);
                colors[i][j] = colorPalette[Math.abs(random.nextInt() % 4)];
                buttons[i][j].setBackgroundColor(colors[i][j]);
                k++;
            }
        }
    }

    public boolean checkWin() {
        int firstColor = colors[0][0];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (colors[i][j] != firstColor) {
                    return false;
                }
            }
        }
        return true;
    }

    public void changeColor(int row, int col) {
        try {
            buttons[row][col].setBackgroundColor(getNextColor(colors[row][col]));
            colors[row][col] = getNextColor(colors[row][col]);
        } catch (ArrayIndexOutOfBoundsException ignored) {}
    }

    public void autoWin() {
        Random random = new Random();
        int randomColor = colorPalette[Math.abs(random.nextInt() % 4)];
        int randomRow = Math.abs(random.nextInt() % 3);
        int randomCol = Math.abs(random.nextInt() % 3);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                buttons[i][j].setBackgroundColor(randomColor);
                colors[i][j] = randomColor;
            }
        }

        changeColor(randomRow - 1, randomCol);
        changeColor(randomRow + 1, randomCol);
        changeColor(randomRow, randomCol - 1);
        changeColor(randomRow, randomCol + 1);
    }
}