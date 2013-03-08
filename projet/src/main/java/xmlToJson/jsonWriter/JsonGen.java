package xmlToJson.jsonWriter;

import net.sf.json.JSONObject;


public class JsonGen {
	
	/**
	 * @author nawal 
	 */
	
	public JsonGen (){		
	}
	

	
	public JSONObject simpleElement (JSONObject oJson, String nomBalise, String valeurBalise) throws Exception{
		/*
		 * cette méthode permet de créer un element simple c'est à dire : 
		 * <nomBalise>valeurBalise</nomBalise>  --> {"nomBalise": "valeurBalise"}		 
		 */
		oJson.put(nomBalise,valeurBalise);
		return null;	
	}	
	
	
	public JSONObject baliseObjet (JSONObject oJson, JSONObject object, String nomObject) throws Exception{
		/*
		 * cette méthode permet de créer un object c'est à dire : 
		 * 
		 *  <menu>										{"menu":
		 * 		<attribut2>valeur2</attribut2>    --->         {"attribut2": "valeur2"}   
		 *	</menu>                      				}
	     *
		 */
		oJson.put(nomObject,object);
		return null;	
	}
	

	
	
	
	public JSONObject VecteurElementSimple (JSONObject oJson, String NomVecteur, String nomElement){
		
		/*
		 * cette méthode permet de créer un vecteur contenant des élements simple c'est à dire : 
		 * 
		 *   <NomVecteur>nomElement1</vecteur>                     {"NomVecteur":   [    
	     *   <NomVecteur>nomElement2</vecteur>            -->   		 "nomElement1", "nomElement2" 
    	 *			    										                ]	
    	 *	   													   }
		 */		
		
		oJson.accumulate(NomVecteur,nomElement);				
		
		return oJson;		
	}
	
	
	
	
	public JSONObject VecteurListElement (JSONObject oJson, String NomVecteur, JSONObject object){		
		
		/*
		 * Cette méthode permet de créer un vecteur contenant une liste d'élements Objects c'est à dire :
		 * 
		 * <NomVecteur>										{ "NomVecteur" :  [
				<attribut1>valeur1</attribut1>						{"attribut1": "valeur1"},
		   </NomVecteur>			               ---->	        {"attribut2": "valeur2"}
		   <NomVecteur>														]
		        <attribut2>valeur2</attribut2>               }
           </NomVecteur>
           
		 */
		
		oJson.accumulate(NomVecteur,object);				
		
		return oJson;		
	}
	
	
	
}
