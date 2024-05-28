package com.example.calculatriceg1java;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.calculatriceg1java.Entities.Calcul;
import com.example.calculatriceg1java.database.CalculBaseHelper;
import com.example.calculatriceg1java.database.CalculDao;

public class HistoriqueActivity extends AppCompatActivity {

CalculDao calculDao;

private TextView textViewDernierCalcul;
private TextView textViewNombreCalcul;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_historique);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        textViewDernierCalcul = findViewById(R.id.textview_dernier_calcul);
        textViewNombreCalcul = findViewById(R.id.textview_nombre_calcul);
        calculDao = new CalculDao(new CalculBaseHelper(this,"db_alt",null,1));
        long nombreCalcul = calculDao.count();
        Calcul dernierCalcul = calculDao.lastOrNull();
        textViewNombreCalcul.setText(String.format(getString(R.string.TEXT_NOMBRE_CALCUL),nombreCalcul));
        if(dernierCalcul!=null){
            String maChaine = dernierCalcul.getPremierElement()+" "+
                    dernierCalcul.getSymbole()+" "+
                    dernierCalcul.getDeuxiemeElement()+ " "+
                    dernierCalcul.getResultat();
            textViewDernierCalcul.setText(String.format(getString(R.string.TEXT_DERNIER_CALCUL),maChaine));
        }
    }
}