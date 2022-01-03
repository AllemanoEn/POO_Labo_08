package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.Case;

public class Tour extends PiecePremierDeplacement {
    public Tour(PlayerColor color) {
        super(color);
        pieceType = PieceType.ROOK;
        directionType = DirectionType.DROIT;
        distanceDeplacementMax = 10;
    }

    @Override
    protected MouvementType mouvementPossible(Case src, Case dest) {
        if (super.mouvementPossible(src,dest) == MouvementType.NON_VALIDE){
            return MouvementType.NON_VALIDE;
        }
        return mouvementDirectionnelPossible(src, dest);
    }


    @Override
    public String toString() {
        return "Tour";
    }
}
