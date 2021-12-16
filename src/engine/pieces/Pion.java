package engine.pieces;//Voici un test de header 

import engine.Case;

public class Pion extends PieceDeplacementStandard{
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
