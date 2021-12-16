package engine;

import chess.ChessController;
import chess.ChessView;
import chess.PlayerColor;
import engine.pieces.*;

import java.util.ArrayList;
import java.util.Arrays;

public class Plateau implements ChessController {

    private static final int DIMENSION = 8;

    private int tour;
    private Case[][] plateau;
    private ChessView view;
    private boolean echec;

    /**
     * Constructeur, crée le plateau de jeu
     */
    public Plateau(){
        plateau = new Case[DIMENSION][DIMENSION];
        for(int colonne = 0; colonne < DIMENSION; colonne ++){
            for(int ligne = 0; ligne < DIMENSION; ligne ++){
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
        tour = 1;
        echec = false;

        // On vide l'echiquier
        for (int col = 0; col < DIMENSION; ++col) {
            for (int row = 0; row < DIMENSION; ++row) {
                Case caseCourrante = plateau[col][row];
                if (!caseCourrante.estVide()) {
                    plateau[col][row].supprimerPiece();
                }
            }
        }

        ArrayList<Piece> whitePieces = new ArrayList<>(
                Arrays.asList(
                        new Tour(PlayerColor.WHITE),
                        new Cavalier(PlayerColor.WHITE),
                        new Fou(PlayerColor.WHITE),
                        new Dame(PlayerColor.WHITE),
                        new Roi(PlayerColor.WHITE),
                        new Fou(PlayerColor.WHITE),
                        new Cavalier(PlayerColor.WHITE),
                        new Tour(PlayerColor.WHITE)
                )
        );


        ArrayList<Piece> blackPieces = new ArrayList<>(
                Arrays.asList(
                        new Tour(PlayerColor.BLACK),
                        new Cavalier(PlayerColor.BLACK),
                        new Fou(PlayerColor.BLACK),
                        new Dame(PlayerColor.BLACK),
                        new Roi(PlayerColor.BLACK),
                        new Fou(PlayerColor.BLACK),
                        new Cavalier(PlayerColor.BLACK),
                        new Tour(PlayerColor.BLACK)
                )
        );

        for (int col = 0; col < DIMENSION; ++col) {
            Piece pionBlanc = new Pion(PlayerColor.WHITE);
            Piece pionNoir = new Pion(PlayerColor.BLACK);
            plateau[col][1].placerPiece(pionBlanc);
            plateau[col][6].placerPiece(pionNoir);

            view.putPiece(pionBlanc.getPieceType(), pionBlanc.getColor(), col, 1);
            view.putPiece(pionNoir.getPieceType(), pionNoir.getColor(), col, 6);
        }


        for (int i = 0; i < DIMENSION; ++i) {
            Piece p = whitePieces.get(i);

            plateau[i][0].placerPiece(p);

            view.putPiece(p.getPieceType(), p.getColor(), i, 0);
        }


        for (int i = 0; i < DIMENSION; ++i) {
            Piece p = blackPieces.get(i);

            plateau[i][7].placerPiece(p);

            view.putPiece(p.getPieceType(), p.getColor(), i, 7);
        }
    }
}
