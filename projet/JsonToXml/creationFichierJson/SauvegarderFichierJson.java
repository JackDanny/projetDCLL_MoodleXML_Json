package creationFichierJson;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SauvegarderFichierJson {
	
	private String jsonContent; 
	
	public SauvegarderFichierJson(String contenu){
		this.jsonContent = contenu;
	}

	public void sauvegarde (){
		String cheminDuFichier = "JsonToXml/creationFichierJson/fichierJsonResultat.txt";		
		
		File file = new File(cheminDuFichier);

		try {
			if (!file.exists())
				file.createNewFile();
			FileWriter writer = new FileWriter(file);
			writer.write(this.jsonContent);
			writer.flush();
			writer.close();

			System.out.println("fichier crée ");
		} catch (IOException e) {
			System.out.println("Erreur: impossible de créer le fichier '"
					+ cheminDuFichier + "'");
		}
	}
}