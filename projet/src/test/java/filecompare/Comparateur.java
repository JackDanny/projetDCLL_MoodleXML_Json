package filecompare;

/**
 * comparateur interface.
 *
 *
 */

public interface Comparateur {

/**
* @param filename1 XML or json file names
* @param filename2 XML or json file names
* @return a boolean: true if the two files are the same else false
*
*/
   boolean compare(final String filename1, final String filename2);

}
