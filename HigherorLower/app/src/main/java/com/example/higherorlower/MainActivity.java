package com.example.higherorlower;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

int rand;
    public void generaterand(){
    Random random = new Random();
    rand = random.nextInt(20)+1;
    }
    public void highlow(View view){


        EditText editText = (EditText)findViewById(R.id.num);
        Log.i("Random",Integer.toString(rand));

        int num =  Integer.parseInt(editText.getText().toString());
        Log.i("Entered Num",editText.getText().toString());
        if(rand<num){
            Toast.makeText(this, "Higher",Toast.LENGTH_SHORT).show();
        }
        else if(rand == num ){
            Toast.makeText(this, "Congrats,U guessed right",Toast.LENGTH_SHORT).show();
            generaterand();
        }
        else
            Toast.makeText(this, "Lower",Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        generaterand();
    }
}
