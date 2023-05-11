package com.antonin.a97cards;

import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import org.w3c.dom.Text;

import java.util.Stack;
import java.util.Vector;

public class VerificationJeu {

    public VerificationJeu() {

    }


    public boolean verifierHaut(int newValue, int oldValue ){

        if(newValue >= oldValue || newValue == oldValue - 10 )
            return true;
        return false;
    }

    public boolean verifierBas(int newValue, int oldValue ){

        if(newValue <= oldValue || newValue == oldValue + 10 )
            return true;
        return false;
    }


    public boolean verifierFinDeJeu(Vector<TextView> cartesTextView, Vector<TextView> pilesTextView){
        boolean valide = true;
        int carteText;
        int cartePile1;
        int cartePile2;
        int cartePile3;
        int cartePile4;

        for(int i =0; i < cartesTextView.size();i++) {

            carteText = Integer.parseInt(cartesTextView.get(i).getText().toString());
            cartePile1 = Integer.parseInt(pilesTextView.get(0).getText().toString());
            cartePile2 = Integer.parseInt(pilesTextView.get(1).getText().toString());
            cartePile3 = Integer.parseInt(pilesTextView.get(2).getText().toString());
            cartePile4 = Integer.parseInt(pilesTextView.get(3).getText().toString());
            if(cartesTextView.get(i).getText() != null){
                if((carteText > cartePile1) || (carteText > cartePile1 - 10))
                    valide = true;
                else if ((carteText > cartePile2) || (carteText > cartePile2 - 10))
                    valide = true;
                else if ((carteText < cartePile3) || (carteText < cartePile3 + 10))
                    valide = true;
                else if ((carteText < cartePile4) || (carteText < cartePile4 + 10))
                    valide = true;
                else
                    valide = false;


            } else
                valide = true;


//            if ((Integer.parseInt(cartesTextView.get(i).getText().toString()) > Integer.parseInt(pilesTextView.get(0).getText().toString()))
//                    || (Integer.parseInt(cartesTextView.get(i).getText().toString()) > Integer.parseInt(pilesTextView.get(0).getText().toString()) - 10))
//                valide = true;
//            else if ((Integer.parseInt(cartesTextView.get(i).getText().toString()) > Integer.parseInt(pilesTextView.get(1).getText().toString()) )
//                    || (Integer.parseInt(cartesTextView.get(i).getText().toString()) > Integer.parseInt(pilesTextView.get(1).getText().toString()) - 10))
//                valide = true;
//            else if ((Integer.parseInt(cartesTextView.get(i).getText().toString()) < Integer.parseInt(pilesTextView.get(2).getText().toString()) )
//                    ||( Integer.parseInt(cartesTextView.get(i).getText().toString())< Integer.parseInt(pilesTextView.get(2).getText().toString()) + 10))
//                valide = true;
//            else if( (Integer.parseInt(cartesTextView.get(i).getText().toString()) < Integer.parseInt(pilesTextView.get(3).getText().toString()) )
//                    || (Integer.parseInt(cartesTextView.get(i).getText().toString()) < Integer.parseInt(pilesTextView.get(3).getText().toString()) + 10))
//                valide = true;
//            else
//                valide = false;

        }
        return valide;

    }


    public void ajouterCartePile(TextView cartePile, TextView carteMain, int iD, Vector<Integer> tabID){

        cartePile.setText(carteMain.getText());
        cartePile.setBackground(carteMain.getBackground());
        LinearLayout.LayoutParams params =(LinearLayout.LayoutParams) carteMain.getLayoutParams();
        cartePile.setLayoutParams(params);
        tabID.add(iD);


    }






}

