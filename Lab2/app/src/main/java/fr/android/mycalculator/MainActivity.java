package fr.android.mycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.strictmode.IntentReceiverLeakedViolation;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button numberZero;
    Button numberOne;
    Button numberTwo;
    Button numberThree;
    Button numberFour;
    Button numberFive;
    Button numberSix;
    Button numberSeven;
    Button numberEight;
    Button numberNine;
    Button equals;
    Button minus;
    Button add;
    Button divide;
    Button multiply;

    TextView viewOperation;
    TextView viewResult;

    String operation = "";
    
    public String calculate(String operation){
        double result = 0;
        int a,b;
        char c = operation.charAt(1);
        a = Character.getNumericValue(operation.charAt(0));
        System.out.println(+a);
        b = Character.getNumericValue(operation.charAt(2));
        switch (c){
            case'+':
                result=a+b;
                break;
            case'-':
                result=a-b;
                break;
            case'*':
                result=a*b;
                break;
            case'/':
                result=a/b;
                break;
        }
        return Double.toString(result);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        numberZero = (Button)findViewById(R.id.numberZero);
        numberOne = (Button)findViewById(R.id.numberOne);
        numberTwo = (Button)findViewById(R.id.numberTwo);
        numberThree = (Button)findViewById(R.id.numberThree);
        numberFour = (Button)findViewById(R.id.numberFour);
        numberFive = (Button)findViewById(R.id.numberFive);
        numberSix = (Button)findViewById(R.id.numberSix);
        numberSeven = (Button)findViewById(R.id.numberSeven);
        numberEight = (Button)findViewById(R.id.numberEight);
        numberNine = (Button)findViewById(R.id.numberNine);
        equals = (Button)findViewById(R.id.equals);
        minus = (Button)findViewById(R.id.minus);
        add = (Button)findViewById(R.id.add);
        divide = (Button)findViewById(R.id.divide);
        multiply = (Button)findViewById(R.id.multiply);

        numberZero.setOnClickListener(onClickListener);
        numberOne.setOnClickListener(onClickListener);
        numberTwo.setOnClickListener(onClickListener);
        numberThree.setOnClickListener(onClickListener);
        numberFour.setOnClickListener(onClickListener);
        numberFive.setOnClickListener(onClickListener);
        numberSix.setOnClickListener(onClickListener);
        numberSeven.setOnClickListener(onClickListener);
        numberEight.setOnClickListener(onClickListener);
        numberNine.setOnClickListener(onClickListener);
        equals.setOnClickListener(onClickListener);
        add.setOnClickListener(onClickListener);
        minus.setOnClickListener(onClickListener);
        divide.setOnClickListener(onClickListener);
        multiply.setOnClickListener(onClickListener);

    }

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((operation.length() < 3)||(String.valueOf(v.getTag()).equals("="))) {
                    if (!(String.valueOf(v.getTag()).equals("="))){
                        viewOperation = (TextView) findViewById(R.id.viewOperation);
                        operation = operation.concat(String.valueOf(v.getTag()));
                        viewOperation.setText(operation);
                    } else {
                        System.out.println("t");
                        viewResult = (TextView) findViewById(R.id.viewResult);
                        viewResult.setText(calculate(operation));
                    }
                }
            }
        };
}
