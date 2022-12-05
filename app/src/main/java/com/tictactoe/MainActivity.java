package com.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import kotlin.reflect.KFunction;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    int activePlayer = 0;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    // State meanings:
    // 0 - X
    // 1 - O
    // 2 - Empty cell
    int[][] winPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}};
    public int counter = 0;

    public void playerTap(View view) {
        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());

        if (gameState[tappedImage] == 2) {
            ImageView currentTurnImage = findViewById(R.id.imageCurrentSituation);
            counter++;

            gameState[tappedImage] = activePlayer;
            img.setTranslationY(-1000f);

            if (activePlayer == 0) {
                img.setImageResource(R.drawable.x);
                activePlayer = 1;
                currentTurnImage.setImageResource(R.drawable.oplay);
            } else {
                img.setImageResource(R.drawable.o);
                activePlayer = 0;
                currentTurnImage.setImageResource(R.drawable.xplay);
            }
            img.animate().translationYBy(1000f).setDuration(300);
        }
        int flag = 0;
        ImageView resultImage = findViewById(R.id.imageCurrentSituation);
        // Check if any player has won
        for (int[] winPosition : winPositions) {
            if (gameState[winPosition[0]] == gameState[winPosition[1]] &&
                    gameState[winPosition[1]] == gameState[winPosition[2]] &&
                    gameState[winPosition[0]] != 2) {
                flag = 1;
                counter = 0;

                if (gameState[winPosition[0]] == 0) {
                    resultImage.setImageResource(R.drawable.xwin);
                } else {
                    resultImage.setImageResource(R.drawable.owin);
                }
                endOfGame(view);
            }
        }
        if (counter == 9 && flag == 0) {
            counter = 0;
            resultImage.setImageResource(R.drawable.nowin);
            endOfGame(view);
        }
    }

    public void endOfGame(View view) {
        MaterialButton resetButton = (MaterialButton)findViewById(R.id.resetGame);
        resetButton.setVisibility(view.VISIBLE);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activePlayer = 0;
                ImageView currentTurnImage = findViewById(R.id.imageCurrentSituation);

                for (int i = 0; i < gameState.length; i++) {
                    gameState[i] = 2;
                }
                resetButton.setVisibility(view.GONE);

                ((ImageView) findViewById(R.id.imageView0)).setImageResource(0);
                ((ImageView) findViewById(R.id.imageView1)).setImageResource(0);
                ((ImageView) findViewById(R.id.imageView2)).setImageResource(0);
                ((ImageView) findViewById(R.id.imageView3)).setImageResource(0);
                ((ImageView) findViewById(R.id.imageView4)).setImageResource(0);
                ((ImageView) findViewById(R.id.imageView5)).setImageResource(0);
                ((ImageView) findViewById(R.id.imageView6)).setImageResource(0);
                ((ImageView) findViewById(R.id.imageView7)).setImageResource(0);
                ((ImageView) findViewById(R.id.imageView8)).setImageResource(0);

                currentTurnImage.setImageResource(R.drawable.xplay);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
