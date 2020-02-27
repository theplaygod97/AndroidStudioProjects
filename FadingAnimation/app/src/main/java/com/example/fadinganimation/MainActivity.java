package com.example.fadinganimation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {


    public void fade(View view){
        ImageView IV = (ImageView)findViewById(R.id.imageView);
        ImageView IV2 = (ImageView)findViewById(R.id.imageView2);
        //IV.animate().scaleX(0.5f).scaleY(0.5f).setDuration(2000);
        //IV2.animate().alpha(1).setDuration(2000);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView IV = (ImageView)findViewById(R.id.imageView);
        IV.setX(-1000);
        IV.animate().scaleXBy(1000).rotation(1800).setDuration(2000);
    }
}
