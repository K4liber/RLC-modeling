package prodev.GraphicsInterface;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class TopPanel extends JPanel {

	private static final long serialVersionUID = -7891984535923483679L;

	LeftTopPanel leftTopPanel;
	RightTopPanel rightTopPanel;
	
	public TopPanel() {
		setLayout(new GridLayout(1, 2));
		
		leftTopPanel = new LeftTopPanel();
		rightTopPanel = new RightTopPanel();
		leftTopPanel.setBorder(BorderFactory.createLineBorder(new Color(20,20,20)));
		rightTopPanel.setBorder(BorderFactory.createLineBorder(new Color(20,20,20)));
		add(leftTopPanel);
		add(rightTopPanel);
	}
	
}
