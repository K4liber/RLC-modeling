package prodev.GraphicsInterface;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import prodev.Main.FileManager;
import prodev.Main.Main;
import prodev.MathModel.ValuesOfElements;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 2269971701250845501L;
	
	public TopPanel topPanel;
	public BottomPanel bottomPanel;
	JMenuBar menu = new JMenuBar();
	public static JMenu load = new JMenu("Wczytaj");
	private static ArrayList<JMenuItem> loadItems = new ArrayList<>();
	static public JMenu save = new JMenu("Zapisz");
	static public JMenu help = new JMenu("Pomoc");
	static boolean runAnimation = false;
	static ValuesOfElements values;
	public static boolean elementsValidation = false;
	private static FileManager file;
	
	public MainFrame() {
		super("RLC");
		
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(new GridLayout(2, 1));
		
		topPanel = new TopPanel();
		bottomPanel = new BottomPanel();
		bottomPanel.setBorder(BorderFactory.createLineBorder(new Color(20,20,20)));
		
		setFile(new FileManager());
		getFile().loadFilesNames();
		
		for(JMenuItem e : getLoadItems()) {
			load.add(e);
		}
		
		help.addMenuListener(new MenuListener() {
			@Override
		    public void menuSelected(MenuEvent e) {
				Main.helpFrame.setVisible(true);
		    }

		    @Override
		    public void menuDeselected(MenuEvent e) {

		    }
		    
		    @Override
		    public void menuCanceled(MenuEvent e) {
		        
		    }
		});
		
		menu.add(load);
		menu.add(save);
		menu.add(help);
		
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
