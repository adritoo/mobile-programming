package fr.android.calculatrice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent  intent = getIntent();
        if (intent != null){
            String str ="";
            if (intent.hasExtra("textview")){
                str = intent.getStringExtra("textview");
                TextView textView =(TextView)findViewById(R.id.txt1);
                textView.setText(str);
            }
        }
    }
}
