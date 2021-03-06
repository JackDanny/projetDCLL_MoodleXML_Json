/**
 * package test pour derniere etape XMLtoJson
 */
package jsonWriter;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.jdom2.Element;

import filecompare.ComparateurImpl;

import xmltojson.jsonWriter.ToJson;

//TODO JAVADOC + Completer les cas de tests

/**
 * Test classe ToJson assoçiée.
 * @author Raphaël
 *
 */
public class ToJsonTest extends TestCase {

    /**
     * Attribut prive ToJson.
     */
    private ToJson tj;
    /**
     * Element prive.
     */
    private Element e;

    /**
     * Constructeur ToJsonTest.
     * @param name nom
     */
    public ToJsonTest(final String name) {
        super(name);
    }

    /**
     * Set up.
     * @throws Exception
     */
    protected final void setUp() throws Exception {
        super.setUp();
        tj = new ToJson("src/test/resources/USE_TrueFalse_AUTOGEN.json");
        e = new Element("Test");
    }

    /**
     * tear down.
     * @throws Exception
     */
    protected final void tearDown() throws Exception {
        super.tearDown();
        tj = null;
        e = null;
    }

    /**
     * Premier Test.
     */
    public final void testTraductionJson() {
        tj.toJson("src/test/resources/USE_TrueFalse_RSC.xml");
        ComparateurImpl comparator = new ComparateurImpl();
        String path1 =
                new String("src/test/resources/USE_TrueFalse_AUTOGEN.json");
        String path2 =
                new String("src/test/resources/USE_TrueFalseCompare_RSC.json");
        assertTrue(comparator.compare(path1, path2));

        tj = null;
        tj = new ToJson("src/test/resources/USE_exemple_AUTOGEN.json");
        tj.toJson("src/test/resources/USE_exemple_RSC.xml");
        path1 = new String("src/test/resources/USE_exemple_AUTOGEN.json");
        path2 = new String("src/test/resources/USE_exempleCompare_RSC.json");
        assertTrue(comparator.compare(path1, path2));

        tj = null;
        tj = new ToJson("src/test/resources/USE_TestLimite_AUTOGEN.json");
        tj.toJson("src/test/resources/USE_TestLimite_RSC.xml");
        path1 = new String("src/test/resources/USE_TestLimite_AUTOGEN.json");
        path2 = new String("src/test/resources/USE_TestLimiteCompare_RSC.json");
        assertTrue(comparator.compare(path1, path2));

        tj = null;
        tj = new ToJson("src/test/resources/USE_XmlWriterTest_AUTOGEN.json");
        tj.toJson("src/test/resources/USE_XmlWriterTest_RSC.xml");
        path1 =
                new String("src/test/resources/USE_XmlWriterTest_AUTOGEN.json");
        path2 =
          new String("src/test/resources/USE_XmlWriterTestCompare_RSC.json");
        assertTrue(comparator.compare(path1, path2));
    }

    /**
     * Teste la fonction cptEquals lorsqu' elle doit renvoyer 0.
     */
    public final void testCptEquals0() {
        List<Element> elems = new ArrayList<Element>();
        Element e2 = new Element("Name");
        assertEquals("CptEquals sur liste vide.",
                0, tj.cptEquals(elems, e2, 0));
        elems.add(e);
        assertEquals("CptEquals sur liste 1 element.",
                0, tj.cptEquals(elems, elems.get(0), 0));
        elems.add(new Element("autreElement"));
        assertEquals("CptEquals sur liste sans elem communs.",
                0, tj.cptEquals(elems, elems.get(0), 0));

    }

    /**
     * Teste la fonction cptEquals lorsqu' elle doit renvoyer != 0.
     */
    public final void testCptEquals1() {
        List<Element> elems = new ArrayList<Element>();
        Element e2 = new Element("Name");
        elems.add(e);
        elems.add(e);
        assertEquals("CptEquals sur elem identique en debut liste.",
                1, tj.cptEquals(elems, e, 0));
        elems.clear();
        elems.add(e2);
        elems.add(e);
        elems.add(e);
        assertEquals("CptEquals sur sur elem identique en fin liste.",
                1, tj.cptEquals(elems, e, 1));
        elems.add(e);
        elems.add(e2);
        assertEquals("CptEquals sur elem identiques en milieu liste",
                2, tj.cptEquals(elems, e, 1));

    }

    /**
     * Teste la fonction addChild.
     */
    public final void testAddChild() {
        assertFalse("Un element vide a des fils", tj.addChild(e));
        e.addContent(new Element("Fils"));
        assertTrue("Un element avec fils non détecté", tj.addChild(e));
    }

    /**
     * Teste la fonction addAttributes.
     */
    public final void testAddAttributes() {
        assertFalse("Un element vide a des attributs", tj.addAttributes(e));
        e.setAttribute("Attribut", "attributDeTest");
        assertTrue("Un element avec attributs non détectés"
                , tj.addAttributes(e));
    }



}
