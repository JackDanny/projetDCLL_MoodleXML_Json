package xmltojson.jsonWriter;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
	
	//en cours d'elaboration ne fonctionne pas
	public void indempter() {
	    File file = new File(this.pathFile);
	    int indLvl = 0;
	    
	    FileReader fr;
	    FileWriter fw;
        try {
            fr = new FileReader(file);
            fw = new FileWriter(file, true);
            int c = fr.read();
            while (c != -1) {
                indLvl = traitementCar((char)c, indLvl, fw);
                
            }
            fr.close();
            fw.flush();
            fw.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	    
	    
//	    try {
//	        FileReader flotLecture = new FileReader(file);
//	        FileWriter flotEcriture = new FileWriter(file);
//	        long longueurFichier= file.length();
//	        int dejaLu = 0;
//	        char car=0;
//	        while (/*fichier non parcouru en entier*/) {
//	          car = (char)flotLecture.read();
//	          dejaLu = dejaLu + 1;
//	          indLvl = traitementCar(car, indLvl, flotEcriture);
//	        }
//	        flotLecture.close();
//	        flotEcriture.flush();
//	        flotEcriture.close();
//	      } catch (IOException e) {
//	        System.out.println(" erreur :" + e.toString());
//	      }    
	}

    public int traitementCar(char car, int indLvl, FileWriter fw) {
        if ( (car == '{') || (car == '[') ) {
            try {
                fw.write("\n");
                indLvl++;
                for (int i = 0; i < indLvl; i++) {
                    fw.write("\t");
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } 
        
        if ( (car == '}') || (car == ']') ) {
            try {
                fw.write("\n");
                indLvl--;
                for (int i = 0; i < indLvl; i++) {
                    fw.write("\t");
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
      
        
        
        return indLvl;
    }
}
