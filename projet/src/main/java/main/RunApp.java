package main;

import java.io.InputStream;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;
import net.sf.json.xml.JSONTypes;
import net.sf.json.xml.XMLSerializer;

import org.apache.commons.io.IOUtils;
import java.io.InputStream;

import net.sf.json.JSON;
import net.sf.json.xml.XMLSerializer;

import org.apache.commons.io.IOUtils;

public class RunApp {

	public static void main(String[] args) throws Exception {
		InputStream is = 
			RunApp.class.getResourceAsStream("exemple.xml");
		String xml = IOUtils.toString(is);
		
		XMLSerializer xmlSerializer = new XMLSerializer(); 
		JSON json = xmlSerializer.read( xml );  
		System.out.println( json.toString(2) );
	}
}
