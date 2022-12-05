package model.outils;

import java.util.List;

public class PièceData {
    Boolean active;
    String nom;
    String murSud;
    String murNord;
    String murEst;
    String murOuest;


        @Override
        public String toString() {
            return "      \"active\":" + active + "\",\n" +
                    "      \"nom\": \"" + nom + "\",\n" +
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

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getMurSud() {
        return murSud;
    }

    public void setMurSud(String murSud) {
        this.murSud = murSud;
    }

    public String getMurNord() {
        return murNord;
    }

    public void setMurNord(String murNord) {
        this.murNord = murNord;
    }

    public String getMurEst() {
        return murEst;
    }

    public void setMurEst(String murEst) {
        this.murEst = murEst;
    }

    public String getMurOuest() {
        return murOuest;
    }

    public void setMurOuest(String murOuest) {
        this.murOuest = murOuest;
    }
}
