package com.example.readingjson;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    public  class  DownloadTask extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... urls) {
            String result = "";
            URL url ;
            HttpURLConnection urlConnection = null;
            try{
                url = new URL(urls[0]);
                urlConnection =(HttpURLConnection) url.openConnection();
                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data = reader.read();

                while(data != -1){
                    char current = (char) data;
                    result += current;
                    data = reader.read();
                }
                return  result;
            }catch(Exception e){
                e.printStackTrace();
                return  null;
            }

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DownloadTask task = new DownloadTask();
        String result = null;
        try {
            result = task.execute("http://api.openweathermap.org/data/2.5/weather?q=Bengaluru&appid=72d67a651b0b134d20eca7e661c575b8").get();

            JSONObject jsonObject = new JSONObject(result);
            String weather = jsonObject.getString("weather");
            Log.i("Weather",weather);

            JSONArray arr = new JSONArray(weather);
            for(int i=0;i<arr.length();i++){
                JSONObject jsonPart = arr.getJSONObject(i);

                Log.i("main",jsonPart.getString("main"));
                Log.i("desc",jsonPart.getString("description"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
