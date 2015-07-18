package prodev.GraphicsInterface;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import prodev.Main.FileManager;
import prodev.Main.Main;
import prodev.MathModel.ValuesOfElements;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 2269971701250845501L;
	
	public TopPanel topPanel;
	public BottomPanel bottomPanel;
	JMenuBar menu = new JMenuBar();
	public static JMenu load;
	private static ArrayList<JMenuItem> loadItems = new ArrayList<>();
	static public JMenu save;
	static public JMenu options;
	static boolean runAnimation = false;
	static ValuesOfElements values;
	public static boolean elementsValidation = false;
	public static FileManager file;
	
	public MainFrame() {
		super("RLC Model");
		String loadName = "Load";
		String saveName = "Save";
		String exitName = "Exit";
		String optionsName = "Options";
		String helpName = "Help";
		try {
			loadName = Main.translator.getTranslatedPhrase("Load");
			saveName = Main.translator.getTranslatedPhrase("Save");
			exitName = Main.translator.getTranslatedPhrase("Exit");
			optionsName = Main.translator.getTranslatedPhrase("Options");
			helpName = Main.translator.getTranslatedPhrase("Help");
		}catch (IOException e1) {
			e1.printStackTrace();
		}
		
		load = new JMenu(loadName);
		save = new JMenu(saveName);
		options = new JMenu(optionsName);
		JMenuItem help = new JMenuItem(helpName);
		JMenuItem exit = new JMenuItem(exitName);
		
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new GridLayout(2, 1));
		setFile(new FileManager());
		
		topPanel = new TopPanel();
		bottomPanel = new BottomPanel();
		bottomPanel.setBorder(BorderFactory.createLineBorder(new Color(20,20,20)));

		getFile().loadFilesNames();
		
		for(JMenuItem e : getLoadItems()) {
			load.add(e);
		}
		
		help.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.helpFrame = new HelpFrame();
				Main.helpFrame.setVisible(true);
			}
		});		
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});	
		
		options.add(help);
		options.add(exit);
		
		menu.add(load);
		menu.add(save);
		menu.add(options);
		
		setJMenuBar(menu);
		add(topPanel);
		add(bottomPanel);
	}
	
	public static void setRunState(boolean state){
		runAnimation = state;
	}

	public FileManager getFile() {
		return file;
	}

	public void setFile(FileManager f) {
		file = f;
	}

	public static ArrayList<JMenuItem> getLoadItems() {
		return loadItems;
	}

	public static void setLoadItems(ArrayList<JMenuItem> loadItems) {
		MainFrame.loadItems = loadItems;
	}

}
