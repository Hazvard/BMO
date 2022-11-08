package model.outils;


/*
    La classe n'est pour l'instant pas utilisée car l'id correspond à la date de la capture.
 */


public class FabriqueNumero {


    private int cptPorte;
    private int cptMur;
    private int cptPiece;
    private int cptConstruction;

    /**
     * permet de renvoyer une instance de fabrique numéros
     */
    private static FabriqueNumero instance = new FabriqueNumero();

    /**
     * renvoyer une instance de la fabrique
     * @return
     */
    public static FabriqueNumero getInstance(){
        return instance;
    }


    /**
     * Renvoie un numéro de porte et l'incrémente
     * @return cptPorte
     */
    public int getNumeroPorte(){
        return cptPorte ++;
    }

    /**
     * Renvoie un numéro de mur et l'incrémente
     * @return cptMur
     */
    public int getNumeroMur(){
        return cptMur ++;
    }

    /**
     * Renvoie un numéro de Piece et l'incrémente
     * @return cptSemaphore
     */
    public int getNumeroPiece(){
        return cptPiece ++;
    }

    /**
     * Renvoie un numéro de Construction et l'incrémente
     * @return cptConstruction
     */
    public int getNumeroConstruction(){
        return cptConstruction ++;
    }




    /**
     * permet de reset les compteurs
     */
    public  void reset(){
        cptPiece = 0;
        cptPorte = 0;
        cptConstruction = 0;
        cptMur = 0;
    }
}
