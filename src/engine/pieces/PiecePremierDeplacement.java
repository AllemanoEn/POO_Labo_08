/* ---------------------------
Laboratoire : Labo 08: Jeu d'échecs
Fichier : PiecePremierDeplacement.java
Auteur(s) : Romano Malo et Enzo Allemano
Date : 12.01.2022

But : Déclaration de la classe abstraite : PiecePremierDeplacement

Remarque(s) : Toutes les pièces qui nécessites de savoir si elles ont déjà été déplacées héritent de cette classe

Compilateur : jdk1.8.0_221

--------------------------- */
package engine.pieces;

import chess.PlayerColor;
import engine.Case;

public abstract class PiecePremierDeplacement extends PieceDeplacementStandard{
    boolean premierDeplacement = true;  // Permet de déterminer si une pièce a déjà effectué son premier mouvement

    public PiecePremierDeplacement(PlayerColor color) {
        super(color);
    }

    @Override
    public MouvementType deplacer(Case src, Case dest) {
        MouvementType mouvementType = super.deplacer(src, dest);
        if(mouvementType == MouvementType.NON_VALIDE)
            return MouvementType.NON_VALIDE;
        premierDeplacement = false;
        return mouvementType;
    }

    /**
     * Getter de la varible premierDeplacement
     * @return la valeur de premierDeplacement
     */
    public boolean isPremierDeplacement() {
        return premierDeplacement;
    }
}
