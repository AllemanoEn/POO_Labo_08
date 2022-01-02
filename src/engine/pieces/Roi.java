package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.Case;

public class Roi extends PiecePremierDeplacement {
    public Roi(PlayerColor color) {
        super(color);
        pieceType = PieceType.KING;
        directionType = DirectionType.TOUS;
        distanceDeplacementMax = 1;
    }

    @Override
    public MouvementType mouvementPossible(Case src, Case dest) {
        if (super.mouvementPossible(src, dest) == MouvementType.NON_VALIDE) {
            return MouvementType.NON_VALIDE;
        }

        if (mouvementDirectionnelPossible(src, dest) == MouvementType.CLASSIQUE) {
            premierDeplacement = false;
            return MouvementType.CLASSIQUE;
        }
        if (premierDeplacement) {
            if (color == PlayerColor.WHITE) {
                if (dest.getX() == 6 && dest.getY() == 0) {
                    premierDeplacement = false;
                    return MouvementType.PETIT_ROQUE;
                }
                if (dest.getX() == 1 && dest.getY() == 0) {
                    premierDeplacement = false;
                    return MouvementType.GRAND_ROQUE;
                }
            }
            if (color == PlayerColor.BLACK) {
                if (dest.getX() == 6 && dest.getY() == 7) {
                    premierDeplacement = false;
                    return MouvementType.PETIT_ROQUE;
                }
                if (dest.getX() == 1 && dest.getY() == 7) {
                    premierDeplacement = false;
                    return MouvementType.GRAND_ROQUE;
                }
            }
        }
        return MouvementType.NON_VALIDE;
    }

    @Override
    public boolean deplacer(Case dest) {
        return false;
    }

    @Override
    public String toString() {
        return "Roi";
    }
}
