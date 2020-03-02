package fr.android.calculatrice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static fr.android.calculatrice.R.menu.menu;

/* ---- On procédé de la manière suivante : Créer une seule méthode pour gérer les bouton en utilissant le statment this en implementant View.OnClickListener
 l'utilisateur taper une opération , le programme qu'on a proposé prend la chaine de caractère tapée et distingue entre l'opérande et l'opérateur
 en utilisant un index puis transforme les deux en double et les stockes dans des variable pour pouvoir les utiliser lors des méthodes pour effectuer un calcul */

public class MainActivity extends AppCompatActivity implements View.OnClickListener { // implémentation( View.onCLicklistner pour utiliser v View avec get Id
     private Boolean cliquepositif=false;// indique si l'utilisateur a cliqué sur l'un des bouton(+-*/)
     private double premiernb=0;        //le premier chiffre tapé
     private  int deuxiemenb=0;        // le deuxième chiffre tapé indéxé ( après avoir tapé l'opérateur)
     private  char op ;                // utilisé dans la condition if(){} pour savoir sur quel opérateur l'utilisateur a cliqué




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView operation = findViewById(R.id.oper);
        TextView res = findViewById(R.id.Result);
        Button buttonplus = findViewById(R.id.addition);
        Button buttonmoins = findViewById(R.id.soustraction);
        Button buttonfois = findViewById(R.id.multiplication);
        Button buttondiv = findViewById(R.id.division);
        Button buttonres = findViewById(R.id.egale);
        Button button1 = findViewById(R.id.nbUn);
        Button button2 = findViewById(R.id.nbDeux);
        Button button3 = findViewById(R.id.nbTrois);
        Button button4 = findViewById(R.id.nbQuatre);
        Button button5 = findViewById(R.id.nbCinq);
        Button button6 = findViewById(R.id.nbSix);
        Button button7 = findViewById(R.id.nbSept);
        Button button8 = findViewById(R.id.nbHuit);
        Button button9 = findViewById(R.id.nbNeuf);
        Button button0 = findViewById(R.id.nbZero);




                                                     // signifie on place notre activité ici elle meme , mais pour cela
        buttonfois.setOnClickListener(this);        // il faut implementer this onclicklistener
        buttondiv.setOnClickListener(this);         //interface puis onClick pour l'utiliser une seule fois
        buttonmoins.setOnClickListener(this);
        buttonplus.setOnClickListener(this);
        buttonres.setOnClickListener(this);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
        button0.setOnClickListener(this);
        operation.setOnClickListener(this);
        res.setOnClickListener(this);



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
            TextView res=(TextView)findViewById(R.id.Result) ;///////
            String str = res.getText().toString();
            Intent intent = new Intent(MainActivity.this,Main2Activity.class);
            intent.putExtra("textview",str);
            startActivity(intent);

            return false;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v)
    {
        final TextView operation =(TextView)findViewById(R.id.oper); // final est utilisé pour pourvoir l'utiliser en dehors du scope pour le bouton effacer
        String contenu = operation.getText().toString();  // la chaine de caractère que l'utilisateur tape ( 1+1 par exemple )
        TextView res=(TextView)findViewById(R.id.Result) ;

        switch (v.getId()) {
// append() permet de renvoyer plusieurs caractères à la fois , au contraire de setText() qui renvoie un caractère unique
                case R.id.nbZero:
                    operation.append("0");
                    break;
                case R.id.nbUn:
                    operation.append("1");
                    break;
                case R.id.nbDeux:
                    operation.append("2");
                    break;
                case R.id.nbTrois:
                    operation.append("3");
                    break;
                case R.id.nbQuatre:
                    operation.append("4");
                    break;
                case R.id.nbCinq:
                    operation.append("5");
                    break;
                case R.id.nbSix:
                    operation.append("6");
                    break;
                case R.id.nbSept:
                    operation.append("7");
                    break;
                case R.id.nbHuit:
                    operation.append("8");
                    break;
                case R.id.nbNeuf:
                    operation.append("9");
                    break;
                case R.id.addition:

                    premiernb=Double.parseDouble(contenu);    // Convertir String à un Double
                    deuxiemenb=contenu.length()+1;            // pointe sur lélement qui suit l'opérateur
                    operation.append("+");
                    cliquepositif=true;
                    op='+';
                    break;
                case R.id.soustraction:
                    premiernb=Double.parseDouble(contenu);
                    deuxiemenb=contenu.length()+1;
                    operation.append("-");
                    cliquepositif=true;
                    op='-';
                    break;
                case R.id.multiplication:
                    premiernb=Double.parseDouble(contenu);
                    deuxiemenb=contenu.length()+1;
                    operation.append("*");
                    cliquepositif=true;
                    op='*';
                    break;
                case R.id.division:
                    premiernb=Double.parseDouble(contenu);
                    deuxiemenb=contenu.length()+1;
                    operation.append("/");
                    cliquepositif=true;
                    double resultat=0;
                    op='/';
                    break;
                case R.id.egale:
                    if(cliquepositif){  // si le bouton = true
                        String deuxiemenbstring = contenu.substring(deuxiemenb,contenu.length());
                        double deuxiemenombre = Double.parseDouble(deuxiemenbstring); // On stransforme la chaine de caractère indéxée à partir de l'opérateur en double
                        if(op=='+'){
                           deuxiemenombre+= premiernb ;
                           res.setText(String.valueOf(deuxiemenombre));
                        }else if(op=='-'){
                            resultat= premiernb-deuxiemenombre ;
                            res.setText(String.valueOf(resultat));
                        }else if (op=='*'){
                            deuxiemenombre= premiernb *deuxiemenombre;
                            res.setText(String.valueOf(deuxiemenombre));
                        }else if (op=='/'){
                            deuxiemenombre= premiernb/deuxiemenombre ;
                            res.setText(String.valueOf(deuxiemenombre));
                        }
                    }
                    break;


            }

        Button Buttonc = findViewById(R.id.effacer);
        Buttonc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operation.setText(""); // chaine de caractère vide quand on efface

            }
        });
    }



//--------------méthodes---------------------------------


}

