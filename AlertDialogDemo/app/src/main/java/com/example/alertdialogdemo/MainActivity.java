package com.example.alertdialogdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    SharedPreferences pref;
    TextView textView;
    Boolean Init = false;
    public  void setLang (String lang){

        pref.edit().putString("lang",lang).apply();
        textView = findViewById(R.id.langText);
        textView.setText(lang);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = new MenuInflater(MainActivity.this);
        menuInflater.inflate(R.menu.lang_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        super.onOptionsItemSelected(item);
        switch (item.getItemId()){
            case R.id.eng:
                setLang("English");
                return true;
            case R.id.odia:
                setLang("Odia");
                return true;
            default:
                return false;

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pref = getApplicationContext().getSharedPreferences("com.example.alertdialogdemo", 0);
        textView = findViewById(R.id.langText);
        String language = pref.getString("lang","Error");

        if(language.equals("Error")) {

            new AlertDialog.Builder(this)
                    .setIcon(R.drawable.ic_language_black_24dp)
                    .setTitle("Select language")
                    .setMessage("Which Language u prefer")
                    .setPositiveButton("English", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Init = true;
                            Toast.makeText(MainActivity.this, "English", Toast.LENGTH_SHORT).show();
                            setLang("English");

                        }
                    })
                    .setNegativeButton("Odia", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Init = true;
                            Toast.makeText(MainActivity.this, "Odia", Toast.LENGTH_SHORT).show();
                            setLang("Odia");
                        }
                    })
                    .show();
        }else{
            textView.setText(language);
        }

    }
}
