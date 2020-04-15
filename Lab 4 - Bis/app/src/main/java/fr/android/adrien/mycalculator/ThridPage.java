package fr.android.adrien.mycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;

public class ThridPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        //récupere les infos de creation d activité
        Bundle extras = getIntent().getExtras();

        //Lance le webview
        WebView myWebView = (WebView) findViewById(R.id.PageWeb);
        myWebView.loadUrl("https://" + extras.getString("URL"));
    }
}
