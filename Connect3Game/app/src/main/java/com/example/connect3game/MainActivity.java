package com.example.connect3game;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int activeplayer = 0;
    boolean gameActive = true;
    int[] gameState = {2,2,2,2,2,2,2,2,2,2};
    int[][] winningposition ={{0,1,2},{3,4,5},{6,7,8},
            {0,3,6},{1,4,7},{2,5,8},
            {0,4,8},{2,4,6}};


    public void dropin(View view){

        ImageView counter = (ImageView) view;
        Log.i("Tag",counter.getTag().toString());

        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if(gameState[tappedCounter] == 2 && gameActive) {
            gameState[tappedCounter] = activeplayer;
            counter.setTranslationY(-1500);

            if (activeplayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activeplayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activeplayer = 0;
            }

            counter.animate().translationYBy(1500).setDuration(1000);


            for (int[] winPos : winningposition) {
                if (gameState[winPos[0]] == gameState[winPos[1]] &&
                        gameState[winPos[1]] == gameState[winPos[2]] && gameState[winPos[0]] != 2) {
                    gameActive = false;
                    String Winner;
                    if (activeplayer == 1) {
                        Winner = "Yellow";
                    } else Winner = "Red";


                    Button playAgain = (Button) findViewById( R.id.button);
                    TextView winText = (TextView) findViewById( R.id.textView);

                    winText.setText(Winner+" has won");
                    playAgain.setVisibility(View.VISIBLE);
                    winText.setVisibility(View.VISIBLE);
                }
            }

        }

    }
    public void playAgain(View view) {

        Button playAgainButton = (Button) findViewById(R.id.button);

        TextView winnerTextView = (TextView) findViewById(R.id.textView);

        playAgainButton.setVisibility(View.INVISIBLE);

        winnerTextView.setVisibility(View.INVISIBLE);

        androidx.gridlayout.widget.GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

        for(int i=0; i<gridLayout.getChildCount(); i++) {

            ImageView counter = (ImageView) gridLayout.getChildAt(i);

            counter.setImageDrawable(null);

        }

        for (int i=0; i<gameState.length; i++) {

            gameState[i] = 2;

        }

        activeplayer = 0;

        gameActive = true;

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
