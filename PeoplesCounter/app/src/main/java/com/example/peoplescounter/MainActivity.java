package com.example.peoplescounter;

import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends WearableActivity {

    private TextView mTextView;

    int count = 0;

    public void plusOne(View view){
        count ++;
        mTextView.setText(Integer.toString(count));
    }

    public void reset(View view){
        count = 0;
        mTextView.setText(Integer.toString(count));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView) findViewById(R.id.textView);

        // Enables Always-on
        setAmbientEnabled();
    }
}
