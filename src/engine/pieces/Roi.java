package engine.pieces;//Voici un test de header 

import chess.PieceType;
import chess.PlayerColor;
import engine.Case;

public class Roi extends PieceRoquable{
    public Roi(PlayerColor color) {
        super(color);
        pieceType = PieceType.KING;
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
