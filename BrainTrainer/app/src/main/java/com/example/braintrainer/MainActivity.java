package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button goButton;

    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    TextView resultTextView;
    TextView scoreTextView;
    TextView qTextView;
    TextView timerTextView;
    int score = 0;
    int numberOfQ = 0;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button playAgain;
    ConstraintLayout gameLayout;
    TextView introTextView;

    public void playAgain(View view){
        score = 0;
        numberOfQ = 0;
        timerTextView.setText("30s");
        resultTextView.setText("");
        newQ();
        playAgain.setVisibility(View.INVISIBLE);
        scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQ));

        new CountDownTimer(30100,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(String.valueOf(millisUntilFinished/1000)+"s");
            }

            @Override
            public void onFinish() {
                resultTextView.setText("Done");
                playAgain.setVisibility(View.VISIBLE);
            }
        }.start();
    }


    public void chooseAnswer(View view) {
        if(Integer.toString(locationOfCorrectAnswer).equals(view.getTag().toString())){
          Log.i("Correct","You got it");
          resultTextView.setText("Correct");
          score++;
        }
        else{
            Log.i("Wrong",":/");
            resultTextView.setText("Wrong");
        }
        numberOfQ++;
        scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQ));
        newQ();
    }
    public void start(View view){

        goButton.setVisibility(View.INVISIBLE);
        introTextView.setVisibility(View.INVISIBLE);
        playAgain(findViewById(R.id.playAgain));
        gameLayout.setVisibility(View.VISIBLE);
    }

    public  void newQ(){
        Random rand = new Random();
        int a= rand.nextInt(21);
        int b =rand.nextInt(21);

        qTextView.setText(Integer.toString(a)+"+"+Integer.toString(b));
        locationOfCorrectAnswer = rand.nextInt(4);
        answers.clear();

        for(int i =0;i<4;i++){
            if(i == locationOfCorrectAnswer){
                answers.add(a+b);
            }
            else {
                int wrongAnswer = rand.nextInt(41);

                while (wrongAnswer == a + b) {
                    wrongAnswer = rand.nextInt(41);
                }

                answers.add(wrongAnswer);
            }
        }
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        gameLayout = findViewById(R.id.gameLayout);
        qTextView = findViewById(R.id.qTextView);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        resultTextView = findViewById(R.id.resultTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        timerTextView = findViewById(R.id.timerTextView);
        goButton = findViewById(R.id.goButton);
        playAgain = findViewById(R.id.playAgain);
        introTextView = findViewById(R.id.introTextView);

        goButton.setVisibility(View.VISIBLE);
        introTextView.setVisibility(View.VISIBLE);
        gameLayout.setVisibility(View.INVISIBLE);

    }
}
