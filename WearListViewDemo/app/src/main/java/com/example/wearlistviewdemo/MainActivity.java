package com.example.wearlistviewdemo;

import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends WearableActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = findViewById(R.id.listView);
        final String[] friends = {"Jeo","Sarah","Jane","Jeo","Sarah","Jane",
                "Jeo","Sarah","Jane","Jeo","Sarah","Jane"
        ,"Jeo","Sarah","Jane","Jeo","Sarah","Jane"};

        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,friends);

        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this,friends[position],Toast.LENGTH_SHORT).show();
            }
        });
        // Enables Always-on
        setAmbientEnabled();
    }
}
