package prodev.GraphicsInterface;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import prodev.MathModel.ValuesOfElements;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 2269971701250845501L;
	
	public TopPanel topPanel;
	public BottomPanel bottomPanel;
	JMenuBar menu = new JMenuBar();
	public static JMenu load = new JMenu("Wczytaj");
	static public JMenu save = new JMenu("Zapisz");
	static public JMenu help = new JMenu("Pomoc");
	static boolean runAnimation = false;
	static ValuesOfElements values;
	public static boolean elementsValidation = false;
	
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
	
}
