package com.example.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SeekBar timerSeek ;
    Button button;
    TextView timerText;
    Boolean counter = false;
    CountDownTimer countDownTimer;
    ImageView img;

    public void resetTimer(){
        img = (ImageView) findViewById(R.id.imageView);
        timerText.setText("00:30");
        timerSeek.setProgress(30);
        timerSeek.setEnabled(true);
        countDownTimer.cancel();
        button.setText("GO");
        counter=false;

        img.setImageResource(R.drawable.egg);

    }

    public  void buttonClicked(View view){
        button = (Button) findViewById(R.id.button);
        img = (ImageView) findViewById(R.id.imageView);
        if(counter){
           resetTimer();
        }
        else {
            counter = true;
            timerSeek.setEnabled(false);
            button.setText("STOP");
            countDownTimer = new CountDownTimer(timerSeek.getProgress() * 1000, 1000) {
                @Override
                public void onTick(long l) {
                    updateTimer((int) l / 1000);

                }

                @Override
                public void onFinish() {
                    button.setText("RESET");
                    img.setImageResource(R.drawable.egg2);
                    MediaPlayer mplayer = MediaPlayer.create(getApplicationContext(), R.raw.chicken);
                    mplayer.start();

                }
            }.start();
        }
    }

  public void updateTimer(int secondsLeft){
      int minutes = secondsLeft/60;
      int seconds = secondsLeft - (minutes * 60);

      timerText.setText(Integer.toString(minutes)+":"+Integer.toString(seconds));


  }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerSeek = (SeekBar) findViewById(R.id.seekBar);
        timerText = (TextView) findViewById(R.id.textView);
        timerSeek.setMax(30);
        timerSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateTimer(progress);
                 }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
