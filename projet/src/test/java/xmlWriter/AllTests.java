package xmlWriter;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

    public static Test suite() {
        TestSuite suite = new TestSuite(AllTests.class.getName());
        //$JUnit-BEGIN$
    //    suite.addTestSuite(MultichoiceTests.class);
        suite.addTestSuite(CategoryTests.class);
        suite.addTestSuite(TruefalseTests.class);

        //$JUnit-END$
        return suite;
    }

}
