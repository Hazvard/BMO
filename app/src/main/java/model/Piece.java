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

    private Boolean active;

    public Piece(String id){

        murEst = null;
        murSud = null;
        murNord  = null;
        murOuest  = null;
        active = false;

        this.id = id;

    }

    public Piece(){
        Date maintenant = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("YYYYMMddHHmmss");
        String idPiece = "Piece"+ formatter.format(maintenant);
    }

    public Piece(Boolean active, String id, String murOuest, String murEst, String murNord, String murSud){
        this.id = id;
        this.murEst = new Mur(murEst);
        this.murSud = new Mur(murSud);
        this.murNord = new Mur(murNord);
        this.murOuest = new Mur(murOuest);
        this.active = active;
    }

    public void ajouterMur(String photo, Direction direction){
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

    @Override
    public String toString() {
        return "      \"active\":" + active + "\",\n" +
                "      \"nom\": \"" + id + "\",\n" +
                "      \"murSud\": \""+ murSud +"\",\n" +
                "      \"murNord\":\"" + murNord + "\" ,\n" +
                "      \"murEst\": \"" + murEst + "\" ,\n" +
                "      \"murOuest\": \"" + murOuest  +"\"";
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Mur getMurSud() {
        return murSud;
    }

    public Mur getMurEst() {
        return murEst;
    }

    public Mur getMurOuest() {
        return murOuest;
    }

    public Mur getMurNord() {
        return murNord;
    }
}
