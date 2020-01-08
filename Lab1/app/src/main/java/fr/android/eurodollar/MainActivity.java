package fr.android.eurodollar;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editText1;

    public void myClickHandler(View view) {
        switch (view.getId()) {
            case R.id.button1:
                RadioButton euroButton = (RadioButton) findViewById(R.id.EuroButton);
                RadioButton dollarButton = (RadioButton) findViewById(R.id.DollarButton);
                if (editText1.getText().length() == 0) {
                    Toast.makeText(this, "Please enter a valid number",
                            Toast.LENGTH_LONG).show();
                    return;
                }

                float inputValue = Float.parseFloat(editText1.getText().toString());
                if (euroButton.isChecked()) {
                    editText1.setText(String
                            .valueOf(convertDollarToEuro(inputValue)));
                    euroButton.setChecked(true);
                    dollarButton.setChecked(false);
                } else {
                    editText1.setText(String
                            .valueOf(convertEuroToDollar(inputValue)));
                    dollarButton.setChecked(true);
                    euroButton.setChecked(false);
                }
                break;
        }
    }

    // Convertir Dollar à Euro
    private float convertDollarToEuro(float dollar) {
        return dollar*(0.89f); // formule à utiliser
    }

    // Convertir Euro à Dollar
    private float convertEuroToDollar(float euro) {
        return euro*(1.12f); // formule à utiliser
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        editText1= (EditText)findViewById(R.id.editText1);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
