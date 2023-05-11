package com.antonin.a97cards;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class SauvegardeDonnees extends AppCompatActivity {

    private Intent intent;
    private ListView listJoueurs;
    private Button buttonMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sauvegarde);

        buttonMenu = findViewById(R.id.retourMenu);
        listJoueurs = findViewById(R.id.listJoueurs);

        GestionDB instance = GestionDB.getInstance(this);

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, instance.retournerScore());
        listJoueurs.setAdapter(arrayAdapter);

        buttonMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(SauvegardeDonnees.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}