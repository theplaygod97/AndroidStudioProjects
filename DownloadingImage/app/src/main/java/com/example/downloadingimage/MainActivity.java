package com.example.downloadingimage;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    public void download(View view) throws ExecutionException, InterruptedException {
        Log.i("Button Tapped","OK");
        ImageDownloader task = new ImageDownloader();
        Bitmap myImage;

        myImage = task.execute("https://images.news18.com/ibnlive/uploads/2020/02/hrithik-roshan-dabboo.jpg").get();

        imageView.setImageBitmap(myImage);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageView);
    }
    public  class ImageDownloader extends AsyncTask<String,Void, Bitmap>{

        @Override
        protected Bitmap doInBackground(String... urls) {
            try {
                URL url = new URL(urls[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream in = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(in);
                return  myBitmap;
            } catch (Exception e) {
                e.printStackTrace();
                return  null;
            }

        }
    }
}
