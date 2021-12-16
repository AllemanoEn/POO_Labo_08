package engine.pieces;//Voici un test de header 

import chess.PlayerColor;

public abstract class PieceRoquable extends PieceDeplacementStandard{
    boolean premierDeplacement;

    public PieceRoquable(PlayerColor color) {
        super(color);
    }
}
