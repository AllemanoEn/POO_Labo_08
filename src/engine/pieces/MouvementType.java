package engine.pieces;

public enum MouvementType {
    CLASSIQUE,
    NON_VALIDE,
    PRISE, //Est-ce que c'est vraiment util ? Dans tous les cas lros d'un déplacement d'une piece si il y a une piece sr son passage elle disparait
    GRAND_ROQUE,
    PETIT_ROQUE,
    PROMOTION,
    DOUBLE,
    EN_PASSANT
}
