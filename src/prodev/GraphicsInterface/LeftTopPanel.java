package prodev.GraphicsInterface;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import prodev.Main.Main;
import prodev.MathModel.ValuesOfElements;

public class LeftTopPanel extends JPanel {

	private static final long serialVersionUID = -2916744287014266788L;

	ArrayList<BottomElement> bottomElements = new ArrayList<>();
	
	public static ImagePanel images[] = {null,null,null,null};
	public JLabel titleLabel;
	public String name = "Modelowanie uk³adu R-L-C";
	public JPanel panel;
	public JButton playButton;
	
	public LeftTopPanel() {
		setLayout(null);
		
		titleLabel = new JLabel(this.name);
		titleLabel.setFont(new Font("Arial", 1, 20));
		titleLabel.setBounds(70,20,280,20);
		add(titleLabel);
	
		images[1] = new ImagePanel("CCOFF.png");
		images[1].setPreferredSize(new Dimension(172, 82));
		images[1].setBounds(198,96,172,82);
		images[3] = new ImagePanel("GOFF.png");
		images[3].setPreferredSize(new Dimension(168, 82));
		images[3].setBounds(30,96,168,82);
		images[0] = new ImagePanel("ROFF.png");
		images[0].setPreferredSize(new Dimension(341, 36));
		images[0].setBounds(30,60,341,36);
		images[2] = new ImagePanel("LOFF.png");
		images[2].setPreferredSize(new Dimension(341, 54));
		images[2].setBounds(30,178,341,54);
		
		
		panel = new JPanel();
		panel.setLayout(new GridLayout(1, 3, 20, 10));
		panel.setBounds(50,235,300,30);

		playButton = new JButton(">");
		playButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainFrame.values = new ValuesOfElements();
				int ii=0;
				for (RightTopElement element : RightTopPanel.elements) {
					String valueStr = element.valueField.getText();
					String powerStr = element.powerField.getText();
					try {
						if(element.valueField.isEnabled()) {
							double value = Double.parseDouble(valueStr);
							int power = Integer.parseInt(powerStr);
							double elementValue = (value*Math.pow(10,power));
							MainFrame.runAnimation = true;
							MainFrame.values.setElementValue(elementValue,ii);
							MainFrame.values.setState(true, ii);
							
						}
					} catch (NumberFormatException ex) {
						MainFrame.runAnimation = false;
						JOptionPane.showMessageDialog(null, "Poprawny format: 0,1202*10^-11");	
						break;
					}
					ii++;
				}
				MainFrame.values.validationStates();
			}
		});
	
		for(int ii=0;ii<4;ii++){
			add(images[ii]);
		}
		
		panel.add(playButton);
		JButton stopButton = new JButton("\u23F9");
		stopButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainFrame.runAnimation = false;
				for (BottomElement belement : Main.frame.bottomPanel.bottomElements) {
					belement.animation.counter = 0;
					belement.animation.setStartFrequency(MainFrame.values.getFrequencyValue());
					belement.animation.setStartAmplitude(MainFrame.values.getAmplitudeValue());
				}
			}
		});
		JButton pauseButton = new JButton("||");
		pauseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainFrame.runAnimation = false;
			}
		});
		panel.add(stopButton);
		panel.add(pauseButton);
		
		add(panel);
	}
	
}
