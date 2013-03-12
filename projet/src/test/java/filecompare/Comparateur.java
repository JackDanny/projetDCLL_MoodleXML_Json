package filecompare;

/**
 * 
 * comparateur interface
 * 
 *
 */


public interface Comparateur {

	
	/**
	* @param filename1 , filename2 the XML or json file names
	 * @return a boolean:
	 * true if the two files are the same
	 * else false
	 * 
	 * */
	public boolean compare(String filename1,String filename2);




}
