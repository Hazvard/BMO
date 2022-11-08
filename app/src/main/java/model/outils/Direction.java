package model.outils;

public enum Direction {
    NORD("N"), SUD("S"), EST("E"), OUEST("W");

    private String abreviation ;

    private Direction(String abreviation) {
        this.abreviation = abreviation ;
    }

    public String getAbreviation() {
        return  this.abreviation ;
    }
}
