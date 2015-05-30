package prodev.GraphicsInterface;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JPanel;

public class RightTopPanel extends JPanel {

	private static final long serialVersionUID = -6796341926621702462L;

	public static ArrayList<RightTopElement> elements = new ArrayList<>();
	
	static public boolean[] checkBoxStates = {false, false, false, false,false};
	
	static void setCheckBoxState(int i, boolean v) {
		checkBoxStates[i] = v;
	}

	public RightTopPanel() {
		setLayout(new GridLayout(5, 1));
		elements.add(new RightTopElement("Rezystor", "resistor-icon.png", "\u03A9", true));
		elements.add(new RightTopElement("Indukcyjnoœæ", "coil-icon.png", "H", true));
		elements.add(new RightTopElement("Pojemnoœæ", "capacitor-icon.png", "f", true));
		RightTopElement generatorElement = new RightTopElement("Amplituda", "generator-icon.png", "V", true);
		RightTopElement frequencyElement = new RightTopElement("Czêstotliwoœæ", null, "Hz", false);
		generatorElement.setNextElement(frequencyElement);
		elements.add(generatorElement);
		elements.add(frequencyElement);
		for(RightTopElement element: elements){
			add(element);
		}
	}

}
