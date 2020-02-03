package fr.android.adrien.mycalculator;
import androidx.appcompat.app.AppCompatActivity;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.LinearLayout;





public class MainActivity extends AppCompatActivity {

    Button numberZero, numberOne, numberTwo, numberTree, numberFour, numberFive, numberSix,
            numberSeven, numberEight, numberNine, add, minus, divide,
            multiply, equal;
    TextView viewOperation, viewResult;

    float val1, val2;

    boolean badd, bminus, bmultiply, bdiv;

    Handler handler;

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

        equal.setOnClickListener(myEqualHandler());  //Handler
        //equal.setOnClickListener(myAsyncEqualHandler());  // Async


        linear.addView(equal);

        handler = new Handler();
    }

    //HANDLER
    View.OnClickListener myEqualHandler() {

        return new View.OnClickListener() {
            public void onClick(View v) {
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        String calcul = "";

                        val2 = Float.parseFloat(viewOperation.getText() + "");

                        if ( badd) {
                            calcul=val1+val2 + "";
                            badd= false;
                        }else if (bminus) {
                            calcul=val1-val2 + "";
                            bminus= false;
                        }else if(bmultiply) {
                            calcul=val1*val2 + "";
                            bmultiply= false;
                        }else if(bdiv) {
                            calcul=val1/val2 + "";
                            bdiv= false;
                        }

                        final String finalCalcul = calcul;
                        handler.post(
                                new Runnable() {
                                    @Override
                                    public void run() {

                                        viewResult.setText(finalCalcul);
                                        viewOperation.setText(null);

                                    }
                                }
                        );
                    }
                };
                new Thread(runnable).start();
            }
        };
    }

    //ASYNC
    View.OnClickListener myAsyncEqualHandler()  {
        return new View.OnClickListener() {
            public void onClick(View v) {
                new AsyncEqual().execute();
            }
        };
    }

    private class AsyncEqual extends AsyncTask<Void, Integer, String> {
        protected String doInBackground(Void... vals) {
            String calcul = "";

            val2 = Float.parseFloat(viewOperation.getText() + "");

            if ( badd) {
                calcul = val1+val2 + "";
                badd= false;
            }else if (bminus) {
                calcul = val1-val2 + "";
                bminus= false;
            }else if(bmultiply) {
                calcul = val1*val2 + "";
                bmultiply= false;
            }else if(bdiv) {
                calcul = val1/val2 + "";
                bdiv= false;
            }

            return calcul;
        }
        protected void onProgressUpdate(Integer... progress) {
            //...
        }
        protected void onPostExecute(String result) {

            //Affiche le resultat
            viewResult.setText(result);
            viewOperation.setText(null);
        }
    }

    public void myClickHandler(View view) {
        switch (view.getId()) {
            case R.id.numberOne:
                viewOperation.setText(viewOperation.getText() + "1");
                break;
            case R.id.numberTwo:
                viewOperation.setText(viewOperation.getText() + "2");
                break;
            case R.id.numberTree:
                viewOperation.setText(viewOperation.getText() + "3");
                break;
            case R.id.numberFour:
                viewOperation.setText(viewOperation.getText() + "4");
                break;
            case R.id.numberFive:
                viewOperation.setText(viewOperation.getText() + "5");
                break;
            case R.id.numberSix:
                viewOperation.setText(viewOperation.getText() + "6");
                break;
            case R.id.numberSeven:
                viewOperation.setText(viewOperation.getText() + "7");
                break;
            case R.id.numberEight:
                viewOperation.setText(viewOperation.getText() + "8");
                break;

            case R.id.numberNine:
                viewOperation.setText(viewOperation.getText() + "9");
                break;
            case R.id.numberZero:
                viewOperation.setText(viewOperation.getText() + "0");
                break;


            case R.id.add:
                if(viewOperation.getText()==null){
                    viewOperation.setText("");
                }else {
                    val1 = Float.parseFloat(viewOperation.getText() + "");
                    badd = true;
                    viewOperation.setText("");
                }

                break;

            case R.id.minus:
                if(viewOperation.getText()==null){
                    viewOperation.setText("");
                }else {
                    val1 = Float.parseFloat(viewOperation.getText() + "");
                    bminus = true;
                    viewOperation.setText("");
                }

                break;

            case R.id.multiply:
                if(viewOperation.getText()==null){
                    viewOperation.setText("");
                }else {
                    val1 = Float.parseFloat(viewOperation.getText() + "");
                    bmultiply = true;
                    viewOperation.setText("");
                }

                break;

            case R.id.divide:
                if(viewOperation.getText()==null){
                    viewOperation.setText("");
                }else {
                    val1 = Float.parseFloat(viewOperation.getText() + "");
                    bdiv = true;
                    viewOperation.setText("");
                }

                break;

        }
    }
}


