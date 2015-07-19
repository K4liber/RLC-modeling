package prodev.GraphicsInterface;

import javax.swing.*;

import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;


public class RightTopElement extends JPanel {

	private static final long serialVersionUID = 1L;
	public JCheckBox checkBox = null;
	public ImagePanel image;
	public JTextField valueField;
	public JLabel label;
	public JLabel signLabel;
	public JButton button;
	public String name;
	public JTextField powerField;
	public JLabel powerLabel;
	public RightTopElement nextElement = null;
	
	private static int id = 0;
	public int i;
	
	public void setNextElement(RightTopElement element){
		nextElement = element;
	}
	public RightTopElement(String n, String path, String s, boolean isCheckbox) {
		setLayout(null);
		
		i = id;
		id++;
		
		name = n;

		image = path != null ? new ImagePanel(path) : null;
		if(image != null){
			image.setBounds(40, 0, 70, 70);
			image.setPreferredSize(new Dimension(70,70));
		}
		label = new JLabel(name);
		label.setBounds(120, 10, 100, 30);

		valueField = new JTextField();
		valueField.setBounds(230, 13, 50, 25);

		powerLabel = new JLabel("*10^");
		powerLabel.setBounds(290, 10, 40, 30);

		powerField = new JTextField();
		powerField.setBounds(330, 13, 30, 25);

		signLabel = new JLabel(s);
		signLabel.setBounds(370, 10, 30, 30);

		valueField.setEnabled(false);
		powerField.setEnabled(false);
		
		if(isCheckbox) {
			checkBox = new JCheckBox();
			checkBox.setBounds(10, 10, 20, 20);
			add(checkBox);
			add(image);
			checkBox.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					boolean enabled = checkBox.isSelected();
					RightTopPanel.setCheckBoxState(i, enabled);
					valueField.setEnabled(enabled);
					powerField.setEnabled(enabled);
					
					if(nextElement != null)
					{
						nextElement.valueField.setEnabled(enabled);
						nextElement.powerField.setEnabled(enabled);
					}
					for(int ii=0;ii<4;ii++){	
						LeftTopPanel.images[ii].reloadState(RightTopPanel.checkBoxStates[ii]);
						LeftTopPanel.images[ii].repaint();
					}
					
				}
			});

		} 
		
		add(label);
		add(valueField);
		add(powerLabel);
		add(powerField);
		add(signLabel);
	}
	
}
