/* ---------------------------
Laboratoire : Labo 08: Jeu d'échecs
Fichier : Tour.java
Auteur(s) : Romano Malo et Enzo Allemano
Date : 12.01.2022

But : Implémentation de la pièce : tour

Remarque(s) : -

Compilateur : jdk1.8.0_221

--------------------------- */
package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.Case;

public class Tour extends PiecePremierDeplacement {
    public Tour(PlayerColor color) {
        super(color);
        pieceType = PieceType.ROOK;
        directionType = DirectionType.DROIT;
        distanceDeplacementMax = 10;
    }

    @Override
    public MouvementType mouvementPossible(Case src, Case dest) {
        if (super.mouvementPossible(src,dest) == MouvementType.NON_VALIDE){
            return MouvementType.NON_VALIDE;
        }
        return mouvementDirectionnelPossible(src, dest);
    }

    @Override
    public Piece clone() {
        Tour p = new Tour(this.color);
        p.premierDeplacement = this.premierDeplacement;
        return p;
    }

    @Override
    public String toString() {
        return "Tour";
    }
}
