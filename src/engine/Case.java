package engine;//Voici un test de header 

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
        return pieceCourante == null;
    }

    public void placerPiece(Piece piece){
        pieceCourante = piece;
    }

    public Piece supprimerPiece(){
        throw new NotImplementedException();
    }

}
