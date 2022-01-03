package engine;

import chess.ChessController;
import chess.ChessView;
import chess.PieceType;
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

        // Permet de savoir quelle couleur doit jouer
        if (tour % 2 == 1 && p.getColor() != PlayerColor.WHITE || tour % 2 == 0 && p.getColor() != PlayerColor.BLACK){
            return false;
        }


        MouvementType mouvementTypeActuel = MouvementType.NON_VALIDE;
        if(trajectoireLibre(caseFrom, caseTo) || p.getPieceType() == PieceType.KNIGHT){
            mouvementTypeActuel = p.deplacer(caseFrom, caseTo);
        }

        // Si le mouvement est non-valide ou si la trajectoire n'est pas libre (pour les pièces autres que cavalier)
        if (mouvementTypeActuel == MouvementType.NON_VALIDE || (!trajectoireLibre(caseFrom, caseTo) && p.getPieceType() != PieceType.KNIGHT)){
            return false;
        }

        view.displayMessage("");
        view.removePiece(fromX,fromY);
        view.putPiece(p.getPieceType(),p.getColor(),toX,toY);

        // Prise en passant
        if(caseTo.getPionFantome() != null && p.getPieceType() == PieceType.PAWN){
            // Position du vrai pion par rapport à la case du pion fantôme
            // Est inversé si le joueur est noir
            int pionPosition = -1;
            if(p.getColor() == PlayerColor.BLACK)
                pionPosition = 1;

            // Suppresion du vrai pion
            plateau[toX][toY+pionPosition].supprimerPiece();
            view.removePiece(toX, toY + pionPosition);

            view.displayMessage("Prise en passant");
        }

        // Si le mouvement est une promotion on promeut
        if (mouvementTypeActuel == MouvementType.PROMOTION){
            promouvoir(p.getColor(),caseTo);

            view.displayMessage("Promotion");
        }

        // Si le mouvement est une tentative de petit roque
        if(mouvementTypeActuel == MouvementType.PETIT_ROQUE){
            if(roque(1, toX, toY))
                view.displayMessage("Petit roque");
        }

        // Si le mouvement est une tentative de grand roque
        if(mouvementTypeActuel == MouvementType.GRAND_ROQUE){
            if(roque(-1, toX, toY))
                view.displayMessage("Grand roque");
        }

        tour++;

        // Vider le plateau de tous les pions fantomes
        for(int x = 0; x < DIMENSION;++x){
            for(int y = 0; y < DIMENSION; ++y){
                plateau[x][y].supprimerPionFantome();
            }
        }

        // Si le mouvement est un premier mouvement d'un pion de 2 en avant
        if(mouvementTypeActuel == MouvementType.EN_PASSANT){
            // Placer le pion fantome a la case entre la source et la destination
            plateau[(toX+fromX)/2][(toY+fromY)/2].placerPionFantome((Pion) p);

            view.displayMessage("Premier déplacement d'un pion (avancée de 2)");
        }
        return true;
    }

    /**
     * Permet de déplacer la tour lors du petit et du grand roque, après avoir vérifier si le roque est permis
     * @param roque vaut 1 si c'est un petit roque, et -1 si c'est un grand roque
     * @param x x de destination du roi
     * @param y y de destination du roi
     */
    public boolean roque(int roque, int x, int y){
        if(plateau[x+roque][y].getPieceCourante().getPieceType() == PieceType.ROOK){
            Tour tourRoque = (Tour) plateau[x+roque][y].getPieceCourante();
            if(tourRoque.premierDeplacement){
                view.removePiece(x+roque, y);
                view.putPiece(tourRoque.getPieceType(), tourRoque.getColor(), x-roque, y);
                plateau[x+roque][y].supprimerPiece();
                plateau[x-roque][y].placerPiece(tourRoque);

                return true;
            }
        }
        return false;
    }

    public boolean trajectoireLibre(Case src, Case dest){
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

    private void promouvoir(PlayerColor color, Case dest) {
        // Les nouvelles pieces possibles
        Piece dame = new Dame(color);
        Piece cavalier = new Cavalier(color);
        Piece tour = new Tour(color);
        Piece fou = new Fou(color);

        Piece pieceSelectionee = view.askUser("Promotion", "Choisir une pièce pour la promotion",dame,cavalier,tour,fou);

        int x = dest.getX(), y = dest.getY();

        // On effectue la promotion du pion
        plateau[x][y].supprimerPiece();
        plateau[x][y].placerPiece(pieceSelectionee);
        view.removePiece(x, y);
        view.putPiece(pieceSelectionee.getPieceType(), pieceSelectionee.getColor(),x, y);
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
