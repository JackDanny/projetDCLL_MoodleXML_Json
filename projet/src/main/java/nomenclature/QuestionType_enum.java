package nomenclature;

/*
 * Enumération des différent types de question Moodel
 *
 * deplus contient une méthode indiquant si le type de question est implémenté.
 * exemple :
         QuestionType_enum qType_enum = QuestionType_enum.valueOf("category");
         qType_enum.isImplemented() // true

         QuestionType_enum qType_enum = QuestionType_enum.valueOf("cloze");
         qType_enum.isImplemented() // false

         QuestionType_enum.calculated.isImplemented() // true


    Remarque : Quand un nouveau type de question est implémenté il suffit de
    metre true à la place de false
 **/

/**

 * @author florent
 *
 *  */

public enum QuestionType_enum {
/**
 * */
category(true), /***/
calculated(true), /***/
description(false), /***/
essay(false),  /***/
matching(false), /***/
cloze(false), /***/
multichoice(false), /***/
numerical(false), /***/
shortanswer(false), /***/
truefalse(true); /***/

/**
 * */
private boolean isImplement;
/**
 * @param isImplemented value
 * */
QuestionType_enum(final boolean isImplemented) {
    this.isImplement = isImplemented;
}

/**
 * @return indicate if the attribute is implemented
 * */
public boolean isImplemented() {
    return isImplement;
}

}

