package fr.android.adrien.mycalculator;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SecondePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        //Recupere les extras (les infos passé)
        Bundle extras = getIntent().getExtras();

        //recupere les champs pour afficher les infos
        TextView last_operation = (TextView) findViewById(R.id.calculprecedent);
        TextView last_result = (TextView) findViewById(R.id.resultatprecedent);

        //On essaye de recuperer les info si il y en a
        try {
            last_operation.setText(extras.getString("LAST_CALCULE"));
            last_result.setText(extras.getString("LAST_RESULT"));
        }
        catch (Error r){
            last_operation.setText(r.getMessage());
        }

    }

    public void openWebView(View view){

        //Recupere l'url rentre
        EditText theURL = (EditText) findViewById(R.id.lienweb);
        //On créer la nouvelle activite en passant l url
        Intent intent = new Intent(this, ThridPage.class);
        intent.putExtra("URL", theURL.getText().toString());
        startActivity(intent);
    }
}
