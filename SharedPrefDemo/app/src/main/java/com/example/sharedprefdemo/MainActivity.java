package com.example.sharedprefdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = this.getSharedPreferences("com.example.SharedPrefDemo", Context.MODE_PRIVATE);

        /*
        ArrayList<String> friends = new ArrayList<String>();
        friends.add("Avi");friends.add("Sambhu");friends.add("Subham");

        try {


            sharedPreferences.edit().putString("friends",ObjectSerializer.serialize(friends)).apply();

            Log.i("friends",ObjectSerializer.serialize(friends));
        } catch (Exception e) {
            e.printStackTrace();
        }
*/
        ArrayList<String> newfriends = new ArrayList<String>();
        try {
            newfriends = (ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString("friends",ObjectSerializer.serialize(new ArrayList<String>())));
        } catch (Exception e) {
            e.printStackTrace();
        }

        Log.i("newFriends",newfriends.toString());

        //sharedPreferences.edit().putString("username","Satya").apply();

        //String username = sharedPreferences.getString("username","A");

        //Log.i("This is the username",username);
    }
}
