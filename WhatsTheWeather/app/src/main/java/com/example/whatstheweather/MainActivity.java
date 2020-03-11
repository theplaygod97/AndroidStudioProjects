package com.example.whatstheweather;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class MainActivity extends AppCompatActivity {

    TextView enterCity;
    Button checkWeather;
    TextView cityName;
    TextView dispWeather;
    ImageView weatherIcon;
    String iconid = null;
    String main = null;

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

    public void findWeather(View view){
        DownloadTask task = new DownloadTask();
        String result = null;
        String city = cityName.getText().toString();

        try {
            result = task.execute("http://api.openweathermap.org/data/2.5/weather?q="+city+"&appid=72d67a651b0b134d20eca7e661c575b8").get();

            JSONObject jsonObject = new JSONObject(result);
            String weather = jsonObject.getString("weather");
            Log.i("Weather",weather);

            JSONArray arr = new JSONArray(weather);
            for(int i=0;i<arr.length();i++){
                JSONObject jsonPart = arr.getJSONObject(i);

                Log.i("main",jsonPart.getString("main"));
                Log.i("desc",jsonPart.getString("description"));
                iconid = jsonPart.getString("icon");

                dispWeather.setText(jsonPart.getString("main"));
            }
            Picasso.get().load("http://openweathermap.org/img/wn/"+iconid+"@2x.png").into(weatherIcon);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityName = findViewById(R.id.cityName);
        dispWeather = findViewById(R.id.dispWeather);
        enterCity = findViewById(R.id.enterCity);
        checkWeather = findViewById(R.id.checkWeather);
        weatherIcon = findViewById(R.id.weatherIcon);



    }
}
