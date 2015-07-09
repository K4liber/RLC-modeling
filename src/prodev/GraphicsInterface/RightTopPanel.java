package prodev.GraphicsInterface;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JPanel;

import prodev.Main.Main;

public class RightTopPanel extends JPanel {

	private static final long serialVersionUID = -6796341926621702462L;

	public static ArrayList<RightTopElement> elements = new ArrayList<>();
	
	static public boolean[] checkBoxStates = {false, false, false, false,false};
	
	static void setCheckBoxState(int i, boolean v) {
		checkBoxStates[i] = v;
	}

	public RightTopPanel() {
		setLayout(new GridLayout(5, 1));
		String frequencyName = "Frequency";
		String amplitudeName = "Amplitude";

		try {
			frequencyName = Main.translator.getTranslatedPhrase("Frequency");
			amplitudeName = Main.translator.getTranslatedPhrase("Amplitude");
			elements.add(new RightTopElement(Main.translator.getTranslatedPhrase("Resistor"), "resistor-icon.png", "\u03A9", true));
			elements.add(new RightTopElement(Main.translator.getTranslatedPhrase("Inductance"), "coil-icon.png", "H", true));
			elements.add(new RightTopElement(Main.translator.getTranslatedPhrase("Capacity"), "capacitor-icon.png", "f", true));
		} catch (IOException e) {
			e.printStackTrace();
		}
		RightTopElement generatorElement = new RightTopElement(amplitudeName, "generator-icon.png", "V", true);
		RightTopElement frequencyElement = new RightTopElement(frequencyName, null, "Hz", false);
		generatorElement.setNextElement(frequencyElement);
		elements.add(generatorElement);
		elements.add(frequencyElement);
		for(RightTopElement element: elements){
			add(element);
		}
	}

}
