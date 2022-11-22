package model;

import android.graphics.Bitmap;
import android.icu.text.SimpleDateFormat;
import model.outils.Direction;

import java.util.Date;
import java.util.HashMap;

public class Piece {

    private Mur murSud;
    private Mur murEst;
    private Mur murOuest;
    private Mur murNord;
    private String id;

    public Piece(String id){

        murEst = null;
        murSud = null;
        murNord  = null;
        murOuest  = null;

        this.id = id;

    }

    public Piece(){
        Date maintenant = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("YYYYMMddHHmmss");
        String idPiece = "Piece"+ formatter.format(maintenant);
    }

    public void ajouterMur(Bitmap photo, Direction direction){
        if(direction == Direction.EST)
            murEst = new Mur(photo);
        if(direction == Direction.OUEST)
            murOuest = new Mur(photo);
        if(direction == Direction.SUD)
            murSud = new Mur(photo);
        if(direction == Direction.NORD)
            murNord = new Mur(photo);
    }

    public Mur getMur(Direction direction) {
        if(direction == Direction.EST)
            return murEst;
        if(direction == Direction.OUEST)
            return murOuest;
        if(direction == Direction.SUD)
            return murSud ;
        if(direction == Direction.NORD)
            return murNord;

        return null;
    }

    public Boolean complete(){
        return murNord != null && murEst != null && murSud != null && murOuest != null ;
    }

    public String getId() {
        return id;
    }
}
