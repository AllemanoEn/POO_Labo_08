package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.Case;

public class Dame extends PieceDeplacementStandard{
    public Dame(PlayerColor color) {
        super(color);
        pieceType = PieceType.QUEEN;
        distanceDeplacementMax = 10;
        directionType = DirectionType.TOUS;
    }

    @Override
    public MouvementType mouvementPossible(Case src, Case dest) {
        if (super.mouvementPossible(src,dest) == MouvementType.NON_VALIDE){
            return MouvementType.NON_VALIDE;
        }

        return super.mouvementPossible(src, dest);
    }

    @Override
    public Piece clone() {
        Dame p = new Dame(this.color);
        return p;
    }

    @Override
    public String toString() {
        return "Dame";
    }
}
