package prodev.GraphicsInterface;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

import prodev.Main.Main;
import prodev.MathModel.ValuesOfElements;

public class LeftTopPanel extends JPanel {

	private static final long serialVersionUID = -2916744287014266788L;

	ArrayList<BottomElement> bottomElements = new ArrayList<>();
	
	public static ImagePanel images[] = {null,null,null,null};
	public JLabel titleLabel;
	public String name;
	public JButton playButton;
	public JPanel titlePanel;
	public JPanel systemImagePanel;
	public JPanel controlPanel;
	
	
	public LeftTopPanel() {
		setLayout(new BorderLayout());
		try {
			name = Main.translator.getTranslatedPhrase("RLC modeling system");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		panelDefinition();
		setTitlePanelAspect();
		setControlPanelAspect();
		setSystemImagePanelAspect();
		addPanels();
	}
	
	private void setTitlePanelAspect() {
		titlePanel.setLayout(new FlowLayout());
		titleLabel = new JLabel(this.name);
		titleLabel.setFont(new Font("Arial", 1, 20));
		titleLabel.setBounds(0,0,280,20);
		titlePanel.add(titleLabel);
		titlePanel.setBounds(2,2,280,30);
	}
	
	private void setSystemImagePanelAspect(){
		systemImagePanel.setLayout(new FlowLayout());
		JPanel imagePanel = new JPanel();
		imagePanel.setLayout(null);
		imagePanel.setPreferredSize(new Dimension(400,300));
		images[1] = new ImagePanel("CCOFF.png");
		images[1].setPreferredSize(new Dimension(172, 82));
		images[1].setBounds(188,36,172,82);
		images[3] = new ImagePanel("GOFF.png");
		images[3].setPreferredSize(new Dimension(168, 82));
		images[3].setBounds(20,36,168,82);
		images[0] = new ImagePanel("ROFF.png");
		images[0].setPreferredSize(new Dimension(341, 36));
		images[0].setBounds(20,0,341,36);
		images[2] = new ImagePanel("LOFF.png");
		images[2].setPreferredSize(new Dimension(341, 54));
		images[2].setBounds(20,118,341,54);
		
		for(int ii=0;ii<4;ii++){
			imagePanel.add(images[ii]);
		}
		
		systemImagePanel.add(imagePanel);
		systemImagePanel.setBounds(0,0,400,300);
	}
	
	public void setControlPanelAspect() {
		controlPanel.setLayout(new GridLayout(1, 3, 20, 10));

		playButton = new JButton(">");
		playButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				for (BottomElement belement : Main.frame.bottomPanel.bottomElements) {
					if(belement.animation.trit.isAlive())
						belement.animation.runForest(false);
						belement.titleLabel.setForeground(new Color(200,200,200));
				}
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
						try {
							JOptionPane.showMessageDialog(null, Main.translator.getTranslatedPhrase("Correct format is") + ": 0,1202*10^-11");
						} catch (HeadlessException e1) {
							e1.printStackTrace();
						} catch (IOException e1) {
							e1.printStackTrace();
						}	
						break;
					}
					ii++;
				}
				MainFrame.values.validationStates();
				for (BottomElement belement : Main.frame.bottomPanel.bottomElements) {
					belement.animation.vDiv.setText(" ? V/div");
				}
			}
		});
	
		
		controlPanel.add(playButton);
		JButton stopButton = new JButton("\u23F9");
		stopButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stopSimulation();
			}
		});
		JButton pauseButton = new JButton("||");
		pauseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainFrame.runAnimation = false;
			}
		});
		controlPanel.add(stopButton);
		controlPanel.add(pauseButton);
	}
	
	private void panelDefinition() {
		systemImagePanel = new JPanel();
		controlPanel = new JPanel();
		titlePanel = new JPanel();
	}
	
	public void addPanels(){
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(titlePanel,BorderLayout.NORTH);
		mainPanel.add(systemImagePanel,BorderLayout.CENTER);
		mainPanel.add(controlPanel,BorderLayout.SOUTH);
		
		add(mainPanel,BorderLayout.CENTER);
		add(new JPanel(),BorderLayout.WEST);
		add(new JPanel(),BorderLayout.EAST);
		add(new JPanel(),BorderLayout.SOUTH);
		add(new JPanel(),BorderLayout.NORTH);
	}
	
	public void stopSimulation() {
		MainFrame.runAnimation = false;
		for (BottomElement belement : Main.frame.bottomPanel.bottomElements) {
			if(belement.animation.isRunning()){
				belement.animation.counter = 0;
				belement.animation.setStartFrequency(MainFrame.values.getFrequencyValue());
				belement.animation.setStartAmplitude(MainFrame.values.getAmplitudeValue());
			}
		}
	}
	
	public void unpaintAnimations(){
		for (BottomElement belement : Main.frame.bottomPanel.bottomElements) {
			if(belement.animation.isRunning()){
				belement.animation.runForest(false);
			}
		}
	}
}
