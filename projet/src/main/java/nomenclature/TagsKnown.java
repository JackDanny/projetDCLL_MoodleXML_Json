package nomenclature;

import java.util.Set;
import java.util.TreeSet;

public class TagsKnown {

    /**Ensemble de balises simple connues.*/
    transient final private Set<String> knownTags;

    public TagsKnown(){
        knownTags = new TreeSet<String>();
        knownTags.add("image");
        knownTags.add("image_base64");
        knownTags.add("penalty");
        knownTags.add("hidden");
        knownTags.add("defaultgrade");
        knownTags.add("single");
        knownTags.add("answernumbering");
        knownTags.add("usecase");
        knownTags.add("generalfeedback");
        knownTags.add("name");
        knownTags.add("correctfeedback");
        knownTags.add("partiallycorrectfeedback");
        knownTags.add("incorrectfeedback");
        knownTags.add("category");
        knownTags.add("subquestion");
        knownTags.add("name");
        knownTags.add("dataset_item");
        knownTags.add("dataset_items");
        knownTags.add("decimals");
        knownTags.add("maximum");
        knownTags.add("minimum");
        knownTags.add("itemcount");
        knownTags.add("value");
        knownTags.add("number_of_items");
        knownTags.add("dataset_definition");
        knownTags.add("dataset_definitions");
        knownTags.add("distribution");
        knownTags.add("status");
        knownTags.add("units");
        knownTags.add("type");
    }
    
    public boolean isKnown(String tagName){
        return knownTags.contains(tagName);
    }
    
}
