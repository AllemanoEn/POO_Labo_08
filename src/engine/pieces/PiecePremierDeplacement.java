package engine.pieces;

import chess.PlayerColor;

public abstract class PiecePremierDeplacement extends PieceDeplacementStandard{
    public boolean premierDeplacement = true;

    public PiecePremierDeplacement(PlayerColor color) {
        super(color);
    }
}
