package jsonParser;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.node.ObjectNode;


public class Main {
//http://wiki.fasterxml.com/JacksonInFiveMinutes
	/**
	 * @param args
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		// TODO Auto-generated method stub
		//Full Data Binding (POJO) Example
		/*
		ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
		
	    
	    User user = mapper.readValue(new File("user.json"), User.class);
	    mapper.writeValue(new File("user-modified.json"), user);
	    */
	    /**
	* Parse a JSON HAR
	*
	* @param har a JSON String
	* @return the HarLog representation
	* @throws IOException if file not found
	*/
	//tree Model Example, voir 
		
		ObjectMapper m = new ObjectMapper();
	
		// can either use mapper.readTree(source), or mapper.readValue(source, JsonNode.class);
		JsonFactory f = new JsonFactory();
		JsonParser jp = f.createJsonParser(new File("src/test/resources/user.json"));
		JsonNode rootNode = m.readTree(jp);
		// ensure that "last name" isn't "Xmler"; if is, change to "Jsoner"
		JsonNode nameNode = rootNode.path("name");
		String lastName = nameNode.path("last").getTextValue();
		if ("xmler".equalsIgnoreCase(lastName)) {
		  ((ObjectNode)nameNode).put("last", "Jsoner");
		}
		// and write it out:
		m.writeValue(new File("user-modified.json"), rootNode);
	
	}
	
	

}
