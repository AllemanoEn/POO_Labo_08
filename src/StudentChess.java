import chess.ChessController;
import chess.ChessView;
import chess.views.gui.GUIView;
import engine.Board;

public class StudentChess {

  public static void main(String[] args) {

    // 1. Création du contrôleur pour gérer le jeu d'échec
    ChessController controller = new Board(); // Board est une classe faite et nommée par les étudiant

    // 2. Création de la vue désirée
    ChessView view = new GUIView(controller); // ou console
//    ChessView view = new ConsoleView(controller); // ou GUI

    // 3. Lancement du programme.
    controller.start(view);

  }

}
