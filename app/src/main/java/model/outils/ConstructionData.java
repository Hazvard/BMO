package model.outils;

import model.Construction;
import model.Piece;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class ConstructionData {
        private String nom;
        private int nbpieces;
        private ArrayList<Piece> pieces;


        public ConstructionData(Construction construction){
            nom = construction.getId();
            nbpieces = construction.getNbPieces();
            pieces = (ArrayList<Piece>) construction.getPieces().values()
                    .stream()
                    .collect(Collectors.toList());
        }

        public String getnom() {
            return nom;
        }

        public void setnom(String nom) {
            this.nom = nom;
        }

    public int getNbpieces() {
        return nbpieces;
    }

    public void setNbpieces(int nbpieces) {
        this.nbpieces = nbpieces;
    }


    @Override
    public String toString() {
            StringBuilder buildpieces = new StringBuilder();
            Iterator<Piece> iter = pieces.iterator();
            if(iter.hasNext())
                buildpieces.append("{ \n" + iter.next());
            while (iter.hasNext()) {
                buildpieces.append("\n} , {\n" + iter.next());
            }
            buildpieces.append("\n}");



            return  "{\n" +
                "\"nom\":\"" + nom + "\",\n" +
                "\"nbpieces\":\"" + nbpieces + "\",\n" +
                "\"pieces\": [\n" +
                buildpieces +
                "  ]\n" +
                "}";
    }

    public ArrayList<Piece> getPieces() {
        return pieces;
    }

    public void setPieces(ArrayList<Piece> pieces) {
        this.pieces = pieces;
    }
}