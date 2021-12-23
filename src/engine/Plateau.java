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
        Case caseFrom = plateau[fromX][fromY];
        Case caseTo = plateau[toX][toY];

        if (caseFrom.estVide()){
            return false;
        }

        // Piece deplacee
        Piece p = caseFrom.getPieceCourante();

        // Permet de savoir quel couleur doit jouer
        if (tour % 2 == 1 && p.getColor() != PlayerColor.WHITE || tour % 2 == 0 && p.getColor() != PlayerColor.BLACK){
            return false;
        }

        if (p.mouvementPossible(caseFrom,caseTo) == MouvementType.NON_VALIDE || !trajectoireLibre(caseFrom, caseTo)){
            return false;
        }

        //Il faudrait utiliser la méthode "déplacer" de la pièce ???

        caseFrom.supprimerPiece();
        caseTo.placerPiece(p);

        view.displayMessage("");
        view.removePiece(fromX,fromY);
        view.putPiece(p.getPieceType(),p.getColor(),toX,toY);

        tour++;

        return true;
    }
    public boolean trajectoireLibre(Case src, Case dest ){
        int deltaX = Math.abs(src.getX() - dest.getX());
        int deltaY = Math.abs(src.getY() - dest.getY());

        int dirX = 0;
        int dirY = 0;

        if(deltaX != 0)
            dirX = (src.getX() > dest.getX())? -1: 1;
        if(deltaY != 0)
            dirY = (src.getY() > dest.getY())? -1: 1;

        int x = src.getX() + dirX;
        int y = src.getY() + dirY;
        for(int i = 1; i < Math.max(deltaX, deltaY); i++){

            if(!plateau[x][y].estVide())
                return false;
            x+= dirX;
            y+= dirY;
        }
        return true;
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
            Piece pieceBlanche = whitePieces.get(col);
            Piece pieceNoire = blackPieces.get(col);

            plateau[col][1].placerPiece(pionBlanc);
            plateau[col][6].placerPiece(pionNoir);
            plateau[col][0].placerPiece(pieceBlanche);
            plateau[col][7].placerPiece(pieceNoire);

            view.putPiece(pieceBlanche.getPieceType(), pieceBlanche.getColor(), col, 0);
            view.putPiece(pieceNoire.getPieceType(), pieceNoire.getColor(), col, 7);
            view.putPiece(pionBlanc.getPieceType(), pionBlanc.getColor(), col, 1);
            view.putPiece(pionNoir.getPieceType(), pionNoir.getColor(), col, 6);
        }
    }
}
