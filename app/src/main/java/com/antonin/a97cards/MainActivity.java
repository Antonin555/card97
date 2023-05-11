package com.antonin.a97cards;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

 // faire une classe carte, piles, la main, Partie, DB
    // shuffle methode pour les cartes



    private Button play;
    private Button continuer;
    private Button menuScore;

    private Intent intent;
    private GestionDB gestionDB;

    private Ecouteur ec;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);


        play = findViewById(R.id.play);
        continuer = findViewById(R.id.continuer);
        menuScore = findViewById(R.id.menuScore);
        ec = new Ecouteur();
        gestionDB = GestionDB.getInstance(this);
        play.setOnClickListener(ec);
        continuer.setOnClickListener(ec);
        menuScore.setOnClickListener(ec);

    }

    private class Ecouteur implements View.OnClickListener{


        @Override
        public void onClick(View view) {

            if(view == play){
                intent = new Intent(MainActivity.this, Partie.class);
                startActivity(intent);
            }else if (view == continuer) {
                intent = new Intent(MainActivity.this,Partie.class);
                startActivity(intent);

            }else if (view == menuScore){
                intent = new Intent(MainActivity.this, SauvegardeDonnees.class);
                startActivity(intent);
            }
        }
    }



}