package engine;

import chess.ChessController;
import chess.ChessView;

public class Plateau implements ChessController {

    private final int dimension = 8;

    private int tour;
    private Case[][] plateau;
    private ChessView view;

    /**
     * Constructeur, crée le plateau de jeu
     */
    public Plateau(){
        plateau = new Case[dimension][dimension];
        for(int colonne = 0; colonne < dimension; colonne ++){
            for(int ligne = 0; ligne < dimension; ligne ++){
                plateau[colonne][ligne] = new Case(colonne,ligne);
            }
        }
    }

    @Override
    public void start(ChessView view) {
        this.view = view;
        view.startView();
    }

    @Override
    public boolean move(int fromX, int fromY, int toX, int toY) {
        return false;
    }

    @Override
    public void newGame() {

    }
}
