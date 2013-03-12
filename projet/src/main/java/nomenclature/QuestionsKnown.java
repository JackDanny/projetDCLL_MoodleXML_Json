package nomenclature;

import java.util.Set;

public class QuestionsKnown {
    private Set<String> questionType;

    
    public QuestionsKnown(){
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

    
 public boolean isKnownQuestion(String type){
    return questionType.contains(type);
 }
    
}