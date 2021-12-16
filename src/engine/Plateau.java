package engine;//Voici un test de header 

import chess.ChessController;
import chess.ChessView;

public class Plateau implements ChessController {

    private int tour;
    private Case[][] plateau;

    @Override
    public void start(ChessView view) {

    }

    @Override
    public boolean move(int fromX, int fromY, int toX, int toY) {
        return false;
    }

    @Override
    public void newGame() {

    }
}
