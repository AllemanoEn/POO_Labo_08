/* ---------------------------
Laboratoire : Labo 08: Jeu d'échecs
Fichier : StudentChess.java
Auteur(s) : Romano Malo et Enzo Allemano
Date : 12.01.2022

But : Déclaration de la classe : StudentChess

Remarque(s) : Util pour démarrer l'interface graphique

Compilateur : jdk1.8.0_221

--------------------------- */
import chess.ChessController;
import chess.ChessView;
import chess.views.gui.GUIView;
import chess.views.console.ConsoleView;
import engine.Plateau;

public class StudentChess {

  public static void main(String[] args) {

    // 1. Création du contrôleur pour gérer le jeu d'échec
    ChessController controller = new Plateau(); // Board est une classe faite et nommée par les étudiant

    // 2. Création de la vue désirée
    ChessView view = new GUIView(controller); // ou console
//    ChessView view = new ConsoleView(controller); // ou GUI

    // 3. Lancement du programme.
    controller.start(view);

  }

}
