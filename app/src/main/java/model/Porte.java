package model;

public class Porte {

    private String id;
    private String idPieceSuivante;

    public Porte(String id, String idPieceSuivante) {
        this.id = id;
        this.idPieceSuivante = idPieceSuivante;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
