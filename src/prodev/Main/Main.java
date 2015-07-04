package prodev.Main;
import java.io.IOException;

import prodev.GraphicsInterface.HelpFrame;
import prodev.GraphicsInterface.MainFrame;

public class Main {
	
	public static MainFrame frame;
	public static HelpFrame helpFrame;

	
	public static void main(String[] args) throws IOException {		
		frame = new MainFrame();
		helpFrame = new HelpFrame();
		frame.setVisible(true);
	}
}