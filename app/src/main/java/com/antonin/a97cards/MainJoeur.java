package com.antonin.a97cards;

import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Vector;

public class MainJoeur {

    private Vector <Cartes> cartesMain;
    private CartesJeu cartesJeu;

    private static int compt =8;


    public MainJoeur(CartesJeu cartesJeu) {

        this.cartesJeu = cartesJeu;
        this.cartesMain = new Vector<>();

    }


    public void initialiserMainCards(int nombre) {

        for(int i = 0; i < nombre; i++){
            this.cartesMain.add(this.cartesJeu.getCartesDuJeu(i));
        }
    }

    public int getSizeMainJoueur(){

        return this.cartesMain.size();

    }

    public Vector<Cartes> getCartesMain() {
        return this.cartesMain;
    }

    public  void setCartesMain(Vector<Cartes> cartesMain) {
        cartesMain = cartesMain;
    }








}












