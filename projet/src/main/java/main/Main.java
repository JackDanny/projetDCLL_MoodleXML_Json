package main;

import xmlparser.XmlParserImpl;

/**
 * 
 * @author Rapha�l
 *
 */
public class Main {

	
	/** 
	 * @param args florent add test
	 */
	public static void main(String[] args) {
		System.out.println("deb main");
		XmlParserImpl xmlparser = new XmlParserImpl();
		System.out.println("deb main");
		xmlparser.parser(args[0]);
	}

}
