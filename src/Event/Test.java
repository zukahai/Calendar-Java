package Event;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Test {
    public static void main(String[] args) {
    	String FILE_URL = "Data.txt";
    	File file = new File(FILE_URL);
        InputStream inputStream;
		try {
			inputStream = new FileInputStream(file);
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
	        BufferedReader reader = new BufferedReader(inputStreamReader);
	 
	        String line = "";
	        try {
				while((line = reader.readLine()) != null){
				    System.out.println(line);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
