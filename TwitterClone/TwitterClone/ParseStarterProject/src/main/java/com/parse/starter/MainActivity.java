/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.parse.starter;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;


public class MainActivity extends AppCompatActivity {
 public  void redirectUser(){
     if(ParseUser.getCurrentUser()!= null){
         Intent intent = new Intent(getApplicationContext(),UsersActivity.class);
         startActivity(intent);
     }
 }

 public void signuplogin(View view){
    final EditText usernameEditText = findViewById(R.id.useranemEditText);
    final EditText passwordEditText = findViewById(R.id.passwordEditText);

   ParseUser.logInInBackground(usernameEditText.getText().toString(),
           passwordEditText.getText().toString(), new LogInCallback() {
             @Override
             public void done(ParseUser user, ParseException e) {
               if(e == null){
                 Log.i("Login","Success");
                 redirectUser();
               }else{
                 ParseUser parseUser = new ParseUser();
                 parseUser.setUsername(usernameEditText.getText().toString());
                 parseUser.setPassword(passwordEditText.getText().toString());
                 parseUser.signUpInBackground(new SignUpCallback() {
                   @Override
                   public void done(ParseException e) {
                     if(e == null){
                       Log.i("Signup","Success");
                       redirectUser();
                     }else{
                       Toast.makeText(MainActivity.this,e.getMessage().substring(e.getMessage().indexOf(" ")),Toast.LENGTH_SHORT).show();
                     }
                   }
                 });
               }
             }
           });

 }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    setTitle("Twitter Login");
    redirectUser();
    
    ParseAnalytics.trackAppOpenedInBackground(getIntent());
  }

}