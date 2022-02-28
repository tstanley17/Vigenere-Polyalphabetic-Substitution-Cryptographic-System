package cryptography;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ReadFolder {
	
	public ReadFolder() {
		super();
	}
	
	public String readFolder(String file) {
		ArrayList<String> LineList = new ArrayList();
		
		try {
			BufferedReader dis = new BufferedReader(new InputStreamReader(new BufferedInputStream(new FileInputStream(file))));
 
			StringBuffer inputLine = new StringBuffer();
		    String tmp;
		    while ((tmp = dis.readLine()) != null) {
		      inputLine.append(tmp);
		    }
		    
		    return inputLine.toString();
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return e.getMessage();
		}
	}
}
