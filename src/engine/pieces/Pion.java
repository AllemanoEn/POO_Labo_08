package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.Case;

public class Pion extends PiecePremierDeplacement{
    public Pion(PlayerColor color) {
        super(color);
        pieceType = PieceType.PAWN;
        distanceDeplacementMax = 1;
        directionType = DirectionType.NON_DEFINI;

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

        //On promeut un pion s'il se trouve sur la première ou dernière ligne de l'échiquier
        if (dest.getY() == 0 || dest.getY() == 7){ // 0 et 7 représentent la première et derrière ligne de l'échiquier
            return MouvementType.PROMOTION;
        }

        // Deplacement standard de 1 en avant
        if(src.getX() == dest.getX() && src.getY() + distanceDeplacementMax == dest.getY()){
            premierDeplacement = false;
            return MouvementType.CLASSIQUE;
        }

        // Premier déplacement de 2 en avant
        if(src.getX() == dest.getX() && this.premierDeplacement && src.getY() + 2*distanceDeplacementMax == dest.getY()){
            premierDeplacement = false;
            return MouvementType.EN_PASSANT;
        }
        // Deplacement en diagonale en avant de 1, pour une prise
        if((src.getX() == dest.getX()+1 || src.getX() == dest.getX()-1) && src.getY() + distanceDeplacementMax == dest.getY() && !dest.estVide()){
            premierDeplacement = false;
            return MouvementType.CLASSIQUE;
        }

        return MouvementType.NON_VALIDE;

    }

    @Override
    public boolean deplacer(Case dest) {
        return false;
    }

    @Override
    public String toString() {
        return "Pion";
    }
}
