package com.example.newsreader;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class NewsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        WebView webView = findViewById(R.id.newsWebView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());

        Intent intent =getIntent();
        String NURL ="";
        NURL = intent.getStringExtra("ContentURL");
        Log.i("Check",NURL);
        webView.loadUrl(intent.getStringExtra("ContentURL"));


    }
}
