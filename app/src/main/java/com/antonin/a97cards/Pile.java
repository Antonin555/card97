package com.antonin.a97cards;

import java.util.Stack;
import java.util.Vector;

public class Pile {

    private int currentValeur;
    private int sens;

    public Pile (int sens) {


        this.sens = sens;
        this.currentValeur = iniatiliserValeur();

    }

    public int iniatiliserValeur() {
        if(sens == 0 || sens == 1){
            currentValeur = 0;
        }else if (sens == 2 || sens == 3)
            currentValeur = 97;
        return currentValeur;
    }

    public int getCurrentValeur() {
        return currentValeur;
    }

    public int setCurrentValeur(int currentValeur) {
        this.currentValeur = currentValeur;
        return currentValeur;
    }


}
