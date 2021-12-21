package engine.pieces;

import chess.PlayerColor;

public abstract class PiecePremierDeplacement extends PieceDeplacementStandard{
    boolean premierDeplacement = true;

    public PiecePremierDeplacement(PlayerColor color) {
        super(color);
    }
}
