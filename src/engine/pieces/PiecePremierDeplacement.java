package engine.pieces;

import chess.PlayerColor;
import engine.Case;

public abstract class PiecePremierDeplacement extends PieceDeplacementStandard{
    public boolean premierDeplacement = true;

    public PiecePremierDeplacement(PlayerColor color) {
        super(color);
    }

    @Override
    public MouvementType deplacer(Case src, Case dest) {
        MouvementType mouvementType = super.deplacer(src, dest);
        if(mouvementType == MouvementType.NON_VALIDE)
            return MouvementType.NON_VALIDE;
        premierDeplacement = false;
        return mouvementType;
    }
}
