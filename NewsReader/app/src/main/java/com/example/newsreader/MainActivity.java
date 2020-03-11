package com.example.newsreader;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.graphics.drawable.AnimationDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> titles = new ArrayList<>();
    ArrayList<String> content = new ArrayList<>();
    ArrayList<String> contentURL = new ArrayList<>();
    CustomListAdapter arrayAdapter;
    SQLiteDatabase newsDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConstraintLayout linearLayout = (ConstraintLayout) findViewById(R.id.main);

        AnimationDrawable animationDrawable = (AnimationDrawable) linearLayout.getBackground();

        animationDrawable.setEnterFadeDuration(2500);
        animationDrawable.setExitFadeDuration(5000);

        animationDrawable.start();

        String date_n = new SimpleDateFormat("EEE,dd MMM", Locale.getDefault()).format(new Date());

        TextView date  = (TextView) findViewById(R.id.dateView);

        date.setText(date_n + "\nTop TECH News");


        newsDB = this.openOrCreateDatabase("NewsReader",0,null);
        newsDB.execSQL("CREATE TABLE IF NOT EXISTS news (id INTEGER PRIMARY KEY, newsID INTEGER ,title VARCHAR ,contentURL VARCHAR)");



        DownloadTask task = new DownloadTask();
        try{
            task.execute("https://hacker-news.firebaseio.com/v0/topstories.json?print=pretty");
        }
        catch (Exception e){
            e.printStackTrace();
        }

        ListView listView = findViewById(R.id.listView);
        arrayAdapter = new CustomListAdapter(this,titles);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),NewsActivity.class);
                intent.putExtra("ContentURL",contentURL.get(position));
                startActivity(intent);
            }
        });
        updateListView();
    }

    public  void updateListView(){
        Cursor c = newsDB.rawQuery("SELECT * FROM news",null);
        int contentURLIndex = c.getColumnIndex("contentURL");
        int titleIndex = c.getColumnIndex("title");

        if(c.moveToFirst()){
            titles.clear();
            contentURL.clear();

            do{
              titles.add(c.getString(titleIndex));
              contentURL.add(c.getString(contentURLIndex));
            }while(c.moveToNext());
            arrayAdapter.notifyDataSetChanged();
        }
    }

    public class DownloadTask extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... urls) {
            String result = "";
            URL url;
            HttpURLConnection urlConnection;
            try{
                url = new URL(urls[0]);
                urlConnection =(HttpURLConnection)url.openConnection();
                InputStream inputStream = urlConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                int data = inputStreamReader.read();
                while(data != -1){
                    char current = (char) data;
                    result += current;
                    data = inputStreamReader.read();
                }

                JSONArray jsonArray = new JSONArray(result);
                int numNews = 20;

                if(jsonArray.length() < 20){
                    numNews=jsonArray.length();
                }


                newsDB.execSQL("DELETE FROM news");

                for(int i =0;i<numNews;i++){
                    String newsID = jsonArray.getString(i);
                    url = new URL("https://hacker-news.firebaseio.com/v0/item/"+newsID+".json?print=pretty");
                    urlConnection =(HttpURLConnection)url.openConnection();
                    inputStream = urlConnection.getInputStream();
                    inputStreamReader = new InputStreamReader(inputStream);
                    data = inputStreamReader.read();
                    String newsinfo="";
                    while(data != -1){
                        char current = (char) data;
                        newsinfo += current;
                        data = inputStreamReader.read();
                    }
                    JSONObject jsonObject = new JSONObject(newsinfo);

                    if(!jsonObject.isNull("title")&&!jsonObject.isNull("url")){
                        String newsTitle = jsonObject.getString("title");
                        String newsURL = jsonObject.getString("url");

                        Log.i("Title and URL",newsTitle+newsURL);
                        //url = new URL(newsURL);
                        //urlConnection =(HttpURLConnection)url.openConnection();
                        //inputStream = urlConnection.getInputStream();
                        //inputStreamReader = new InputStreamReader(inputStream);
                        //data = inputStreamReader.read();
                        //String newsContent="";
                        //while(data != -1){
                        //char current = (char) data;
                          //  newsContent += current;
                            //data = inputStreamReader.read();
                        //}
                        //Log.i("News Content",newsContent);

                        String sql = "INSERT INTO news (newsID ,title,contentURL) VALUES (?,?,?)";
                        SQLiteStatement statement = newsDB.compileStatement(sql);
                        statement.bindString(1,newsID);
                        statement.bindString(2,newsTitle);
                        statement.bindString(3,newsURL);
                        statement.execute();


                    }

                    //Log.i("NewsInfo",newsinfo);
                }

                Log.i("URL content",result);
                return  result;
            }catch(Exception e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            updateListView();
        }
    }
}
