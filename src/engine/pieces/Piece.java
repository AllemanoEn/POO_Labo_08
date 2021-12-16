package engine.pieces;

import chess.ChessView;
import chess.PieceType;
import chess.PlayerColor;
import engine.Case;

public abstract class Piece implements ChessView.UserChoice{
    public abstract boolean mouvementPossible(Case dest);
    public abstract boolean deplacer(Case dest);

    public abstract String toString();

    PieceType pieceType;
    PlayerColor color;

    MouvementType mouvementType;

    public Piece(PlayerColor color) {
        this.color = color;
    }

    public PieceType getPieceType(){return pieceType;}

    public PlayerColor getColor(){return  color;}

    @Override
    public String textValue() {
        return null;
    }
}
