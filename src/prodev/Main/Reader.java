package prodev.Main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class Reader {

    public static void main(String[] args) throws IOException {
      
    }
    
    public static void load() throws IOException{
    	InputStream inputStream = Reader.class.getResourceAsStream("rekopis.txt"); //Otwieramy plik
        InputStreamReader streamReader
                = new InputStreamReader(inputStream, Charset.forName("UTF-8")); //Otwieramy readera
        BufferedReader bufferedReader = new BufferedReader(streamReader); //Buforujemy radera
        String line = bufferedReader.readLine();
        while (line!= null){
            System.out.println(line);
            line = bufferedReader.readLine();
        }
    }
}