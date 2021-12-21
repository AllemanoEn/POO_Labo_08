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
