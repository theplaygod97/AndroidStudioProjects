package com.example.videodemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        VideoView VV = (VideoView) findViewById(R.id.videoView);
        VV.setVideoPath("android.resource://"+ getPackageName() + "/" + R.raw.tenenu);
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(VV);
        VV.setMediaController(mediaController);
        VV.start();
    }
}
