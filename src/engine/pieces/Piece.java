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
    PieceType pieceType;    //Type de la pièce
    PlayerColor color;      //Couleur de la pièce

    public Piece(PlayerColor color) {
        this.color = color;
    }

    /**
     * Determine si un mouvement est possible depuis une case source vers une case destination
     * @param src Case source
     * @param dest Case destination
     * @return un MouvementType en fonction du mouvement
     */
    public MouvementType mouvementPossible(Case src, Case dest){

        //Permet de ne pas pouvoir manger ces propres pièces
        if(dest.getPieceCourante() != null && dest.getPieceCourante().getColor() == this.color){
            return MouvementType.NON_VALIDE;
        }
        return MouvementType.CLASSIQUE;
    }

    /**
     * Permet de déplacer une pièce depuis une case source vers une case destination si le mouvement est valide
     * @param src Case source
     * @param dest Case destination
     * @return un MouvementType en fonction du mouvement
     */
    public MouvementType deplacer(Case src, Case dest){
        MouvementType mouvementType = mouvementPossible(src, dest);
        if(mouvementType == MouvementType.NON_VALIDE)
            return MouvementType.NON_VALIDE;

        src.supprimerPiece();
        dest.placerPiece(this);
        return mouvementType;
    }

    /**
     * Getter du type de la pièce
     * @return le type de la pièce
     */
    public PieceType getPieceType(){return pieceType;}

    /**
     * Getter de la couleur de la pièce
     * @return la couleur de la pièce
     */
    public PlayerColor getColor(){return  color;}

    public abstract Piece clone();

    @Override
    public String textValue() {
        return toString();
    }

    public abstract String toString();
}
