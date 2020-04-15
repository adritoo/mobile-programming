package fr.android.adrien.mycalculator;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.LinearLayout;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.regex.Pattern;


public class MainActivity extends AppCompatActivity {

    Button numberZero, numberOne, numberTwo, numberTree, numberFour, numberFive, numberSix,
            numberSeven, numberEight, numberNine, add, minus, divide,
            multiply, equal;
    TextView viewOperation, viewResult;

    String lastOperation, lastResult;
    float val1, val2;

     Handler handler;

    private String display = "";
    private String currentOperator = "";
    LinearLayout linear;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numberZero = (Button) findViewById(R.id.numberZero);
        numberOne = (Button) findViewById(R.id.numberOne);
        numberTwo = (Button) findViewById(R.id.numberTwo);
        numberTree = (Button) findViewById(R.id.numberTree);
        numberFour = (Button) findViewById(R.id.numberFour);
        numberFive = (Button) findViewById(R.id.numberFive);
        numberSix = (Button) findViewById(R.id.numberSix);
        numberSeven = (Button) findViewById(R.id.numberSeven);
        numberEight = (Button) findViewById(R.id.numberEight);
        numberNine = (Button) findViewById(R.id.numberNine);

        add = (Button) findViewById(R.id.add);
        minus = (Button) findViewById(R.id.minus);
        multiply = (Button) findViewById(R.id.multiply);
        divide = (Button) findViewById(R.id.divide);

        viewOperation = (TextView) findViewById(R.id.viewOperation);

        viewResult = (TextView) findViewById(R.id.viewResult);

        linear = findViewById(R.id.linear);

        equal = new Button(this);

        equal.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.MATCH_PARENT,
                1.0f)
        );
        equal.setText("=");

        equal.setOnClickListener(myEqualHandler(equal));  // Async


        linear.addView(equal);

        handler = new Handler();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true ;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.item1){
            TextView res=(TextView)findViewById(R.id.viewResult);
            String str = res.getText().toString();
            Intent intent;
            intent = new Intent(MainActivity.this, SecondePage.class);
            intent.putExtra("textview",str);
            startActivity(intent);

            return false;
        }
        return super.onOptionsItemSelected(item);
    }

    private void updateScreen() {
        viewOperation.setText(display);
    }

    public void onClickNumber(View v) {
        Button b = (Button) v;
        display += b.getText();
        updateScreen();
    }

    public void onClickOperator(View v) {
        Button b = (Button) v;
        display += b.getText();
        currentOperator = b.getText().toString();
        updateScreen();
    }

    private double operate(String a, String b, String op) {
        switch (op) {
            case "+":
                return Double.valueOf(a) + Double.valueOf(b);
            case "-":
                return Double.valueOf(a) - Double.valueOf(b);
            case "*":
                return Double.valueOf(a) * Double.valueOf(b);
            case "/":
                try {
                    return Double.valueOf(a) / Double.valueOf(b);
                } catch (Exception e) {
                    Log.d("Calc", e.getMessage());
                }
            default:
                return -1;
        }
    }


    //Version Async
    View.OnClickListener myEqualHandler(final Button buttonEqual) {
        return new View.OnClickListener() {
            public void onClick(View v) {
                AsyncEqual task = new AsyncEqual();
                task.execute();
            }
        };
    }

    private class AsyncEqual extends AsyncTask<Void, Void, Double> {
        @SuppressLint("WrongThread")
        protected Double doInBackground(Void... vals) {

            String[] operation = display.split(Pattern.quote(currentOperator));
            //result = operate(operation[0], operation[1], currentOperator);


            try {

                //Creation du socket avec connection a la bonne adress ip
                //Socket soc1 = new Socket("192.168.43.29" , 43622);
                Socket soc1 = new Socket("10.0.2.2", 43622);
                System.out.println(soc1);

                //Création des liens
                DataOutputStream dataOutput = new DataOutputStream(soc1.getOutputStream());
                DataInputStream dataInput = new DataInputStream(soc1.getInputStream());


                double nombre1 = Double.parseDouble(operation[0]);
                double nombre2 = Double.parseDouble(operation[1]);

                //on lui envoie le premier nombre
                dataOutput.writeDouble(nombre1);
                dataOutput.flush(); // Send off the data
                //l'operateur
                dataOutput.writeChars(currentOperator);
                dataOutput.flush();
                //le deuxieme nombre
                dataOutput.writeDouble(nombre2);
                dataOutput.flush(); // Send off the data
                // Send off the data
                //On récupère le resultat du serveur
                Double result = dataInput.readDouble();
                return result;

            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }

            return null;

        }

        protected void onProgressUpdate(Integer... progress) {


        }

        protected void onPostExecute(Double result) {

            //Affiche le resultat
            viewResult.setText(String.valueOf(result));

            //Sauvegarde de la derniere operation
            lastOperation = (String) viewOperation.getText();

            lastResult = (String) viewResult.getText();


        }
    }


    /*public void launchSecondActivity(View view) {

        //Creation du intent pour la nouvelle activité
        Intent intent = new Intent(this, SecondePage.class);

        //On met les données qu on veut passer
        intent.putExtra("LAST_CALCULE", lastOperation);
        intent.putExtra("LAST_RESULT", lastResult);

        //creation de la nouvelle activité
        startActivity(intent);
    }*/
}