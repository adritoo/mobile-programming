package fr.android.calvez;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView calcul;
    TextView resultat;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button equal = new Button(this);


        equal.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.MATCH_PARENT,
                1.0f)
        );
        equal.setText("=");


        //Methode Async
       // equal.setOnClickListener(myEqualHandler(equal));
        //Methode Handler
        equal.setOnClickListener(buttonEqualv1());


        LinearLayout layout = findViewById(R.id.groupe);

        layout.addView(equal);

        calcul = findViewById(R.id.calcul);
        resultat = findViewById(R.id.resultat);

        handler = new Handler();
    }


    //Version Handler
    View.OnClickListener buttonEqualv1() {

        return new View.OnClickListener() {
            public void onClick(View v) {


                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        handler.post(
                                new Runnable() {
                                    @Override
                                    public void run() {
                                        String myText = (String) calcul.getText();
                                        String firstNumber = "";
                                        String secondNumber = "";
                                        String operation = "";

                                        int state = 0;

                                        for (int i = 0; i < myText.length(); i++) {
                                            if (myText.charAt(i) >= '0' && myText.charAt(i) <= '9') {
                                                if (state == 0) {
                                                    firstNumber += myText.charAt(i);
                                                } else if (state == 1) {
                                                    secondNumber += myText.charAt(i);
                                                }
                                            } else {
                                                if (state == 0) {
                                                    operation += myText.charAt(i);
                                                    state = 1;
                                                } else {
                                                    break;
                                                }
                                            }
                                        }

                                        int number1 = Integer.parseInt(firstNumber);
                                        int number2 = Integer.parseInt(secondNumber);
                                        String finalText;

                                        if (operation.equals("*")) {
                                            finalText = String.valueOf(number1 * number2);
                                        } else if (operation.equals("/")) {
                                            finalText = String.valueOf(number1 / number2);
                                        } else if (operation.equals("+")) {
                                            finalText = String.valueOf(number1 + number2);
                                        } else if (operation.equals("-")) {
                                            finalText = String.valueOf(number1 - number2);
                                        } else {
                                            finalText = "Error";
                                        }
                                        resultat.setText(finalText);

                                    }
                                }
                        );
                    }
                };
                new Thread(runnable).start();
            }
        };
    }

    //Version Async
    View.OnClickListener myEqualHandler(final Button equal)  {
        return new View.OnClickListener() {
            public void onClick(View v) {
                AsyncEqual task = new AsyncEqual();
                task.execute();
            }
        };
    }

    private class AsyncEqual extends AsyncTask<Void, Void, String>{
        @SuppressLint("WrongThread")
        protected String doInBackground(Void... vals) {
            String myText = (String) calcul.getText();
            String firstNumber = "";
            String secondNumber = "";
            String operation = "";

            int state = 0;

            for(int i = 0; i < myText.length(); i++){
                if(myText.charAt(i) >= '0' && myText.charAt(i) <= '9'){
                    if(state == 0){
                        firstNumber +=myText.charAt(i);
                    }
                    else if(state == 1){
                        secondNumber += myText.charAt(i);
                    }
                }else{
                    if(state == 0){
                        operation += myText.charAt(i);
                        state = 1;
                    }else{
                        break;
                    }
                }
            }

            int number1 = Integer.parseInt(firstNumber);
            int number2 = Integer.parseInt(secondNumber);
            String finalText;

            if(operation.equals("*")){
                finalText = String.valueOf(number1 * number2);
            }
            else if(operation.equals("/")){
                finalText = String.valueOf(number1 / number2);
            }
            else if(operation.equals("+")){
                finalText = String.valueOf(number1 + number2);
            }
            else if(operation.equals("-")){
                finalText = String.valueOf(number1 - number2);
            }
            else{
                finalText = "Error";
            }
            return finalText;
        }
        protected void onProgressUpdate(Integer... progress) {
            //...
        }
        protected void onPostExecute(String result) {
            resultat.setText(result);
        }
    }

    public void myClickHandler(View view) {
        System.out.println(view);

        TextView calcul = findViewById(R.id.calcul);
        TextView resultat = findViewById(R.id.resultat);
        switch (view.getId()) {
            case R.id.btn0:
                calcul.setText(calcul.getText() + "0");
                break;
            case R.id.btn1:
                calcul.setText(calcul.getText() + "1");
                break;
            case R.id.btn2:
                calcul.setText(calcul.getText() + "2");
                break;
            case R.id.btn3:
                calcul.setText(calcul.getText() + "3");
                break;
            case R.id.btn4:
                calcul.setText(calcul.getText() + "4");
                break;
            case R.id.btn5:
                calcul.setText(calcul.getText() + "5");
                break;
            case R.id.btn6:
                calcul.setText(calcul.getText() + "6");
                break;
            case R.id.btn7:
                calcul.setText(calcul.getText() + "7");
                break;
            case R.id.btn8:
                calcul.setText(calcul.getText() + "8");
                break;
            case R.id.btn9:
                calcul.setText(calcul.getText() + "9");
                break;
            case R.id.btnmoins:
                calcul.setText(calcul.getText() + "-");
                break;
            case R.id.btnplus:
                calcul.setText(calcul.getText() + "+");
                break;
            case R.id.btnx:
                calcul.setText(calcul.getText() + "*");
                break;
            case R.id.btndiv:
                calcul.setText(calcul.getText() + "/");
                break;
        }

    }
}

