package com.example.multipleactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    public void goBack(View view){
        //Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        //startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
        String name = intent.getStringExtra("Name");
        Toast.makeText(this,"Name = "+name,Toast.LENGTH_SHORT).show();

    }
}
