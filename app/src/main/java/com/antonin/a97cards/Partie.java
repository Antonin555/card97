package com.antonin.a97cards;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.Vector;

public class Partie extends AppCompatActivity {

    Chronometer chronometer;

    private LinearLayout linearMoins;
    private LinearLayout linearPlus;
    private TextView pileHautGauche, pileHautDroite ,pileMoinsGauche ,pileMoinsDroite;
    private TextView carte1,carte2,carte3,carte4,carte5,carte6,carte7,carte8;
    private CartesJeu cartesDuJeu;
    private TextView nbreCartes, scoreActivity;
    private Ecouteur ec;
    private TextView carteTemp;
    private Button menu, stop;
    private MainJoeur mainJoeur;
    private Vector<Pile> piles;
    private Vector <TextView> pilesTextView;
    private Vector <TextView> cartesTextView;
    private VerificationJeu verifJeu;
    private DonneesJoueur donneesJoueur;
    private Vector<Integer> tabID;
    private int nbreCarteMain = 8;
    private int compteurCartes = 0;
    private int carteTotalPile = 97;
    private int deltaTime1;
    private int deltaTime2;
    private int score;
    private Intent intent;

    private boolean show;

    private FinDeJeu finDeJeu;

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.partie);
        cartesDuJeu = new CartesJeu();
        piles = new Vector<>();
        cartesTextView = new Vector<>();
        pilesTextView = new Vector<>();
        verifJeu = new VerificationJeu();
        tabID = new Vector<>();
        score = 0;
        donneesJoueur = new DonneesJoueur(score, "");
        ec = new Ecouteur();
        carteTemp = new TextView(this);
        finDeJeu = new FinDeJeu();
        nbreCartes = findViewById(R.id.nbreCartes);
        mainJoeur = new MainJoeur(cartesDuJeu);
        linearPlus = findViewById(R.id.LinearDesPlus);
        linearMoins = findViewById(R.id.LinearDesMoins);
        menu = findViewById(R.id.menu);
        stop = findViewById(R.id.fin);


        scoreActivity = findViewById(R.id.scoreActivity);
        chronometer = findViewById(R.id.simpleChronometer);

        pileHautGauche = findViewById(R.id.pile_0);
        pileHautDroite= findViewById(R.id.pile_1);
        pileMoinsGauche= findViewById(R.id.pile_2);
        pileMoinsDroite= findViewById(R.id.pile_3);

        pilesTextView.add(pileHautGauche);
        pilesTextView.add(pileHautDroite);
        pilesTextView.add(pileMoinsGauche);
        pilesTextView.add(pileMoinsDroite);

        piles.add(new Pile(0));
        piles.add(new Pile(1));
        piles.add(new Pile(2));
        piles.add(new Pile(3));

        pilesTextView.get(0).setText(String.valueOf(piles.get(0).getCurrentValeur()));
        pilesTextView.get(1).setText(String.valueOf(piles.get(1).getCurrentValeur()));
        pilesTextView.get(2).setText(String.valueOf(piles.get(2).getCurrentValeur()));
        pilesTextView.get(3).setText(String.valueOf(piles.get(3).getCurrentValeur()));

        carte1 = findViewById(R.id.carte1);
        carte2 = findViewById(R.id.carte2);
        carte3 = findViewById(R.id.carte3);
        carte4 = findViewById(R.id.carte4);
        carte5 = findViewById(R.id.carte5);
        carte6 = findViewById(R.id.carte6);
        carte7 = findViewById(R.id.carte7);
        carte8 = findViewById(R.id.carte8);

        cartesTextView.add(carte1);
        cartesTextView.add(carte2);
        cartesTextView.add(carte3);
        cartesTextView.add(carte4);
        cartesTextView.add(carte5);
        cartesTextView.add(carte6);
        cartesTextView.add(carte7);
        cartesTextView.add(carte8);

        menu.setOnClickListener(ec);
        stop.setOnClickListener(ec);
        mainJoeur.initialiserMainCards(8);

        for(int i = 0; i < cartesTextView.size(); i++){
            cartesTextView.get(i).setBackground(this.getResources().getDrawable(cartesDuJeu.getCartesDuJeu(i).getCouleur()));
        }

        for(int i =0; i < cartesTextView.size(); i++){
            cartesTextView.get(i).setText(String.valueOf(mainJoeur.getCartesMain().get(i).getNumero())); // ici on pourrait remplacer par Mainjoueur a la place de cartedujeu
            cartesTextView.get(i).setOnTouchListener(ec);
            cartesTextView.get(i).setOnDragListener(ec);
            compteurCartes++;

        }

        for(int i = 0; i < linearPlus.getChildCount();i++ ){
            if(linearPlus.getChildAt(i) instanceof ConstraintLayout) {
                linearPlus.getChildAt(i).setOnDragListener(ec);
            }
        }
        for(int i = 0; i < linearMoins.getChildCount();i++ ){
            if(linearPlus.getChildAt(i) instanceof ConstraintLayout)
            linearMoins.getChildAt(i).setOnDragListener(ec);
        }

        chronometer.start();
        scoreActivity.setText(String.valueOf(donneesJoueur.getScore()));

    }

    private class Ecouteur implements View.OnTouchListener, View.OnDragListener, View.OnClickListener {

        @Override
        public boolean onDrag(View view, DragEvent dragEvent) { // conteneurs qui vont recevoir ce qu'on a bougé

            switch (dragEvent.getAction()){

                case DragEvent.ACTION_DRAG_ENTERED:
                    Log.i("DRAG ENTERED", "  Je suis passé ici DRAG ENTERED");

                    // on récupere la différence de temps entre l'horloge du Systeme et celle du chrono, en millisecondes.
                    deltaTime1 = (int) (SystemClock.elapsedRealtime() - chronometer.getBase());
                    break;
                case DragEvent.ACTION_DRAG_EXITED:

                    Log.i("DRAG EXITED", "  Je suis passé ici DRAG EXITED");
                    break;

                case DragEvent.ACTION_DROP:

                    View view1 = (TextView) dragEvent.getLocalState();
                    TextView dropped = (TextView) view1;
                    int idCarte = dropped.getId();
                    int valeurCarte = Integer.parseInt(dropped.getText().toString());

                    ConstraintLayout constraint = (ConstraintLayout) view;

                    TextView droptarget = (TextView) constraint.getChildAt(0);

                    int tagConstraint = Integer.parseInt( constraint.getTag().toString());// permet d'avoir le tag de la constraint d'arrivée


                    int valeurPile= piles.get(tagConstraint).setCurrentValeur(Integer.parseInt(pilesTextView.get(tagConstraint).getText().toString()));
                    Log.i("VALEUR PILLS : ", " voici la valeur : " + piles.get(tagConstraint).getCurrentValeur() + "   " + tagConstraint);

//                    int valeurPile = Integer.parseInt(pilesTextView.get(tagConstraint).getText().toString());

                    if(constraint.getId() == R.id.LinearPlusGauche || constraint.getId() == R.id.LinearPlusDroite){

                        if(verifJeu.verifierHaut(valeurCarte,valeurPile)){
                            verifJeu.ajouterCartePile(droptarget,dropped,idCarte,tabID);
                            nbreCarteMain --;
                            carteTotalPile --;
                            score += calculerScore();
                            donneesJoueur.setScore(score);
                            scoreActivity.setText(String.valueOf(score));
                        } else
                            dropped.setVisibility(View.VISIBLE);

                    }else if(constraint.getId() == R.id.LinearMoinsGauche || constraint.getId() == R.id.LinearMoinsDroite ){

                        if(verifJeu.verifierBas(valeurCarte,valeurPile)) {
                            verifJeu.ajouterCartePile(droptarget,dropped,idCarte,tabID);
                            nbreCarteMain --;
                            carteTotalPile --;
                            score += calculerScore();
                            donneesJoueur.setScore(score);
                            scoreActivity.setText(String.valueOf(score));
                        } else
                                dropped.setVisibility(View.VISIBLE);

                    }// else
//                        view1.setVisibility(View.VISIBLE);

                    nbreCartes.setText(String.valueOf(carteTotalPile));
                    if(nbreCarteMain == 6) {

                        for (int i = 0; i < tabID.size(); i++) {

                            carteTemp = findViewById(tabID.get(i));
                            carteTemp.setText(String.valueOf(cartesDuJeu.getCartesDuJeu(compteurCartes).getNumero()));
                            carteTemp.setBackground(Partie.this.getResources().getDrawable(cartesDuJeu.getCartesDuJeu(compteurCartes).getCouleur()));////////////////////// a voir
//                            cartesTextView.get(carteTemp.getId()).setText(String.valueOf(carteTemp));
                            carteTemp.setVisibility(View.VISIBLE);
                            compteurCartes++;
                            nbreCarteMain++;

                        }
                        tabID.clear();


                    }

                    // On verifie a la fin que l'on peut encore jouer :

                    boolean valide = verifJeu.verifierFinDeJeu(cartesTextView,pilesTextView);
                    if(!valide || carteTotalPile == 0){

                        finDeJeu();
                    }

                    Log.i("DRAG DROP", "  Je suis passé ici DANS DROP ");

                    break;

                case DragEvent.ACTION_DRAG_ENDED:
//                    if (!show){
//                        show= true;
//
//
//                    }
                    Log.i("DRAG ENDED ", "  Je suis passé ici DANS ENDED + verif FIN DE JEU");

                    // on récupere la différence de temps entre l'horloge du Systeme et celle du chrono, en millisecondes.
                    deltaTime2 = (int) (SystemClock.elapsedRealtime() - chronometer.getBase());

                    break;
            }
            return true;
        }

        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {

            show = false;
            if(motionEvent.getAction() == 0){ // 0 pour le DOWN
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
            int sdkVersion = Build.VERSION.SDK_INT;
            if(sdkVersion <= 24) {

                view.startDrag(null,shadowBuilder,view,0);
            }else
                view.startDragAndDrop(null,shadowBuilder,view,0);

            view.setVisibility(View.INVISIBLE);
            }
            return true;
        }

        @Override
        public void onClick(View view) {
            if(view == menu){
                intent = new Intent(Partie.this, MainActivity.class);
                startActivity(intent);

            }else if (view == stop)
                finish();
        }
    }

    public int calculerScore(){


        int deltaResultat = deltaTime1 - deltaTime2;
        int scoreDeBase= 10;
        int scoreBonus1;
        int resultatFinal = 0;
        if (deltaResultat < 1000) {
             scoreBonus1 = deltaResultat * 20;

        } else if (deltaResultat > 1000 && deltaResultat < 3000){

              scoreBonus1 = deltaResultat / 2;

        } else if (deltaResultat > 3000 && deltaResultat < 5000) {
            scoreBonus1 = deltaResultat / 3;

        } else if(deltaResultat > 5000 && deltaResultat < 10000){
            scoreBonus1 = deltaResultat / 5;

        }else
            scoreBonus1 = 0;

        resultatFinal += (scoreDeBase + scoreBonus1);

        return resultatFinal;

    }

    public void finDeJeu(){

        chronometer.stop();
        intent = new Intent(Partie.this, FinDeJeu.class); //   pour aller dans l'autre activité
        // avec l'intent on récupère les valeurs des attributs qui nous intéressent, ici le score et le nombre de cartes restantes pour les transposer vers l'autre activité
        // Cela marche comme une hashTable, a savoir une clef et une valeur.
        intent.putExtra("score", score);
        intent.putExtra("nombreCartes", carteTotalPile);
        startActivity(intent);

    }


}