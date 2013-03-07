package xmlToJson.traducteurJson;

import java.util.ArrayList;

import net.sf.json.JSON;
import net.sf.json.JSONObject;


public class JsonGen {
	
	/**
	 * @author nawal 
	 */
	
	public JsonGen (){		
	}
	
	
	/**
	 * @param oJson : l'objet, nomBalise : ex : menu 
	 * la méthode baliseSimple : 
	 *    exemple : <menu> </menu>  ---> {"menu": {}}     
	 *    
	 */
	
	public JSONObject baliseSimple (JSONObject oJson, String nomBalise) throws Exception{
		
		JSONObject balise = new JSONObject();
		oJson.put(nomBalise, balise);
		return null;	
	}
	
	
	
	/**
	 * @param oJson : l'objet ,  ArrayList<ArrayList<String>> : liste de liste : une liste d'attributs
	 * dans la liste1nivea : c'est un attribut qui est une liste tel que : element 1 : nom de l'attribut, 
	 * 																	   element 2 : valeur de l'attribut
	 * [[attribut1, valeurAttribut1],[attribut2, valeurAttribut2], .........]
	 *   
	 *    
	 */
	
	public JSONObject attribute(JSONObject oJson, ArrayList<ArrayList<String>> listAttributs){
		
		for(int i=0; i<listAttributs.size(); i++){
			oJson.put(listAttributs.get(i).get(0),listAttributs.get(i).get(1));
		}
		return oJson;		
	}
	
	
	public JSONObject Vecteur (JSONObject oJson, String NomVecteur, String nomElement){
		
		oJson.accumulate(NomVecteur,nomElement);	
		oJson.accumulate(NomVecteur,nomElement);	
		
		return oJson;		
	}
}
