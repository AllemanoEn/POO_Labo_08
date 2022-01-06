package engine.pieces;

import chess.ChessView;
import chess.PieceType;
import chess.PlayerColor;
import engine.Case;

public abstract class Piece implements ChessView.UserChoice{
    //J'ai enlever le abstract de la méthode pour implémenter ce test. Car il devra être executé pour toutes les pièces
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
    };


    public abstract String toString();

    PieceType pieceType;
    PlayerColor color;

    public Piece(PlayerColor color) {
        this.color = color;
    }

    public PieceType getPieceType(){return pieceType;}

    public PlayerColor getColor(){return  color;}

    @Override
    public String textValue() {
        return null;
    }

    public abstract Piece clone();
}
