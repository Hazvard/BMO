package model;

import android.graphics.Bitmap;
import android.icu.text.SimpleDateFormat;
import android.util.Log;
import model.outils.Direction;

import java.util.Date;
import java.util.HashMap;

public class Construction {
    private HashMap<String,Piece> ensembleDesPieces;
    private String id;

    public Construction(){
        ensembleDesPieces = new HashMap<>();
        Date maintenant = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("YYYYMMddHHmmss");
        id = "Construction"+ formatter.format(maintenant);
    }

    public Construction(String propriete){
        // Si on choisi d'ouvrir la construction active on construit celle en cours
        if(propriete.equals("active")){

        }
    }

    public void enregistreActive(Construction construction){

    }

    public void enregistrer(Construction construction){

    }

    public Piece ajouterPiece(){
        Date maintenant = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("YYYYMMddHHmmss");
        String idPiece = "Piece"+ formatter.format(maintenant);
        Piece piece = new Piece(idPiece);
        //ensembleDesPieces.put("idPiece", new Piece("idPiece"));
        return piece;
    }

    public void ajouterPiece(Piece piece){
        ensembleDesPieces.put(piece.getId(), piece);
    }

    public Piece getPiece(String id){
        return ensembleDesPieces.get(id);
    }

    public void ajouterMur(Piece piece, Bitmap photo, Direction direction){
        piece.ajouterMur(photo, direction);
        if(piece.complete()){
            enregistreActive(this);
            Log.i("ajouterMur", "la piece à 4 murs");
        }
    }






}
