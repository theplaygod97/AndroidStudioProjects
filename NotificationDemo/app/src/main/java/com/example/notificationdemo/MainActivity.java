package com.example.notificationdemo;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

public class MainActivity extends WearableActivity {

    private TextView mTextView;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView) findViewById(R.id.text);

        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        CharSequence name;
        NotificationChannel notificationChannel = new NotificationChannel("default", "test", NotificationManager.IMPORTANCE_DEFAULT);
        manager.createNotificationChannel(notificationChannel);


        Intent intent = new Intent(this,MainActivity.class);
        Context context;
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        Notification.Builder builder = new Notification.Builder(this, "default")
                .setContentTitle("Hello")
                .setContentText("Satya")
                .setSmallIcon(android.R.drawable.ic_notification_overlay)
                .setContentIntent(pendingIntent);
        manager.notify(0,builder.build());

        // Enables Always-on
        setAmbientEnabled();
    }
}
