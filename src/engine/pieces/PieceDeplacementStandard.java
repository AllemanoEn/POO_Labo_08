package engine.pieces;//Voici un test de header

import chess.PlayerColor;

public abstract class PieceDeplacementStandard extends Piece{
    int distanceDeplacementMax;

    public PieceDeplacementStandard(PlayerColor color) {
        super(color);
    }
}
