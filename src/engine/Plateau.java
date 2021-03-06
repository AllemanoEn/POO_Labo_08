/* ---------------------------
Laboratoire : Labo 08: Jeu d'échecs
Fichier : Plateau.java
Auteur(s) : Romano Malo et Enzo Allemano
Date : 12.01.2022

But : Déclaration de la classe : Plateau

Remarque(s) : Permet d'assurer le déroulement d'une partie en fonction des règles implémentée.
              Hérite de ChessController

Compilateur : jdk1.8.0_221

--------------------------- */
package engine;

import chess.ChessController;
import chess.ChessView;
import chess.PieceType;
import chess.PlayerColor;
import engine.pieces.*;

import java.util.ArrayList;
import java.util.Arrays;

public class Plateau implements ChessController {

    private static final int DIMENSION = 8; //Défini la dimension standard d'un échiquier

    private int tour;           //Compte le nombre de tour durant une partie
    private Case[][] plateau;   //Echiquier de la partie courante
    private ChessView view;     //Necessaire à l'UI

    /**
     * Constructeur, crée le plateau de jeu
     */
    public Plateau() {
        plateau = new Case[DIMENSION][DIMENSION];
        for (int colonne = 0; colonne < DIMENSION; colonne++) {
            for (int ligne = 0; ligne < DIMENSION; ligne++) {
                plateau[colonne][ligne] = new Case(colonne, ligne);
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
        view.displayMessage("");

        Case caseFrom = plateau[fromX][fromY];
        Case caseTo = plateau[toX][toY];

        if (caseFrom.estVide()) {
            return false;
        }

        // Piece deplacee
        Piece p = caseFrom.getPieceCourante();

        // Permet de savoir quelle couleur doit jouer
        if (tour % 2 == 1 && p.getColor() != PlayerColor.WHITE || tour % 2 == 0 && p.getColor() != PlayerColor.BLACK) {
            return false;
        }

        // Si la trajectoire est libre ou que la pièce est un cavalier
        // Test de mise en echec de son propre roi dans un plateau temporaire
        if (trajectoireLibre(caseFrom, caseTo, plateau) || p.getPieceType() == PieceType.KNIGHT) {
            Case[][] plateauTestEchec = dupliquerPlateau();
            Case caseFromTestEchec = plateauTestEchec[fromX][fromY];
            Case caseToTestEchec = plateauTestEchec[toX][toY];
            Piece pTestEchec = caseFromTestEchec.getPieceCourante();

            MouvementType mouvementTypeTestEchec = pTestEchec.deplacer(caseFromTestEchec, caseToTestEchec);

            // Test si la case parcouru par le roi pendant son roque le mettrait en echec
            if (mouvementTypeTestEchec == MouvementType.PETIT_ROQUE || mouvementTypeTestEchec == MouvementType.GRAND_ROQUE) {
                if (echec(pTestEchec.getColor() == PlayerColor.BLACK ? PlayerColor.WHITE : PlayerColor.BLACK, plateauTestEchec[mouvementTypeTestEchec == MouvementType.GRAND_ROQUE ? toX + 1 : toX - 1][toY], plateauTestEchec)) {
                    view.displayMessage("Roque interdit : la case parcouru met le roi en échec");
                    return false;
                }
            }

            // Test si le mouvement met le roi en echec
            if (echec(pTestEchec.getColor() == PlayerColor.BLACK ? PlayerColor.WHITE : PlayerColor.BLACK, trouverRoi(p.getColor(), plateauTestEchec), plateauTestEchec)) {
                view.displayMessage("Interdit de mettre en echec son roi");
                return false;
            }
        } else {
            return false;
        }

        MouvementType mouvementTypeActuel = p.deplacer(caseFrom, caseTo);

        // Si le mouvement est non-valide
        if (mouvementTypeActuel == MouvementType.NON_VALIDE) {
            return false;
        }


        // Prise en passant
        if (mouvementTypeActuel == MouvementType.EN_PASSANT) {
            // Position du vrai pion par rapport à la case du pion fantôme
            // Est inversé si le joueur est noir
            int pionPosition = -1;
            if (p.getColor() == PlayerColor.BLACK)
                pionPosition = 1;

            // Suppresion du vrai pion
            plateau[toX][toY + pionPosition].supprimerPiece();
            view.removePiece(toX, toY + pionPosition);

            view.displayMessage("Prise en passant");
        }

        // Si le mouvement est une promotion on promeut
        if (mouvementTypeActuel == MouvementType.PROMOTION) {
            promouvoir(p.getColor(), caseTo);

            view.displayMessage("Promotion");
        }

        // Si le mouvement est une tentative de petit roque
        if (mouvementTypeActuel == MouvementType.PETIT_ROQUE) {
            if (roque(1, toX, toY))
                view.displayMessage("Petit roque");


        }

        // Si le mouvement est une tentative de grand roque
        if (mouvementTypeActuel == MouvementType.GRAND_ROQUE) {
            if (plateau[toX - 1][toY].estVide()) {
                if (roque(-2, toX, toY))
                    view.displayMessage("Grand roque");
            } else {
                view.displayMessage("Roque interdit : le chemin entre le roi et la tour n'est pas libre");
                return false;
            }

        }


        // Vider le plateau de tous les pions fantomes
        for (int x = 0; x < DIMENSION; ++x) {
            for (int y = 0; y < DIMENSION; ++y) {
                plateau[x][y].supprimerPionFantome();
            }
        }

        // Si le mouvement est un premier mouvement d'un pion de 2 en avant
        if (mouvementTypeActuel == MouvementType.DOUBLE) {
            // Placer le pion fantome a la case entre la source et la destination
            plateau[(toX + fromX) / 2][(toY + fromY) / 2].placerPionFantome((Pion) p);

            view.displayMessage("Premier déplacement d'un pion (avancée de 2)");
        }


        if (echec(p.getColor(), trouverRoi(p.getColor() == PlayerColor.BLACK ? PlayerColor.WHITE : PlayerColor.BLACK, plateau), plateau)) {
            view.displayMessage("Echec");
        }

        view.removePiece(fromX, fromY);

        // Dans le cas d'une promotion on n'aimerait pas remettre un pion sur la dernière case
        // On a donc pas besoin de d'appeler view.putPiece puisqu'on s'en charge déjà dans la fonction promouvoir
        if (mouvementTypeActuel != MouvementType.PROMOTION) {
            view.putPiece(p.getPieceType(), p.getColor(), toX, toY);
        }

        tour++;

        return true;
    }

    /**
     * Permet de faire une copie de l'échiquier actuel
     * @return une copie de l'échiquier actuel
     */
    private Case[][] dupliquerPlateau() {
        Case[][] plateauDuplique = new Case[DIMENSION][DIMENSION];
        for (int x = 0; x < DIMENSION; x++) {
            for (int y = 0; y < DIMENSION; y++) {
                plateauDuplique[x][y] = new Case(plateau[x][y]);
            }
        }
        return plateauDuplique;
    }

    /**
     * Determine si une case est en échec
     *
     * @param color   Couleur de l'équipe attaquante
     * @param c       Case cible
     * @param plateau Plateau
     * @return vrai si elle est en échec, faux sinon
     */
    public boolean echec(PlayerColor color, Case c, Case[][] plateau) {
        for (int x = 0; x < DIMENSION; ++x) {
            for (int y = 0; y < DIMENSION; ++y) {
                Piece piece = plateau[x][y].getPieceCourante();
                if (piece != null) {
                    if (piece.getColor() == color) {

                        if (piece.mouvementPossible(plateau[x][y], c) != MouvementType.NON_VALIDE) {
                            if (trajectoireLibre(plateau[x][y], c, plateau) || piece.getPieceType() == PieceType.KNIGHT) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * Permet de déplacer la tour lors du petit et du grand roque, après avoir vérifier si le roque est permis
     *
     * @param roque vaut 1 si c'est un petit roque, et -1 si c'est un grand roque
     * @param x     x de destination du roi
     * @param y     y de destination du roi
     */
    public boolean roque(int roque, int x, int y) {
        if (plateau[x + roque][y].getPieceCourante().getPieceType() == PieceType.ROOK) {
            Tour tourRoque = (Tour) plateau[x + roque][y].getPieceCourante();
            if (tourRoque.isPremierDeplacement()) {
                view.removePiece(x + roque, y);
                int tourPosition = x - (roque / Math.abs(roque));
                view.putPiece(tourRoque.getPieceType(), tourRoque.getColor(), tourPosition, y);
                plateau[x + roque][y].supprimerPiece();
                plateau[tourPosition][y].placerPiece(tourRoque);

                return true;
            }
        }
        return false;
    }

    /**
     * Permet de définir si une trajectoir est libre ou non sur un échiquier, en fonction d'une case source et d'une case destination
     * @param src       case source
     * @param dest      case destination
     * @param plateau   échiquier en question
     * @return true si libre, false sinon
     */
    public boolean trajectoireLibre(Case src, Case dest, Case[][] plateau) {
        int deltaX = Math.abs(src.getX() - dest.getX());
        int deltaY = Math.abs(src.getY() - dest.getY());

        int dirX = 0;
        int dirY = 0;

        if (deltaX != 0)
            dirX = (src.getX() > dest.getX()) ? -1 : 1;
        if (deltaY != 0)
            dirY = (src.getY() > dest.getY()) ? -1 : 1;

        int x = src.getX() + dirX;
        int y = src.getY() + dirY;
        for (int i = 1; i < Math.max(deltaX, deltaY); i++) {

            if (plateau[x][y].aUnePiece())
                return false;
            x += dirX;
            y += dirY;
        }
        return true;
    }

    /**
     * Permet de promouvoir un pion en une autre pièce choisi par l'utilisateur
     * @param color Couleur du joueur qui joue son tour
     * @param dest  Case de destination du pion promu
     */
    private void promouvoir(PlayerColor color, Case dest) {
        // Les nouvelles pieces possibles
        Piece dame = new Dame(color);
        Piece cavalier = new Cavalier(color);
        Piece tour = new Tour(color);
        Piece fou = new Fou(color);

        Piece pieceSelectionee = null;

        while (pieceSelectionee == null) {
            pieceSelectionee = view.askUser("Promotion", "Choisir une pièce pour la promotion", dame, cavalier, tour, fou);
        }

        int x = dest.getX(), y = dest.getY();

        // On effectue la promotion du pion
        plateau[x][y].supprimerPiece();
        plateau[x][y].placerPiece(pieceSelectionee);
        view.removePiece(x, y);
        view.putPiece(pieceSelectionee.getPieceType(), pieceSelectionee.getColor(), x, y);
    }

    @Override
    public void newGame() {
        tour = 1;

        // Vider le plateau de tous les pions fantomes
        for (int x = 0; x < DIMENSION; ++x) {
            for (int y = 0; y < DIMENSION; ++y) {
                plateau[x][y].supprimerPionFantome();
            }
        }

        // On vide l'echiquier
        for (int x = 0; x < DIMENSION; ++x) {
            for (int y = 0; y < DIMENSION; ++y) {
                Case caseCourrante = plateau[x][y];
                if (!caseCourrante.estVide()) {
                    plateau[x][y].supprimerPiece();
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

    /**
     * Permet de trouver le roi d'une couleur donnée sur échiquier donné
     * @param color     Couleur du roi à trouver
     * @param plateau   Echiquier sur lequel le roi doit être trouvé
     * @return la case où se trouve le roi de la couleur voulue
     */
    private Case trouverRoi(PlayerColor color, Case[][] plateau) {
        for (int x = 0; x < DIMENSION; ++x) {
            for (int y = 0; y < DIMENSION; ++y) {
                Piece p = plateau[x][y].getPieceCourante();
                if (p == null)
                    continue;
                if (p.getPieceType() == PieceType.KING && p.getColor() == color) {
                    return plateau[x][y];
                }
            }
        }
        return null;
    }
}
