package xmlWriter;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
* Tous les tests.
* */
public class AllTests {

    /**
    *
    * @return retourne un test.
    * */
    public static Test suite() {
        TestSuite suite = new TestSuite(AllTests.class.getName());
        //$JUnit-BEGIN$
        suite.addTestSuite(MultichoiceTests.class);
        suite.addTestSuite(CategoryTests.class);
        suite.addTestSuite(TruefalseTests.class);
   //     suite.addTestSuite(FullExempleTest.class);
        suite.addTestSuite(UnknownTagsTests.class);
        //$JUnit-END$
        return suite;
    }

}

