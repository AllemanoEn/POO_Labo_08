package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.Case;

public class Dame extends PieceDeplacementStandard{
    public Dame(PlayerColor color) {
        super(color);
        pieceType = PieceType.QUEEN;
        distanceDeplacementMax = 10;
        directionType = DirectionType.Tous;
    }

    @Override
    public MouvementType mouvementPossible(Case src, Case dest) {
        if (super.mouvementPossible(src,dest) == MouvementType.NON_VALIDE){
            return MouvementType.NON_VALIDE;
        }

        return mouvementDirectionnelPossible(src, dest);
    }

    @Override
    public boolean deplacer(Case dest) {
        return false;
    }

    @Override
    public String toString() {
        return null;
    }
}
