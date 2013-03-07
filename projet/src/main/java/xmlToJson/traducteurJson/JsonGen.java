package xmlToJson.traducteurJson;

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
	
	
	public JSONObject simpleElement (JSONObject oJson, String nomBalise, String valeurBalise) throws Exception{
		
		oJson.put(nomBalise,valeurBalise);
		return null;	
	}	
	
	
	public JSONObject baliseObjet (JSONObject oJson, JSONObject object, String nomObject) throws Exception{
	
		oJson.put(nomObject,object);
		return null;	
	}
	

	
	
	/**
	 * 
	 * @param oJson : l'élement 
	 * @param NomVecteur pour cet exemple : menuitem": [
      									{"value": "New", "onclick": "CreateNewDoc()"},
      									{"value": "Open", "onclick": "OpenDoc()"},
      									{"value": "Close", "onclick": "CloseDoc()"}
      									
      							nomVecteur c'est "menuitem" 
    ]
	 * @param nomElement 
	 * @return
	 */
	
	
	public JSONObject VecteurElementSimple (JSONObject oJson, String NomVecteur, String nomElement){
		
		oJson.accumulate(NomVecteur,nomElement);				
		
		return oJson;		
	}
	
	public JSONObject VecteurListElement (JSONObject oJson, String NomVecteur, JSONObject object){		
		
		oJson.accumulate(NomVecteur,object);				
		
		return oJson;		
	}
	
	
	
}
