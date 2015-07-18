package prodev.Main;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import prodev.GraphicsInterface.MainFrame;
import prodev.GraphicsInterface.RightTopPanel;

public class FileManager {

	static File loadingFile = null;
	public File savingFile = null;
	String[] filesNames;
	int fileIndex = 0;
	private ActionListener saveButtonListener;

	public FileManager() {
		String saveAsName = "Save as";
		String loadFromFileName = "Load from file";
		String chooseNameTry = "Choose";
		String chooseFileNameTry = "Choose file";

		try {
			saveAsName = Main.translator.getTranslatedPhrase("Save as");
			loadFromFileName = Main.translator
					.getTranslatedPhrase("Load from file");
			chooseNameTry = Main.translator.getTranslatedPhrase("Choose");
			chooseFileNameTry = Main.translator
					.getTranslatedPhrase("Choose file");
		} catch (IOException e2) {
			e2.printStackTrace();
		}

		final String chooseFileName = chooseFileNameTry;
		final String chooseName = chooseNameTry;

		JMenuItem saveItem = new JMenuItem(saveAsName);
		JMenuItem loadItem = new JMenuItem(loadFromFileName);

		saveItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setDialogTitle(chooseFileName);
				int result = chooser.showDialog(null, chooseName);
				if (JFileChooser.APPROVE_OPTION == result) {
					savingFile = chooser.getSelectedFile();
					try {
						save();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});

		loadItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setDialogTitle(chooseFileName);
				int result = chooser.showDialog(null, chooseName);
				if (JFileChooser.APPROVE_OPTION == result) {
					loadingFile = chooser.getSelectedFile();
					try {
						load("Load From File");
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});

		MainFrame.save.add(saveItem);
		MainFrame.load.add(loadItem);
	}

	public void load(String name) throws IOException {
		String wrongFormatName = "Wrong format of loading file!";
		String wrongValueName = "Wrong value!";
		try {
			wrongFormatName = Main.translator
					.getTranslatedPhrase("Wrong format of loading file!");
			wrongValueName = Main.translator
					.getTranslatedPhrase("Wrong value!");
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		InputStreamReader streamReader;
		if (name == "Load From File") {
			streamReader = new InputStreamReader(new FileInputStream(
					loadingFile), Charset.forName("UTF-8"));
		} else {
			URL source = getClass().getResource("savedFiles/" + name);
			streamReader = new InputStreamReader(source.openStream(),
					Charset.forName("UTF-8"));
		}
		BufferedReader bufferedReader = new BufferedReader(streamReader);

		disableElementsValues();
		
		try {
			String line = bufferedReader.readLine();
			int ii = 0;
			while (ii < 5) {
				if (line == "-") {
					ii++;
					continue;
				}
				String results[] = null;
				if (line.contains(" ")) {
					results = line.split(" ");

					try {
						Float.valueOf(results[0]);
						Float.valueOf(results[1]);
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(null, wrongValueName);
						break;
					}

					RightTopPanel.elements.get(ii).valueField
							.setText(results[0]);
					RightTopPanel.elements.get(ii).powerField
							.setText(results[1]);
					if (ii != 4)
						RightTopPanel.elements.get(ii).checkBox
								.setSelected(true);
				}
				line = bufferedReader.readLine();
				ii++;
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, wrongFormatName);
		}
	}

	public void save() throws IOException {
		OutputStream outPutStream = new FileOutputStream(savingFile);
		BufferedWriter bufferedWriter = new BufferedWriter(
				new OutputStreamWriter(outPutStream));
		for (int ii = 0; ii < 5; ii++) {
			String value = RightTopPanel.elements.get(ii).valueField.getText();
			String power = RightTopPanel.elements.get(ii).powerField.getText();
			if (value != null && value.length() == 0 || power != null
					&& power.length() == 0)
				bufferedWriter.write("-");
			else
				bufferedWriter.write(value + " " + power);
			bufferedWriter.newLine();
		}
		bufferedWriter.close();
		loadFilesNames();
	}

	public void loadFilesNames() {
		String line = null;
		fileIndex = 0;
		filesNames = new String[25];
		try {
			InputStream stream = getClass().getResourceAsStream("savedFiles/examplesList.txt");
			Scanner src = new Scanner(stream);
			while (src.hasNext() && fileIndex < filesNames.length) {
				line = src.next();
				filesNames[fileIndex] = line.toString();
				fileIndex++;
			}
			src.close();

			for (int ii = 0; ii < fileIndex; ii++) {
				final String name = filesNames[ii];
				JMenuItem loadItem = new JMenuItem(name);
				loadItem.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							load(name);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				});
				loadItem.setBackground(new Color(220, 220, 254));
				MainFrame.getLoadItems().add(loadItem);
			}

		} catch (Exception e) {

		}
	}

	public ActionListener getSaveButtonListener() {
		return saveButtonListener;
	}

	public void setSaveButtonListener(ActionListener saveButtonListener) {
		this.saveButtonListener = saveButtonListener;
	}

	public void disableElementsValues() {
		for (int zz = 0; zz < RightTopPanel.elements.size(); zz++) {
			RightTopPanel.elements.get(zz).valueField.setText("");
			RightTopPanel.elements.get(zz).powerField.setText("");
			if (RightTopPanel.elements.get(zz).checkBox != null
					&& RightTopPanel.elements.get(zz).checkBox.isSelected())
				RightTopPanel.elements.get(zz).checkBox.setSelected(false);
		}
	}

}
