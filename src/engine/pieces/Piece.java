package engine.pieces;

import chess.ChessView;
import engine.Case;

public abstract class Piece implements ChessView.UserChoice{
    public abstract boolean mouvementPossible(Case dest);
    public abstract boolean deplacer(Case dest);

    public abstract String toString();

    MouvementType mouvementType;

    @Override
    public String textValue() {
        return null;
    }
}
