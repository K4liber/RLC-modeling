package prodev.Main;
import java.io.IOException;

import prodev.GraphicsInterface.MainFrame;

public class Main {
	
	private static MainFrame frame;
	static FileManager file;
	
	public static void main(String[] args) throws IOException {		
		frame = new MainFrame();
		frame.setVisible(true);
		file = new FileManager();
		file.loadFilesNames();
	}
}