package com.proyectodm.wordguesser.iu;

import android.os.Bundle;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

import com.proyectodm.wordguesser.R;

public class TrucosActivity extends WordGuesserActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trucos);
        WebView myWebView = (WebView) findViewById(R.id.urlTrucos);
        myWebView.loadUrl("https://www.xataka.com/basics/wordle-7-trucos-pistas-para-adivinar-palabra-cada-dia");
    }
}
