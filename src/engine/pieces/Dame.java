package engine.pieces;

import engine.Case;

public class Dame extends PieceDeplacementStandard{
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
