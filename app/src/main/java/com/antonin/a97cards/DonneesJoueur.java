package com.antonin.a97cards;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class DonneesJoueur {

    private int score;

    private String nom;

    private GregorianCalendar date;


    public DonneesJoueur(int score, String nom){

        this.score = score;
        this.date = new GregorianCalendar();
        this.nom = nom;

    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getNom() {
        return nom;
    }

    public String getDate() {
        String date1 = null;
        int year = this.date.get(Calendar.YEAR);
        int month = this.date.get(Calendar.MONTH) + 1;
        int day = this.date.get(Calendar.DAY_OF_MONTH);

        date1 = day + "/" + month + "/" + year;

        return date1;
    }

}
