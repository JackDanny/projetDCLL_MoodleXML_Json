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
	private String pathFile;
	
	public SaveFileJson(String contenu, String pathFile){
		this.jsonContent = contenu;
		this.pathFile = pathFile;
	}

	public void sauvegarde (){
				
		File file = new File(this.pathFile);

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
					+ pathFile + "'");
		}
	}
}