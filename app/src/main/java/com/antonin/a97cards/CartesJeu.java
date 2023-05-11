package com.antonin.a97cards;

import android.graphics.Color;
import android.util.Log;
import android.widget.TextView;

import java.util.Collection;
import java.util.Collections;
import java.util.Stack;
import java.util.Vector;

public class CartesJeu {

    private Vector<Cartes> cartesDuJeu;

    public CartesJeu(){
        this.cartesDuJeu = new Vector<>();
        initialiserAllCards();
    }

    public Cartes getCartesDuJeu(int index) {

        return cartesDuJeu.elementAt(index);
    }

    public  void initialiserAllCards() {
        // pour initier une premmiere carte, un peu comme le joker de notre liste de cartes :
        Cartes cartTemp = new Cartes(0,Color.GREEN);

        // on initialise les 97 cartes :
        for(int i = 1; i < 98; i ++){

            cartTemp = new Cartes(i, cartTemp.getCouleur());
            cartesDuJeu.add(cartTemp);

        }
        // on utilise la classe Collections qui permet de mélanger des Vectors
        Collections.shuffle(cartesDuJeu);
    }


    public int getSizeStack(){

        return cartesDuJeu.size();
    }


}


