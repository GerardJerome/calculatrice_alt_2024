package com.example.calculatriceg1java;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.calculatriceg1java.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button boutonCalculatrice;
    private TextView textviewCalcul;
    private Button boutonHistorique;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        boutonCalculatrice = findViewById(R.id.button_calculatrice);
        textviewCalcul = findViewById(R.id.textview_calcul);
        boutonHistorique = findViewById(R.id.bouton_historique);
        boutonCalculatrice.setOnClickListener(view -> {
            Intent intent = new Intent(this,CalculActivity.class);
            startActivity(intent);
            textviewCalcul.setText("jean pierre");
            Toast.makeText(this,"j'affiche un toast",Toast.LENGTH_LONG).show();
        });
        boutonHistorique.setOnClickListener(view -> {
            Intent intent = new Intent(this, HistoriqueActivity.class);
            startActivity(intent);
        });
    }




}