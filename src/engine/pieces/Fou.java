package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.Case;

public class Fou extends PieceDeplacementStandard{
    public Fou(PlayerColor color) {
        super(color);
        pieceType = PieceType.BISHOP;
        distanceDeplacementMax = 10;
    }

    @Override
    public MouvementType mouvementPossible(Case src, Case dest) {
        return MouvementType.NON_VALIDE;
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
