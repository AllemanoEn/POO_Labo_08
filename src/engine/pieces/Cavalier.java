package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.Case;

public class Cavalier extends Piece{
    public Cavalier(PlayerColor color) {
        super(color);
        pieceType= PieceType.KNIGHT;
    }

    @Override
    protected MouvementType mouvementPossible(Case src, Case dest) {
        if (super.mouvementPossible(src,dest) == MouvementType.NON_VALIDE){
            return MouvementType.NON_VALIDE;
        }

        int deltaX = Math.abs(src.getX() - dest.getX());
        int deltaY = Math.abs(src.getY() - dest.getY());

        if((deltaX == 1 && deltaY == 2) || (deltaX == 2 && deltaY == 1)){

            return MouvementType.CLASSIQUE;
        }
        return MouvementType.NON_VALIDE;
    }


    @Override
    public String toString() {
        return "Cavalier";
    }
}
