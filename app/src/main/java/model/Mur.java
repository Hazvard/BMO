package model;

import android.graphics.Bitmap;
import model.outils.Direction;

import java.util.HashMap;

public class Mur {

    private HashMap<String,Porte> ensembleDesPortes;
    private String murURL;


    public Mur(String photo) {

        this.murURL = photo;
        ensembleDesPortes = new HashMap<>();
    }

    public Mur(){
        ensembleDesPortes = null;
        murURL = null;
    }

    public void ajouterPorte(Piece PieceSuivante){

    }
}
