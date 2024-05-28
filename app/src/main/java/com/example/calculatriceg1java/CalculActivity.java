package com.example.calculatriceg1java;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.calculatriceg1java.Entities.Calcul;
import com.example.calculatriceg1java.database.CalculBaseHelper;
import com.example.calculatriceg1java.database.CalculDao;

public class CalculActivity extends AppCompatActivity {

    private Button buttonPlus;
    private Button buttonSubstract;
    private Button buttonMultiply;
    private Button buttonDivide;
    private Button button0;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private Button button8;
    private Button button9;
    private TextView calculText;
    private TypeOperation typeOperation;
    private Integer premierElement=0;
    private Integer deuxiemeElement=0;

    private CalculDao calculDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_calcul);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        buttonPlus=findViewById(R.id.button_add);
        buttonSubstract=findViewById(R.id.button_substract);
        buttonDivide=findViewById(R.id.button_divide);
        buttonMultiply=findViewById(R.id.button_mulitply);
        button0=findViewById(R.id.button_0);
        button1=findViewById(R.id.button_1);
        button2=findViewById(R.id.button_2);
        button3=findViewById(R.id.button_3);
        button4=findViewById(R.id.button_4);
        button5=findViewById(R.id.button_5);
        button6=findViewById(R.id.button_6);
        button7=findViewById(R.id.button_7);
        button8=findViewById(R.id.button_8);
        button9=findViewById(R.id.button_9);
        calculText=findViewById(R.id.calcul_text);

        button0.setOnClickListener(view -> appuieChiffre("0"));
        button1.setOnClickListener(view -> appuieChiffre("1"));
        button2.setOnClickListener(view -> appuieChiffre("2"));
        button3.setOnClickListener(view -> appuieChiffre("3"));
        button4.setOnClickListener(view -> appuieChiffre("4"));
        button5.setOnClickListener(view -> appuieChiffre("5"));
        button6.setOnClickListener(view -> appuieChiffre("6"));
        button7.setOnClickListener(view -> appuieChiffre("7"));
        button8.setOnClickListener(view -> appuieChiffre("8"));
        button9.setOnClickListener(view -> appuieChiffre("9"));
        buttonMultiply.setOnClickListener(view -> appuieType(TypeOperation.MULTIPLY));
        buttonPlus.setOnClickListener(view -> appuieType(TypeOperation.ADD));
        buttonSubstract.setOnClickListener(view -> appuieType(TypeOperation.SUBSTRACT));
        buttonDivide.setOnClickListener(viez -> appuieType(TypeOperation.DIVIDE));
        calculDao = new CalculDao(new CalculBaseHelper(this,"db_alt",null,1));
    }

    private void ajouterCharactere(String AAjouter){
        calculText.setText(calculText.getText()+AAjouter);
    }

    private void appuieChiffre(String AAjouter){
        ajouterCharactere(AAjouter);
        if(typeOperation==null){
            premierElement= 10* premierElement+Integer.parseInt(AAjouter);
        }else{
            deuxiemeElement= 10* deuxiemeElement+Integer.parseInt(AAjouter);
        }
    }

    private void appuieType(TypeOperation typeOperation){
        ajouterCharactere(typeOperation.getSymbole());
        this.typeOperation= typeOperation;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater =getMenuInflater();
        inflater.inflate(R.menu.monmenu,menu);
        MenuItem boutonVider = menu.findItem(R.id.bouton_vider);
        MenuItem boutonCalculer = menu.findItem(R.id.bouton_calcul);
        boutonVider.setOnMenuItemClickListener(view -> vide());
        boutonCalculer.setOnMenuItemClickListener(view -> calcule());
        return super.onCreateOptionsMenu(menu);
    }

    private boolean calcule() {
        Calcul calcul =new Calcul();
        calcul.setPremierElement(premierElement);
        calcul.setDeuxiemeElement(deuxiemeElement);
        calcul.setSymbole(typeOperation.getSymbole());
        String resultat="";
        switch(this.typeOperation){
            case ADD:
                resultat=String.valueOf(premierElement+deuxiemeElement);
                break;
            case DIVIDE:
                resultat=String.valueOf(premierElement/deuxiemeElement);
                break;
            case SUBSTRACT:
                resultat= String.valueOf(premierElement-deuxiemeElement);
                break;
            case MULTIPLY:
                resultat=String.valueOf(premierElement*deuxiemeElement);
                break;

        }
        calcul.setResultat(Integer.valueOf(resultat));
        calculDao.create(calcul);
        Toast.makeText(this,resultat,Toast.LENGTH_LONG).show();
        return true;
    }

    // FAIRE UN BOUTON CALCULER DANS LA TOOLBAR A COTÉ DE VIDER
    // QUAND J APPUIE SUR LE BOUTON
    //ÇA AFFICHE LE RESULTAT DANS UN TOAST

    private boolean vide() {
        premierElement=0;
        deuxiemeElement=0;
        typeOperation=null;
        calculText.setText("");
        return true;
    }
}