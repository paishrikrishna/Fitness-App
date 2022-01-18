package com.example.fitness_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class webview extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        Intent intent = getIntent();
        String str = intent.getStringExtra("title");
        String url = intent.getStringExtra("url");
        TextView txtView = (TextView) findViewById(R.id.title);
        txtView.setText(str);
        TextView link = (TextView) findViewById(R.id.link);
        link.setText(url);
        WebView mWebView = (WebView) findViewById(R.id.web);
        ImageView back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient());
        //mWebView.loadUrl("https://sourceofsupplements.com/");
        mWebView.loadUrl(url);
    }
}