/* ---------------------------
Laboratoire : Labo 08: Jeu d'échecs
Fichier : Main.java
Auteur(s) : Romano Malo et Enzo Allemano
Date : 12.01.2022

But : Déclaration de la classe : Main

Remarque(s) : Util pour démarrer l'interface graphique

Compilateur : jdk1.8.0_221

--------------------------- */
package engine;

import chess.ChessController;
import chess.ChessView;
import chess.views.console.ConsoleView;
import chess.views.gui.GUIView;

public class Main {
    public static void main(String ... args){
        ChessController chessboard = new Plateau();
        //ChessView view = new GUIView(chessboard);
        ChessView view = new ConsoleView(chessboard);
        chessboard.start(view);
    }
}
