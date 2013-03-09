package nomenclature;

import java.util.Set;
import java.util.TreeSet;

public class CommonFields {

	private Set<String>  commonFields = new TreeSet<String>();

	public CommonFields(){
		commonFields.add("penalty");
		commonFields.add("generalfeedback");
		commonFields.add("defaultgrade");
		commonFields.add("hidden" );
		commonFields.add("name");
		commonFields.add("questiontext");
		commonFields.add("image");
		commonFields.add("image_base64");
	}
	
	public boolean contains(String fild){
		return commonFields.contains(fild);
	}
	
}

