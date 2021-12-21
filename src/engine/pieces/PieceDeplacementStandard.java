package engine.pieces;

import chess.PlayerColor;
import engine.Case;

public abstract class PieceDeplacementStandard extends Piece{
    int distanceDeplacementMax;

    public PieceDeplacementStandard(PlayerColor color) {
        super(color);
    }

    @Override
    public MouvementType mouvementPossible(Case src, Case dest) {

        if (super.mouvementPossible(src, dest) == MouvementType.NON_VALIDE){
            return MouvementType.NON_VALIDE;
        }

        return MouvementType.CLASSIC;
    }
}
