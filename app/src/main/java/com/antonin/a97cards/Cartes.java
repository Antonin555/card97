package com.antonin.a97cards;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Cartes  {


    private int numero;
    private int couleur;
    private boolean carteSortie;


    public Cartes(int numero, int couleur ){

        this.numero = numero;
        this.couleur = couleur ;
        this.carteSortie = false;
    }

    public int getNumero() {
        return numero;
    }

    public int getCouleur() {

        if(this.numero < 10)
            couleur = R.drawable.back_couleur_10;
        else if (this.numero >= 10 && this.numero < 20)
            couleur = R.drawable.back_couleur_20;
        else if (this.numero >= 20 && this.numero < 30)
            couleur = R.drawable.back_couleur_30;
        else if (this.numero >= 30 && this.numero < 40)
            couleur = R.drawable.back_couleur_40;
        else if (this.numero >=40 && this.numero < 50)
            couleur = R.drawable.back_couleur_50;
        else if (this.numero >=50 && this.numero < 60)
            couleur = R.drawable.back_couleur_60;
        else if (this.numero >=60 && this.numero < 70)
            couleur = R.drawable.back_couleur_70;
        else if (this.numero >=70 && this.numero < 80)
            couleur = R.drawable.back_couleur_80;
        else if (this.numero >=80 && this.numero < 90)
            couleur = R.drawable.back_couleur_90;
        else if (this.numero >=90 && this.numero < 97)
            couleur = R.drawable.back_couleur_97;


        return couleur;
    }

    public boolean isCarteSortie() {
        return carteSortie;
    }

    public boolean setCarteSortie(boolean carteSortie) {
       return this.carteSortie = carteSortie;
    }

}
