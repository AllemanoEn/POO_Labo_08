package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.Case;

public class Pion extends PiecePremierDeplacement{
    public Pion(PlayerColor color) {
        super(color);
        pieceType = PieceType.PAWN;
        distanceDeplacementMax = 1;
        directionType = DirectionType.Non_defini;

        if(color == PlayerColor.BLACK)
            distanceDeplacementMax *= (-1);
    }

    @Override
    public MouvementType mouvementPossible(Case src, Case dest) {

        if (super.mouvementPossible(src,dest) == MouvementType.NON_VALIDE){
            return MouvementType.NON_VALIDE;
        }

        if(!dest.estVide() && dest.getX() == src.getX())
            return MouvementType.NON_VALIDE;

        //Le code en dessous devrait être factoriser pour toutes les pièces qui peuvent se déplacer verticalement et où on doit checker le premier déplacement.
        //Y'a juste le pion qui fait exception puisque sa distanceMax change en fonction de si c'est son premier coup ou pas

        if(src.getX() == dest.getX() && ((src.getY() + distanceDeplacementMax == dest.getY()
        || this.premierDeplacement && src.getY() + 2*distanceDeplacementMax == dest.getY()))){
            premierDeplacement = false;
            return MouvementType.CLASSIC;
        }
        if((src.getX() == dest.getX()+1 || src.getX() == dest.getX()-1)&&src.getY() + distanceDeplacementMax == dest.getY() && !dest.estVide()){
            premierDeplacement = false;
            return MouvementType.PRISE;
        }

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
