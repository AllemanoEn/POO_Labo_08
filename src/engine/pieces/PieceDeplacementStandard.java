package engine.pieces;

import chess.PlayerColor;
import engine.Case;

public abstract class PieceDeplacementStandard extends Piece{
    int distanceDeplacementMax;
    DirectionType directionType;

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

    public MouvementType mouvementDirectionnelPossible(Case src, Case dest){
        int deltaX = Math.abs(src.getX() - dest.getX());
        int deltaY = Math.abs(src.getY() - dest.getY());

        if((directionType == DirectionType.Droit || directionType == DirectionType.Tous) && (src.getX() != dest.getX() ^ src.getY() != dest.getY())){
            if(Math.max(deltaX, deltaY) <= distanceDeplacementMax)
                return MouvementType.CLASSIC;
        }

        if((directionType == DirectionType.Diagonale || directionType == DirectionType.Tous) && deltaX == deltaY){
            if(deltaX <= distanceDeplacementMax)
                return MouvementType.CLASSIC;
        }

        return MouvementType.NON_VALIDE;
    }
}
