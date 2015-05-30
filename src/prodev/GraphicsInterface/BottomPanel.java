package prodev.GraphicsInterface;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

public class BottomPanel extends JPanel {

	private static final long serialVersionUID = -2916744287014266788L;

	static ArrayList<BottomElement> bottomElements = new ArrayList<>();
	
	public BottomPanel() {
		setLayout(new GridLayout(1, 4));
		
		bottomElements.add(new BottomElement("Napiêcie na rezystorze", "chart.png"));
		bottomElements.add(new BottomElement("Napiêcie na cewce", "chart.png"));
		bottomElements.add(new BottomElement("Napiêcie na kondesatorze", "chart.png"));
		bottomElements.add(new BottomElement("Napiêcie na generatorze", "chart.png"));

		for(BottomElement e : bottomElements) {
			add(e);
		}
	}
	
}
