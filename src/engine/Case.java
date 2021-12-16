package engine;//Voici un test de header 

import engine.pieces.Piece;
import engine.pieces.Pion;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Case {
    private int x;
    private int y;

    private Piece pieceCourante;
    private Pion pionFantome;

    public boolean estVide(){
        return true;
    }

    public void placerPiece(Piece piece){

    }

    public Piece supprimerPiece(){
        throw new NotImplementedException();
    }

}
