package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.Case;

public class Pion extends PieceDeplacementStandard{
    public Pion(PlayerColor color) {
        super(color);
        pieceType = PieceType.PAWN;
    }

    @Override
    public boolean mouvementPossible(Case dest) {
        return false;
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
