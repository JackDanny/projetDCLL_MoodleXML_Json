package main;

import xmlParser.XmlParser;

/**
 * 
 * @author Raphaël
 *
 */
public class Main {

	/**
	 * @param args florent add test
	 */
	public static void main(String[] args) {
		System.out.println("deb main");
		XmlParser xmlparser = new XmlParser();
		xmlparser.parser(args[0]);
	}

}
