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
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;


public class MainActivity extends AppCompatActivity {
    Switch userTypeSwitch;

    public void redirectACtivity() {
        if (ParseUser.getCurrentUser().get("RiderORDriver").equals("Rider")) {
            Intent intent = new Intent(getApplicationContext(), RidersActivity.class);
            startActivity(intent);
        } else {
            userTypeSwitch.setChecked(true);
            Intent intent = new Intent(getApplicationContext(), ViewRequestActivity.class);
            startActivity(intent);
        }
    }


    public void loginClicked(View view) {


        Log.i("Switch Value", String.valueOf(userTypeSwitch.isChecked()));
        String userType = "Rider";
        if (userTypeSwitch.isChecked()) {
            userType = "Driver";
        }
        ParseUser.getCurrentUser().put("RiderORDriver", userType);
        ParseUser.getCurrentUser().saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                redirectACtivity();
            }
        });

        Log.i("Info", "Redirecting as " + userType);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        userTypeSwitch = findViewById(R.id.userTypeSwitch);

        if (ParseUser.getCurrentUser() == null) {
            ParseAnonymousUtils.logIn(new LogInCallback() {
                @Override
                public void done(ParseUser user, ParseException e) {
                    if (e == null) {
                        Log.i("Info", "Anonymous login success");
                    } else {
                        Log.i("Info", "Anonymous login success");

                    }
                }
            });
        } else {
            if (ParseUser.getCurrentUser().get("RiderORDriver") != null) {
                Log.i("Info", "Redirecting as " + ParseUser.getCurrentUser().get("RiderORDriver"));
                redirectACtivity();
            }
        }

        ParseAnalytics.trackAppOpenedInBackground(getIntent());
    }

}