package com.example.androidprojectcollection;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

public class Connect3 extends AppCompatActivity {

    GridLayout grid;
    int size;
    Button[][] buttons;
    String[][] occupiers;
    TextView connectHudTxt;
    Button btnConnectReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect3);

        grid = findViewById(R.id.connectGrid);
        size = grid.getRowCount();
        buttons = new Button[size][size];
        occupiers = new String[size][size];

        connectHudTxt = findViewById(R.id.connectHudTxt);
        connectHudTxt.setText("Player 1's Turn");
        connectHudTxt.setTextColor(getColor(R.color.black));

        btnConnectReset = findViewById(R.id.btnConnectReset);

        reset();

        btnConnectReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reset();
            }
        });

        final boolean[] isPlayer1Turn = {true};

        for (int j = 0; j < size; j++) {
            int col = j;
            buttons[0][j].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int row = 0;
                    if (occupiers[row][col] != null) {
                        return;
                    }
                    if (checkWin("player1")) {
                        Toast.makeText(Connect3.this, "Player 1 already won!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (checkWin("player2")) {
                        Toast.makeText(Connect3.this, "Player 2 already won!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    while (row < size - 1 && occupiers[row + 1][col] == null) {
                        row++;
                    }
                    if (isPlayer1Turn[0]) {
                        occupiers[row][col] = "player1";
                        buttons[row][col].setBackground(getDrawable(R.drawable.btn_connect_player1));
                        if (checkWin("player1")) {
                            connectHudTxt.setText("Player 1 wins!");
                            Toast.makeText(Connect3.this, "Player 1 wins!", Toast.LENGTH_SHORT).show();
                            return;
                        } else {
                            connectHudTxt.setText("Player 2's Turn");
                            connectHudTxt.setTextColor(getColor(R.color.accent2));
                        }
                    } else {
                        occupiers[row][col] = "player2";
                        buttons[row][col].setBackground(getDrawable(R.drawable.btn_connect_player2));
                        connectHudTxt.setText("Player 1's Turn");
                        connectHudTxt.setTextColor(getColor(R.color.black));
                        if (checkWin("player2")) {
                            connectHudTxt.setText("Player 2 wins!");
                            Toast.makeText(Connect3.this, "Player 2 wins!", Toast.LENGTH_SHORT).show();
                            return;
                        } else {
                            connectHudTxt.setText("Player 1's Turn");
                            connectHudTxt.setTextColor(getColor(R.color.black));
                        }
                    }
                    isPlayer1Turn[0] = !isPlayer1Turn[0];
                }
            });
        }
    }

    public void reset() {
        int k = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                buttons[i][j] = (Button) grid.getChildAt(k);
                buttons[i][j].setBackground(getDrawable(R.drawable.btn_connect_empty));
                occupiers[i][j] = null;
                k++;
            }
        }
    }

    public boolean checkWin(String player) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                try {
                    if (occupiers[i][j] == player && occupiers[i][j + 1] == player && occupiers[i][j + 2] == player) {
                        return true;
                    }
                } catch (ArrayIndexOutOfBoundsException ignored){}
            }
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                try {
                    if (occupiers[i][j] == player && occupiers[i + 1][j] == player && occupiers[i + 2][j] == player) {
                        return true;
                    }
                } catch (ArrayIndexOutOfBoundsException ignored){}
            }
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                try {
                    if (occupiers[i][j] == player && occupiers[i + 1][j + 1] == player && occupiers[i + 2][j + 2] == player) {
                        return true;
                    }
                } catch (ArrayIndexOutOfBoundsException ignored){}
            }
        }
        for (int i = size - 1; i >= 0; i--) {
            for (int j = 0; j < size; j++) {
                try {
                    if (occupiers[i][j] == player && occupiers[i - 1][j + 1] == player && occupiers[i - 2][j + 2] == player) {
                        return true;
                    }
                } catch (ArrayIndexOutOfBoundsException ignored){}
            }
        }
        return false;
    }
}
