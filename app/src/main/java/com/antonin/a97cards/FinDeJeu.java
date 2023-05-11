package com.antonin.a97cards;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class FinDeJeu extends AppCompatActivity{

    private Button buttonOk;
    private Ecouteur ec;
    private Intent intent;
    private TextView textNom, textScore, nbreCartesRestantes;
    private String nomDuJoueur;
    private GestionDB gestionDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fin_de_jeu);
        ec = new Ecouteur();

        buttonOk = findViewById(R.id.buttonOk);
        textNom = findViewById(R.id.textNom);
        textScore = findViewById(R.id.votreScore);
        nbreCartesRestantes = findViewById(R.id.nbreCartesRestantes);
        buttonOk.setOnClickListener(ec);
        nomDuJoueur = null;
        gestionDB = GestionDB.getInstance(this);

        // On recupere les valeurs des intent de l'activité Partie :
        int score = (int) getIntent().getSerializableExtra("score");
        int nbreCartes = (int) getIntent().getSerializableExtra("nombreCartes");
        textScore.setText(String.valueOf(score));
        nbreCartesRestantes.setText(String.valueOf(nbreCartes));


    }

    private class Ecouteur implements View.OnClickListener{


        @Override
        public void onClick(View view) {

            nomDuJoueur = textNom.getText().toString();

            if(nomDuJoueur != null){

                gestionDB.ajouterScore(new DonneesJoueur(Integer.parseInt(textScore.getText().toString()),nomDuJoueur));
                intent = new Intent(FinDeJeu.this, MainActivity.class);
                startActivity(intent);
                
            } else
                Toast.makeText(getApplicationContext(), "Veuillez d'abord entrer un pseudo avant de valider", Toast.LENGTH_LONG).show();

        }
    }
}