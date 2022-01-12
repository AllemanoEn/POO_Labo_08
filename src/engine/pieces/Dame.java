/* ---------------------------
Laboratoire : Labo 08: Jeu d'échecs
Fichier : Dame.java
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

public class Dame extends PieceDeplacementStandard{
    public Dame(PlayerColor color) {
        super(color);
        pieceType = PieceType.QUEEN;
        distanceDeplacementMax = 10;
        directionType = DirectionType.TOUS;
    }

    @Override
    public MouvementType mouvementPossible(Case src, Case dest) {
        if (super.mouvementPossible(src,dest) == MouvementType.NON_VALIDE){
            return MouvementType.NON_VALIDE;
        }

        return super.mouvementPossible(src, dest);
    }

    @Override
    public Piece clone() {
        return new Dame(this.color);
    }

    @Override
    public String toString() {
        return "Dame";
    }
}
