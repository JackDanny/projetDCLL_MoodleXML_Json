package creationFichierJson;


import net.sf.json.JSONObject;


public class GenérerFichierJSon {

    public static void main(String[] args) throws Exception {

    	JSONObject json = new JSONObject();
    	
    	
    	
    	// création d'un objet complexe :  
    	JSONObject menu = new JSONObject();
    	menu.put("id", "file");
    	menu.put("value", "File");
    	 // cet objet: "menu" est composé d'un élément composé " popus" 
    		JSONObject popup = new JSONObject();
    		
    		// cet élément " popos " est compsé d'une liste /vecteur "menuitem"
    		JSONObject menuitem = new JSONObject();
    			// chaque case du vecteur est une liste 
    		
    				JSONObject l = new JSONObject();
    				l.put("value", "value");
    				l.put("onclick", "CreateNewDoc()");
    				popup.accumulate("menuitem",l);  
    				
    				JSONObject l2 = new JSONObject();
    				l2.put("value", "Open");
    				l2.put("onclick", "OpenDoc()");
    				popup.accumulate("menuitem",l2);  
    				
    				
    				JSONObject l3 = new JSONObject();
    				l3.put("value", "Open");
    				l3.put("onclick", "OpenDoc()");
    				popup.accumulate("menuitem",l3);  

    		menu.put("popup", popup);
    		
     	json.put("menu",menu );
    	
    	System.out.println( "" + json.toString(2) );
    }
}