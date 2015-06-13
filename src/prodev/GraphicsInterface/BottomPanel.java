package prodev.GraphicsInterface;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

public class BottomPanel extends JPanel {

	private static final long serialVersionUID = -2916744287014266788L;

	public ArrayList<BottomElement> bottomElements = new ArrayList<>();
	
	public BottomPanel() {
		setLayout(new GridLayout(1, 4));
		bottomElements.add(new BottomElement("Resistor", "chart.png"));
		bottomElements.add(new BottomElement("Coil", "chart.png"));
		bottomElements.add(new BottomElement("Capacitor", "chart.png"));
		bottomElements.add(new BottomElement("Generator", "chart.png"));
		for(BottomElement e : bottomElements) {
			add(e);
		}
	}
	
}
