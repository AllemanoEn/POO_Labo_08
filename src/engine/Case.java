/* ---------------------------
Laboratoire : Labo 08: Jeu d'échecs
Fichier : Case.java
Auteur(s) : Romano Malo et Enzo Allemano
Date : 12.01.2022

But : Déclaration de la classe : Case

Remarque(s) : Naviguabilité /!\ -> Une case connaît la pièce qui se trouve dessus, pas l'inverse

Compilateur : jdk1.8.0_221

--------------------------- */
package engine;

import engine.pieces.Piece;
import engine.pieces.Pion;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Case {
    private final int x;    //Position de la case sur l'axe X
    private final int y;    //Position de la case sur l'axe Y

    private Piece pieceCourante;    //Pièce se trouvant sur la case
    private Pion pionFantome;       //Pion Fantôme pour la prise en passant

    Case(int x, int y){
        this.x = x;
        this.y = y;
    }

    Case(Case c){
        this.x = c.x;
        this.y = c.y;
        if(c.pieceCourante != null)
            this.pieceCourante = c.pieceCourante.clone();
        if(c.pionFantome != null)
            this.pionFantome = (Pion) c.pionFantome.clone();
    }

    /**
     * Vérifie s'il y a une pièce ou un pion fantôme sur la case
     * @return false si pas vide, true sinon
     */
    public boolean estVide(){
        return (pieceCourante == null && pionFantome == null);
    }

    /**
     * Vérifie s'il y a une pièce sur la case
     * @return false si pas vide, true sinon
     */
    public boolean aUnePiece(){
        return pieceCourante != null;
    }

    /**
     * Vérifie s'il y a un pion fantôme sur la case
     * @return false si pas vide, true sinon
     */
    public boolean aUnPionFantome(){
        return pionFantome != null;
    }

    /**
     * Getter de la pièce courante
     * @return la pièce courante
     */
    public Piece getPieceCourante() {return pieceCourante;}

    /**
     * Setter de la pièce courante
     * @param piece Pièce à mettre sur la case
     */
    public void placerPiece(Piece piece){
        pieceCourante = piece;
    }

    /**
     * Setter du pion fantôme
     * @param pion Pion fantôme à mettre sur la case
     */
    public void placerPionFantome(Pion pion){
        pionFantome = pion;
    }

    /**
     * Supprimer la pièce courante
     * @return la pièce qui vient d'être supprimée
     */
    public Piece supprimerPiece(){
        Piece p = pieceCourante;
        pieceCourante = null;
        return p;
    }

    /**
     * Supprime le pion fantôme courant
     * @return le pion fantôme qui vient d'être supprimé
     */
    public Pion supprimerPionFantome(){
        Pion p = pionFantome;
        pionFantome = null;
        return p;
    }

    /**
     * Getter de la valeur de l'axe X de la case
     * @return position X
     */
    public int getX() {
        return x;
    }

    /**
     * Getter de la valeur de l'axe Y de la case
     * @return position Y
     */
    public int getY() {
        return y;
    }
}
