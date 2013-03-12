package nomenclature;

import java.util.Set;

/**
*
 * */
public class QuestionsKnown {
    /**
    *
     * */
    private Set<String> questionType;

    /**
    *
     * */
    public QuestionsKnown() {
        questionType.add("category");
        questionType.add("calculated");
        questionType.add("description");
        questionType.add("essay");
        questionType.add("matching");
        questionType.add("multichoice");
        questionType.add("shortanswer");
        questionType.add("truefalse");
        questionType.add("numerical");
        questionType.add("cloze");
    }

/**
* Indique si une balise type est connue.
* @param type : la balise
* @return true si la balise est connue.
* false si la balise est inconnue.
 * */
 public boolean isKnownQuestion(final String type) {
    return questionType.contains(type);
 }

}
