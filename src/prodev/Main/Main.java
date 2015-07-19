package prodev.Main;
import java.io.IOException;
import prodev.GraphicsInterface.HelpFrame;
import prodev.GraphicsInterface.LanguageChooser;
import prodev.GraphicsInterface.MainFrame;
import prodev.GraphicsInterface.Translator;

public class Main {
	
	public static MainFrame frame;
	public static HelpFrame helpFrame;
	public static LanguageChooser languageChooser;
	private static String language = "ENG";
	public static Translator translator;
	
	public static void main(String[] args) throws IOException {	
		languageChooser = new LanguageChooser();
		languageChooser.setVisible(true);
		translator = new Translator();
		helpFrame = new HelpFrame();
	}

	public static String getLanguage() {
		return language;
	}

	public static void setLanguage(String language) {
		Main.language = language;
	}
	
}