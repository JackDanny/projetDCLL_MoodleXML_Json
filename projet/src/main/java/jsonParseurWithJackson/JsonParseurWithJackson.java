package jsonParseurWithJackson;

import java.io.Serializable;

public class JsonParseurWithJackson implements Serializable, Comparable<Oject>> {
	
	private static final long serialVersionUID = 6726511922105682780L;
	
	private String name;
	private String nickname;
	
	public JsonParseurWithJackson(){}
	
	public JsonParseurWithJackson(String name, String nickname) {
		this.setName(name);
		this.setNickname(nickname);
	}
	public String toString() {
		return "Membre [name=" + name + ", nickname="+ nickname + "]";
	}
	public int compareTo(Object o) {
		JsonParseurWithJackson m= (JsonParseurWithJackson) o;
		return (name.compareTo(m.getName()));
		}
	public String getName() {
		return name;
	}
 
	public void setName(String name) {
		this.name = name;
	}
 
	public String getNickname() {
		return nickname;
	}
 
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

}
