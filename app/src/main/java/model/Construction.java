package model;

import android.icu.text.SimpleDateFormat;

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

    public void ajouterPiece(){
        Date maintenant = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("YYYYMMddHHmmss");
        String idPiece = "Piece"+ formatter.format(maintenant);
        ensembleDesPieces.put(idPiece, new Piece(idPiece));
    }

    public void ajouterPiece(Piece piece){
        ensembleDesPieces.put(piece.getId(), piece);
    }

    public Piece getPiece(String id){
        return ensembleDesPieces.get(id);
    }

}
