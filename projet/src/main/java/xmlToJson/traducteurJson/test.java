package xmlToJson.traducteurJson;


import java.sql.Struct;
import java.util.ArrayList;

import net.sf.json.JSONObject;

/**
 * 
 * @author nawal
 *
 */
public class test {

	/**
	 * @param args
	 * @throws JSONException 
	 */
	
	
	public static void main(String[] args) throws Exception {
		String pathFile = "src/test/resources/fichier.json";
		
		JsonGen j = new JsonGen();    
		
    	JSONObject json = new JSONObject();  
    	
    	

    	
    	
    	JSONObject menu = new JSONObject();  
    	
    	
        j.simpleElement(json,"attribut","valeur");  
        
        j.simpleElement(menu,"attribut2","valeur2");        
        j.baliseObjet(json,menu,"menu");
        
        
        

    	JSONObject vecteur = new JSONObject(); 
    	j.VecteurElementSimple(vecteur,"vecteur", "nawal");
    	j.VecteurElementSimple(vecteur,"vecteur", "nawal"); 
    	
        j.baliseObjet(json, menu, "menu");           
      
        j.VecteurListElement(json, "vecteur2", menu);
        j.VecteurListElement(json, "vecteur2", menu);
    
    
    	
    	

    	//j.VecteurListElement(menu, "vecteur", menu);
    	
    //	j.baliseObjet(json, vecteur, "menu");
    	
    	System.out.print(json.toString(2));
    	
    	
    	SaveFileJson save = new SaveFileJson(json.toString(2),pathFile );
    	save.sauvegarde();

    	
    }

}
