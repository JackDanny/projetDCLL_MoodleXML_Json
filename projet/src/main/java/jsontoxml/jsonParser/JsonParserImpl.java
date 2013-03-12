package jsontoxml.jsonParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

import jsontoxml.xmlWriter.XmlWriter;
import jsontoxml.xmlWriter.XmlWriterImpl;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;


/**
 * Implementation of JsonParserImpl.
 * parse a Json File
 * */


public class JsonParserImpl implements JsonParser {
    /**
     * @param filename le fichier Json
     * */

    public final void parser(String filename) {
        Reader reader= null;
        JSONObject o = null;
        JSONArray i = null;
        XmlWriter xmlWriter = new XmlWriterImpl();

        try {
            reader = new FileReader(filename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }				

        JSONTokener jsonT = new JSONTokener(reader);
        try {
            o = new JSONObject(jsonT);
            o = o.getJSONObject("quiz");
            // test pour différencier entre une question (type object) de plusieurs question( type array )
            if(o.optJSONArray("question")==null){
                o = o.getJSONObject("question");
                xmlWriter.writeXmlToJson(o, renomFile(filename));
            }
            else{
                i = o.getJSONArray("question");
                xmlWriter.writeXmlToJson(i, renomFile(filename));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    /**
     * 
     * @param filename nom de fichier Json
     * @return le fichier avec maintenant l'extension xml
     */
    private String renomFile(String filename) {
        //on recupere le nom du fichier sans son extension
        String nomCourt = filename.substring(0,filename.indexOf('.'));
        //on rajoute l'extension xml
        String nomxml=nomCourt+".xml";
        return nomxml;


    }
}
