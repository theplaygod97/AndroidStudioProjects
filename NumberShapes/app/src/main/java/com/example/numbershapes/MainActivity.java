package com.example.numbershapes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    static boolean isTriangular(int num)
    {
        // Base case
        if (num < 0)
            return false;

        // A Triangular number must be
        // sum of first n natural numbers
        int sum = 0;

        for (int n = 1; sum <= num; n++)
        {
            sum = sum + n;
            if (sum == num)
                return true;
        }

        return false;
    }

    static boolean checkPerfectSquare(double x)
    {

        // finding the square root of given number
        double sq = Math.sqrt(x);

        /* Math.floor() returns closest integer value, for
         * example Math.floor of 984.1 is 984, so if the value
         * of sq is non integer than the below expression would
         * be non-zero.
         */
        return ((sq - Math.floor(sq)) == 0);
    }

    public void checknum(View view){
        String message;
        EditText editText = (EditText)findViewById(R.id.editText);
        int num = Integer.parseInt(editText.getText().toString());
        if(isTriangular(num) && checkPerfectSquare(num)!= true){
            message = num+" is Triangular";
        }
        else if (checkPerfectSquare(num) && isTriangular(num)!= true){
            message = num+" is Perfect Square";
        }
        else if(isTriangular(num) && checkPerfectSquare(num)){
            message = "Both";
        }
        else
            message = "Neither";

        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
