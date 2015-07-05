package prodev.Main;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import prodev.GraphicsInterface.MainFrame;
import prodev.GraphicsInterface.RightTopPanel;

public class FileManager {
	
	static File loadingFile = null;
	public File savingFile = null;
	String[] filesNames;
	int fileIndex = 0;
	private ActionListener saveButtonListener;
	
	public void load(String name) throws IOException{
		InputStreamReader streamReader;
		if(name == "Load From File"){
			streamReader = new InputStreamReader(new FileInputStream(loadingFile), Charset.forName("UTF-8"));
		}else{
			URL source = getClass().getResource("savedFiles/" + name);
	    	streamReader = new InputStreamReader(source.openStream(), Charset.forName("UTF-8")); 
		}
        BufferedReader bufferedReader = new BufferedReader(streamReader); //Buforujemy radera
        
        //Zerujemy wszystkie warto�ci
        for(int zz=0;zz<5;zz++){
        	RightTopPanel.elements.get(zz).valueField.setText("");
        	RightTopPanel.elements.get(zz).powerField.setText("");
        	if(zz != 4 && RightTopPanel.elements.get(zz).checkBox.isSelected())
        		RightTopPanel.elements.get(zz).checkBox.setSelected(false);
        }
        try{
	        String line = bufferedReader.readLine();
	        int ii=0;
	        while (ii<5){ 
	        	if(line == "-"){
	        		ii++;
	        		continue;
	        	}
	        	String results[] = null;
	        	if(line.contains(" ")){
		        	results = line.split(" ");
		        	
		        	try{
			        	Float.valueOf(results[0]);
			        	Float.valueOf(results[1]);
		        	}catch(NumberFormatException ex){
		        		JOptionPane.showMessageDialog(null, "Niepoprawna wartosc!");
		        	}
		        	
		        	RightTopPanel.elements.get(ii).valueField.setText(results[0]);
		        	RightTopPanel.elements.get(ii).powerField.setText(results[1]);
		        	if(ii != 4)
		        		RightTopPanel.elements.get(ii).checkBox.setSelected(true);
	        	}
	            line = bufferedReader.readLine();
	            ii++;
	        }
        }
        catch(Exception e){
        	JOptionPane.showMessageDialog(null, "Niepoprawny format wczytywanego pliku!");	
        }
    }
	
	public void save() throws IOException{
		OutputStream outPutStream = new FileOutputStream(savingFile);
		BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outPutStream));
		for(int ii=0;ii<5;ii++){
			String value = RightTopPanel.elements.get(ii).valueField.getText();
			String power = RightTopPanel.elements.get(ii).powerField.getText();
			if(value != null && value.length() == 0 || power != null && power.length() == 0)
				bufferedWriter.write("-");
			else
				bufferedWriter.write(value + " " + power);
			bufferedWriter.newLine();
		}
		bufferedWriter.close();
    	loadFilesNames();
    }
	
	public void loadFilesNames(){
        String line = null;
        fileIndex = 0;
        filesNames = new String[25];
        try{
        	InputStream stream = this.getClass().getResourceAsStream("savedFiles/");
            Scanner src= new Scanner(stream);
            while(src.hasNext()){
                line = src.next();
                filesNames[fileIndex] = line.toString();   
                fileIndex++;
            }
            src.close();
            
            for(int ii=0;ii<fileIndex;ii++){
            	final String name = filesNames[ii];
	            JMenuItem loadItem = new JMenuItem(name);
	            loadItem.addActionListener(new ActionListener() {
	    			public void actionPerformed(ActionEvent e) {
	    				try {
							Main.frame.getFile().load(name);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
	    			}
	    		});
	            loadItem.setBackground(new Color(220,220,254));
	            MainFrame.getLoadItems().add(loadItem);
            }
            
        }catch(Exception e){
            
        }
	}
	
	
	public FileManager() {
		JMenuItem saveItem = new JMenuItem("Zapisz jako");
		JMenuItem loadItem = new JMenuItem("Wczytaj z pliku");
		
        saveItem.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	JFileChooser chooser = new JFileChooser(); // Stworzenie klasy
		        chooser.setDialogTitle("Wybierz plik"); // Ustawienie tytułu okienka
		        int result = chooser.showDialog(null, "Wybierz"); //Otwarcie okienka. Metoda ta blokuje się do czasu wybrania pliku lub zamknięcia okna
		        if (JFileChooser.APPROVE_OPTION == result){ //Jeśli użtytkownik wybrał plik
		            savingFile = chooser.getSelectedFile();
		            try {
						save();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		        }
		    }
		});
        
        loadItem.addActionListener( new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	JFileChooser chooser = new JFileChooser(); // Stworzenie klasy
		        chooser.setDialogTitle("Wybierz plik"); // Ustawienie tytułu okienka
		        int result = chooser.showDialog(null, "Wybierz"); //Otwarcie okienka. Metoda ta blokuje się do czasu wybrania pliku lub zamknięcia okna
		        if (JFileChooser.APPROVE_OPTION == result){ //Jeśli użtytkownik wybrał plik
		            loadingFile = chooser.getSelectedFile();
		            try {
						load("Load From File");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		        }else {
		        }
		    }
		});
        
        MainFrame.save.add(saveItem);
        MainFrame.load.add(loadItem);
        
	}

	public ActionListener getSaveButtonListener() {
		return saveButtonListener;
	}

	public void setSaveButtonListener(ActionListener saveButtonListener) {
		this.saveButtonListener = saveButtonListener;
	}
	
}
