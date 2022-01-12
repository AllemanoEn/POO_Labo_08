/* ---------------------------
Laboratoire : Labo 08: Jeu d'échecs
Fichier : MouvementType.java
Auteur(s) : Romano Malo et Enzo Allemano
Date : 12.01.2022

But : Déclaration de l'enum : MouvementType

Remarque(s) : -

Compilateur : jdk1.8.0_221

--------------------------- */
package engine.pieces;

public enum MouvementType {
    CLASSIQUE,
    NON_VALIDE,
    GRAND_ROQUE,
    PETIT_ROQUE,
    PROMOTION,
    DOUBLE,
    EN_PASSANT
}
