package prodev.GraphicsInterface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;

import prodev.Main.Main;

public class Translator {

	public Translator() {
		
	}

	public String getTranslatedPhrase(String phrase) throws IOException{
		String translatedPhrase = phrase;
		if(Main.getLanguage() == "POL"){
			InputStreamReader streamReader;
			URL source = getClass().getResource("dictionary");
		    streamReader = new InputStreamReader(source.openStream(), Charset.forName("UTF-8")); 
	        BufferedReader bufferedReader = new BufferedReader(streamReader); 
	        String line;
	        String results[] = null;
	        line = bufferedReader.readLine();
	        while(!line.contains(phrase)){
	        	line = bufferedReader.readLine();
	        }
	        if(line != null){
		        results = line.split(":");
		        translatedPhrase = results[1];
	        }
		}
		return translatedPhrase;
	}
}
