/* ---------------------------
Laboratoire : Labo 08: Jeu d'échecs
Fichier : Cavalier.java
Auteur(s) : Romano Malo et Enzo Allemano
Date : 12.01.2022

But : Implémentation de la pièce : cavalier

Remarque(s) : -

Compilateur : jdk1.8.0_221

--------------------------- */
package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.Case;

public class Cavalier extends Piece{
    public Cavalier(PlayerColor color) {
        super(color);
        pieceType= PieceType.KNIGHT;
    }

    @Override
    public MouvementType mouvementPossible(Case src, Case dest) {
        if (super.mouvementPossible(src,dest) == MouvementType.NON_VALIDE){
            return MouvementType.NON_VALIDE;
        }

        int deltaX = Math.abs(src.getX() - dest.getX());
        int deltaY = Math.abs(src.getY() - dest.getY());

        if((deltaX == 1 && deltaY == 2) || (deltaX == 2 && deltaY == 1)){

            return MouvementType.CLASSIQUE;
        }
        return MouvementType.NON_VALIDE;
    }

    @Override
    public Piece clone() {
        return new Cavalier(this.color);
    }

    @Override
    public String toString() {
        return "Cavalier";
    }
}
