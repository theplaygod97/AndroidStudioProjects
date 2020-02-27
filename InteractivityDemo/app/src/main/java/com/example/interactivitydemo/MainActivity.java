package com.example.interactivitydemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {



    public void clickFunction(View view){
        EditText nameEditText = (EditText)findViewById(R.id.nameEditText);
        EditText passEditText = (EditText)findViewById(R.id.passEditText);
        Log.i("Info","Button Pressed !");
        Log.i("Info",nameEditText.getText().toString());
        Log.i("Info",passEditText.getText().toString());
        Toast.makeText(this, "Hi ,"+nameEditText.getText().toString(),Toast.LENGTH_LONG).show();

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
