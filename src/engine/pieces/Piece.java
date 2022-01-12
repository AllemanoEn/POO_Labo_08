/* ---------------------------
Laboratoire : Labo 08: Jeu d'échecs
Fichier : Piece.java
Auteur(s) : Romano Malo et Enzo Allemano
Date : 12.01.2022

But : Déclaration de la classe abstraite : Piece

Remarque(s) : Toutes les pièces utilisées dans le jeu hérite de cette classe

Compilateur : jdk1.8.0_221

--------------------------- */
package engine.pieces;

import chess.ChessView;
import chess.PieceType;
import chess.PlayerColor;
import engine.Case;

public abstract class Piece implements ChessView.UserChoice{
    PieceType pieceType;
    PlayerColor color;

    public Piece(PlayerColor color) {
        this.color = color;
    }

    public MouvementType mouvementPossible(Case src, Case dest){
        //Permet de ne pas pouvoir manger ces propres pièces
        if(dest.getPieceCourante() != null && dest.getPieceCourante().getColor() == this.color){
            return MouvementType.NON_VALIDE;
        }
        return MouvementType.CLASSIQUE;
    }

    public MouvementType deplacer(Case src, Case dest){
        MouvementType mouvementType = mouvementPossible(src, dest);
        if(mouvementType == MouvementType.NON_VALIDE)
            return MouvementType.NON_VALIDE;

        src.supprimerPiece();
        dest.placerPiece(this);
        return mouvementType;
    }

    public PieceType getPieceType(){return pieceType;}

    public PlayerColor getColor(){return  color;}

    public abstract Piece clone();

    @Override
    public String textValue() {
        return toString();
    }

    public abstract String toString();
}
