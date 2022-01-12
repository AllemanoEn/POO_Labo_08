/* ---------------------------
Laboratoire : Labo 08: Jeu d'échecs
Fichier : Pion.java
Auteur(s) : Romano Malo et Enzo Allemano
Date : 12.01.2022

But : Implémentation de la pièce : pion

Remarque(s) : Fonction mouvementPossible spécifique

Compilateur : jdk1.8.0_221

--------------------------- */
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
        if ((dest.getY() == 0 || dest.getY() == 7) && (dest.getY() == src.getY() + distanceDeplacementMax)){ // 0 et 7 représentent la première et derrière ligne de l'échiquier
            return MouvementType.PROMOTION;
        }

        // Deplacement standard de 1 en avant
        if(src.getX() == dest.getX() && src.getY() + distanceDeplacementMax == dest.getY()){
            return MouvementType.CLASSIQUE;
        }

        // Premier déplacement de 2 en avant
        if(src.getX() == dest.getX() && this.premierDeplacement && src.getY() + 2*distanceDeplacementMax == dest.getY()){
            return MouvementType.DOUBLE;
        }

        // Deplacement en diagonale en avant de 1, pour une prise
        if((src.getX() == dest.getX()+1 || src.getX() == dest.getX()-1) && src.getY() + distanceDeplacementMax == dest.getY() && dest.aUnePiece()){
            return MouvementType.CLASSIQUE;
        }

        // Deplacement en diagonale en avant de 1, pour une prise
        if((src.getX() == dest.getX()+1 || src.getX() == dest.getX()-1) && src.getY() + distanceDeplacementMax == dest.getY() && dest.aUnPionFantome()){
            return MouvementType.EN_PASSANT;
        }

        return MouvementType.NON_VALIDE;

    }

    @Override
    public Piece clone() {
        Pion p = new Pion(this.color);
        p.premierDeplacement = this.premierDeplacement;
        return p;
    }

    @Override
    public String toString() {
        return "Pion";
    }
}
