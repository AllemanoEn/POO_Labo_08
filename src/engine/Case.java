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
        return true;
    }

    public void placerPiece(Piece piece){

    }

    public Piece supprimerPiece(){
        throw new NotImplementedException();
    }

}
