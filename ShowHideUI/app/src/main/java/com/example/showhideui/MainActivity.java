package com.example.showhideui;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView text ;
    public void show(View view){
        text = (TextView) findViewById( R.id.textView);
        text.setVisibility(View.VISIBLE);
    }
    public void hide(View view){
        text = (TextView) findViewById( R.id.textView);
        text.setVisibility(View.INVISIBLE);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        text = (TextView) findViewById( R.id.textView);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }
}
