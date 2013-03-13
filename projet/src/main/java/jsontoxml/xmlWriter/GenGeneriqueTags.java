package jsontoxml.xmlWriter;

import java.util.Iterator;

import org.jdom2.Element;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Créé de manière récursive l'arboresence XML
 * à partir d'objet JSON.
 * */
public class GenGeneriqueTags {

    /**
     * Génére les touts les éléments XML d'un JSONObject.
     * N'est utilisé que pour les balises (clé) inconnues.
     * @param jsonO : l'objet JSON de la question Moodle en cours
     * @param rootBase : la racine où doit être placé les nouveaux éléments.
     * @param key : le nom de la clé inconnue
     * @throws JSONException exception JSON
     * */
    public static void genBaseComplexElem(final JSONObject jsonO
            , final String key, final Element rootBase) throws JSONException {
        if (null != jsonO.optJSONArray(key)) { //si tableau
            genRecComplexElemArray(jsonO.getJSONArray(key), key, rootBase);
        } else { //sinon
            Element root = new Element(key); //création de l'élément racine
            if (null != jsonO.optJSONObject(key)) { //si objet complexe
                //contruction de l'objet
                genRecComplexElem(jsonO.getJSONObject(key), key, root);
            } else { //sinon simple
                if (!jsonO.isNull(key)) { //si valeur non null
                    root.addContent(jsonO.getString(key)); //ajout de la valeur
                } else {
                    root.addContent(""); //sinon ajout vide
                }
            }
            rootBase.addContent(root);
        }
    }

    /**
     * Ajoute récursivement les éléments à un élément
     * racine XML à partir d'un object JSON.
     * @param jsonO : l'élément Json à convertir en XML
     * @param name : le nom de l'élément Json
     * @param root : la racine d'élément.
     * @throws JSONException exception JSON
     * */
    private static void genRecComplexElem(final JSONObject jsonO
             , final String name, final Element root) throws JSONException {
        Element child = null;
        Iterator<String> it = jsonO.keys();
        while (it.hasNext()) {
            String key = it.next();
            child = new Element(key);
            if (null != jsonO.optJSONArray(key)) { //si tableau
                genRecComplexElemArray(jsonO.getJSONArray(key), key, root);
            } else {
                if (null != jsonO.optJSONObject(key)) { //si objet
                    genRecComplexElem(jsonO.getJSONObject(key), key, child);
                } else { //sinon simple
                    if (!jsonO.isNull(key)) {
                        child.addContent(jsonO.getString(key));
                    } else {
                        child.addContent("");
                    }
                }
                root.addContent(child);
            }
        }
    }

    /**
     * Ajoute récursivemen les éléments correspondant
     * à un tableau Json.
     * @param jsonA : le tableau Json
     * @param name : le nom du tableau
     * @param root : la racine où placé
     * les nouveaux éléments.
    * @throws JSONException exception JSON
    */
    private static void genRecComplexElemArray(final JSONArray jsonA
            , final String name, final Element root)
                    throws JSONException {
        Element child;
        for (int i = 0; jsonA.length() > i; ++i) {
            child = new Element(name);
            if (null != jsonA.optJSONArray(i)) { //si tableau
                genRecComplexElemArray(jsonA.getJSONArray(i), name, child);
            } else if (null != jsonA.optJSONObject(i)) { //si objet
                genRecComplexElem(jsonA.getJSONObject(i), name, child);
            } else { //sinon simple
                if (!jsonA.isNull(i)) {
                    child.addContent(jsonA.getString(i));
                } else {
                    child.addContent("");
                }
            }
            root.addContent(child);
        }
    }


}
