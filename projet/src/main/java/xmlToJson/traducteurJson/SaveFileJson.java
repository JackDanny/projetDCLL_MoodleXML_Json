package xmlToJson.traducteurJson;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


/**
 * 
 * @author nawal
 *
 */
public class SaveFileJson {
	
	private String jsonContent; 
	
	public SaveFileJson(String contenu){
		this.jsonContent = contenu;
	}

	public void sauvegarde (){
		String cheminDuFichier = "src/test/resources/fichierJsonResultat.txt";		
		
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