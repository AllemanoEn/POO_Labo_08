package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.Case;

public class Fou extends PieceDeplacementStandard{
    public Fou(PlayerColor color) {
        super(color);
        pieceType = PieceType.BISHOP;
        distanceDeplacementMax = 10;
        directionType = DirectionType.DIAGONALE;
    }

    @Override
    public MouvementType mouvementPossible(Case src, Case dest) {
        if (super.mouvementPossible(src,dest) == MouvementType.NON_VALIDE){
            return MouvementType.NON_VALIDE;
        }

        return mouvementDirectionnelPossible(src, dest);
    }


    @Override
    public Piece clone() {
        Fou p = new Fou(this.color);
        return p;
    }

    @Override
    public String toString() {
        return "Fou";
    }
}
