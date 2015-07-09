package prodev.GraphicsInterface;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import prodev.Main.Main;

public class LanguageChooser extends JFrame {

	private static final long serialVersionUID = 1L;
	private JButton englishSelect;
	private JButton polishSelect;
	private JLabel chooserTextInEnglish;
	private JLabel chooserTextInPolish;
	private JPanel mainPanel;
	private JPanel buttonsPanel;
	
	public LanguageChooser() throws HeadlessException {
		setTitle("RLC Model");
		setSize(275, 200);
		setLayout(new BorderLayout());
		mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(3,1));
		buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new GridLayout(1,2,20,0));
		URL polishFlagImageURL = getClass().getResource("images/" + "flaga-polski.png");
		URL englishFlagImageURL = getClass().getResource("images/" + "flaga-anglii.png");
		polishSelect = new JButton(new ImageIcon(polishFlagImageURL));
		englishSelect = new JButton(new ImageIcon(englishFlagImageURL));
		
		polishSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.setLanguage("POL");
				Main.frame = new MainFrame();
				Main.frame.setVisible(true);
				dispose(); 
			}
		});
		englishSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.setLanguage("ENG");
				Main.frame = new MainFrame();
				Main.frame.setVisible(true);
				dispose(); 
			}
		});
		
		chooserTextInEnglish = new JLabel("Select a Language");
		chooserTextInPolish = new JLabel("Wybierz jêzyk");
		chooserTextInEnglish.setHorizontalAlignment(JLabel.CENTER);
		chooserTextInPolish.setHorizontalAlignment(JLabel.CENTER);
		
		buttonsPanel.add(englishSelect);
		buttonsPanel.add(polishSelect);
		mainPanel.add(chooserTextInEnglish);
		mainPanel.add(buttonsPanel);
		mainPanel.add(chooserTextInPolish);

		add(mainPanel,BorderLayout.CENTER);
		add(new JPanel(),BorderLayout.WEST);
		add(new JPanel(),BorderLayout.EAST);
		
		setAlwaysOnTop(true);
		setLocationRelativeTo(null);
		setVisible(false);
	}

}
