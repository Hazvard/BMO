package model;

import android.graphics.Bitmap;
import model.outils.Direction;

import java.util.HashMap;

public class Mur {

    private HashMap<String,Porte> ensembleDesPortes;
    private Bitmap vueDuMur;


    public Mur(Bitmap photo) {

        this.vueDuMur = photo;
        ensembleDesPortes = new HashMap<>();
    }

    public Mur(){
        ensembleDesPortes = null;
        vueDuMur = null;
    }

    public void ajouterPorte(Piece PieceSuivante){

    }
}
