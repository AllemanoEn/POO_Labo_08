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
                    return MouvementType.PETIT_ROQUE;
                }
                if (dest.getX() == 2 && dest.getY() == 0) {
                    return MouvementType.GRAND_ROQUE;
                }
            }
            if (color == PlayerColor.BLACK) {
                if (dest.getX() == 6 && dest.getY() == 7) {
                    return MouvementType.PETIT_ROQUE;
                }
                if (dest.getX() == 2 && dest.getY() == 7) {
                    return MouvementType.GRAND_ROQUE;
                }
            }
        }
        return MouvementType.NON_VALIDE;
    }

    @Override
    public Piece clone() {
        Roi p = new Roi(this.color);
        p.premierDeplacement = this.premierDeplacement;
        return p;
    }
    @Override
    public String toString() {
        return "Roi";
    }
}
