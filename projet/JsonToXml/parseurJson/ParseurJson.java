package parseurJson;

import java.io.BufferedReader; 
import java.io.File; 
import java.io.FileReader; 
import java.io.IOException; 
import net.sf.json.JSONException;


public class ParseurJson { 

/** 
* @param args 
* @throws IOException 
* @throws JSONException 
*/ 
	
		public static void main(String[] args) throws IOException, JSONException {
			// TODO Auto-generated method stub 
			
			File f = new File("fichierJson.txt"); 
			FileReader fr = new FileReader(f); 
			BufferedReader br = new BufferedReader(fr); 
			StringBuffer sb = new StringBuffer(); 
			String eachLine = br.readLine(); 

			while (eachLine != null) {
				sb.append(eachLine); 
				eachLine = br.readLine(); 
			}

			String readFile = sb.toString(); 
			System.out.println(readFile); 
		}

} 