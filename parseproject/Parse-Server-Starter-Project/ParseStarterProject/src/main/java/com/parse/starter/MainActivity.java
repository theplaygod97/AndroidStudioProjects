/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.parse.starter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;


public class MainActivity extends AppCompatActivity implements  View.OnClickListener,View.OnKeyListener{
  Boolean signupState = true;
  TextView loginTextView;
  EditText usernameEditText;
  EditText passwordEditText;

  public void showUserList(){
    Intent intent = new Intent(getApplicationContext(),UserListActivity.class);
    startActivity(intent);
  }


  public void signupclicked(View view){
    usernameEditText = findViewById(R.id.usernameEditText);
    passwordEditText = findViewById(R.id.passwordEditText);

    if(usernameEditText.getText().toString().matches("") || passwordEditText.getText().toString().matches("")){
      Toast.makeText(this,"A username and Password in required",Toast.LENGTH_SHORT).show();

    }else {

      if (signupState) {
        ParseUser user = new ParseUser();
        user.setUsername(usernameEditText.getText().toString());
        user.setPassword(passwordEditText.getText().toString());
        user.signUpInBackground(new SignUpCallback() {
          @Override
          public void done(ParseException e) {
            if (e == null) {
              Log.i("Sign Up ", "Success");
              showUserList();
            } else {
              e.printStackTrace();
              Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
          }
        });
      }else{
        //LOGIN
        ParseUser.logInInBackground(usernameEditText.getText().toString(), passwordEditText.getText().toString(), new LogInCallback() {
          @Override
          public void done(ParseUser user, ParseException e) {
            if(user != null){
              Log.i("LOGIN","Success");
              showUserList();
            }else {
              e.printStackTrace();
              Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
          }
        });
      }
    }
  }
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    setTitle("Instagram");
    //username - user
    //pass - 0M0Agq9b2zgI

    /*ParseObject score = new ParseObject("Score");
    score.put("username","nick");
    score.put("score",45);
    score.saveInBackground(new SaveCallback() {
      @Override
      public void done(ParseException e) {
        if(e == null){
          //OK
          Log.i("Success","Score Saved");
        }else{
          e.printStackTrace();
        }
      }
    });

    ParseQuery<ParseObject> query = ParseQuery.getQuery("Score");
    query.getInBackground("s6yFXXrHK7", new GetCallback<ParseObject>() {
      @Override
      public void done(ParseObject object, ParseException e) {
        if(e == null && object !=null){

          Log.i("username",object.getString("username"));
          Log.i("score",Integer.toString(object.getInt("score")));
        }
      }
    });

    ParseObject tweet = new ParseObject("Tweet");
    tweet.put("username","Satya");
    tweet.put("tweet","Hello All");
    tweet.saveInBackground(new SaveCallback() {
      @Override
      public void done(ParseException e) {
        if(e == null){
          //OK
          Log.i("Success","Tweeted");
        }else{
          e.printStackTrace();
        }
      }
    });



    ParseQuery<ParseObject> query = ParseQuery.getQuery("Tweet");
    query.getInBackground("opIHt21hqz", new GetCallback<ParseObject>() {
      @Override
      public void done(ParseObject object, ParseException e) {
        if(e == null && object !=null){
          object.put("tweet","Hi all");
          object.saveInBackground();
          Log.i("username",object.getString("username"));
          Log.i("tweet",Integer.toString(object.getInt("tweet")));
        }
      }
    });


    ParseQuery<ParseObject> query = ParseQuery.getQuery("Score");

    query.whereGreaterThan("score",50);


    query.findInBackground(new FindCallback<ParseObject>() {
      @Override
      public void done(List<ParseObject> objects, ParseException e) {
        if(e == null){
          if(objects.size() > 0){
            for(ParseObject object : objects){
              object.put("score",object.getInt("score")+20);
              object.saveInBackground();
              Log.i("username",object.getString("username"));
              Log.i("Score",Integer.toString(object.getInt("score")));
            }
          }
        }
      }
    });
*/

    //Log in Parse User
    /*
    ParseUser user = new ParseUser();
    user.setUsername("Satya");
    user.setPassword("123");
    user.signUpInBackground(new SignUpCallback() {
      @Override
      public void done(ParseException e) {
        if(e == null){
          //OK
        Log.i("Sign Up OK!","Signed UP");
        }else{
          e.printStackTrace();
        }
      }
    });

    ParseUser.logInInBackground("Satya", "123", new LogInCallback() {
      @Override
      public void done(ParseUser user, ParseException e) {
        if(user != null){
          Log.i("Success","Logged IN");
        }else{
          e.printStackTrace();
        }
      }
    });

    ParseUser.logOut();
    if(ParseUser.getCurrentUser() != null){
      Log.i("Signed IN",ParseUser.getCurrentUser().getUsername());

    }else{
      Log.i("Error","Not Logged IN");
    }
    */

    loginTextView = findViewById(R.id.loginTextView);
    loginTextView.setOnClickListener(this);


    usernameEditText = findViewById(R.id.usernameEditText);
    passwordEditText = findViewById(R.id.passwordEditText);

    ImageView logoImageView = findViewById(R.id.logoImageView);
    RelativeLayout bgLayout = findViewById(R.id.backgroundLayout);

    logoImageView.setOnClickListener(this);
    bgLayout.setOnClickListener(this);

    passwordEditText.setOnClickListener(this);

    if(ParseUser.getCurrentUser() != null){
      showUserList();
    }


    ParseAnalytics.trackAppOpenedInBackground(getIntent());
  }

  @Override
  public void onClick(View v) {
    if(v.getId() == R.id.loginTextView){
      Log.i("Switch","Tapped");
      Button signupButton = findViewById(R.id.signupButton);
      if(signupState){
        signupState = false;
        signupButton.setText("Log IN");
        loginTextView.setText("Or, Sign UP");
      }
      else{
        signupState = true;
        signupButton.setText("Sign UP");
        loginTextView.setText("Or, Log IN");
      }

    }else if(v.getId() == R.id.logoImageView || v.getId() == R.id.backgroundLayout){
      InputMethodManager inputMethodManager = (InputMethodManager) getBaseContext().getSystemService(Context.INPUT_METHOD_SERVICE);
      inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
    }
  }

  @Override
  public boolean onKey(View view, int keyCode, KeyEvent event) {
    if(keyCode == EditorInfo.IME_ACTION_DONE || keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN){
      signupclicked(view);
    }
    return false;
  }
}