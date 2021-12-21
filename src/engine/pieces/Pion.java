package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.Case;

public class Pion extends PiecePremierDeplacement{
    public Pion(PlayerColor color) {
        super(color);
        pieceType = PieceType.PAWN;
        distanceDeplacementMax = 1;
    }

    @Override
    public MouvementType mouvementPossible(Case src, Case dest) {

        if (super.mouvementPossible(src,dest) == MouvementType.NON_VALIDE){
            return MouvementType.NON_VALIDE;
        }

        if(!dest.estVide())
            return MouvementType.NON_VALIDE;

        //Le code en dessous devrait être factoriser pour toutes les pièces qui peuvent se déplacer verticalement et où on doit checker le premier déplacement.
        //Y'a juste le pion qui fait exception puisque sa distanceMax change en fonction de si c'est son premier coup ou pas
        int deplacementPossible = distanceDeplacementMax;
        deplacementPossible += premierDeplacement ? 0 : 1;
        if(color == PlayerColor.BLACK)
            deplacementPossible *= (-1);

        //La fonction ci-dessous ne fonctionne que pour le premier double déplacement blanc...
        if(src.getX() == dest.getX() && ((src.getY() + deplacementPossible == dest.getY()) || (src.getY() + deplacementPossible == dest.getY()- (premierDeplacement ? 1 : 0)))){
            premierDeplacement = false;
            return MouvementType.CLASSIC;
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
