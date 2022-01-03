package engine;

import engine.pieces.Piece;
import engine.pieces.Pion;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Case {


    private final int x;
    private final int y;

    private Piece pieceCourante;
    private Pion pionFantome;

    Case(int x, int y){
        this.x = x;
        this.y = y;
    }

    public boolean estVide(){
        return (pieceCourante == null && pionFantome == null);
    }

    public Piece getPieceCourante() {return pieceCourante;}
    public Pion getPionFantome() {return pionFantome;}

    public void placerPiece(Piece piece){
        pieceCourante = piece;
    }
    public void placerPionFantome(Pion pion){
        pionFantome = pion;
    }

    public Piece supprimerPiece(){
        Piece p = pieceCourante;
        pieceCourante = null;
        return p;
    }

    public Pion supprimerPionFantome(){
        Pion p = pionFantome;
        pionFantome = null;
        return p;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
