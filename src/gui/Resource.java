package gui;

import java.net.URL;

final public class Resource {
	
	public static URL load(String path) {
		
		URL input = Resource.class.getResource(path);
		if (input == null) {
			input = Resource.class.getResource("/" + path);
		}
		
		return input;
	}

}
