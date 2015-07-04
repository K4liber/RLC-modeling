package prodev.GraphicsInterface;

import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class HelpFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextArea helpTextArea;
	
	public HelpFrame() throws HeadlessException {
		setTitle("Help");
		setSize(400, 300);
		helpTextArea = new JTextArea();
		loadTextFromFile();
		helpTextArea.setEditable(false);
		JScrollPane sp = new JScrollPane(helpTextArea);
		add(sp);

		setAlwaysOnTop(true);
		setLocationRelativeTo(null);
		setVisible(false);
	}

	public JTextArea gethelpTextArea() {
		return helpTextArea;
	}

	public void setHelpTextArena(JTextArea textField) {
		this.helpTextArea = textField;
	}
	
	public void loadTextFromFile(){
		InputStreamReader streamReader = null;
		
		helpTextArea.setText("");

    	URL source = getClass().getResource("help.txt");
    	try {
			streamReader = new InputStreamReader(source.openStream(), Charset.forName("UTF-8"));
		} catch (IOException e) {
			e.printStackTrace();
		}
        BufferedReader bufferedReader = new BufferedReader(streamReader);
        
        String line = null;
		try {
			line = bufferedReader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
        while (line!= null){ 
        	helpTextArea.append(line);
        	helpTextArea.append("\n");
            try {
				line = bufferedReader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
	}

}
