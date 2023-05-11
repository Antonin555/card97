package com.antonin.a97cards;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Vector;

public class GestionDB extends SQLiteOpenHelper {

    //instance unique de la classe Singleton GestionBD
    private static GestionDB instance;

    private SQLiteDatabase database; // on cree un objet pour ouvrir la db


    // méthode de base pour un Singleton
    public static GestionDB getInstance( Context contexte )
    {
        if ( instance == null )
            instance = new GestionDB(contexte);
        return instance;
    }


    // le constructeur du singleton, il est private pour pas etre appeler de l'exterieur, on s'assure de ne pas creer d'objets
    private GestionDB( Context context) {
        super(context, "db", null, 1);
        // je me connecte a la BD tout de suite :

        ouvrirDB();
    }

    // appeler automatiquement par android, si db n'existe pas il la créee
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE scoreTable (_id INTEGER PRIMARY KEY AUTOINCREMENT, nom TEXT, score INTEGER, date TEXT)");



    }

    public void ajouterScore(DonneesJoueur s) {

        ContentValues cv = new ContentValues();
        cv.put("nom", s.getNom());
        cv.put("score", s.getScore());
        cv.put("date", s.getDate());

        database.insert("scoreTable", null, cv );

    }

    // Si la version est différente, il va l'update
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists scoreTable");
        onCreate(db);
    }


    public void ouvrirDB() {

        database = this.getWritableDatabase(); // par defaut c'est mieux de mettre getWrite et getRead - on pourra a la fois lire et ecrire

    }


    public void fermerDB() {

        database.close(); // toujours la fermer quand on quitte l,activité.
    }

    public void LireDB(){

        database = this.getReadableDatabase();
    }




    public Vector<String> retournerScore(){

        Cursor c = database.rawQuery("SELECT nom,score,date FROM scoreTable ORDER BY score DESC LIMIT 10",null);
        int i = 1;
        Vector <String> vectorResult = new Vector<>();

        while(c.moveToNext()) {

            vectorResult.add(i  + " - " + c.getString(0) + " - " + c.getString(1) + " points - " + c.getString(2));
            i++;

        }
        return vectorResult;
    }
}





