package com.example.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public  void convert(View view){
        EditText usdtoinr = (EditText)findViewById(R.id.editText);
        double usd = Double.parseDouble(usdtoinr.getText().toString());
        double inr = usd * 71.17 ;
        Toast.makeText(this, usd+" dollar is equal to "+inr +" rupees",Toast.LENGTH_SHORT).show();

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
