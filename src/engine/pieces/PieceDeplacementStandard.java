/* ---------------------------
Laboratoire : Labo 08: Jeu d'échecs
Fichier : PieceDeplacementStandard.java
Auteur(s) : Romano Malo et Enzo Allemano
Date : 12.01.2022

But : Déclaration de la classe abstraite : PieceDeplacementStandard

Remarque(s) : Toutes les pièces utilisées dans le jeu hérite de cette classe, sauf le cavalier

Compilateur : jdk1.8.0_221

--------------------------- */
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

        int deltaX = Math.abs(src.getX() - dest.getX());
        int deltaY = Math.abs(src.getY() - dest.getY());

        if((directionType == DirectionType.DROIT || directionType == DirectionType.TOUS) && (src.getX() != dest.getX() ^ src.getY() != dest.getY())){
            if(Math.max(deltaX, deltaY) <= Math.abs(distanceDeplacementMax))
                return MouvementType.CLASSIQUE;
        }

        if((directionType == DirectionType.DIAGONALE || directionType == DirectionType.TOUS) && deltaX == deltaY){
            if(deltaX <= distanceDeplacementMax)
                return MouvementType.CLASSIQUE;
        }
        return MouvementType.NON_VALIDE;
    }
}
