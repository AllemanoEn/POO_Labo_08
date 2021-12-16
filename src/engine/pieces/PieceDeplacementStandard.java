package engine.pieces;

import chess.PlayerColor;

public abstract class PieceDeplacementStandard extends Piece{
    int distanceDeplacementMax;

    public PieceDeplacementStandard(PlayerColor color) {
        super(color);
    }
}
